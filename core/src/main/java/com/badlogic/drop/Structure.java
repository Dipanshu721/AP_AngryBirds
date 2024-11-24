package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Structure extends GameObject{

    protected int x;  // X position of the structure
    protected int y;  // Y position of the structure
    protected int width;  // Width of the structure
    protected int height;  // Height of the structure

    // Constructor that initializes the texture and position
    public Structure(String texturePath, int x, int y,int width, int height, BodyDef bodyDef, World world) {
        super(texturePath, bodyDef, world);
        this.texture = new Texture(texturePath);// Create the texture using the given texture path
        bodyDef.position.set(x, y);  // Set position for each structure
        this.x = (int) x;  // Set X position
        this.y = (int) y;  // Set Y position
        this.width = width;  // Set width of the structure
        this.height = height;  // Set height of the structure

        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f, height / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    // Dispose of the texture when no longer needed
    public void dispose() {
        texture.dispose();
    }

    // Getter for the texture
    public Texture getTexture() {
        return texture;
    }

    // Getters and setters for position
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters and setters for width and height
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
