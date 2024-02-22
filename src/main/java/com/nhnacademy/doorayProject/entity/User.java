package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dooray_user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    @Column(name = "user_name")
    private String userName;
    private String email;
    @Column(name = "latest_login")
    private LocalDateTime latestLogin;
}
