package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Promotion {

    @Id
    @SequenceGenerator(name="PROMOTION_seq",sequenceName="PROMOTION_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROMOTION_seq")
    @Column(name = "PROMOTION_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    private @NonNull String desc;

    @OneToOne
    private PromotionCode promotionCode;

    @ManyToOne
    private PromotionImage promotionImage;

    @ManyToOne
    private PromotionStatus promotionStatus;

    //Gettter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PromotionCode getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(PromotionCode promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionStatus getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(PromotionStatus promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public PromotionImage getPromotionImage() {
        return promotionImage;
    }

    public void setPromotionImage(PromotionImage promotionImage) {
        this.promotionImage = promotionImage;
    }
}