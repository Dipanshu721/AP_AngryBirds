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
            if (body != null) {
                world.destroyBody(body);
            }
        }
        objectsToDestroy.clear();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Example handling logic
        if (fixtureA.getBody().getUserData() instanceof Bird) {
            handleCollision(fixtureA, fixtureB);
        } else if (fixtureB.getBody().getUserData() instanceof Bird) {
            handleCollision(fixtureB, fixtureA);
        }
    }

    private void handleCollision(Fixture birdFixture, Fixture otherFixture) {
        if (birdFixture.getBody().getUserData() instanceof Bird) {
            Bird bird = (Bird) birdFixture.getBody().getUserData();

            if (otherFixture.getBody().getUserData() instanceof Piggy) {
                Piggy piggy = (Piggy) otherFixture.getBody().getUserData();
                piggy.reduceHealth(100);
                if (piggy.isDestroyed()) {
                    queueForDestruction(otherFixture.getBody());// Use `otherFixture`
                    piggy.dispose();
                }
            } else if (otherFixture.getBody().getUserData() instanceof Structure) {
                Structure structure = (Structure) otherFixture.getBody().getUserData();
                structure.reduceHealth(80);
                if (structure.isDestroyed()) {
                    queueForDestruction(otherFixture.getBody());  // Use `otherFixture`
                structure.dispose();
                }
            }
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
