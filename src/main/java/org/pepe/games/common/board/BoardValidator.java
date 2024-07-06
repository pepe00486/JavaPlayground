package org.pepe.games.common.board;

import lombok.RequiredArgsConstructor;
import org.pepe.games.common.InputValidator;

@RequiredArgsConstructor
public class BoardValidator implements InputValidator {
    private final Board board;

    public boolean validateInput(String input) {
        if (input.length() != 2) return false;
        if (!Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(1))) return false;
        int inputX = input.charAt(0) - 48;
        int inputY = input.charAt(1) - 48;
        if (inputX >= board.getSize() || inputY >= board.getSize()) return false;
        return (inputX >= 0 && inputY >= 0);
    }
}
