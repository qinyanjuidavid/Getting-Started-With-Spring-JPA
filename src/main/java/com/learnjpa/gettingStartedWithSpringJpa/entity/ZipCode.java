package com.learnjpa.gettingStartedWithSpringJpa.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="ZipCode")
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="city_id")
    private City city;


    public ZipCode(String name, City city){
        this.name=name;
        this.city=city;
    }
    
}
