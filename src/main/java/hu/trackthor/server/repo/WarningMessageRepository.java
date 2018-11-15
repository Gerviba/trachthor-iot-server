package hu.trackthor.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.trackthor.server.model.WarningMessage;

@Repository
public interface WarningMessageRepository extends CrudRepository<WarningMessage, Long> {
    
}
