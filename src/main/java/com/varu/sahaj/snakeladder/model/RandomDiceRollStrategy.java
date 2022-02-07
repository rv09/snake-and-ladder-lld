package com.varu.sahaj.snakeladder.model;

import java.util.Random;

public class RandomDiceRollStrategy implements DiceRollStrategy {
    @Override
    public int executeRoll(int[] numbers, int lastNumber) {
        return numbers[new Random().nextInt(numbers.length)]; //returns 0 to (numbers.length - 1)
    }
}
