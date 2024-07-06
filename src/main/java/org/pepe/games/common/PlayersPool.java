package org.pepe.games.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayersPool {
    private final Player[] players;
    private int currentIndex = 0;

    public Player current() {
        return players[currentIndex];
    }

    public void next() {
        currentIndex = currentIndex == players.length - 1 ? 0 : currentIndex + 1;
    }
}

