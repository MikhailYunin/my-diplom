package com.restik.mydiplom.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="restaurant_tbl")
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name="restID")
    private int restID;

    @Column(name="restName")
    private String restName;

    @Column(name="tablesQty")
    private int tablesQty;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="restAdminID")
    private AdminOfRestaurant restAdmin;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="restaurant",cascade=CascadeType.ALL)
    List<TableOfRestaurant> restTable = new ArrayList<TableOfRestaurant>();


    public Restaurant(){
    }

    public List<TableOfRestaurant> getRestTable() {
        return restTable;
    }
    public void setRestTable(List<TableOfRestaurant> restTable) {
        this.restTable = restTable;
    }

    public AdminOfRestaurant getRestAdmin() {
        return restAdmin;
    }
    public void setRestAdmin(AdminOfRestaurant restAdmin) {
        this.restAdmin = restAdmin;
    }

    public int getRestID() {
        return restID;
    }
    public void setRestID(int restID) {
        this.restID = restID;
    }

    public String getRestName() {
        return restName;
    }
    public void setRestName(String restName) {
        this.restName = restName;
    }
}





/*
@Table
public class Restaurant {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int restaurantId;

    private String restaurantName;
    private String adress;
    private String phoneNumber;
    private int tablesQty;

    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Tables> tablesList = new ArrayList<>();


    public int getTablesQty() {
        return tablesQty;
    }
    public void setTablesQty(int tablesQty) {
        this.tablesQty = tablesQty;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Tables> getTablesList() {
        return tablesList;
    }
    public void setTablesList(List<Tables> tablesList) {
        this.tablesList = tablesList;
    }
}
*/