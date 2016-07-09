package com.theironyard.controllers;
import static java.util.Comparator.comparing;

import com.theironyard.entities.Song;
import com.theironyard.entities.User;
import com.theironyard.services.LikeRepository;
import com.theironyard.services.SongRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
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
        return user;
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
}


