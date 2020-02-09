package com.cpe.team24.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class PromotionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EPromotionStatus name;

    @OneToMany
    private Collection<Promotion> promotions;

    public PromotionStatus(){}
    public PromotionStatus(EPromotionStatus name){
        this.name = name;
    }
}
