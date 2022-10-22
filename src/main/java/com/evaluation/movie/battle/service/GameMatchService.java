package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.builder.UserMapper;
import com.evaluation.movie.battle.dto.GameMatchDTO;
import com.evaluation.movie.battle.dto.GameStatusDTO;
import com.evaluation.movie.battle.dto.MovieStatusDTO;
import com.evaluation.movie.battle.dto.UserDTO;
import com.evaluation.movie.battle.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class GameMatchService {

    private final UserMapper userMapper;
    private GameService gameService;


    public GameStatusDTO startGame(UserDTO userDTO, List<String> movieTileList) {
        MovieStatusDTO movieStatusDTO = gameService.validationMovieNameRepeated(movieTileList);
        GameStatusDTO gameStatusDTO = new GameStatusDTO();

        if (movieStatusDTO.getIsFailValidation()) {
            gameStatusDTO.setGameStatus("Existem filmes repetidos, tente novammente, Filme repetido: " + movieStatusDTO.getRepeatedMovies());
        }
        User user = userMapper.convert(userDTO);
        GameMatchDTO gameMatchDTO = new GameMatchDTO();
        gameMatchDTO.setUser(user);

        gameMatchDTO.setCreateDate(new Date());
        gameService.saveGameMath(gameMatchDTO);
        return gameStatusDTO;
    }

    public GameStatusDTO showGameResult(UserDTO userDTO){
        GameMatchDTO gameMatchDTO = gameService.findActiveGameMatchByUser(userDTO);
        GameStatusDTO gameStatusDTO = new GameStatusDTO();

        if(Objects.isNull(gameMatchDTO.getMatchEnded())) {
            gameStatusDTO.setGameStatus("Não existe jogo inicializado! inicialize um jogo e termineo para consultar o resultado!");
            gameStatusDTO.setGameMatchDTO(gameMatchDTO);
            return gameStatusDTO;
        }

        if(gameMatchDTO.getMatchEnded()) {
            gameStatusDTO.setGameStatus("O jogo chegou ao fim! os resultados podem ser conferidos a seguir!");
            gameStatusDTO.setGameMatchDTO(gameMatchDTO);
            return gameStatusDTO;
        }
        gameStatusDTO.setGameStatus("O jogo não chegou ao fim! encerre o jogo atual ou termineo para consultar o resultado!");
        return gameStatusDTO;
    }

    public GameStatusDTO endGame(UserDTO userDTO) {
       GameMatchDTO gameMatchDTO = gameService.findActiveGameMatchByUser(userDTO);
       gameService.deleteGameMatch(gameMatchDTO);
       return new GameStatusDTO("O jogo chegou ao fim!", null);
    }

    public List<UserDTO> showHiScoreGames(){
        return gameService.showBestUserScore();
    }

}
