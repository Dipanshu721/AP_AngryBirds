package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Piggy extends GameObject {

    protected float x;  // X position of the object
    protected float y;  // Y position of the object
    protected int height= 100;
    protected float width = 100;

    public Piggy(String texturePath, float x, float y, World world) {
        super(texturePath, x, y, world);  // Passing the parameters to the parent constructor
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f, height / 2f); // Adjusting size for the pig's shape

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;  // Bounciness factor for pigs

        body.createFixture(fixtureDef); // Attach the fixture to the body
        shape.dispose();  // Clean up shape after use
    }
    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void dispose() {
        super.dispose();  // Call parent's dispose
    }
}
