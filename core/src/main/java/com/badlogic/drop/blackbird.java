package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class blackbird extends Bird {

    public blackbird(String imagePath, float x, float y, BodyDef bodydef, World world) {
        super(imagePath, x, y, bodydef, world);  // Call the superclass constructor
    }
}
