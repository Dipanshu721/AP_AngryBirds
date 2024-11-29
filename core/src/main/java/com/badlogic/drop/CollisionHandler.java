package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler implements ContactListener {

    private static final List<Body> objectsToDestroy = new ArrayList<>();
    public static void queueForDestruction(Body body) {
        if (!objectsToDestroy.contains(body)) {
            objectsToDestroy.add(body);
        }
    }
    public void processQueuedDestruction(World world) {
        for (Body body : objectsToDestroy) {
            if (world.isLocked()){
                continue;
            }
            body.setActive(false);
        }
        objectsToDestroy.clear();
    }

    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();

        // Handle Pig
        if (userDataA instanceof Piggy) {
            handlePigCollision((Piggy) userDataA, contact.getFixtureB());
        } else if (userDataB instanceof Piggy) {
            handlePigCollision((Piggy) userDataB, contact.getFixtureA());
        }

        // Handle Structure
        if (userDataA instanceof Structure) {
            handleStructureCollision((Structure) userDataA, contact.getFixtureB());
        } else if (userDataB instanceof Structure) {
            handleStructureCollision((Structure) userDataB, contact.getFixtureA());
        }
    }

    private void handlePigCollision(Piggy piggy, Fixture otherFixture) {
        if (otherFixture.getBody().getUserData() instanceof Bird) {
            piggy.reduceHealth(100); // Adjust damage value as needed
            if (piggy.isDestroyed()) {
                CollisionHandler.queueForDestruction(otherFixture.getBody());
                piggy.dispose();
                GameScreen2.pigDestroyed();
            }
        }
    }

    private void handleStructureCollision(Structure structure, Fixture otherFixture) {
        // Check if collided with a bird or another structure
        if (otherFixture.getBody().getUserData() instanceof Bird) {
            structure.reduceHealth(80); // Adjust damage value as needed
        } else if (otherFixture.getBody().getUserData() instanceof Structure) {
            structure.reduceHealth(35);
        }

        if (structure.isDestroyed()) {
            CollisionHandler.queueForDestruction(otherFixture.getBody());
            structure.dispose();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
