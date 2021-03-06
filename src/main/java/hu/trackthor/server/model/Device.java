package hu.trackthor.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "devices")
@AllArgsConstructor
public class Device {

    @Id
    private String uid;
    
    @Column
    private String displayName;
    
    @Column
    @Size(max = 250)
    private String description;
    
    @Column
    private String type;
    
    @Column
    private long lastUpdated;
    
}
