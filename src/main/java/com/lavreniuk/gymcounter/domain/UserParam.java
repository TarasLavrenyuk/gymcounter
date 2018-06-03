package com.lavreniuk.gymcounter.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @author taras
 * @date 03.06.18.
 */
@Entity
@Table(name = "user_params")
@Proxy(lazy = false)
@Data
public class UserParam {

    @Id
    @Column(name = "user_param_id")
    private String userParamId;
    @Column(name = "param_id")
    private String paramId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "value")
    private Double value;
    @Column(name = "param_date")
    private Date date;

    @Transient
    private User user;
    @Transient
    private Param param;

    public UserParam() {
    }

    public UserParam(String userParamId, String paramId, Double value, Date date) {
        this.userParamId = userParamId;
        this.paramId = paramId;
        this.value = value;
        this.date = date;
    }
}
