package org.henry.dao;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private String age;

    @Column(name = "phone_number")
    private String phoneNumber;

}