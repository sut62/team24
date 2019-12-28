package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;


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

    @Column(name = "Date")
    private @NonNull Date date;

    @Column(name = "Email")
    private @NonNull String email;

    @Column(name = "BoardingPass")
    private @NonNull String boardingPass;

}
