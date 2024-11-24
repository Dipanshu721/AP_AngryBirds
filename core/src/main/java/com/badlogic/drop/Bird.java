package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird extends GameObject{

    protected Texture Face;
    protected float x;  // Position X
    protected float y;  // Position Y


    public Bird(String texturePath, float x, float y, BodyDef bodyDef, World world) {
        super(texturePath, bodyDef, world);
        bodyDef.position.set(x, y);  // Set the bird's unique position
        this.Face = new Texture(texturePath);

        // Set position
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // Define a square shape (for simplicity)
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f); // Adjust size as needed

        // Define fixture properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.2f; // Higher density for birds
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f; // Make it bouncy for "angry" effect

        body.createFixture(fixtureDef);
        shape.dispose();

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
