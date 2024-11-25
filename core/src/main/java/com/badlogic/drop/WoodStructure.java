package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class WoodStructure extends Structure {

    public WoodStructure(float x, float y, float width, float height, World world) {
        super("wood.png", x, y, width, height, world);
    }
}
