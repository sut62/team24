package com.cpe.team24.entity;

import lombok.*;


import javax.persistence.Id;

import javax.persistence.SequenceGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class BaggageImage {
    @Id
    @SequenceGenerator(name="BAGGAGEIMAGE_SEQ",sequenceName="BAGGAGEIMAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAGGAGEIMAGE_SEQ")
    @Column(name="BAGGAGEIMAGE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    private @NonNull String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String imagename) {
        this.name = imagename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}