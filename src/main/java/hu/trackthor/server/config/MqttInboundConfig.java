package hu.trackthor.server.config;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import hu.trackthor.server.mqtt.DeviceSystemData;
import hu.trackthor.server.mqtt.DeviceTelemetryData;
import hu.trackthor.server.service.PacketHandlerService;

@Configuration
@EnableIntegration
public class MqttInboundConfig {

    @Value("${trackthor.inbound.clientId:trackThorClient}")
    String clientId;

    @Value("${trackthor.inbound.host:tcp://localhost:1883}")
    String host;
    
    @Value("${trackthor.inbound.qos:0}")
    int qos;

    @Value("${trackthor.inbound.completion-timeout:5000}")
    int completionTimeout;

    @Value("${trackthor.inbound.telemetry-topic:telemetry}")
    String telemetryTopic;

    @Value("${trackthor.inbound.system-topic:system}")
    String systemTopic;
    
    @Autowired
    PacketHandlerService packets;
    
    private Map<String, Consumer<Message<?>>> handlers = new HashMap<>();
    
    @PostConstruct
    public void init() {
        handlers.put(telemetryTopic, message -> {
            System.out.println(message.getHeaders().entrySet());
            System.out.println("Telemetry: " + message.getPayload()); 
            
            String[] args = ((String) message.getPayload()).split(",");
            packets.registerTelemetry(new DeviceTelemetryData(
                    args[0],
                    args[1],
                    
                    Double.parseDouble(args[2]),
                    Double.parseDouble(args[3]),
                    Double.parseDouble(args[4]),
                    Integer.parseInt(args[5]),
                    args[6],
                    Double.parseDouble(args[7]),
                    
                    Double.parseDouble(args[8]),
                    Double.parseDouble(args[9]),
                    Double.parseDouble(args[10]),
                    Double.parseDouble(args[11]),
                    Double.parseDouble(args[12]),

                    Double.parseDouble(args[13]),
                    Double.parseDouble(args[14]),
                    Integer.parseInt(args[15]),
                    
                    Long.parseLong(args[16])));
        });
        
        handlers.put(systemTopic, message -> {
            System.out.println(message.getHeaders().entrySet());
            System.out.println("Sensor: " + message.getPayload()); 
            
            String[] args = ((String) message.getPayload()).split(",");
            packets.registerSysinfo(new DeviceSystemData(
                    args[0],
                    args[1],
                    args[2],
                    Double.parseDouble(args[2]),
                    Long.parseLong(args[16])));
        });
    }
    
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(host, clientId,
                        telemetryTopic, systemTopic);
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(qos);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            handlers.getOrDefault(
                    message.getHeaders().get("mqtt_receivedTopic"),
                    defaultMessage -> {
                        System.err.println("Failed to handle channel: "
                                + defaultMessage.getHeaders().get("mqtt_receivedTopic"));
                    }).accept(message);
        };
    }
    
}
