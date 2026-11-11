package com.badlogic.drop;

import com.badlogic.gdx.physics.box2d.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionHandler implements ContactListener {

    private final List<Body> objectsToDestroy = new ArrayList<>();

    public void queueForDestruction(Body body) {
        if (!objectsToDestroy.contains(body)) {
            objectsToDestroy.add(body);
        }
    }

    public void processQueuedDestruction(World world) {
        if (world.isLocked()) return;
        for (Body body : objectsToDestroy) {
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
        if (piggy.isDestroyed()) return;
        Object other = otherFixture.getBody().getUserData();
        if (other instanceof Bird) {
            piggy.reduceHealth(100);
        } else if (other instanceof Structure) {
            piggy.reduceHealth(40); // Pigs can be crushed by falling structures
        }
    }

    private void handleStructureCollision(Structure structure, Fixture otherFixture) {
        if (structure.isDestroyed()) return;
        Object other = otherFixture.getBody().getUserData();
        if (other instanceof Bird) {
            structure.reduceHealth(80);
        }
        // Removed structure-on-structure damage to prevent towers self-destructing on spawn
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
