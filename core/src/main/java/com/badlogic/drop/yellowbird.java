package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class yellowbird extends Bird {

    public yellowbird(String imagePath, float x, float y, BodyDef bodyDef, World world) {
        super(imagePath, x, y, bodyDef, world);  // Call the superclass constructor
    }
}
