package com.cpe.team24.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class PromotionImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    @OneToMany
    private Collection<Promotion> promotion;

    public PromotionImage(){}
    public PromotionImage(String name , String url){
        this.name =name;
        this.url = url;
    }
}
