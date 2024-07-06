package org.pepe.games.tictactoe;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.pepe.games.common.*;
import org.pepe.games.common.board.Board;
import org.pepe.games.common.board.BoardGameInput;

import java.util.stream.IntStream;

@RequiredArgsConstructor
public class TicTacToe implements Game {
    private final InputHandler inputHandler;
    private final GameState gameState;
    private final Board board;
    private final PlayersPool playersPool;

    private static int getRow(String input) {
        return input.charAt(0) - 48;
    }

    private static int getColumn(String input) {
        return input.charAt(1) - 48;
    }

    public void gameLoop() {
        gameState.start();
        printBoard();
        while (gameState.getState() == GameState.State.ONGOING) {
            Player currentPlayer = playersPool.current();

            System.out.printf("It is %s's turn.", currentPlayer.name());
            String input = inputHandler.nextInput();

            inputHandler.handleInput(input);
            if (gameState.getState() == GameState.State.DONE) {
                break;
            }
            if (inputWasInvalidAndGameNeedRestart()) {
                continue;
            }

            BoardGameInput lastInputPlayed = playGameWithInput(input, currentPlayer);

            if (inputWasInvalidAndGameNeedRestart()) {
                continue;
            }
            printBoard();
            evaluateStateOfTheGame(lastInputPlayed);
            playersPool.next();
        }
    }

    private void evaluateStateOfTheGame(BoardGameInput lastInputPlayed) {
        if (WinConditionUtil.moveWins(lastInputPlayed, board)) {
            gameState.win(playersPool.current());
        } else if (board.hasNoSpaceLeft()) {
            gameState.draw();
        }
    }

    private BoardGameInput playGameWithInput(String input, Player currentPlayer) {
        BoardGameInput boardGameInput = new BoardGameInput(getRow(input), getColumn(input), currentPlayer.symbol());
        if (!board.playField(boardGameInput)) {
            System.out.println("Field already taken!");
            gameState.invalidate();
        } else
            System.out.printf("Player %s playing %s at row %s and column %s%n", currentPlayer.name(), currentPlayer.symbol(), boardGameInput.row(), boardGameInput.column());

        return boardGameInput;
    }

    private boolean inputWasInvalidAndGameNeedRestart() {
        if (gameState.getState() == GameState.State.INVALID) {
            gameState.redo();
            return true;
        }
        return false;
    }

    private void printBoard() {
        System.out.println(board);
    }
}


@NoArgsConstructor(access = AccessLevel.PRIVATE)
class WinConditionUtil {
    public static boolean moveWins(BoardGameInput boardGameInput, Board board) {
        boolean winRow = isWinRow(boardGameInput, board);
        boolean winColumn = isWinColumn(boardGameInput, board);
        boolean winDiagonalPos = isWinDiagonalPos(boardGameInput, board);
        boolean winDiagonalNeg = isWinDiagonalNeg(boardGameInput, board);
        return winRow || winColumn || winDiagonalPos || winDiagonalNeg;
    }

    private static boolean isWinRow(BoardGameInput boardGameInput, Board board) {
        return IntStream.range(0, board.getSize()).allMatch(i -> board.getFieldValue(boardGameInput.row(), i).equals(boardGameInput.symbol()));
    }

    private static boolean isWinColumn(BoardGameInput boardGameInput, Board board) {
        return IntStream.range(0, board.getSize()).allMatch(i -> board.getFieldValue(i, boardGameInput.column()).equals(boardGameInput.symbol()));
    }

    private static boolean isWinDiagonalNeg(BoardGameInput boardGameInput, Board board) {
        return IntStream.iterate(board.getSize() - 1, i -> i >= 0, i -> i - 1)
                .allMatch(i -> board.getFieldValue(board.getSize() - i - 1, i).equals(boardGameInput.symbol()));
    }

    private static boolean isWinDiagonalPos(BoardGameInput boardGameInput, Board board) {
        return IntStream.range(0, board.getSize()).allMatch(i -> board.getFieldValue(i, i).equals(boardGameInput.symbol()));
    }
}
