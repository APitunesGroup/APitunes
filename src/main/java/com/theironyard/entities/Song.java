package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by Erik on 7/8/16.
 */
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String artist;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String file;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    int likes;

    @ManyToOne
    User user;

    public Song() {
    }

    public Song(String artist, String title, String genre, String file, User user) {
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.file = file;
        this.user = user;

    }

    public Song(String artist, String title, String file, String genre, int likes, User user) {
        this.artist = artist;
        this.title = title;
        this.file = file;
        this.genre = genre;
        this.likes = likes;
        this.user = user;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public int compareTo(Object otherSong) {
//        if (this.getLikes() < Integer.valueOf(otherSong.getLikes())){
//            return -1;
//
//        } else if (this.getLikes() > (Song) Integer.valueOf(otherSong.getLikes())){
//            return 1;
//
//        } else {
//            return 0;
//
//        }
//    }
}


