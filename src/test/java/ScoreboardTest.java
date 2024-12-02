import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

public class ScoreboardTest {

    private Scoreboard scoreboard;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scoreboard = spy(new Scoreboard());
    }

    @Test
    public void startMatchEmptyTeams() {
        // Execute
        scoreboard.startMatch(null, null);
    }

    @Test
    public void startMatchEmptyHomeTeam() {
        // Initialize
        String homeTeam = "homeTeam";

        // Execute
        scoreboard.startMatch(homeTeam, null);
    }

    @Test
    public void startMatchEmptyAwayTeam() {
        // Initialize
        String awayTeam = "awayTeam";

        // Execute
        scoreboard.startMatch(null, awayTeam);
    }

    @Test
    public void startMatchBaseFlow() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
    }

    @Test
    public void startMatchWithHomeTeamAlreadyPlaying() {
        // Initialize
        scoreboard.startMatch("homeTeam", "awayTeam");

        String homeTeam = "homeTeam";
        String awayTeam = "newTeam";

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
    }

    @Test
    public void startMatchWithAwayTeamAlreadyPlaying() {
        // Initialize
        scoreboard.startMatch("homeTeam", "awayTeam");

        String homeTeam = "newTeam";
        String awayTeam = "awayTeam";

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
    }

    @Test
    public void startMatchSameTeam() {
        // Initialize
        String homeTeam = "newTeam";
        String awayTeam = "newTeam";

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
    }

    @Test
    public void updateScoreNegativeUpdateScore() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        int homeScore = -1;
        int awayScore = 0;

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
        scoreboard.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Test
    public void updateScoreBaseFlow() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        int homeScore = 1;
        int awayScore = 0;

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
        scoreboard.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Test
    public void updateScoreMoreThanOne() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        int homeScore = 4;
        int awayScore = 0;

        // Execute
        scoreboard.startMatch(homeTeam, awayTeam);
        scoreboard.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }

    @Test
    public void finishMatchBaseFlow() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        // Prepare
        scoreboard.startMatch(homeTeam, awayTeam);

        // Execure
        scoreboard.finishMatch(homeTeam, awayTeam);
    }

    @Test
    public void finishMatchSameTeam() {
        // Initialize
        String homeTeam = "homeTeam";
        String awayTeam = "homeTeam";

        // Prepare
        scoreboard.startMatch(homeTeam, awayTeam);

        // Execure
        scoreboard.finishMatch(homeTeam, awayTeam);
    }

    @Test
    public void getSummaryBaseFlow() throws InterruptedException {
        // Initialize
        String homeTeam1 = "homeTeam1";
        String awayTeam1 = "awayTeam1";
        int homeScore1 = 2;
        int awayScore1 = 2;

        String homeTeam2 = "homeTeam2";
        String awayTeam2 = "awayTeam2";
        int homeScore2 = 1;
        int awayScore2 = 3;

        // Prepare
        scoreboard.startMatch(homeTeam1, awayTeam1);
        scoreboard.updateScore(homeTeam1, awayTeam1, homeScore1, awayScore1);
        Thread.sleep(500L);
        scoreboard.startMatch(homeTeam2, awayTeam2);
        scoreboard.updateScore(homeTeam2, awayTeam2, homeScore2, awayScore2);

        // Execute
        List<Match> summary = scoreboard.getSummary();

        // Validate
        assertNotNull(summary);
        assertEquals(2, summary.size());

        assertEquals(homeTeam2, summary.get(0).getHomeTeam());
        assertEquals(homeScore2, summary.get(0).getHomeScore());
        assertEquals(awayTeam2, summary.get(0).getAwayTeam());
        assertEquals(awayScore2, summary.get(0).getAwayScore());

        assertEquals(homeTeam1, summary.get(1).getHomeTeam());
        assertEquals(homeScore1, summary.get(1).getHomeScore());
        assertEquals(awayTeam1, summary.get(1).getAwayTeam());
        assertEquals(awayScore1, summary.get(1).getAwayScore());

        //System.out.println(summary.stream().map(match -> match.toString()).collect(Collectors.joining("\n")));
    }
}