package hu.trackthor.server.config;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class MqttOutboundConfig {

    @Value("${trackthor.outbound.username:null}")
    String username;

    @Value("${trackthor.outbound.password:null}")
    String password;

    @Value("${trackthor.outbound.broker-host:tcp://127.0.0.1:1883}")
    String[] hosts;

    @Value("${trackthor.outbound.clientId:trackThorServer}")
    String clientId;

    @Value("${trackthor.outbound.action-channel-format:/action/{device-id}}")
    String actionChannelFormat;
    
    @Value("${trackthor.outbound.action-all-channel:/action/all/}")
    String defaultChannel;
    
    @Value("${trackthor.outbound.qos:1}")
    int qos;
    
    @Value("${trackthor.outbound.auto-reconnect:true}")
    boolean autoReconnect;
    
    @Value("${trackthor.outbound.clean-session:true}")
    boolean cleanSession;
    
    @Value("${trackthor.backend-name:BETA}")
    String backendName;
    
    @PostConstruct
    public void init() {
        backendName = "[" + backendName + "]";
    }
    
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
    
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(hosts);
        options.setUserName(username.equals("null") ? null : username);
        options.setPassword(password.equals("null") ? null : password.toCharArray());
        options.setAutomaticReconnect(autoReconnect);
        options.setCleanSession(cleanSession);
        factory.setConnectionOptions(options);
        return factory;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                       new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultChannel);
        messageHandler.setDefaultQos(qos);
        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface RawActionGateway {
        void send(String data);
        void send(@Header("mqtt_topic") String topic, String data);
    }
    
    public class ActionGateway {
        
        @Autowired
        RawActionGateway rag;
        
        public void sendRaw(String data) {
            rag.send(data);
        }
        
        public void sendRaw(String topic, String data) {
            rag.send(topic, data);
        }
        
        public void sendToDevice(String device, String data) {
            rag.send(actionChannelFormat
                    .replace("{device-id}", device),
                    data);
        }
        
        public void sendAction(String device, String regionStatus) {
            rag.send(actionChannelFormat
                    .replace("{device-id}", device),
                    System.currentTimeMillis() + "," + backendName + "," + regionStatus);
        }
        
    }
    
    @Bean
    public ActionGateway actionGateway() {
        return new ActionGateway();
    }
    
}
