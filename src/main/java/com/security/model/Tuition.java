package com.security.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Tuition {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDate date;
   private double paidAmount;
   private String collector;

   @OneToOne
    private User user;
}
