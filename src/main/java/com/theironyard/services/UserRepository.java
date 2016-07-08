package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Erik on 7/8/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
