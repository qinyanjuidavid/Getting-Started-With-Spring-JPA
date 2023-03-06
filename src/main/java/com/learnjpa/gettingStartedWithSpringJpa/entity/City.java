package com.learnjpa.gettingStartedWithSpringJpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="City")
public class City {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    public City(String name){
        this.name=name;
    }
  
}
