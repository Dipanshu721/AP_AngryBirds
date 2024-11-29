package com.badlogic.drop;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class yellowbird extends Bird {
    private boolean abilityUsed;

    public yellowbird(float x, float y, World world) {
        super("yellowbird.png", x, y, world);
    }

    public void activateSpecialAbility() {
        if (!abilityUsed && isLaunched()) {
            Vector2 velocity = body.getLinearVelocity();
            body.setLinearVelocity(velocity.scl(1.5f)); // Increase speed by 50%
            abilityUsed = true; // Ensure ability is only used once
        }
    }
}
