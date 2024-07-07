package org.pepe.games.snakeandlader;

import org.pepe.games.common.InputValidator;

public class SnakeBoardValidator implements InputValidator {
    @Override
    public boolean validateInput(String input) {
        return "1".equals(input);
    }
}
