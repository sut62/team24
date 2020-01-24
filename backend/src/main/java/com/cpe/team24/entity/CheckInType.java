package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;


@CrossOrigin(origins = "*")
@Data
@Entity
@NoArgsConstructor
@Table(name = "CHECKIN_TYPE")
public class CheckInType {

    @Id
    @SequenceGenerator(name = "CHECKIN_TYPE_SEQ", sequenceName = "CHECKIN_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_TYPE_SEQ")
    @Column(name = "CHECKIN_TYPE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany
    @JsonIgnore
    private Collection<CheckIn> checkIn;

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

    public Collection<CheckIn> getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Collection<CheckIn> checkIn) {
        this.checkIn = checkIn;
    }
}
