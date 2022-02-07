package com.varu.sahaj.snakeladder.model;

import com.varu.sahaj.snakeladder.exception.InvalidGameStateException;
import com.varu.sahaj.snakeladder.statistic.Statistic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private List<Statistic> statisticList;
    private GameState state;

    public Game(Board board, Dice dice, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(players);
        this.state = GameState.YET_TO_START;
        this.statisticList = new ArrayList<>();
    }

    public void captureStatistics(List<Statistic> list) {
        statisticList.addAll(list);
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players); //todo: return deep copy
    }

    public Player getWinner() throws InvalidGameStateException {
        if (!isFinished()) {
            throw new InvalidGameStateException("Can't tell winner, game is not finished");
        }

        return players.peek(); //todo: return deep copy
    }

    public boolean isFinished() {
        return state == GameState.FINISHED;
    }

    public void playNextMove() throws InvalidGameStateException {
        if (state == GameState.FINISHED) {
            throw new InvalidGameStateException("Can't play further, game is finished");
        } else if (state == GameState.YET_TO_START) {
            state = GameState.RUNNING;
        }

        Player player = players.peek();
        if (player == null) {
            throw new RuntimeException("No players to play the game");
        }

        int previousTokenPosition = player.getToken().getCurrentPosition();
        int diceRollNumber = dice.roll();
        player.getToken().move(diceRollNumber);
        int distanceMovedByToken = player.getToken().getCurrentPosition() - previousTokenPosition;

        System.out.println(player.getName() + " ->[+"+ diceRollNumber + "]-> " + player.getToken().getCurrentPosition() + (diceRollNumber == distanceMovedByToken ? "" : (diceRollNumber < distanceMovedByToken ? " \uD83E\uDE9C" : (diceRollNumber + previousTokenPosition > 100 ? " ✋" : " \uD83D\uDC0D"))));

        captureStatistics(diceRollNumber, distanceMovedByToken, player.getToken(), player);

        if (player.getToken().isReachedWinningPosition()) {
            state = GameState.FINISHED;
            return;
        }

        if (dice.receivedBonusRoll()) {
            playNextMove();
        } else {
            players.add(players.remove());
        }
    }

    private void captureStatistics(int lastDiceRoll, int lastMovedDistance, Token lastMovedToken, Player lastPlayedPlayer) {
        for (Statistic stat : statisticList) {
            stat.calculateStat(this, lastDiceRoll, lastMovedDistance, lastMovedToken, lastPlayedPlayer);
        }
    }

    public void showGameStatistics() {
        for (Statistic stat: statisticList) {
            System.out.println(stat.show());
        }
    }
}
