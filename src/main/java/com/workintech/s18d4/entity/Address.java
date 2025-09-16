package com.workintech.s18d4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="address", schema="fsweb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="no")
    private Integer no;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="description", nullable = true)
    private String description;


    @JsonBackReference("customer-address")
    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private Customer customer;
}