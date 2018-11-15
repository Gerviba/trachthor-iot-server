package hu.trackthor.server.mqtt;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sensor")
public class DeviceSensorData {

    @Id
    private final String device;
    
    private final double accelerationX;
    private final double accelerationY;
    private final double accelerationZ;
    private final double rotationX;
    private final double rotationY;
    private final double rotationZ;
    private final double compassX;
    private final double compassY;
    private final double compassZ;
    private final double preassure;
    
    private final int fuel;
    
}
