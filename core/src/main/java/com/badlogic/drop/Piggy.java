package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Piggy extends GameObject {
    // Protected texture for the pig
    protected Texture Face;

    // Position of the pig
    private float x, y;

    // Constructor for setting the texture and position
    public Piggy(String texturePath, float x, float y, BodyDef bodyDef, World world) {
        super(texturePath, bodyDef, world);
        bodyDef.position.set(x, y);
        this.Face = new Texture(texturePath);  // Load the texture based on the path
        this.x = x;                            // Set the X position
        this.y = y;                            // Set the Y position

        // Set position
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // Define a square shape (simpler for collision detection)
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f); // 1x1 meter square (adjust as needed)

        // Define fixture properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f; // Set density for collision response
        fixtureDef.friction = 0.5f; // Friction when sliding
        fixtureDef.restitution = 0.3f; // Slight bounce on impact

        body.createFixture(fixtureDef); // Attach the fixture to the body
        shape.dispose(); // Dispose of the shape once it's no longer needed

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
