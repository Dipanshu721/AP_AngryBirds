package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class SteelStructure extends Structure {

    public SteelStructure(float x, float y, float width, float height, World world) {
        super("stone.png", x, y, width, height, world);
    }
}
