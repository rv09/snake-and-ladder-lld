package com.varu.sahaj.snakeladder.model;

import com.varu.sahaj.snakeladder.exception.InvalidBoardException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardBuilderTest {

    @Test
    public void testBoardBuilder_givenNoSnakesNoLadders_thenCreateBoard() {
        assertDoesNotThrow(() -> {
            new Board.Builder().build();
        });
    }

    @Test
    public void testBoardBuilder_givenValidSnakesNoLadders_thenCreateBoard() {
        assertDoesNotThrow(() -> {
            new Board.Builder().addSnake(new Board.Snake(27,5)).build();
        });
    }

    @Test
    public void testBoardBuilder_givenNoSnakesValidLadders_thenCreateBoard() {
        assertDoesNotThrow(() -> {
            new Board.Builder().addLadder(new Board.Ladder(4, 25)).build();
        });
    }

    @Test
    public void testBoardBuilder_givenValidSnakesValidLadders_thenCreateBoard() {
        assertDoesNotThrow(() -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(27, 5))
                    .addLadder(new Board.Ladder(4, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenInvalidSnakesNoLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(2, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenNoSnakesInvalidLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addLadder(new Board.Ladder(25, 2))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenNoLengthSnakesNoLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(25, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenNoSnakesNoLengthLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addLadder(new Board.Ladder(25, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenOutRangeSnakesNoLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(105, 25))
                    .build();
        });

        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(25, 0))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenNoSnakesOutRangeLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addLadder(new Board.Ladder(25, 105))
                    .build();
        });

        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addLadder(new Board.Ladder(0, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenLoopedSnakesLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(25, 5))
                    .addLadder(new Board.Ladder(5, 25))
                    .build();
        });
    }

    @Test
    public void testBoardBuilder_givenSameStartSnakesLadders_thenDoNotCreateBoard() {
        assertThrows(InvalidBoardException.class, () -> {
            new Board.Builder()
                    .addSnake(new Board.Snake(25, 5))
                    .addLadder(new Board.Ladder(25, 35))
                    .build();
        });
    }
}