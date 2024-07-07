package org.pepe.games.snakeandlader;

import org.pepe.games.common.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SnakeAndLadder implements Game {
    private static final String LS = System.lineSeparator();
    private final PlayersPool playersPool;
    private final int[] playBoard = new int[100];
    private final InputHandler inputHandler;
    private final GameState gameState;
    private final Dice dice;
    private final Map<Player, Integer> positions = new HashMap<>();

    {
        IntStream.iterate(0, i -> i < 100, i -> i + 1).forEach(i -> playBoard[i] = 0);
        playBoard[10] = 10;
        playBoard[20] = -20;
        playBoard[30] = 5;
        playBoard[40] = -15;
        playBoard[50] = 50;
        playBoard[51] = -51;
        playBoard[60] = 10;
        playBoard[70] = 10;
        playBoard[80] = -10;
        playBoard[90] = -90;
        playBoard[99] = -10;
    }

    public SnakeAndLadder(PlayersPool playersPool, InputHandler inputHandler, GameState gameState, Dice dice) {
        this.playersPool = playersPool;
        this.inputHandler = inputHandler;
        this.gameState = gameState;
        this.dice = dice;
        init();
    }

    private void init() {
        for (Player player : playersPool.getPlayers()) {
            positions.put(player, 0);
        }
    }

    @Override
    public void gameLoop() {
        gameState.start();
        while (gameState.getState() == GameState.State.ONGOING) {
            Player currentPlayer = playersPool.current();
            int currentPosition = positions.get(currentPlayer);
            System.out.printf("%s's turn, currently at %s. Press 1 to roll the dice. ", currentPlayer.name(), currentPosition);
            inputHandler.handleInput(inputHandler.nextInput());

            if (inputWasInvalidAndGameNeedRestart())
                continue;

            int diceValue = rollADice();

            if (playerCanMoveToPosition(currentPlayer, diceValue))
                currentPosition = calculateNextPosition(currentPlayer, diceValue);
            else
                System.out.printf("%s%s can't move!%s", LS, currentPlayer.name(), LS);

            updatePosition(currentPlayer, currentPosition);
            evaluateStateOfTheGame(currentPlayer);

            playersPool.next();
            System.out.println();
        }
    }

    private void updatePosition(Player currentPlayer, int currentPosition) {
        System.out.printf("%s's current position is %s. %s", currentPlayer.name(), currentPosition, LS);
        positions.put(currentPlayer, currentPosition);
    }

    private void evaluateStateOfTheGame(Player currentPlayer) {
        if (WinConditionUtil.moveWins(currentPlayer, positions)) {
            gameState.win(playersPool.current());
        }
    }

    private boolean playerCanMoveToPosition(Player currentPlayer, int diceValue) {
        int currentPosition = positions.get(currentPlayer);
        boolean above = currentPosition + diceValue <= 100;
        boolean cannotStart = currentPosition != 0 || diceValue == 1;
        return above && cannotStart;
    }

    private int calculateNextPosition(Player currentPlayer, int diceValue) {
        int currentPosition = positions.get(currentPlayer);
        currentPosition += diceValue;
        int boardValue = playBoard[currentPosition];
        if (boardValue < 0)
            System.out.printf("%sUnfortunately player %s is going back by %s.", LS, currentPlayer.name(), boardValue * -1);
        else if (boardValue > 0)
            System.out.printf("%sGreat luck! player %s is going forward by %s.", LS, currentPlayer.name(), boardValue);
        currentPosition += boardValue;
        System.out.println();
        return currentPosition;
    }

    private int rollADice() {
        int diceValue = dice.roll();
        System.out.printf("A dice shows: %s.", diceValue);
        return diceValue;
    }

    private boolean inputWasInvalidAndGameNeedRestart() {
        if (gameState.getState() == GameState.State.INVALID) {
            gameState.redo();
            return true;
        }
        return false;
    }
}

class WinConditionUtil {
    public static boolean moveWins(Player player, Map<Player, Integer> positions) {
        return positions.get(player) >= 100;
    }
}
