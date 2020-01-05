package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@CrossOrigin(origins = "*")
@Data
@Entity
@NoArgsConstructor
@Table(name = "CHECKIN_STATUS")
public class CheckInStatus {

    @Id
    @SequenceGenerator(name = "CHECKIN_STATUS_SEQ", sequenceName = "CHECKIN_STATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_STATUS_SEQ")
    @Column(name = "CHECKIN_STATUS_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "Name")
    private @NonNull String name;

    @OneToOne(mappedBy = "checkInStatus", cascade = CascadeType.ALL)
    @JsonIgnore
    private CheckIn checkIn;

}
