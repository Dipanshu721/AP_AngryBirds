package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public class SteelStructure extends Structure {

    // Constructor that accepts texture path, position (x, y), width, and height
    public SteelStructure(String texturePath, int x, int y, int width, int height) {
        super(texturePath, x, y, width, height);  // Call the parent constructor (Structure) with texture path, position, width, and height
    }

    // Optional: Override render or other methods if needed for WoodStructure-specific behavior
}
