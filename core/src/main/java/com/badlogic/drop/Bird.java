package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public abstract class Bird {

    protected Texture Face;
    protected float x;  // Position X
    protected float y;  // Position Y

    public Bird() {
        // Default constructor (optional)
    }

    public Bird(String texturePath, float x, float y) {
        this.Face = new Texture(texturePath);
        this.x = x;
        this.y = y;
    }

    public void dispose() {
        Face.dispose();
    }

    public Texture getFace() {
        return Face;
    }

    // Getters and Setters for x and y
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
