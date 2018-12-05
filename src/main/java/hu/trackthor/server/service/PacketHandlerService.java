package hu.trackthor.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.trackthor.server.config.MqttOutboundConfig.ActionGateway;
import hu.trackthor.server.model.WarningMessage.LogLevel;
import hu.trackthor.server.mqtt.DeviceSystemData;
import hu.trackthor.server.mqtt.DeviceTelemetryData;
import hu.trackthor.server.repo.SystemRepository;
import hu.trackthor.server.repo.TelemetryRepository;

@Service
public class PacketHandlerService {

    @Autowired
    TelemetryRepository telemetry;

    @Autowired
    SystemRepository system;

    @Autowired
    PoligonUtil poligons;

    @Autowired
    ActionGateway gateway;

    @Autowired
    WarningService warnings;

    public void registerTelemetry(DeviceTelemetryData deviceTelemetryData) {
        telemetry.save(deviceTelemetryData);
        gateway.sendAction(deviceTelemetryData.getModelUid(),
                poligons.getGeofenceStatus(
                        deviceTelemetryData.getLatitude(),
                        deviceTelemetryData.getLongnitude()));
    }

    public void registerSysinfo(DeviceSystemData deviceSystemData) {
        system.save(deviceSystemData);
        if (deviceSystemData.getLoad() > 2.0)
            warnings.add(LogLevel.WARNING, "CPU load is too high for "
                    + deviceSystemData.getLoad() + " (" + deviceSystemData.getLoad() + ")");
    }

}
