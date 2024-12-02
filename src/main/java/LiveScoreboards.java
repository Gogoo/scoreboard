import java.util.List;
import java.util.stream.Collectors;

public class LiveScoreboards {

    public static void main(String[] args) throws InterruptedException {
        Scoreboard scoreboard = new Scoreboard();

        // Start matches
        scoreboard.startMatch("Mexico", "Canada");
        Thread.sleep(500L);    // Added time sleep because summary
        scoreboard.startMatch("Spain", "Brazil");
        Thread.sleep(500L);    // Added time sleep because summary
        scoreboard.startMatch("Germany", "France");
        Thread.sleep(500L);    // Added time sleep because summary
        scoreboard.startMatch("Uruguay", "Italy");
        Thread.sleep(500L);    // Added time sleep because summary
        scoreboard.startMatch("Argentina", "Australia");

        // Update scores
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        // Summary
        List<Match> matches = scoreboard.getSummary();
        System.out.println(matches.stream().map(match -> match.toString()).collect(Collectors.joining("\n")));

    }
}
