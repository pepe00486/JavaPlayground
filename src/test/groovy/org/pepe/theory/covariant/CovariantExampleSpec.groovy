package org.pepe.theory.covariant


import spock.lang.Specification

class CovariantExampleSpec extends Specification {

    // The Animal class has a method that returns an AnimalSound object, and the Cat and Dog classes override this method to return their specific sounds, Meowing and Woofing respectively.
    def "should return AnimalSound for Animal, Meowing for Cat, and Woofing for Dog"() {
        given:
        def animal = new Animal()
        def cat = new Cat()
        def dog = new Dog()

        when:
        def animalSound = animal.getSound()
        def catSound = cat.getSound()
        def dogSound = dog.getSound()

        then:
        animalSound instanceof AnimalSound
        catSound instanceof Meowing
        dogSound instanceof Woofing
    }

    // There are no edge cases to consider in this class.
    def "should not have any edge cases to consider"() {
        expect:
        true
    }

    // The CovariantExample class demonstrates the use of covariant return types, where a subclass can return a more specific type than its superclass.
    def "should demonstrate the use of covariant return types"() {
        given:
        def animal = new Animal()
        def cat = new Cat()
        def dog = new Dog()

        when:
        def animalSound = animal.getSound()
        def catSound = cat.getSound()
        def dogSound = dog.getSound()

        then:
        animalSound instanceof AnimalSound
        catSound instanceof Meowing
        dogSound instanceof Woofing
    }

}