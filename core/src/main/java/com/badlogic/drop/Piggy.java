package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Piggy extends GameObject {

    protected float x;  // X position of the object
    protected float y;  // Y position of the object
    protected int height= 10;
    protected float width = 10;

    public Piggy(String texturePath, float x, float y, World world) {
        super(texturePath, x, y, world);  // Passing the parameters to the parent constructor
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f, height / 2f); // Adjusting size for the pig's shape

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.25f;  // Bounciness factor for pigs

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

    @Override
    public void dispose() {
        super.dispose();  // Call parent's dispose
    }
}
