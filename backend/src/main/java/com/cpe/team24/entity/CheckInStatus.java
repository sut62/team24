package entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@CrossOrigin(origins = "*")
@Data
@Entity
@NoArgsConstructor
@Table(name = "CHECKINSTATUS")
public class CheckInStatus {

    @Id
    @SequenceGenerator(name = "CHECKINSTATUS_SEQ", sequenceName = "CHECKINSTATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKINSTATUS_SEQ")
    @Column(name = "CHECKINSTATUS_ID", unique = true, nullable = false)
    private @NonNull Long id;

    @Column(name = "NAME",nullable = false)
    private @NonNull String name;

    //entity checkin type
    

}