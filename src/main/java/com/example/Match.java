package com.example;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    public Match(String homeTeam, String awayTeam) throws Exception {
        if (homeTeam == null
                || awayTeam == null
                || homeTeam.isBlank()
                || awayTeam.isBlank()) {
            throw new Exception("Missing team");
        }

        if (homeTeam.toLowerCase().equals(awayTeam.toLowerCase())){
            throw new Exception("Teams are equal. A team cannot play against itself.");
        }


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

    public void setHomeScore(int homeTeamScore) throws Exception {
        if (homeTeamScore < 0 ) {
            throw new Exception("Score can not be a negative number");
        }
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayScore(int awayTeamScore) throws Exception {
        if (awayTeamScore < 0 ) {
            throw new Exception("Score can not be a negative number");
        }
        this.awayTeamScore = awayTeamScore;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    @Override
    public String toString() {
        return homeTeam + " " + homeTeamScore + " - " + awayTeam + " " + awayTeamScore;
    }

}
