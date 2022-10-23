package com.evaluation.movie.battle.repository;

import com.evaluation.movie.battle.model.GameMatch;
import com.evaluation.movie.battle.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GameMatchRepository extends CrudRepository<GameMatch, Long> {

    @Query(" SELECT gm " +
            " FROM GameMatch gm " +
            " WHERE gm.matchEnded = 'true' " +
            " AND gm.user = :currentUser ")
    List<GameMatch> findEndedGameMatchByUser(@Param("currentUser") User user);

    @Query(" SELECT gm " +
            " FROM GameMatch gm " +
            " WHERE gm.matchEnded = 'false' " +
            " AND gm.user = :currentUser ")
    List<GameMatch> findActiveGameMatchByUser(@Param("currentUser") User user);

    @Query(" SELECT COUNT(id) " +
            " FROM GameMatch gm WHERE gm.user = :currentUser ")
    Integer countGameMatcherActivesByUser(@Param("currentUser") User user);

    @Query(" SELECT SUM(gameScore) " +
            " FROM GameMatch gm WHERE " +
            " gm.matchEnded = 'false' AND " +
            " gm.user = :currentUser ")
    Integer sumScoresByAllActiveGameMatches(@Param("currentUser") User user);

    @Query(" SELECT SUM(gameScore) " +
            " FROM GameMatch gm WHERE " +
            " gm.user = :currentUser ")
    Integer sumHiScoreByUser(@Param("currentUser") User user);

}
