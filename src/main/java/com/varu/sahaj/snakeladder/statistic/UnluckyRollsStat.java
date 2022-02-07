package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public class UnluckyRollsStat implements Statistic {

    private int count = 0;

    @Override
    public void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        if (game.getBoard().isSnake(lastMovedToken.getCurrentPosition() - lastMovedDistance - lastDiceRoll)) {
            count ++;
        }
    }

    @Override
    public String show() {
        return "Unlucky rolls in game are " + count;
    }
}
