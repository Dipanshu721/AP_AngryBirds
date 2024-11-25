package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class normalPiggy extends Piggy {

    public normalPiggy(float x, float y, World world) {
        // Call the superclass constructor with 4 arguments
        super("normalpiggy.png", x, y, world);
    }
}
