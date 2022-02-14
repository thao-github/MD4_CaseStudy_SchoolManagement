package com.security.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Mark {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double theory; //ly thuyet, thuc hanh
    private Double practice;

    @ManyToOne
    private Module module;

    @OneToOne
    private User user;
}
