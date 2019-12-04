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
@Table(name="PROMOTION")
public class Promotion {

    @Id
    @SequenceGenerator(name="PROMOTION_seq",sequenceName="PROMOTION_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROMOTION_seq")
    @Column(name = "PROMOTION_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="NAME")
    private @NonNull String name;
}