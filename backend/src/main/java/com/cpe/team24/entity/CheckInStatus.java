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
@Table(name = "CHECKINSTATUS")
public class CheckInStatus {

    @Id
    @SequenceGenerator(name = "CHECKINSTATUS_SEQ", sequenceName = "CHECKINSTATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKINSTATUS_SEQ")
    @Column(name = "CHECKINSTATUS_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "Name")
    private @NonNull String name;
}
