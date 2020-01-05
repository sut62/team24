package com.cpe.team24.entity;

import lombok.*;


import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="BAGGAGEIMAGE")
public class BaggageImage {
    @Id
    @SequenceGenerator(name="BAGGAGEIMAGE_SEQ",sequenceName="BAGGAGEIMAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAGGAGEIMAGE_SEQ")
    @Column(name="BAGGAGEIMAGE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String imagename;

    private @NonNull String url;



    
    
	

	

}