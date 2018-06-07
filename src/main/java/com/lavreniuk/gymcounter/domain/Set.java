package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @author taras
 * @date 28.04.18.
 */
@Entity
@Table(name = "sets")
@Proxy(lazy = false)
@Data
public class Set {

    @Id
    @Column(name = "set_id")
    private String setId;
    @Column(name = "exercise_id")
    private String exerciseId;
    @Column(name = "training_id")
    private String trainingId;
    @Column(name = "set_time")
    private Date date;
    @Column(name = "reps")
    private Integer reps;
    @Column(name = "weight")
    private Double weight;

    @Transient
    private Exercise exercise;
    @Transient
    private Training training;

    public Set() {
    }

    public Set(String setId, String exerciseId, String trainingId, Date date, Integer reps, Double weight) {
        this.setId = setId;
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.date = date;
        this.reps = reps;
        this.weight = weight;
    }
}
