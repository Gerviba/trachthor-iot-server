package hu.trackthor.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "devices")
public class Device {

    @Id
    private String id;
    
    @Column
    private String displayName;
    
    @Column
    @Size(max = 250)
    private String description;
    
    @Column
    private String type;
    
}
