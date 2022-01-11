package com.example.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id
    private long id;
    private String nom;
    private String prenom;
}
