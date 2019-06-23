package com.restik.mydiplom.entity;


import javax.persistence.*;

@Entity
@Table(name="rest_admin_tbl")
@PrimaryKeyJoinColumn(name="personID")
public class AdminOfRestaurant extends Person{

    @OneToOne(fetch= FetchType.EAGER, mappedBy="restAdmin", cascade=CascadeType.ALL)
    private Restaurant restaurant;


    public AdminOfRestaurant() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
