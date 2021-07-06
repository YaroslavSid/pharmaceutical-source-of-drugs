package com.example.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    @NotBlank
    String name;

    @Column(nullable = false)
    @NotBlank
    String surname;

    @Column(nullable = false)
    @NotBlank
    @Email
    String email;

    @Column(nullable = false)
    @NotBlank
    String password;

    @Transient
    transient String confirmPassword;

    @Temporal(TemporalType.TIMESTAMP)
    Date createTime;

    @Column(nullable = false)
    String role;
}
