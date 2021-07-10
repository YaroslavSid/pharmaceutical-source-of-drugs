package com.example.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

//    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE,
//            CascadeType.PERSIST,
//            CascadeType.DETACH,
//            CascadeType.REFRESH})
//    @BatchSize(size = 10)
//    Set<Drugs> drugs;
}
