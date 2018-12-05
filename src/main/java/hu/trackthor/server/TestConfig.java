package hu.trackthor.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import hu.trackthor.server.config.MqttOutboundConfig.ActionGateway;
import hu.trackthor.server.model.Device;
import hu.trackthor.server.model.User;
import hu.trackthor.server.model.WarningMessage.LogLevel;
import hu.trackthor.server.repo.DeviceRepository;
import hu.trackthor.server.repo.UserRepository;
import hu.trackthor.server.service.HashingService;
import hu.trackthor.server.service.PoligonUtil;
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
    DeviceRepository devices;
    
    @Autowired
    HashingService hashing;
    
    @Autowired
    PoligonUtil poligons;
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        
        users.save(new User(null, "admin", 
                hashing.hash("admin123"), true));
        
        users.save(new User(null, "TestUser1", 
                hashing.hash("letmein"), false));
        
        warnings.add(LogLevel.INFO, "Test info message");
        warnings.add(LogLevel.WARNING, "Warning! This is a test warning message!");
        warnings.add(LogLevel.SUCCESS, "Ohh! That's fine.");
        warnings.add(LogLevel.DANGER, "This is a fatal error");
        
        devices.save(new Device("DEV001", "Device001", "This is our test device. It runs on Raspbian.", "TrackThorV1RP", 0));
        devices.save(new Device("DEV002", "Device002", "This is a theoretical device. It doesn't exists.", "TrackThorV2RP", 0));
        
        poligons.addPoligon("Building Q", "#00FF00", true, 
                "47.473688 19.060691\n" + 
                "47.473796 19.059166\n" + 
                "47.472884 19.059150\n" + 
                "47.473117 19.060622");
        
        poligons.addPoligon("Square", "#FF0000", false, 
                "47.473696 19.060734\n" + 
                "47.473144 19.060669\n" + 
                "47.472764 19.061764\n" + 
                "47.473818 19.061407");
    }
    
}
