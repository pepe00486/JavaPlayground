package org.pepe.games.common.board;

import lombok.Getter;

import java.util.Arrays;

public class Board {
    private final String[][] board;
    @Getter
    private final int size;
    private final int spaceLeft;
    private String emptyFieldSymbol;

    public Board(int size) {
        this.size = size;
        this.spaceLeft = size;
        this.board = new String[size][size];
        this.emptyFieldSymbol = "";
    }

    public void initializeBoard(String emptyFieldSymbol) {
        this.emptyFieldSymbol = emptyFieldSymbol;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = emptyFieldSymbol;
            }
        }
    }

    public String getFieldValue(int row, int column) {
        return board[row][column];
    }

    public boolean hasNoSpaceLeft() {
        return spaceLeft <= 0;
    }

    public boolean playField(BoardGameInput boardGameInput) {
        if (hasNoSpaceLeft() || !isEmptyField(boardGameInput)) {
            return false;
        }
        this.board[boardGameInput.row()][boardGameInput.column()] = boardGameInput.symbol();
        return true;
    }

    private boolean isEmptyField(BoardGameInput boardGameInput) {
        return this.board[boardGameInput.row()][boardGameInput.column()].equals(this.emptyFieldSymbol);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] strings : this.board) {
            sb.append(Arrays.toString(strings));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
