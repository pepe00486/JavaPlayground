package org.pepe.games.common;

import lombok.Getter;

@Getter
public class GameState {
    private State state = State.NOT_STARTED;
    private Player winner = null;

    public void draw() {
        System.out.println("Game was a draw!");
        state = State.DONE;
    }

    public void win(Player player) {
        System.out.println("Player " + player.name() + " wins!");
        winner = player;
        state = State.DONE;
    }

    public void start() {
        System.out.println("The game begins !");
        state = State.ONGOING;
    }

    public void redo() {
        state = State.ONGOING;
    }

    public void exit() {
        state = State.DONE;
    }

    public void invalidate() {
        state = State.INVALID;
    }

    public enum State {
        NOT_STARTED, ONGOING, DONE, INVALID
    }
}
