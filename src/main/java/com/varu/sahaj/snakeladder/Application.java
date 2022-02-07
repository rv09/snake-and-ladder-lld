package com.varu.sahaj.snakeladder;

import com.varu.sahaj.snakeladder.exception.InvalidBoardException;
import com.varu.sahaj.snakeladder.exception.InvalidGameStateException;
import com.varu.sahaj.snakeladder.model.*;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        Simulator simulator = new SnakesAndLaddersSimulator();
        Board board;
        try {
            board = new Board.Builder()
                    .addLadder(new Board.Ladder(4, 25))
                    .addLadder(new Board.Ladder(13, 46))
                    .addLadder(new Board.Ladder(33, 49))
                    .addLadder(new Board.Ladder(42, 63))
                    .addLadder(new Board.Ladder(50, 69))
                    .addLadder(new Board.Ladder(62, 81))
                    .addLadder(new Board.Ladder(74, 92))
                    .addSnake(new Board.Snake(27, 5))
                    .addSnake(new Board.Snake(40, 3))
                    .addSnake(new Board.Snake(43, 18))
                    .addSnake(new Board.Snake(54, 31))
                    .addSnake(new Board.Snake(66, 45))
                    .addSnake(new Board.Snake(76, 58))
                    .addSnake(new Board.Snake(89, 53))
                    .addSnake(new Board.Snake(99, 41))
                    .build();
        } catch (InvalidBoardException e) {
            System.out.println(e.getMessage());
            return;
        }
        Dice dice = new Dice();
        Game game = new Game(board, dice, Arrays.asList(
                new Player("Foo", new Token(board.getMoveStrategy())),
                new Player("Bar", new Token(board.getMoveStrategy()))
        ));

        simulator.startSimulation(game);
        simulator.showSimulationStatistics();

        try {
            simulator.autoPlayMoves(10);
            simulator.showSimulationStatistics();
            simulator.autoPlayTillEnd();
            simulator.showSimulationStatistics();
        } catch (InvalidGameStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
