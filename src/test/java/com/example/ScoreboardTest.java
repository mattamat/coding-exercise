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

    @Test
    void startNewMatch_checkSummaryTeamNewContent() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Japan", "Italy");
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals("Japan", match.getHomeTeam());
        Assertions.assertEquals("Italy", match.getAwayTeam());
    }

    @Test
    void startNewMatch_checkSummaryScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Japan", "Italy");
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals(0, match.getScoreHomeTeam());
        Assertions.assertEquals(0, match.getScoreAwayTeam());
    }

    @Test
    void updateScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        scoreboard.updateScore("homeTeam", "awayTeam", 1, 0);
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals(1, match.getScoreHomeTeam());
        Assertions.assertEquals(0, match.getScoreAwayTeam());
    }

    @Test
    void updateScore_multipleMatches() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        scoreboard.startMatch("Poland", "France");
        scoreboard.updateScore("Poland", "France", 1, 0);
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertEquals(3, summary.size());
        var match = summary.get(2);
        Assertions.assertEquals(1, match.getScoreHomeTeam());
        Assertions.assertEquals(0, match.getScoreAwayTeam());
    }

    @Test
    void updateScore_negativeScoreThrowsException() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        var exception = Assertions.assertThrows((Exception.class),
                () -> scoreboard.updateScore("homeTeam", "awayTeam", -1, 0));
        Assertions.assertEquals("Score can not be a negative number", exception.getMessage());
    }





}
