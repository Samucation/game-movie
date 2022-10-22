package com.evaluation.movie.battle.repository;

import com.evaluation.movie.battle.model.GameMatch;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameMatch, Long> {


}
