package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject {
    protected Texture texture;
    protected Body body;

    public GameObject(String texturePath, float x, float y, World world) {
        this.texture = new Texture(texturePath);
        // Body creation is left to subclasses (Bird, Piggy, Structure)
    }

    public void dispose() {
        texture.dispose();
        // Dispose of texture when no longer needed
    }

    public Texture getTexture() {
        return texture; // Return texture to be used
    }

    public Body getBody() {
        return body; // Return body for physics
    }
}
