package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Structure extends GameObject {

    private float width;
    private float height;
    protected int health;

    public Structure(String texturePath, float x, float y, float width, float height, World world, int hardcoded) {
        super(texturePath, x, y, world);

        this.width = width; // Initialize width and height
        this.height = height;
        this.health = hardcoded;
        body.setUserData(this);

        // Define the body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        this.body = world.createBody(bodyDef);

        // Define the shape and attach it to the body
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f, height / 2f); // Box dimensions in Box2D units

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.2f;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.15f;

        body.createFixture(fixtureDef);
        shape.dispose(); //dispose it
    }
    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        reduceHealth(50);
        System.out.println("Structure health: " + health);
        if (health <= 0) {
            System.out.println("Structure queued for destruction.");
            CollisionHandler.queueForDestruction(body);
        }
    }
    public boolean isDestroyed() {
        return health <= 0;
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

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }

    @Override
    public void dispose() {
        super.dispose(); // Dispose of texture
        if (texture != null) {
            texture.dispose();
        }
    }
}
