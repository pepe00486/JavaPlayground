package org.pepe.games.main;

import org.pepe.games.common.*;
import org.pepe.games.common.board.Board;
import org.pepe.games.common.board.BoardValidator;
import org.pepe.games.snakeandlader.SnakeAndLadder;
import org.pepe.games.snakeandlader.SnakeBoardValidator;
import org.pepe.games.tictactoe.TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        playTicTacToe();
        playSnakeAndLadder();
    }

    private static void playSnakeAndLadder() {
        Player[] players = {new Player("Chhaya", "X"), new Player("Mateusz", "Y"),  new Player("Dudus", "Z")};
        GameState gameState = new GameState();
        InputValidator validator = new SnakeBoardValidator();
        InputHandler inputHandler = new InputHandler(gameState, validator, new Scanner(System.in));
        Dice dice = new Dice(1,6);
        Game game = new SnakeAndLadder(new PlayersPool(players), inputHandler, gameState, dice);
        game.gameLoop();
    }

    private static void playTicTacToe() {
        Player[] players = {new Player("Chhaya", "X"), new Player("Mateusz", "Y")};

        Board board = new Board(3);
        board.initializeBoard("#");

        GameState gameState = new GameState();
        InputValidator validator = new BoardValidator(board);
        InputHandler inputHandler = new InputHandler(gameState, validator, new Scanner(System.in));
        Game game = new TicTacToe(inputHandler, gameState, board, new PlayersPool(players));
        game.gameLoop();
    }
}
