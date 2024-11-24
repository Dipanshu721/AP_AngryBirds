package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class IceStructure extends Structure {

    // Constructor that accepts texture path, position (x, y), width, and height
    public IceStructure(String texturePath, int x, int y, int width, int height, BodyDef bodyDef, World world) {
        super(texturePath, x, y, width, height, bodyDef, world);  // Call the parent constructor (Structure) with texture path, position, width, and height
    }

    // Optional: Override render or other methods if needed for WoodStructure-specific behavior
}
