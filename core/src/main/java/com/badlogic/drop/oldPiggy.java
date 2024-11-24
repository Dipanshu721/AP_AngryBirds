package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public class oldPiggy extends Piggy {
    private float x, y;

    public oldPiggy(String texturePath, float x, float y) {
        super("oldpiggy.png", x, y);
        this.Face = new Texture(texturePath);
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
