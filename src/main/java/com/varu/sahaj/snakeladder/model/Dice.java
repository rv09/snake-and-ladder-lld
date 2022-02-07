package com.varu.sahaj.snakeladder.model;

public class Dice {

    private static final int DEFAULT_INITIAL_NUMBER = 1;
    public static DiceRollStrategy DEFAULT_DICE_ROLL_STRATEGY = new RandomDiceRollStrategy();

    private final int[] numbers = new int[] {1, 2, 3, 4, 5, 6};
    private int currentNumber;
    private DiceRollStrategy diceRollStrategy;

    public Dice() {
        this.diceRollStrategy = DEFAULT_DICE_ROLL_STRATEGY;
        this.currentNumber = DEFAULT_INITIAL_NUMBER;
    }
    public Dice(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
        this.currentNumber = DEFAULT_INITIAL_NUMBER;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public int roll() {
        currentNumber = diceRollStrategy.executeRoll(numbers, currentNumber);
        return currentNumber;
    }

    public boolean receivedBonusRoll() {
        return currentNumber == 6;
    }
}
