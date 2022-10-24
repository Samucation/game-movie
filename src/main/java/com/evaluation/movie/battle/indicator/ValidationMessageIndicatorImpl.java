package com.evaluation.movie.battle.indicator;

import java.util.List;

public interface ValidationMessageIndicatorImpl {

    String repeatedMovies(List<String> repeatedMovies);

    String currentGameMatchStatus(Integer currentGameStage, Integer numberAttemptsAllowed);

    String exceededNumberOfMovies(Integer currentGameStage, Integer numberAttemptsAllowed);

    String theGameIsNotOverYet(Integer activeGameMatchers, Integer numberAttemptsAllowed);

    String theGameHasComeToAnEnd();

    String theGameWasEndedBeforeReachingTheEnd();


}
