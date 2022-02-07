package com.varu.sahaj.snakeladder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testMove_givenMoveStrategyFindNewPosition_thenCurrentPositionShouldChange() {
        Token token = new Token(0, (startPosition, distanceToMove) -> distanceToMove);
        token.move(5);
        assertEquals(5, token.getCurrentPosition());
    }

    @Test
    void testMove_givenMoveStrategyFindSamePosition_thenCurrentPositionShouldNotChange() {
        Token token = new Token(0, (startPosition, distanceToMove) -> startPosition);
        token.move(5);
        assertEquals(0, token.getCurrentPosition());
    }

    @Test
    void testIsReachedWinningPosition_givenMoveStrategyFind100AsEnd_thenReturnTrue() {
        Token token = new Token(0, (startPosition, distanceToMove) -> distanceToMove);
        token.move(100);
        assertTrue(token.isReachedWinningPosition());
    }

    @Test
    void testIsReachedWinningPosition_givenMoveStrategyFindNon100AsEnd_thenReturnFalse() {
        Token token = new Token(0, (startPosition, distanceToMove) -> distanceToMove);
        token.move(50);
        assertFalse(token.isReachedWinningPosition());
    }

    @Test
    void testIsReachedWinningPosition_givenNoMove_thenReturnFalse() {
        Token token = new Token(0, (startPosition, distanceToMove) -> distanceToMove);
        assertFalse(token.isReachedWinningPosition());
    }
}