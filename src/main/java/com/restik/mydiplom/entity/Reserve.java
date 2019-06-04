package com.restik.mydiplom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity
public class Reserve {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reserveStart;

    @OneToOne (mappedBy =  "reserve")
    private Visitors visitor = new Visitors();

    @OneToOne
    @JoinColumn(nullable = false)
    private Tables tables;

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

    public Visitors getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitors visitor) {
        this.visitor = visitor;
    }
}