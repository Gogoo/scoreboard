import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Scoreboard {

    private static final Logger log = Logger.getLogger(Scoreboard.class.getSimpleName());

    private final List<Match> matches;
    private final Validator validator;

    public Scoreboard() {
        this.matches = new ArrayList<>();
        this.validator = new Validator();
    }

    public void startMatch(String homeTeam, String awayTeam) {
        if(validator.isValidTeamNames(homeTeam, awayTeam) && !validator.isTeamPlaying(homeTeam, awayTeam, matches)) {
            log.info("Match started successfully.");
            matches.add(new Match(homeTeam, awayTeam));
        }
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        if(validator.isValidScore(homeScore, awayScore)) {
            Match match = findMatch(homeTeam, awayTeam);
            if (match != null) {
                match.setHomeScore(homeScore);
                match.setAwayScore(awayScore);
                log.info("Match score updated successfully.");
            } else {
                log.info("Match not found.");
            }
        } else {
            log.info("Score update failed. Score can't be negative.");
        }
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        if (match != null) {
            matches.remove(match);
            log.info("Match finished successfully.");
        } else {
            log.severe("Error match not found.");
        }
    }

    public List<Match> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches);
        sortedMatches.sort((m1, m2) -> {
            int scoreComparison = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            return m2.getStartTime().compareTo(m1.getStartTime());
        });

        return sortedMatches;
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        if(validator.isValidTeamNames(homeTeam, awayTeam)) {
            return matches.stream()
                    .filter(match -> match.getHomeTeam().trim().equalsIgnoreCase(homeTeam.trim()) &&
                            match.getAwayTeam().trim().equalsIgnoreCase(awayTeam.trim()))
                    .findFirst().orElse(null);
        }

        return null;
    }
}
