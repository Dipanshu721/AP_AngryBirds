package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class kingPiggy extends Piggy {

    public kingPiggy(float x, float y, World world) {
        super("kingpiggy.png", x, y, world);
        this.health = 100;
    }
}
