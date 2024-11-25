package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird extends GameObject {

    protected float x;  // X position of the object
    protected float y;  // Y position of the object
    protected int height= 100;
    protected int width = 100;

    public Bird(String texturePath, float x, float y, World world) {
        super(texturePath, x, y, world);  // Pass the texture path to the parent constructor

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f, height / 2f); // Box dimensions

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.2f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();
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
        spriteBatch.draw(texture, renderX, renderY, width, height);
    }

    public void dispose() {
        super.dispose(); // Cleanup
    }
}
