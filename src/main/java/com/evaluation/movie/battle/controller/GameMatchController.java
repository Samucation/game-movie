package com.evaluation.movie.battle.controller;

import com.evaluation.movie.battle.dto.GameStartDTO;
import com.evaluation.movie.battle.dto.GameStatusDTO;
import com.evaluation.movie.battle.dto.UserDTO;
import com.evaluation.movie.battle.service.GameMatchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GameMatchController {

    private final GameMatchService gameMatchService;

    @RequestBody
    @ApiOperation(value = "Inicializa o jogo, passe até dois filmes para começar!")
    @PostMapping(path = "start-game", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatusDTO startGame(@RequestBody() GameStartDTO gameStartDTO){
        return gameMatchService.startGame(gameStartDTO.getUserDTO(), gameStartDTO.getMovieTitleList(), gameStartDTO.getHiScoreMovieName());
    }

    @RequestBody
    @ApiOperation(value = "Mostra o resultado da partida, se ganhou ou perdeu")
    @GetMapping(path = "show-result", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatusDTO showGameResult(@RequestBody() UserDTO userDTO){
        return gameMatchService.showGameResult(userDTO);
    }

    @RequestBody
    @ApiOperation(value = "Mostra os melhores resultados do maior para o menor!")
    @GetMapping(path = "show-hi-score-by-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer showHiScoreByUser(@RequestBody() UserDTO userDTO){
        return gameMatchService.showHiScoreByUser(userDTO);
    }

    @RequestBody
    @ApiOperation(value = "Mostra os melhores resultados do maior para o menor!")
    @GetMapping(path = "show-all-hi-scores", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer showAllHiScores(){
        return null;
    }

    @RequestBody
    @ApiOperation(value = "Encerra um jogo que não foi terminado!")
    @PostMapping(path = "end-game", produces = MediaType.APPLICATION_JSON_VALUE)
    public GameStatusDTO endGame(@RequestBody() UserDTO userDTO){
        return gameMatchService.endGame(userDTO);
    }

}
