package hu.trackthor.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.trackthor.server.mqtt.DeviceSensorData;

public interface SensorRepository extends MongoRepository<DeviceSensorData, String> {

}
