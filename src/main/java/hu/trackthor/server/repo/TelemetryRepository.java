package hu.trackthor.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.trackthor.server.mqtt.DeviceTelemetryData;

public interface TelemetryRepository extends MongoRepository<DeviceTelemetryData, String> {

    
    
}
