package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Erik on 7/8/16.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    boolean isAdmin = false;

    @Column(nullable = false)
    boolean isArtist = false;

    @Column(nullable = false)
    boolean isUser = true;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        isAdmin = isAdmin;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public void setIsArtist(boolean isArtist) {
        isArtist = isArtist;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        isUser = isUser;
    }
}

