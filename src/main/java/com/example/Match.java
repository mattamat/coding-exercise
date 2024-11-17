package com.example;

public class Match {

    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

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
        return homeTeamScore;
    }

    public int getScoreAwayTeam() {
        return awayTeamScore;
    }

    public void setHomeScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
