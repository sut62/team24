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
@Table(name = "CHECKIN")
public class CheckIn {

    @Id
    @SequenceGenerator(name = "CHECKIN_SEQ", sequenceName = "CHECKIN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_SEQ")
    @Column(name = "CHECKIN_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "date")
    private @NonNull String date;

    @Column(name = "bagWeigth")
    private @NonNull float bagWeigth;

    @Column(name = "boardingPass")
    private @NonNull float boardingPass;

}