package com.saas.plm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
public class User {

    @Id @Getter(lazy = true) @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Getter @Setter
    private String password;
}
