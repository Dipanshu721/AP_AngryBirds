package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.World;

public class IceStructure extends Structure {

    public IceStructure(float x, float y, float width, float height, World world) {
        super("glass.png", x, y, width, height, world);
    }
}
