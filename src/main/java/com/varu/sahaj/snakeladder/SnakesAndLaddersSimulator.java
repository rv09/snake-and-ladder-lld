package com.varu.sahaj.snakeladder;

import com.varu.sahaj.snakeladder.exception.InvalidGameStateException;
import com.varu.sahaj.snakeladder.model.*;
import com.varu.sahaj.snakeladder.statistic.*;

import java.util.Arrays;

public class SnakesAndLaddersSimulator implements Simulator {

    private Game game;

    @Override
    public void startSimulation(Game game) {
        this.game = game;
        this.game.captureStatistics(Arrays.asList(
                new RollsToWinStat(),
                new ClimbStat(),
                new SlideStat(),
                new BiggestClimbStat(),
                new BiggestSlideStat(),
                new LongestTurnStat(),
                new UnluckyRollsStat(),
                new LuckyRollsStat()
        ));

        System.out.println("-----------SIMULATION START----------");
        System.out.println(game.getBoard());
    }

    @Override
    public void stopSimulation() {
        this.game = null;

        System.out.println("-----------SIMULATION STOP-----------");
    }

    @Override
    public void autoPlayMoves(int moveCount) throws InvalidGameStateException {
        System.out.println("-----------AUTO PLAY ["+ moveCount+" Moves]-----------");

        for (int i = 0; i < moveCount; i++) {
            if (game.isFinished()) {
                System.out.println("------------WINNER------------");
                System.out.println(game.getWinner());
                return;
            }

            game.playNextMove();
        }
    }

    @Override
    public void autoPlayTillEnd() throws InvalidGameStateException {
        System.out.println("-----------AUTO PLAY-----------");

        while (!game.isFinished()) {
            game.playNextMove();
        }

        System.out.println("------------WINNER------------");
        System.out.println(game.getWinner());
    }

    @Override
    public void showSimulationStatistics() {
        System.out.println("\n--------------STATISTICS--------------");
        game.showGameStatistics();
    }
}
