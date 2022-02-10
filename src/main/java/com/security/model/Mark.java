package com.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mark {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //ly thuyet, thuc hanh
    private Double mark;

    @ManyToOne
    private Module module;
}
