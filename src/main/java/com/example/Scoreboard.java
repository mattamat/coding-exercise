package com.example;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<Match> summary = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        summary.add(new Match(homeTeam, awayTeam));
    }

    public List<Match> getSummary() {
        return summary;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) throws Exception {

        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new Exception("Score can not be a negative number");
        }

        var matchToUpdate  = summary.stream()
                .filter(match ->
                        match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .get();

        matchToUpdate.setHomeScore(homeTeamScore);
        matchToUpdate.setAwayScore(awayTeamScore);

    }
}
