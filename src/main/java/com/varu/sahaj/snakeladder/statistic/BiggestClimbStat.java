package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public class BiggestClimbStat implements Statistic {

    private Player player;
    private int biggestClimb = 0;

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (biggestClimb < lastMovedDistance - lastDiceRoll) {
            biggestClimb = lastMovedDistance - lastDiceRoll;
            player = lastPlayedPlayer;
        }
    }

    @Override
    public String show() {
        return "Biggest Climb in game is " + biggestClimb + " by " + (player == null ? "None" : player);
    }
}
