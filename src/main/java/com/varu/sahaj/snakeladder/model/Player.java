package com.varu.sahaj.snakeladder.model;

public class Player {

    private String name;
    private Token token;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    public boolean isWinner() {
        return getToken().isReachedWinningPosition();
    }

    @Override
    public String toString() {
        return name;
    }
}
