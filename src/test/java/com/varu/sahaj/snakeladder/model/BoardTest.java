package com.varu.sahaj.snakeladder.model;

import com.varu.sahaj.snakeladder.exception.InvalidBoardException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    static Board board;

    @BeforeAll
    static void beforeAll() throws InvalidBoardException {
        board = new Board.Builder()
                .addSnake(new Board.Snake(12, 5))
                .addLadder(new Board.Ladder(7, 25))
                .build();
    }

    @Test
    void testMoveStrategy_givenValidStartValidDistance_thenFindValidEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(1, 2);
         assertEquals(1+2, endPosition);
    }

    @Test
    void testMoveStrategy_givenSnakeStartValidDistance_thenFindValidEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(10, 2);
         assertEquals(5, endPosition);
    }

    @Test
    void testMoveStrategy_givenLadderStartValidDistance_thenFindValidEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(5, 2);
         assertEquals(25, endPosition);
    }

    @Test
    void testMoveStrategy_givenEndStartMoreThanTargetDistance_thenDoNotMoveEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(98, 4);
         assertEquals(98, endPosition);
    }

    @Test
    void testMoveStrategy_givenEndStartMoreThanTargetDistance6_thenDoNotMoveEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(98, 6);
         assertEquals(98, endPosition);
    }

    @Test
    void testMoveStrategy_givenStartAsLastPositionValidDistance_thenDoNotMoveEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(100, 2);
         assertEquals(100, endPosition);
    }

    @Test
    void testMoveStrategy_givenInvalidStartValidDistance_thenDoNotMoveEnd() {
        int endPosition = board.getMoveStrategy().findEndPosition(101, 2);
        assertEquals(101, endPosition);
    }

    @Test
    void testIsSnake_givenValidPosition_thenReturnTrue() {
        boolean isSnake = board.isSnake(12);
        assertTrue(isSnake);
    }

    @Test
    void testIsSnake_givenInvalidPosition_thenReturnFalse() {
        boolean isSnake = board.isSnake(7);
        assertFalse(isSnake);
    }

    @Test
    void testIsLadder_givenValidPosition_thenReturnTrue() {
        boolean isLadder = board.isLadder(7);
        assertTrue(isLadder);
    }

    @Test
    void testIsLadder_givenInvalidPosition_thenReturnFalse() {
        boolean isLadder = board.isLadder(12);
        assertFalse(isLadder);
    }
}