package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author taras
 * @date 28.04.18.
 */
@Entity
@Table(name = "trainings")
@Proxy(lazy = false)
@Data
public class Training {

    @Id
    @Column(name = "training_id")
    private String trainingId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;


    @Transient
    private List<Set> sets;
    @Transient
    private User user;

    public Training() {
    }

    public Training(String trainingId, String description, Date date) {
        this.trainingId = trainingId;
        this.description = description;
        this.date = date;
    }

    public void addSet(Set set) {
        if (this.sets == null) {
            this.sets = new LinkedList<>();
        }
        this.sets.add(set);
    }

    public void addSets(List<Set> sets) {
        if (this.sets == null) {
            this.sets = new LinkedList<>();
        }
        this.sets.addAll(sets);
    }
}
