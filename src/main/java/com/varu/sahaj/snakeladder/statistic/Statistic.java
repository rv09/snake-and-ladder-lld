package com.varu.sahaj.snakeladder.statistic;

import com.varu.sahaj.snakeladder.model.Game;
import com.varu.sahaj.snakeladder.model.Player;
import com.varu.sahaj.snakeladder.model.Token;

public interface Statistic {

    void calculateStat(Game game, int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer);

    String show();
}
