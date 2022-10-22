package com.evaluation.movie.battle;

import com.evaluation.movie.battle.dto.MovieStatusDTO;
import com.evaluation.movie.battle.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class GameMovieApplicationTests {

	@Autowired
	private GameService gameService;

	@Test
	public void contextLoads() {
		List<String> movieTileList = new ArrayList<>();
		movieTileList.add("Matrix");
		movieTileList.add("Titanic");
		movieTileList.add("titanic");

		MovieStatusDTO movieStatusDTO = gameService.ValidationMovieNameRepeated(movieTileList);

		assertEquals(movieStatusDTO.getIsFailValidation(), Boolean.TRUE);

		System.out.println("Call Tests. validateSatusDTO: " + movieStatusDTO);
		System.out.println("End");
	}

}
