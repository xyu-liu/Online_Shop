package com.jackliu.OnlineShop.security;

import com.jackliu.OnlineShop.cart.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    @NotNull
    @Size(max = 20)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(max = 50)
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Cart cart;*/


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


