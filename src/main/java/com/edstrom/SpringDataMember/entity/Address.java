package com.edstrom.SpringDataMember.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String street;

    @Column(nullable = false, length = 20)
    private String postalCode;

    @Column(nullable = false, length = 20)
    private String city;

    @OneToMany(mappedBy = "address")
    private List<Member> members;

    protected Address(){}

    public Address(String street, String postalCode, String city){
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
