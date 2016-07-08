package com.theironyard.services;

import com.theironyard.entities.Song;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Erik on 7/8/16.
 */
public interface SongRepository extends CrudRepository<Song, Integer> {

}
