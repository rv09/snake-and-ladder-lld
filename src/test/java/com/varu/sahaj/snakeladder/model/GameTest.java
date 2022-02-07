package com.varu.sahaj.snakeladder.model;

import com.varu.sahaj.snakeladder.exception.InvalidBoardException;
import com.varu.sahaj.snakeladder.exception.InvalidGameStateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private static Board plainBoard;

    @BeforeAll
    static void beforeAll() throws InvalidBoardException {
        plainBoard = new Board.Builder().build();
    }

    @Test
    void testGetWinner_givenGameNotFinished_thenThrowException() {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token(plainBoard.getMoveStrategy()))
        ));

        assertThrows(InvalidGameStateException.class, () -> game.getWinner());
    }

    @Test
    void testGetWinner_givenGameFinished_thenReturnPlayer() {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token((startPosition, distanceToMove) -> 100))
        ));

        assertDoesNotThrow(() -> game.playNextMove());
        Player winner = null;
        try {
            winner = game.getWinner();
        } catch (InvalidGameStateException e) {
            assert false;
        }
        assertNotNull(winner);
        assertEquals("testPlayer", winner.getName());
    }

    @Test
    void testIsFinished_givenGameNotStarted_thenReturnFalse() {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token((startPosition, distanceToMove) -> 100))
        ));

        assertFalse(game.isFinished());
    }

    @Test
    void testIsFinished_givenGameStartedButNotFinished_thenReturnFalse() throws InvalidGameStateException {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token((startPosition, distanceToMove) -> 50))
        ));

        game.playNextMove();
        assertFalse(game.isFinished());
    }

    @Test
    void testIsFinished_givenGameFinished_thenReturnTrue() throws InvalidGameStateException {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token((startPosition, distanceToMove) -> 100))
        ));

        game.playNextMove();
        assertTrue(game.isFinished());
    }

    @Test
    void testPlayNextMove_givenGameFinished_thenThrowException() {
        Game game = new Game(plainBoard, new Dice(), Arrays.asList(
                new Player("testPlayer", new Token((startPosition, distanceToMove) -> 100))
        ));

        try {
            game.playNextMove();
        } catch (InvalidGameStateException e) {
            assert false;
        }

        assertThrows(InvalidGameStateException.class, () -> game.playNextMove());
    }

    @Test
    void testPlayNextMove_givenRollGivesOne6_thenTokenShouldMovesTwice() throws InvalidGameStateException {
        DiceRollStrategy diceRollStrategy = new DiceRollStrategy() {
            int count = 0;
            @Override
            public int executeRoll(int[] numbers, int lastNumber) {
                if (count == 0) {
                    count ++;
                    return 6;
                } else {
                    return 2;
                }
            }
        };
        Game game = new Game(plainBoard, new Dice(diceRollStrategy), Arrays.asList(
                new Player("testPlayer", new Token(plainBoard.getMoveStrategy()))
        ));

        game.playNextMove();

        assertEquals(8, game.getPlayers().get(0).getToken().getCurrentPosition());
    }
}