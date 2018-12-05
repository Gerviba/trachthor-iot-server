package hu.trackthor.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import hu.trackthor.server.mqtt.DeviceSystemData;

public interface SystemRepository extends MongoRepository<DeviceSystemData, String> {

}
