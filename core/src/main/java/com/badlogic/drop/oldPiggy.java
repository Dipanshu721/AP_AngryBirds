package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class oldPiggy extends Piggy {

    public oldPiggy(float x, float y, World world) {
        super("oldpiggy.png", x, y, world);
        this.health = 60;
    }
}
