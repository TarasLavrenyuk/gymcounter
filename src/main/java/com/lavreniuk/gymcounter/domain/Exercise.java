package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @author taras
 * @date 28.04.18.
 */
@Entity
@Table(name = "exercises")
@Proxy(lazy = false)
@Data
public class Exercise {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "exercise_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Exercise() {
    }
}
