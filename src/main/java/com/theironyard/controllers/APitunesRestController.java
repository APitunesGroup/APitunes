package com.theironyard.controllers;

import com.theironyard.entities.Song;
import com.theironyard.entities.User;
import com.theironyard.services.LikeRepository;
import com.theironyard.services.SongRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathandavidblack on 7/8/16.
 */
@RestController
public class APitunesRestController {

    @Autowired
    UserRepository users;

    @Autowired
    SongRepository songs;

    @Autowired
    LikeRepository likes;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpSession session) throws Exception {
        User userFromDatabase = users.findFirstByUsername(user.getUsername());

        if (userFromDatabase == null) {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            user.setUsername(user.getUsername());
            user.setIsAdmin(false);
            user.setIsArtist(false);
            user.setIsUser(true);
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDatabase.getPassword())) {
            throw new Exception("BAD PASS");
        }
        session.setAttribute("username", user.getUsername());
        return userFromDatabase;
    }
    @RequestMapping(path = "/userList", method = RequestMethod.GET)
    public List<Song> allSongs() {

        ArrayList<Song> sortedSongs = (ArrayList<Song>) songs.findAll();

        sortedSongs
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getLikes(),
                        s1.getLikes()));
        //test here...
        // sout sorted songs
        return sortedSongs;
    }

    @RequestMapping(path = "/artistList", method = RequestMethod.GET)
    public List<Song> allArtistSongs(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        ArrayList<Song> sortedSongs = (ArrayList<Song>) songs.findByUser(user);

        sortedSongs
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getLikes(),
                        s1.getLikes()));
        //test here...
        // sout sorted songs
        return sortedSongs;
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void addSong (HttpSession session, String artist, String title, String genre, MultipartFile audioFile,  HttpServletResponse response) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);
        if (user.getIsArtist() == false) {
            throw new Exception("Must be an artist to upload.");
        }

        if (audioFile.getContentType().contains("audio")) {
            File dir = new File("Public/songs");
            dir.mkdirs();
            File songFile = File.createTempFile("song", audioFile.getOriginalFilename(), dir);
            FileOutputStream fos = new FileOutputStream(songFile);
            fos.write(audioFile.getBytes());
            Song song = new Song(artist, title, genre, songFile.getName(), user);
            songs.save(song);

        }
        else {
            throw new Exception("Invalid File Type");
        }

        response.sendRedirect("/#/artist");


    }

    @RequestMapping(path = "/upVote{id}", method = RequestMethod.POST)
    public Integer upVotedSongList(HttpSession session, @PathVariable int id) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);

        Song upVotedSong = songs.findOne(id);
        upVotedSong.setLikes(upVotedSong.getLikes() + 1);
        songs.save(upVotedSong);

        List<Song> entireList = (List<Song>) songs.findAll();

        return upVotedSong.id;

    }
    @RequestMapping(path = "/downVote{id}", method = RequestMethod.POST)
    public Integer downVotedSongList(HttpSession session, @PathVariable int id) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByUsername(username);

        Song downVotedSong = songs.findOne(id);
        downVotedSong.setLikes(downVotedSong.getLikes() - 1);
        songs.save(downVotedSong);

        List<Song> entireList = (List<Song>) songs.findAll();
        return downVotedSong.id;
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public HttpStatus logout(HttpSession session) {
        session.invalidate();

        return HttpStatus.OK;
    }

}



