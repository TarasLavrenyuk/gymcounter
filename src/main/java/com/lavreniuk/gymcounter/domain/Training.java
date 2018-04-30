package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "training_id")
    private String id;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;

    @Transient
    private List<Set> sets;

    public Training() {
    }
}
