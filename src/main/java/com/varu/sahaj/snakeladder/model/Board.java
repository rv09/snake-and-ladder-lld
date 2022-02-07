package com.varu.sahaj.snakeladder.model;

import com.varu.sahaj.snakeladder.exception.InvalidBoardException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int BOARD_SIZE = 10;
    public static final int DEFAULT_START_POSITION = 0;
    public static final int GAME_OVER_POSITION = BOARD_SIZE * BOARD_SIZE;

    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    private Board(Builder builder) throws InvalidBoardException {
        snakes = new HashMap<>();
        ladders = new HashMap<>();

        for (Snake snake: builder.snakes) {
            snakes.put(snake.getStartPosition(), snake.getEndPosition());
        }
        for (Ladder ladder: builder.ladders) {
            ladders.put(ladder.getStartPosition(), ladder.getEndPosition());

            if (snakes.containsKey(ladder.getStartPosition())) {
                throw new InvalidBoardException("Invalid start of snake and ladder at same position " + ladder.getStartPosition());
            }
            if (snakes.containsKey(ladder.getEndPosition()) && ladders.containsKey(snakes.get(ladder.getEndPosition()))) {
                throw new InvalidBoardException("Invalid snake (at " + ladder.getEndPosition() + ") and ladder (at " + ladder.getStartPosition() + "), forming a repeating loop");
            }
        }
    }

    public MoveStrategy getMoveStrategy() {
        return (startPosition, distanceToMove) -> {
            int endPosition = startPosition + distanceToMove;
            if (endPosition > GAME_OVER_POSITION) {
                return startPosition;
            }

            if (snakes.containsKey(endPosition)) {
                return snakes.get(endPosition);
            }

            if (ladders.containsKey(endPosition)) {
                return ladders.get(endPosition);
            }

            return endPosition;
        };
    }

    public boolean isSnake(int position) {
        return snakes.containsKey(position);
    }

    public boolean isLadder(int position) {
        return ladders.containsKey(position);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int position;
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            if (i % 2 == 1) {
                for (int j = BOARD_SIZE - 1; j >= 0 ; j--) {
                    position = (i * BOARD_SIZE) + j + 1;
                    if (snakes.containsKey(position)) {
                        stringBuilder.append(snakes.get(position));
                    } else if (ladders.containsKey(position)) {
                        stringBuilder.append(ladders.get(position));
                    } else {
                        stringBuilder.append(position);
                    }
                    stringBuilder.append(",");
                }
            } else {
                for (int j = 0; j < BOARD_SIZE ; j++) {
                    position = (i * BOARD_SIZE) + j + 1;
                    if (snakes.containsKey(position)) {
                        stringBuilder.append(snakes.get(position));
                    } else if (ladders.containsKey(position)) {
                        stringBuilder.append(ladders.get(position));
                    } else {
                        stringBuilder.append(position);
                    }
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static abstract class Path {
        private int startPosition;
        private int endPosition;

        public Path(int startPosition, int endPosition) {
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public int getEndPosition() {
            return endPosition;
        }

        abstract boolean isValidPath();

        @Override
        public String toString() {
            return startPosition + "->" + endPosition;
        }
    }

    public static class Snake extends Path {

        public Snake(int start, int end) {
            super(start, end);
        }

        @Override
        boolean isValidPath() {
            return getStartPosition() > getEndPosition()
                    && getEndPosition() > 0
                    && getStartPosition() < GAME_OVER_POSITION;
        }
    }

    public static class Ladder extends Path {

        public Ladder(int start, int end) {
            super(start, end);
        }

        @Override
        boolean isValidPath() {
            return getStartPosition() < getEndPosition()
                    && getStartPosition() > 0
                    && getEndPosition() < GAME_OVER_POSITION + 1;
        }
    }

    public static class Builder {

        private List<Snake> snakes;
        private List<Ladder> ladders;

        public Builder() {
            snakes = new ArrayList<>();
            ladders = new ArrayList<>();
        }

        public Builder addSnake(Snake snake) throws InvalidBoardException {
            if (!snake.isValidPath()) throw new InvalidBoardException(snake + " is invalid Snake");
            snakes.add(snake);
            return this;
        }

        public Builder addLadder(Ladder ladder) throws InvalidBoardException {
            if (!ladder.isValidPath()) throw new InvalidBoardException(ladder + " is invalid Ladder");
            ladders.add(ladder);
            return this;
        }

        public Board build() throws InvalidBoardException {
            return new Board(this);
        }
    }
}
