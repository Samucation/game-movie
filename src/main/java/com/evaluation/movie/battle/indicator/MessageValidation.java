package com.evaluation.movie.battle.indicator;

import java.util.List;

public enum MessageValidation implements ValidationMessageIndicatorImpl {

    TYPES {
        @Override
        public String repeatedMovies(List<String> repeatedMovies) {
            StringBuilder repeatedMovivesStr = new StringBuilder();
            repeatedMovivesStr.append("Existem filmes repetidos, tente novammente, filmes repetidos:");
            for (String movieRepeated : repeatedMovies) {
                repeatedMovivesStr.append(", " + movieRepeated);
            }
            return repeatedMovivesStr.toString();
        }

        @Override
        public String currentGameMatchStatus(Integer currentGameStage, Integer numberAttemptsAllowed) {
            return "Stagio atual do jogo: " + currentGameStage + " número máximo de stagios atuais configurados: " + numberAttemptsAllowed;
        }

        @Override
        public String exceededNumberOfMovies(Integer currentGameStage, Integer numberAttemptsAllowed) {
            return "O número de estagios atuais excede o permitido pelo jogo! número de estagios atual: " + currentGameStage +
                    " número permitido: " + numberAttemptsAllowed + " Encerre o jogo para iniciar uma nova partida.";
        }

        @Override
        public String theGameIsNotOverYet(Integer activeGameMatchers, Integer numberAttemptsAllowed) {
            return "O jogo ainda não chegou ao fim! continue jogando até chegar ao fim do jogo! estagio atual: " + activeGameMatchers + " " +
                    "de estagios necessários para encerrar a partida: " + numberAttemptsAllowed;
        }

        @Override
        public String theGameHasComeToAnEnd() {
            return "O jogo chegou ao fim! os resultados podem ser conferidos a seguir!";
        }

        @Override
        public String theGameWasEndedBeforeReachingTheEnd() {
            return "O jogo foi encerrado antes de chegar ao fim! todos os pontos da partida atual foram perdidos.";
        }
    };

}
