package com.example.bioproject.entities;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerMobile;
    @Column(nullable = false)
    private int numberOfPeople;

    public Reservation() {}

    public Long getId() {
        return id;
    }

    public Screening getScreening() {
        return screening;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

}
