package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SlideStat implements Statistic {

    private Set<Integer> slides = new TreeSet<>();

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (lastDiceRoll > lastMovedDistance && lastMovedDistance != 0) {
            slides.add((-1 * lastMovedDistance) - lastDiceRoll);
        }
    }

    @Override
    public String show() {
        int min = 0, avg = 0, max = 0;
        if (!slides.isEmpty()) {
            List<Integer> sortedSlides = new ArrayList<>(slides);

            min = sortedSlides.get(0);
            max = sortedSlides.get(sortedSlides.size() - 1);

            for (int i = 0; i < sortedSlides.size(); i++) {
                avg += sortedSlides.get(i);
            }
            avg = avg / sortedSlides.size();
        }

        return "Min amount of slide in game is " + min + "\n"
                + "Avg amount of slide in game is " + avg + "\n"
                + "Max amount of slide in game is " + max;
    }
}
