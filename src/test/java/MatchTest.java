import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;

public class MatchTest {

    private Match match;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMatchFlow() {
        // Prepare
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        // Execute
        match = spy(new Match(homeTeam, awayTeam));

        // Validate
        assertNotNull(match);
        assertNotNull(match.getStartTime());
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
        assertEquals(0, match.getTotalScore());
    }

    @Test
    public void testCreateMatchScoreUpdateFlow() {
        // Prepare
        String homeTeam = "homeTeam";
        String awayTeam = "awayTeam";

        int homeScore = 2;
        int awayScore = 3;
        int totalScore = homeScore + awayScore;

        // Execute
        match = spy(new Match(homeTeam, awayTeam));
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);

        // Validate
        assertNotNull(match);
        assertNotNull(match.getStartTime());
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(homeScore, match.getHomeScore());
        assertEquals(awayScore, match.getAwayScore());
        assertEquals(totalScore, match.getTotalScore());
    }

}