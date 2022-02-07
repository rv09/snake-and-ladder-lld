package com.varu.sahaj.snakeladder.model;

public class Token {

    private int currentPosition;
    private final MoveStrategy moveStrategy;

    public Token(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        this.currentPosition = Board.DEFAULT_START_POSITION;
    }

    public Token(int startPosition, MoveStrategy moveStrategy) {
        this.currentPosition = startPosition;
        this.moveStrategy = moveStrategy;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void move(int distance) {
        currentPosition = moveStrategy.findEndPosition(currentPosition, distance);
    }

    public boolean isReachedWinningPosition() {
        return currentPosition == Board.GAME_OVER_POSITION;
    }
}
