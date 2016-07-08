package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Erik on 7/8/16.
 */
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    boolean isGood;

    @ManyToOne
    User user;

    @ManyToOne
    Song song;

    public Like() {
    }

    public Like(boolean isGood, User user, Song song) {
        this.isGood = isGood;
        this.user = user;
        this.song = song;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setIsGood(boolean isGood) {
        isGood = isGood;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
