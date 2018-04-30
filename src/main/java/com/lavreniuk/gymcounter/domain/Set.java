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
    private String id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "exercise_id")
    private String exerciseId;
    @Column(name = "training_id")
    private String trainingId;
    @Column(name = "set_date")
    private Date date;
    @Column(name = "reps")
    private Integer reps;

    @Transient
    private User user;
    @Transient
    private Exercise exercise;
    @Transient
    private Training training;

    public Set() {
    }
}
