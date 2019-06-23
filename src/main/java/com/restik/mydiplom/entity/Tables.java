package com.restik.mydiplom.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table
@Entity
public class Tables {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int tableId;

    private int tableNum;
    private int visitorsVolume;

    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "tables",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Reserve> reserveList = new ArrayList<>();


    public int getTableNum() {
        return tableNum;
    }
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getVisitorsVolume() {
        return visitorsVolume;
    }
    public void setVisitorsVolume(int visitorsVolume) {
        this.visitorsVolume = visitorsVolume;
    }

    public List<Reserve> getReserveList() {
        return reserveList;
    }
    public void setReserveList(List<Reserve> reserveList) {
        this.reserveList = reserveList;
    }
}
