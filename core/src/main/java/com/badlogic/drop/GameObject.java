package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject {
    protected Texture texture;
    protected Body body;

    public GameObject(String texturePath, BodyDef bodyDef, World world) {
        this.texture = new Texture(texturePath);
        this.body = world.createBody(bodyDef);  // Create body in the world
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public Body getBody() {
        return body;
    }
}
