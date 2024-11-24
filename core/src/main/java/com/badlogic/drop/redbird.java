package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class redbird extends Bird {

    public redbird(String texturePath, float x, float y, BodyDef bodyDef, World world) {
        super(texturePath, x, y, bodyDef, world);  // Calling parameterized constructor
    }

    // Example method where we use getFace()
    public void someMethod() {
        Texture texture = getFace();  // Correct way to access the texture
    }
}
