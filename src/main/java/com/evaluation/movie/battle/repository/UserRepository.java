package com.evaluation.movie.battle.repository;

import com.evaluation.movie.battle.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    @Query(" SELECT us " +
           " FROM User us " +
           " ORDER BY hiGameScore DESC ")
    List<User> listUserByHiScoreAsc();

    @Query(" SELECT us " +
            " FROM User us " +
            " WHERE us = :currentUser ")
    User findUser(@Param("currentUser") User user);

}
