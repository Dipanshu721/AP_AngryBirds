package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Piggy extends GameObject {

    protected float x;  // X position of the object
    protected float y;  // Y position of the object
    protected int height= 10;
    protected float width = 10;
    int health;

    public Piggy(String texturePath, float x, float y, World world) {
        super(texturePath, x, y, world);  // Passing the parameters to the parent constructor
        body.setUserData(this);

        CircleShape shape = new CircleShape();
        shape.setRadius(width / 2f); // Adjust the radius to the bird's size

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.6f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0.15f;  // Bounciness factor

        body.createFixture(fixtureDef); // Attach the fixture to the body
        shape.dispose();  // Clean up shape after use
    }
    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void render(SpriteBatch spriteBatch) {
        Vector2 position = body.getPosition();
        float renderX = position.x - (width / 2f);  // Use actual width and height
        float renderY = position.y - (height / 2f);
        float rotation = (float) Math.toDegrees(body.getAngle()); // Convert radians to degrees

        spriteBatch.draw(texture, renderX, renderY, width / 2f, height / 2f, width, height,
            1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(),
            false, false);
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            CollisionHandler.queueForDestruction(body);
        }
    }

    public boolean isDestroyed() {
        return health <= 0; // Adjust based on how destruction is tracked
    }


    @Override
    public void dispose() {
        super.dispose();  // Call parent's dispose
        if (texture != null) {
            texture.dispose();
        }
    }
}
