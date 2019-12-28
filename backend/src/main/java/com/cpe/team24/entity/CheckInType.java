package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;


@CrossOrigin(origins = "*")
@Data
@Entity
@NoArgsConstructor
@Table(name = "CHECKINTYPE")
public class CheckInType {

    @Id
    @SequenceGenerator(name = "CHECKINTYPE_SEQ", sequenceName = "CHECKINTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKINTYPE_SEQ")
    @Column(name = "CHECKINTYPE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "Name")
    private @NonNull String name;
}
