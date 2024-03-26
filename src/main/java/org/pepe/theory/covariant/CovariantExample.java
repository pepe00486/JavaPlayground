package org.pepe.theory.covariant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


import static org.pepe.theory.covariant.SoundGenerator.generateSound;

/*
 * Covariant return types allow return type of method in its subclass to be more specific.
 * Meaning that if type T is a subtype of type U then when method in the superclass returns U
 * then an overriding method in the subclass can return T.
 */
public class CovariantExample {
    public static void main(String[] args) {
        List<Animal> animals = List.of(new Dog(), new Cat());
        for (Animal animal : animals) {
            AnimalSound sound = animal.getSound();
            generateSound(sound.play());
        }
    }
}

class Animal {
    public AnimalSound getSound() {
        return new AnimalSound();
    }
}

/*
 *  The method getSound() in the subclass Dog and Cat are covariant,
 *  because they return a subtype of AnimalSound.
 */
class Dog extends Animal {
    @Override
    public Woofing getSound() {
        return new Woofing();
    }
}

class Cat extends Animal {
    @Override
    public Meowing getSound() {
        return new Meowing();
    }
}

interface Playable {
    String play();
}

class Woofing extends AnimalSound {
    @Override
    public String play() {
        return "Woof!";
    }
}


class Meowing extends AnimalSound {
    @Override
    public String play() {
        return "Meow!";
    }
}

class AnimalSound implements Playable {
    @Override
    public String play() {
        return "What does the fox say?";
    }
}

class SoundGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoundGenerator.class);

    private SoundGenerator() {
    }

    public static void generateSound(String play) {
        LOGGER.info("Play: {}", play);
    }
}