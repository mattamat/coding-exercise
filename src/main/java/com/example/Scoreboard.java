package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Scoreboard {

    private final List<Match> summary = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) throws Exception {
        if (summary.stream().anyMatch(isMatchInSummary(homeTeam, awayTeam))) {
            throw new Exception();
        }
        summary.add(new Match(homeTeam, awayTeam));
    }


    public List<Match> getSummary() {
        return summary.stream()
                .sorted(Comparator.comparing(Match::getTotalScore))
                .toList()
                .reversed();
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) throws Exception {
        var matchToUpdate  = summary.stream()
                .filter(isMatchInSummary(homeTeam, awayTeam))
                .findFirst()
                .orElseThrow();

        matchToUpdate.setHomeScore(homeTeamScore);
        matchToUpdate.setAwayScore(awayTeamScore);

    }

    public void finishMatch(String homeTeam, String awayTeam) {
        var finishedMatch = summary.stream()
                .filter(isMatchInSummary(homeTeam, awayTeam))
                .findFirst()
                .orElseThrow();

        summary.remove(finishedMatch);
    }

    private Predicate<Match> isMatchInSummary(String homeTeam, String awayTeam) {
        return match ->
                match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam);
    }
}
