package com.wander.coding.challenge.usermanagement.dataaccess.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String status;
    private String country;
    private Date creationDate;
    private String role;
}
