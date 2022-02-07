package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public class LuckyRollsStat implements Statistic {

    private int count = 0;
    private int turnSum = 0;

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        int position = lastMovedToken.getCurrentPosition() - lastMovedDistance - lastDiceRoll;

        //found ladder
        if (game.getBoard().isLadder(position)) {
            count ++;
        }

        //win with first roll
        if (turnSum % 6 != 0) {
            turnSum = 0;
        }
        turnSum += lastDiceRoll;
        if (lastMovedToken.isReachedWinningPosition() && turnSum == lastDiceRoll) {
            count ++;
        }

        //misses snake by 1 or 2
        if (game.getBoard().isSnake(position - 2)
                || game.getBoard().isSnake(position - 1)
                || game.getBoard().isSnake(position + 1)
                || game.getBoard().isSnake(position + 2)) {
            count ++;
        }
    }

    @Override
    public String show() {
        return "Lucky rolls in game are " + count;
    }
}
