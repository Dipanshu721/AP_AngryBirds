package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;

public class normalPiggy extends Piggy {
    private float x, y;

    public normalPiggy(String texturePath, float x, float y) {
        super("normalpiggy.png", x, y);
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
