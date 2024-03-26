package org.pepe.theory.contravariant;

import lombok.Getter;

/*
 * Contravariance means that if a type T is a super-type of a type U,
 * then a method that takes a type U as an argument can also accept a type T as an argument.
 * In other words method is Contravariant if it can also accept more general type.
 */
public class ContravariantExample {
    public static void main(String[] args) {
        Tool hammer = new Tool();

        ToolBox toolBox = new Container();
        Container container = new Container();

        toolBox.store(hammer);
        container.store(hammer);
    }
}

/*
 * The method store(Tool tool) in the subclass Container is covariant.
 * It can accept a Tool, which is a subtype of Item.
 */
@Getter
class ToolBox {
    private Tool tool;

    void store(Tool tool) {
        this.tool = tool;
    }

}

/*
 * The method store(Item item) in the super-class ToolBox is contravariant.
 * It can accept an Item, which is a super-type of Tool.
 */
@Getter
class Container extends ToolBox {
    private Item item;
    void store(Item item) {
        this.item = item;
    }
}

interface Item {
}

class Tool implements Item {
}