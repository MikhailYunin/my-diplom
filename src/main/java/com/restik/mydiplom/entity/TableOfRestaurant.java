package com.restik.mydiplom.entity;

import javax.persistence.*;

@Entity
@Table(name="restaurant_table_tbl")
public class TableOfRestaurant {
    @Id
    @GeneratedValue
    @Column(name="tableID",unique=true,nullable=false)
    private int tableID;

    @Column(name="tableNo")
    private int tableNo;

    @Column(name="tableStatus")
    private String tableStatus;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="restID")
    private Restaurant restaurant;


    public TableOfRestaurant(){
    }

    public int getTableNo() {
        return tableNo;
    }
    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getTableID() {
        return tableID;
    }
    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getTableStatus() {
        return tableStatus;
    }
    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }
}
