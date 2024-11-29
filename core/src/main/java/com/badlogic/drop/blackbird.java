package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class blackbird extends Bird {
    private static final float EXPLOSION_RADIUS = 5f; // Define radius for the explosion effect
    private static final float EXPLOSION_FORCE = 100f;
    private boolean abilityUsed;
    public blackbird(float x, float y, World world) {
        super("blackbird.png", x, y, world);
    }

    public void handleInput() {
        if (isLaunched() && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            activateSpecialAbility();
        }
    }


    @Override
    public void activateSpecialAbility() {

    }
}
