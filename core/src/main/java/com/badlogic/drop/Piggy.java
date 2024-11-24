package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public abstract class Piggy {
    // Protected texture for the pig
    protected Texture Face;

    // Position of the pig
    private float x, y;

    // Constructor for setting the texture and position
    public Piggy(String texturePath, float x, float y) {
        this.Face = new Texture(texturePath);  // Load the texture based on the path
        this.x = x;                            // Set the X position
        this.y = y;                            // Set the Y position
    }

    // Getter for the pig's face (texture)
    public Texture getFace() {
        return Face;
    }

    // Getter for the pig's X position
    public float getX() {
        return x;
    }

    // Getter for the pig's Y position
    public float getY() {
        return y;
    }

    // Method to dispose of the pig texture when not needed
    public void dispose() {
        if (Face != null) {
            Face.dispose();
        }
    }
}
