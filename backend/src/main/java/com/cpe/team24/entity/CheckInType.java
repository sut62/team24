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
@Table(name = "CHECKINTYPE")
public class CheckInType {

    @Id
    @SequenceGenerator(name = "CHECKINTYPE_SEQ", sequenceName = "CHECKINTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKINTYPE_SEQ")
    @Column(name = "CHECKINTYPE_ID", unique = true, nullable = false)
    private @NonNull Long id;

    @Column(name = "NAME",nullable = false)
    private @NonNull String name;

    //entity checkintype
    

}