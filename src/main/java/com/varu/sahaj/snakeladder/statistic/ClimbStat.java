package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ClimbStat implements Statistic {

    private Set<Integer> climbs = new TreeSet<>();

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (lastDiceRoll >= lastMovedDistance) {
            return;
        }

        climbs.add(lastMovedDistance - lastDiceRoll);
    }

    @Override
    public String show() {
        int min = 0, avg = 0, max = 0;
        if (!climbs.isEmpty()) {
            List<Integer> sortedClimbs = new ArrayList<>(climbs);

            min = sortedClimbs.get(0);
            max = sortedClimbs.get(sortedClimbs.size() - 1);

            for (int i = 0; i < sortedClimbs.size(); i++) {
                avg += sortedClimbs.get(i);
            }
            avg = avg / sortedClimbs.size();
        }

        return "Min amount of climb in game is " + min + "\n"
                + "Avg amount of climb in game is " + avg + "\n"
                + "Max amount of climb in game is " + max;
    }
}
