package org.pepe.theory.contravariant

import spock.lang.Specification

class ContravariantExampleSpec extends Specification {
    // The main happy path is to create a Tool object and store it in both a ToolBox and a Container object using their respective store methods.
    def "should store a Tool object in both ToolBox and Container"() {
        given:
        Tool hammer = new Tool()

        when:
        ToolBox toolBox = new Container()
        Container container = new Container()

        then:
        toolBox.store(hammer)
        container.store(hammer)
    }

    // Another happy path is to create a Container object and store a Tool object in it using its store method.
    def "should store a Tool object in a Container"() {
        given:
        Tool hammer = new Tool()
        Container container = new Container()

        when:
        container.store(hammer)

        then:
        // assert that the tool stored in the container is the same as the one passed in
        container.getTool() == hammer
    }

    // An edge case is to create a Container object and try to store an Item object in it using its store method, which should not be possible.
    def "should not store an Item object in a Container"() {
        given:
        Item item = new Item(){}
        Container container = new Container()

        when:
        container.store(item)

        then:
        // assert that the tool stored in the container is null, indicating that the item was not stored
        container.getTool() == null
    }

}