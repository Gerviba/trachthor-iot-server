package hu.trackthor.server;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import hu.trackthor.server.config.MqttOutboundConfig.ActionGateway;
import hu.trackthor.server.model.User;
import hu.trackthor.server.model.WarningMessage.LogLevel;
import hu.trackthor.server.repo.UserRepository;
import hu.trackthor.server.service.HashingService;
import hu.trackthor.server.service.WarningService;

@Service
public class TestConfig {

    @Autowired
    ActionGateway gateway;
    
    @Autowired
    UserRepository users;
    
    @Autowired
    WarningService warnings;
    
    @Autowired
    HashingService hashing;
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        gateway.sendRaw("telemetry", new UUID(0, 1).toString() + ";" 
                + System.currentTimeMillis() + ";47000.21;N;19000.41;E;");        
        
        users.save(new User(null, "Admin", 
                hashing.hash("admin123"), true));
        
        users.save(new User(null, "TestUser1", 
                hashing.hash("letmein"), false));
        
        warnings.add(LogLevel.INFO, "Test info message");
        warnings.add(LogLevel.WARNING, "Warning! This is a test warning message!");
        warnings.add(LogLevel.SUCCESS, "Ohh! That's fine.");
        warnings.add(LogLevel.DANGER, "This is a fatal error");
    }
    
}
