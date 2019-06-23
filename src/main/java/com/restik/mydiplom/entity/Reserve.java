package com.restik.mydiplom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


import java.time.LocalDateTime;


@Table
@Entity
public class Reserve {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reserveStart;


    @ManyToOne
    @JoinColumn(name = "tableId")
    private Tables tables;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getReserveStart() {
        return reserveStart;
    }
    public void setReserveStart(LocalDateTime reserveStart) {
        this.reserveStart = reserveStart;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Tables getTables() {
        return tables;
    }
    public void setTables(Tables tables) {
        this.tables = tables;
    }
}
