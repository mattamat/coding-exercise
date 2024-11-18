package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Scoreboard {

    private final List<Match> summary = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) throws Exception {
        var match = new Match(homeTeam, awayTeam);
        checkIfMatchAlreadyExists(match);
        summary.add(match);
    }

    public List<Match> getSummary() {
        return summary.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore))
                .toList()
                .reversed();
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) throws Exception {
        var matchToUpdate = getMatch(homeTeam, awayTeam);
        matchToUpdate.setHomeScore(homeTeamScore);
        matchToUpdate.setAwayScore(awayTeamScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        var finishedMatch = getMatch(homeTeam, awayTeam);
        summary.remove(finishedMatch);
    }

    private Predicate<Match> isMatchInSummary(String homeTeam, String awayTeam) {
        return match ->
                match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)
                        || match.getHomeTeam().equals(awayTeam) && match.getAwayTeam().equals(homeTeam);
    }

    private Match getMatch(String homeTeam, String awayTeam) {
        return summary.stream()
                .filter(isMatchInSummary(homeTeam, awayTeam))
                .findFirst()
                .orElseThrow();
    }

    private void checkIfMatchAlreadyExists(Match match) throws Exception {
        if (summary.stream().anyMatch(isMatchInSummary(match.getHomeTeam(), match.getAwayTeam()))) {
            throw new Exception();
        }
    }
}
