package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScoreboardTest {

    @Test
    public void startNewMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertNotNull(summary);
    }
}
