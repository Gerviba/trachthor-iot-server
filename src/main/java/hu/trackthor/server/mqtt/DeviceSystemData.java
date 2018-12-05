package hu.trackthor.server.mqtt;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document(collection = "system")
@AllArgsConstructor
public class DeviceSystemData {

    private String modelType;

    @Id
    private String modelUid;
    
    private String os;
    
    private double load;
    
    private long timestamp;
    
}
