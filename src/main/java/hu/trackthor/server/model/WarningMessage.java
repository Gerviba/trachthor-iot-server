package hu.trackthor.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "warnings")
public class WarningMessage {
    
    public enum LogLevel {
        INFO,
        WARNING,
        ERROR,
        FATAL;
    }
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    @Enumerated(EnumType.STRING)
    private LogLevel level;
    
    @Column
    @Size(max = 250)
    private String message;
    
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public WarningMessage(LogLevel level, @Size(max = 250) String message) {
        this.level = level;
        this.message = message;
    }
    
}
