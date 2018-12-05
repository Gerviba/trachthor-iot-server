package hu.trackthor.server.mqtt;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "telemetry")
@AllArgsConstructor
public class DeviceTelemetryData {

    @Id
    private String modelUid;
    private String modelType;
    
    // BN-220
    private double longnitude;
    private double latitude;
    private double altitude;
    private int satelliteCount;
    private String gpsStatus; // NO, 2D, 3D
    private double speed;
    
    // MPU9255
    private double roll;
    private double pitch;
    private double yaw;
    private double compass;
    private double headings;
    
    // BMP280
    private double temperature;
    private double pressure;
    
    // Imaginary
    private int fuel;

    private long timestamp;
}
