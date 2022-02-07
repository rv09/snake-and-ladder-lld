package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public class LongestTurnStat implements Statistic {

    private int longestTurnSum = 0;
    private int tempTurnSum = 0;
    private Player longestTurnPlayer;

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (tempTurnSum % 6 != 0) {
            tempTurnSum = 0;
        }

        tempTurnSum += lastDiceRoll;

        if (longestTurnSum < tempTurnSum) {
            longestTurnSum = tempTurnSum;
            longestTurnPlayer = lastPlayedPlayer;
        }
    }

    @Override
    public String show() {
        return "Longest turn in game is " + longestTurnSum + " by " + (longestTurnPlayer == null ? "None" : longestTurnPlayer);
    }
}
