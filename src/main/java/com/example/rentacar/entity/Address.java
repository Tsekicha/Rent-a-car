package com.example.rentacar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "city_zip_code",nullable = false)
    private String cityZipCode;
    @Column(name = "country",nullable = false)
    private String country;
    @Column(name = "street_name",nullable = false)
    private String streetName;
    @Column(name = "street_number",nullable = false)
    private String streetNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
