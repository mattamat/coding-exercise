package com.example;

public class Match {

    private String homeTeam;
    private String awayTeam;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getScoreHomeTeam() {
        return 0;
    }

    public int getScoreAwayTeam() {
        return 0;
    }
}
