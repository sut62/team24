package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="BAGGAGETYPE")
public class BaggageType {
    @Id
    @SequenceGenerator(name="BAGGAGETYPE_SEQ",sequenceName="BAGGAGETYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAGGAGETYPE_SEQ")
    @Column(name="BAGGAGETYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String btypename;

	
}