package competitiveprogramming.arrays;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A tournament is announced! Each team plays other team exactly once.
 * Each match: [home, guest]
 * Each score: 0 = guest wins, 1 = home team wins
 * where, ith score ~ ith match
 * <p>
 * Input :
 * Matches: [
 * ["HTML", "C#"],
 * ["C#", "Python"],
 * ["Python", "HTML"]
 * ]
 * <p>
 * Scores: [0, 0, 1]
 * <p>
 * Output: "Python"
 *
 * @See
 * <a href = "https://www.algoexpert.io/questions/Tournament%20Winner">AlgoExpert Link</a>
 */
public class P08TournamentWinner {

    public String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {

        HashMap<String, Integer> teamToScoreMap = new HashMap<>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) == 0) {
                incrementScore(competitions.get(i).get(1), teamToScoreMap);
            } else {
                incrementScore(competitions.get(i).get(0), teamToScoreMap);
            }
        }
        return getMaxScoreTeam(teamToScoreMap);
    }

    private void incrementScore(String team, HashMap<String, Integer> teamToScoreMap) {
        Integer scoreBefore = teamToScoreMap.get(team);
        if (scoreBefore == null) {
            // Add team -> 1
            teamToScoreMap.put(team, 1);
        } else {
            // Increment score
            teamToScoreMap.replace(team, scoreBefore + 1);
        }
    }

    private String getMaxScoreTeam(HashMap<String, Integer> teamToScoreMap) {
        Integer maxScore = 0;
        String winner = "";
        for (String team : teamToScoreMap.keySet()) {
            if (teamToScoreMap.get(team) > maxScore) {
                maxScore = teamToScoreMap.get(team);
                winner = team;
            }
        }
        return winner;
    }
}
