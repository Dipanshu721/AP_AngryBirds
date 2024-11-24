package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class normalPiggy extends Piggy {
    private float x, y;

    public normalPiggy(String texturePath, float x, float y, BodyDef bodyDef, World world) {
        super("normalpiggy.png", x, y, bodyDef, world);
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
