package hu.trackthor.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "segments")
@AllArgsConstructor
public class Poligon {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String color;

    @Column
    private String name;

    @Column
    private boolean goZone;
    
}
