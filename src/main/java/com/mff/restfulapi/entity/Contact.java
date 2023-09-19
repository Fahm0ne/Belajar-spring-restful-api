package com.mff.restfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
@Entity
public class Contact {

    @Id
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Users user;

    @OneToMany(mappedBy = "contact")
    private List<Address> addresses;

}
