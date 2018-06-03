package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

/**
 * @author taras
 * @date 03.06.18.
 */
@Entity
@Table(name = "params")
@Proxy(lazy = false)
@Data
public class Param {

    @Id
    @Column(name = "param_id")
    private String paramId;
    @Column(name = "name")
    private String paramName;
    @Column(name = "unit")
    private String unit;

    @Transient
    List<UserParam> params;

    public Param() {
    }

    public Param(String paramId, String paramName, String unit) {
        this.paramId = paramId;
        this.paramName = paramName;
        this.unit = unit;
    }
}