package com.theironyard.controllers;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by jonathandavidblack on 7/8/16.
 */
@Controller
public class APitunesController {

    @Autowired
    UserRepository users;

    @Autowired
    SongRepository songs;

    @Autowired
    LikeRepository likes;


    @PostConstruct
    public void init() throws SQLException, FileNotFoundException {
        Server.createWebServer().start();
        parseUsers("APitunesUsers.csv");

    }
    public void parseUsers(String fileName) throws FileNotFoundException {
        if (users.count() == 0) {
            File usersFile = new File(fileName);
            Scanner fileScanner = new Scanner(usersFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String[] columns = fileScanner.nextLine().split(",");
                User user = new User(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);

            }
        }
    }
}
