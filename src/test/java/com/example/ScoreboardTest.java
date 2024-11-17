package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreboardTest {

    @Test
    void startNewMatch_notNull() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertNotNull(summary);
    }

    @Test
    void startNewMatch_summaryNotEmpty() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertFalse(summary.isEmpty());
    }

    @Test
    void startNewMatch_checkSummaryTeamContent() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals("homeTeam", match.getHomeTeam());
        Assertions.assertEquals("awayTeam", match.getAwayTeam());
    }


}
