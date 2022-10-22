package com.evaluation.movie.battle.repository;

import com.evaluation.movie.battle.model.GameMatch;
import com.evaluation.movie.battle.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface GameMatchRepository extends CrudRepository<GameMatch, Long> {

    @Query("SELECT gm " +
            " FROM GameMatch gm " +
            " WHERE gm.matchEnded = 'true' " +
            " AND gm.user = :currentUser ")
    GameMatch findActiveGameMatchByUser(@Param("currentUser") User user);

}
