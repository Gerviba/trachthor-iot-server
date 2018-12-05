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
public class Segment {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private long poligon;

    @Column
    private double lat1;

    @Column
    private double long1;

    @Column
    private double lat2;

    @Column
    private double long2;
    
    @Column
    private int order;
    
}
