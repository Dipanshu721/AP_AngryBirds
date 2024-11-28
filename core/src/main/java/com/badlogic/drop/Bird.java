package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird extends GameObject {

    private boolean launched = false; // Launch state
    protected int height = 9;
    protected int width = 9;
    protected World world;

    public Bird(String texturePath, float x, float y, World world) {
        super(texturePath, x, y, world); // Pass to parent

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        this.body = world.createBody(bodyDef);
        this.world = world;
        body.setUserData(this);

        CircleShape shape = new CircleShape();
        shape.setRadius(width / 2f); // Adjust the radius to the bird's size

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.8f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void launch(float velocity, float angle) {
        body.setType(BodyDef.BodyType.DynamicBody);  // Ensure the body is dynamic
        body.setAwake(true); // Wake the body up
        launched = true; // Mark it launched

        // Apply impulse in the direction of the drag
        float impulseX = (float) (Math.pow(10, 10)*velocity * Math.cos(angle));
        float impulseY = (float) (Math.pow(10, 10)*velocity * Math.sin(angle));

        // Apply impulse directly to the body
        body.applyLinearImpulse(new Vector2((float) impulseX, (float) impulseY), body.getWorldCenter(), true);
    }

    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle()); // Move the bird's body in Box2D
    }

    public boolean isLaunched() {
        return launched;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void render(SpriteBatch spriteBatch) {
        Vector2 position = body.getPosition();
        float renderX = position.x - (width / 2f);
        float renderY = position.y - (height / 2f);
        float rotation = (float) Math.toDegrees(body.getAngle());

        spriteBatch.draw(texture, renderX, renderY, width / 2f, height / 2f, width, height,
            1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(),
            false, false);
    }

    public abstract void activateSpecialAbility();

    @Override
    public void dispose() {
        super.dispose();
    }
}
