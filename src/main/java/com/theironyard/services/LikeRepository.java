package com.theironyard.services;

import com.theironyard.entities.Like;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Erik on 7/8/16.
 */
public interface LikeRepository extends CrudRepository<Like, Integer> {
}
