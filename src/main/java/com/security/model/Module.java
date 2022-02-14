package com.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Module {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String studyTime;

    @ManyToOne
    private Course course;
}
