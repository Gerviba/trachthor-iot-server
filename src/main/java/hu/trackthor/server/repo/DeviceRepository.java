package hu.trackthor.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.trackthor.server.model.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String> {
    
}
