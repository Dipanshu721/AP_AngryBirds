package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Slingshot extends Actor {
    private Bird bird;
    private final Vector2 initialPosition;
    private final Vector2 currentPosition = new Vector2();
    private boolean isDragging = false;

    public Slingshot(Bird bird) {
        this.bird = bird;
        this.initialPosition = bird.getBody().getPosition().cpy();

        setPosition(initialPosition.x - 10, initialPosition.y - 10);
        setSize(20, 20);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //special ability / deactivated
//                if (bird.isLaunched()) {
//                    if (bird instanceof blackbird) {
//                        ((blackbird) bird).activateSpecialAbility();
//                    } else if (bird instanceof yellowbird) {
//                        ((yellowbird) bird).activateSpecialAbility();
//                    }
//                }
//                else{
                // Standard drag logic..
                Vector2 worldCoords = new Vector2(event.getStageX(), event.getStageY());
                if (worldCoords.dst(initialPosition) < 35) {
                    isDragging = true;
                    currentPosition.set(worldCoords);
                    return true;
                }
                return false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging) {
                    Vector2 worldCoords = new Vector2(event.getStageX(), event.getStageY());
                    currentPosition.set(worldCoords);
                    Slingshot.this.bird.getBody().setTransform(currentPosition, 0);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging) {
                    isDragging = false;
                    float velocity = (initialPosition.dst(currentPosition));
                    float angle = initialPosition.sub(currentPosition).angleRad();
                    Slingshot.this.bird.launch(velocity, angle);
                }
            }
        });
    }
    @Override
    public void act(float delta) {
        super.act(delta);

        // Check for SpaceBar press to activate special ability
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (bird.isLaunched()) {
                if (bird instanceof yellowbird) {
                    bird.activateSpecialAbility();
                } else if (bird instanceof blackbird) {
                    bird.activateSpecialAbility();
                }
            }
        }
    }
}

