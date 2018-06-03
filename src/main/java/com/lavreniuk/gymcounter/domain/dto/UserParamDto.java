package com.lavreniuk.gymcounter.domain.dto;

import lombok.Data;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author taras
 * @date 03.06.18.
 */
@Entity
@Subselect("")
@Data
public class UserParamDto {

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
    @Column(name = "name")
    private String name;
    @Column(name = "unit")
    private String unit;

    public UserParamDto() {
    }
}
