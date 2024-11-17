package com.example;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<Match> summary = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        summary.add(new Match());
    }

    public List<Match> getSummary() {
        return summary;
    }
}
