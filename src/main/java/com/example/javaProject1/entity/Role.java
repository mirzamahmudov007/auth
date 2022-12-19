package com.example.javaProject1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
