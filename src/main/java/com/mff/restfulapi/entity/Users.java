package com.mff.restfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class Users {
    @Id
    private String username;

    private String password;

    private String name;

    private String token;

    private Long tokenExpredAt;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

}
