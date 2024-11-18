package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ScoreboardTest {

    @Test
    void startNewMatch_notNull() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertNotNull(summary);
    }

    @Test
    void startNewMatch_summaryNotEmpty() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        Assertions.assertFalse(summary.isEmpty());
    }

    @Test
    void startNewMatch_checkSummaryTeamContent() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("homeTeam", "awayTeam");
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals("homeTeam", match.getHomeTeam());
        Assertions.assertEquals("awayTeam", match.getAwayTeam());
    }

    @Test
    void startNewMatch_checkSummaryTeamNewContent() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Japan", "Italy");
        List<Match> summary = scoreboard.getSummary();
        var match = summary.getFirst();
        Assertions.assertEquals("Japan", match.getHomeTeam());
        Assertions.assertEquals("Italy", match.getAwayTeam());
    }

    @Test
    void startNewMatch_checkSummaryScore() throws Exception {
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

        var match = summary.getFirst();
        Assertions.assertEquals(1, match.getScoreHomeTeam());
        Assertions.assertEquals(0, match.getScoreAwayTeam());
    }

    @Test
    void updateScore_negativeScoreThrowsException() throws Exception {
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
    void finishMatch() throws Exception {
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
    void finishMatch_lastGameFinishesFirst() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        scoreboard.startMatch("China", "Russia");
        scoreboard.finishMatch("China", "Russia");

        var summary = scoreboard.getSummary();
        Assertions.assertTrue(summary.stream().noneMatch(match -> match.getHomeTeam().equals("China")));
    }

    @Test
    void finishMatch_MatchNotExisting() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");

        Assertions.assertThrows((Exception.class),
                () -> scoreboard.finishMatch("Russia", "China"));
    }

    @Test
    void summary_printScoreBoard() throws Exception {
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

    @Test
    void summary_mostRecentFirst() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Brazil", "Japan");
        scoreboard.startMatch("Norway", "Sweden");
        scoreboard.startMatch("Poland", "England");

        var score1 = scoreboard.getSummary().get(0).toString();
        var score2 = scoreboard.getSummary().get(1).toString();
        var score3 = scoreboard.getSummary().get(2).toString();

        Assertions.assertEquals("Poland 0 - England 0", score1);
        Assertions.assertEquals("Norway 0 - Sweden 0", score2);
        Assertions.assertEquals("Brazil 0 - Japan 0", score3);

    }

    @Test
    void summary_HighestTotalScoreAndMostRecentWhenSameScore() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        var score1 = scoreboard.getSummary().get(0).toString();
        var score2 = scoreboard.getSummary().get(1).toString();
        var score3 = scoreboard.getSummary().get(2).toString();
        var score4 = scoreboard.getSummary().get(3).toString();
        var score5 = scoreboard.getSummary().get(4).toString();

        Assertions.assertEquals("Uruguay 6 - Italy 6", score1);
        Assertions.assertEquals("Spain 10 - Brazil 2", score2);
        Assertions.assertEquals("Mexico 0 - Canada 5", score3);
        Assertions.assertEquals("Argentina 3 - Australia 1", score4);
        Assertions.assertEquals("Germany 2 - France 2", score5);

    }

    @Test
    void startTwoEqualMatches() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch("Mexico", "Canada");
        Assertions.assertThrows(Exception.class, () -> scoreboard.startMatch("Mexico", "Canada"));

    }

    @Test
    void startMatchWithSameTeamTwice() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        Assertions.assertThrows(Exception.class, () -> scoreboard.startMatch("Mexico", "Mexico"));
    }

    @Test
    void startMatchWithOneTeamBeingNull() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        Assertions.assertThrows(Exception.class, () -> scoreboard.startMatch("Mexico", null));

    }


}
