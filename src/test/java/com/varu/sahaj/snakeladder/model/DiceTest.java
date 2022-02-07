package com.varu.sahaj.snakeladder.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private static Dice dice;

    @BeforeAll
    static void beforeAll() {
        dice = new Dice();
    }

    @Test
    void testRoll_givenRandomNumberGenerator_thenDiceNumberIsWithinRange() {
        dice.roll();
        assertFalse(dice.getCurrentNumber() < 0);
        assertFalse(dice.getCurrentNumber() > 6);
    }

    @Test
    void testRoll_givenRandomNumberGenerator_thenDiceNumberMatchesToRollReturn() {
        int returnValue = dice.roll();
        assertEquals(dice.getCurrentNumber(), returnValue);
    }

    @Test
    void testReceivedBonusRoll_givenRollReturn6_thenReturnTrue() {
        Dice dice = new Dice(((numbers, lastNumber) -> 6));
        dice.roll();
        assertTrue(dice.receivedBonusRoll());
    }

    @Test
    void testReceivedBonusRoll_givenRollReturnNon6_thenReturnFalse() {
        Dice dice = new Dice(((numbers, lastNumber) -> 5));
        dice.roll();
        assertFalse(dice.receivedBonusRoll());
    }
}