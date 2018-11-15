package hu.trackthor.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.trackthor.server.model.WarningMessage;
import hu.trackthor.server.repo.WarningMessageRepository;

@Service
public class WarningService {

    @Autowired
    WarningMessageRepository repo;
    
    public Iterable<WarningMessage> getAll() {
        return repo.findAll();
    }
    
    public void add(WarningMessage.LogLevel level, String message) {
        repo.save(new WarningMessage(level, message));
    }
    
}
