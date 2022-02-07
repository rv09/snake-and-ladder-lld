package com.varu.sahaj.snakeladder;

import com.varu.sahaj.snakeladder.exception.InvalidGameStateException;
import com.varu.sahaj.snakeladder.model.Game;

public interface Simulator {

    void startSimulation(Game game);

    void stopSimulation();

    void autoPlayMoves(int moveCount) throws InvalidGameStateException;

    void autoPlayTillEnd() throws InvalidGameStateException;

    void showSimulationStatistics();
}
