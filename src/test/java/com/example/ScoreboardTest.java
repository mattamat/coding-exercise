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
    void updateScore_oneMatch() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        scoreboard.updateScore("homeTeam", "awayTeam", 1, 0);
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals(1, match.getScoreHomeTeam());
        Assertions.assertEquals(0, match.getScoreAwayTeam());
    }

    @Test
    void updateScore_multipleMatches() throws Exception {
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

    @Test
    void updateScore_MatchNotExisting() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");

        Assertions.assertThrows((Exception.class),
                () -> scoreboard.updateScore("Russia", "China", 1, 0));
    }

    @Test
    void finishMatch() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        Assertions.assertEquals(2, scoreboard.getSummary().size());

        scoreboard.finishMatch("Brazil", "Japan");

        Assertions.assertEquals(1, scoreboard.getSummary().size());
        Assertions.assertEquals("Norway", scoreboard.getSummary().getFirst().getHomeTeam());
        Assertions.assertEquals("Sweden", scoreboard.getSummary().getFirst().getAwayTeam());
    }


    @Test
    void finishMatch_lastGameFinishesFirst() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        scoreboard.startMatch("China", "Russia");
        var summary = scoreboard.getSummary();

        scoreboard.finishMatch("China", "Russia");

        Assertions.assertTrue(summary.stream().noneMatch(match -> match.getHomeTeam().equals("China")));
    }

    @Test
    void finishMatch_MatchNotExisting() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");

        Assertions.assertThrows((Exception.class),
                () -> scoreboard.finishMatch("Russia", "China"));
    }

    @Test
    void summary_printScoreBoard() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        var score = scoreboard.getSummary().getFirst().toString();
        Assertions.assertEquals("Brazil 0 - Japan 0", score);

    }

    @Test
    void summary_highestTotalScoreFirst() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        scoreboard.startMatch("Poland", "England");

        scoreboard.updateScore("Brazil", "Japan", 3, 3);
        scoreboard.updateScore("Norway", "Sweden", 10, 0);
        scoreboard.updateScore("Poland", "England", 3, 0);

        var score1 = scoreboard.getSummary().get(0).toString();
        Assertions.assertEquals("Norway 10 - Sweden 0", score1);
        var score2 = scoreboard.getSummary().get(1).toString();
        Assertions.assertEquals("Brazil 3 - Japan 3", score2);
        var score3 = scoreboard.getSummary().get(2).toString();
        Assertions.assertEquals("Poland 3 - England 0", score3);

    }




}
