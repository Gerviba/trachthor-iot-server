package hu.trackthor.server.mqtt;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "telemetry")
public class DeviceTelemetryData {

    private final String device;
    
    private final long time;
    private final double longitude;
    private final char ns;
    private final double latitude;
    private final char ew;
    private final int quality;
    private final int satellites;
    private final float precision;
    private final double altitude;
    private final double sep;
    
}
