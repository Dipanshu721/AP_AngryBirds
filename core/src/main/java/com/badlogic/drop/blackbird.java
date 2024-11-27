package com.badlogic.drop;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class blackbird extends Bird {

    public blackbird(float x, float y, World world) {
        super("blackbird.png", x, y, world);
    }

    public void activateSpecialAbility() {
        if (isLaunched()) {
            // Simulate bomb effect
            Vector2 position = body.getPosition();
            float blastRadius = 10f; // Adjust radius as needed
            float blastFactor = 5f; // Adjust impact strength
            Array<Body> bodies = new Array<>();
            world.getBodies(bodies); // Fetch all bodies in the world

            for (Body body : bodies) {
                if (body != this.body && position.dst(body.getPosition()) <= blastRadius) {
                    Vector2 force = body.getPosition().sub(position).nor().scl(blastFactor);
                    body.applyLinearImpulse(force, body.getWorldCenter(), true);
                }
            }
            body.setActive(false); // Destroy the bird after bomb
        }
    }
}
