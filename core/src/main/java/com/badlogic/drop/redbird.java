package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public class redbird extends Bird {

    public redbird(String texturePath, float x, float y) {
        super(texturePath, x, y);  // Calling parameterized constructor
    }

    // Example method where we use getFace()
    public void someMethod() {
        Texture texture = getFace();  // Correct way to access the texture
    }
}
