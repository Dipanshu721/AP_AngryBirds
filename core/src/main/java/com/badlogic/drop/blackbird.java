package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
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

    public void activateSpecialAbility() {
        if (!abilityUsed && isLaunched()) {
            // Trigger explosion force
            applyExplosionForce();
            abilityUsed = true; // Ensure ability is only used once
        }
    }

    private void applyExplosionForce() {
        // Check nearby objects in the world (use Box2D query)
        world.QueryAABB((fixture) -> {
                if (fixture.getBody() != this.body) {
                    Vector2 distance = fixture.getBody().getPosition().cpy().sub(this.body.getPosition());
                    if (distance.len() <= EXPLOSION_RADIUS) {
                        Vector2 force = distance.nor().scl(EXPLOSION_FORCE);
                        fixture.getBody().applyForceToCenter(force, true);
                    }
                }
                return true;
            }, body.getPosition().x - EXPLOSION_RADIUS, body.getPosition().y - EXPLOSION_RADIUS,
            body.getPosition().x + EXPLOSION_RADIUS, body.getPosition().y + EXPLOSION_RADIUS);
    }
}
