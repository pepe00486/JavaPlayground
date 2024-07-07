package org.pepe.games.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Dice {
    private final int min;
    private final int max;

    public int roll() {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }
}
