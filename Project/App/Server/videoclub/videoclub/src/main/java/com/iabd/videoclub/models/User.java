package com.iabd.videoclub.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    @Email
    @NotBlank
    private String email;
    private String password;

    public User(Long id, @Email @NotBlank String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "email_adress", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
