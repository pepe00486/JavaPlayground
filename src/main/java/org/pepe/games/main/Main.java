package org.pepe.games.main;

import org.pepe.games.common.*;
import org.pepe.games.common.board.Board;
import org.pepe.games.common.board.BoardValidator;
import org.pepe.games.tictactoe.TicTacToe;

import java.util.Scanner;

public class Main {
    public static final String EMPTY_FIELD = "#";
    private static final int size = 3;

    public static void main(String[] args) {
        Player[] players = {new Player("Chhaya", "X"), new Player("Mateusz", "Y")};

        Board board = new Board(size);
        board.initializeBoard(EMPTY_FIELD);

        GameState gameState = new GameState();
        InputValidator validator = new BoardValidator(board);
        InputHandler inputHandler = new InputHandler(gameState, validator, new Scanner(System.in));
        Game game = new TicTacToe(inputHandler, gameState, board, new PlayersPool(players));
        game.gameLoop();
    }
}
