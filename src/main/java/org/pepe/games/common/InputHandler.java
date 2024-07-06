package org.pepe.games.common;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputHandler {
    public static final String EXIT_COMMAND = "exit";
    private final GameState state;
    private final InputValidator validator;
    private final Scanner in;

    public String nextInput() {
        return in.nextLine();
    }

    public void handleInput(String input) {
        if (EXIT_COMMAND.equals(input)) {
            state.exit();
            return;
        }
        if (!validator.validateInput(input)) {
            System.out.println("Incorrect input");
            state.invalidate();
        }
    }
}
