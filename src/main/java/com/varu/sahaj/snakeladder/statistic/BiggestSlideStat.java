package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public class BiggestSlideStat implements Statistic {

    private Player player;
    private int biggestSlide = 0;

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (biggestSlide < (-1 * lastMovedDistance) - lastDiceRoll) {
            biggestSlide = (-1 * lastMovedDistance) - lastDiceRoll;
            player = lastPlayedPlayer;
        }
    }

    @Override
    public String show() {
        return "Biggest Slide in game is " + biggestSlide + " by " + (player == null ? "None" : player);
    }
}
