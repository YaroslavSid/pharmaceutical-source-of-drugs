package com.example.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Drugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    @NotBlank
    String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    @Column(nullable = false)
    String dateOfApproval;

    @Column(nullable = false)
    String treatmentFor;

    @Column(nullable = false)
    String description;
}
