import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.logging.Logger;

public class Validator {

    private static final Logger log = Logger.getLogger(Validator.class.getSimpleName());

    public boolean isValidTeamNames(String homeTeam, String awayTeam) {
        if (StringUtils.isBlank(homeTeam)) {
            log.severe("Error: homeTeam name is not valid (null or empty).");
            return false;
        }

        if (StringUtils.isBlank(awayTeam)) {
            log.severe("Error: awayTeam name is not valid (null or empty).");
            return false;
        }

        if (homeTeam.trim().equalsIgnoreCase(awayTeam.trim())) {
            log.severe("Error: homeTeam and awayTeam have the same.");
            return false;
        }

        return true;
    }

    public boolean isTeamPlaying(String homeTeam, String awayTeam, List<Match> matches) {
        if (isPlayingRightNow(homeTeam, matches)) {
            log.severe("Error: homeTeam is already playing.");
            return true;
        }

        if (isPlayingRightNow(awayTeam, matches)) {
            log.severe("Error: awayTeam is already playing.");
            return true;
        }

        return false;
    }

    private boolean isPlayingRightNow(String team, List<Match> matches) {
        return matches.stream().filter(match -> match.getHomeTeam().trim().equalsIgnoreCase(team.trim()) ||
                                                      match.getAwayTeam().trim().equalsIgnoreCase(team.trim())
                                      ).findFirst().isPresent();
    }

    // updating are on bought
    public boolean isValidScore(int homeScore, int awayScore) {
        if (homeScore >= 0 && awayScore >= 0) {
            return true;
        }

        log.severe("Error: score can't be negative.");
        return false;
    }

    // score is updating by 1
    public boolean isValidNewScore(Match match, int newHomeScore, int newAwayScore) {
        return newHomeScore + newAwayScore - match.getTotalScore() == 1;
    }
}
