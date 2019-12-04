package com.cpe.backend.employee.entity;

import lombok.*;
import com.cpe.backend.employee.entity.Foodimg;
import com.cpe.backend.employee.entity.Foodtype;
import com.cpe.backend.employee.entity.Food;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="FOOD")
public class Food {

    @Id
    @SequenceGenerator(name="FOOD_seq",sequenceName="FOOD_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FOOD_seq")
    @Column(name = "FOOD_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="foodname")

    private @NonNull String name;

    private @NonNull String cost;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Foodimg.class)
    @JoinColumn(name = "FOODIMG_ID", insertable = true)
    private Foodimg addfoodimg;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Foodtype.class)
    @JoinColumn(name = "FOODTYPE_ID", insertable = true)
    private  Foodtype addfoodtype;

}