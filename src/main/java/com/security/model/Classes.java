package com.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Classes {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Course course;
}
