package com.lavreniuk.gymcounter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lavreniuk.gymcounter.enums.SexTypeEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author taras
 * @date 15.04.18.
 */
@Entity
@Table(name = "users")
@Proxy(lazy = false)
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @JsonIgnore
    @JsonProperty(value = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "user_role")
    @JsonIgnore
    private String role;
    @Column(name = "sex")
    @Enumerated(value = EnumType.STRING)
    private SexTypeEnum sex;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "height")
    private Double height;

    @Transient
    private List<Training> trainings;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
