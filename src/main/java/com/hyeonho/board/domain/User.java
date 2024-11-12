package com.hyeonho.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String password;


}
