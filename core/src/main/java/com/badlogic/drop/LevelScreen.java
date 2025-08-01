package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LevelScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture play1Texture;
    private Texture play2Texture;
    private Texture play3Texture;
    private Sprite play1Sprite;
    private Sprite play2Sprite;
    private Sprite play3Sprite;

    private OrthographicCamera camera;
    private Viewport viewport;

    private boolean isSavedGame;

    public LevelScreen(Main game, boolean isSavedGame) {
        this.game = game;
        this.isSavedGame = isSavedGame; // Set the flag based on the chosen option
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("levelscreen.png"));
        play1Texture = new Texture(Gdx.files.internal("1.png"));
        play2Texture = new Texture(Gdx.files.internal("2.png"));
        play3Texture = new Texture(Gdx.files.internal("3.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(1946, 1094, camera);

        play1Sprite = new Sprite(play1Texture);
        play2Sprite = new Sprite(play2Texture);
        play3Sprite = new Sprite(play3Texture);

        play1Sprite.setSize(450, 250);
        play2Sprite.setSize(450, 250);
        play3Sprite.setSize(450, 250);

        play1Sprite.setPosition(213, 550);
        play2Sprite.setPosition(751, 550);
        play3Sprite.setPosition(1283, 550);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(camera.combined);

        game.getbatch().begin();
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        play1Sprite.draw(game.getbatch());
        play2Sprite.draw(game.getbatch());
        play3Sprite.draw(game.getbatch());
        game.getbatch().end();

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 213 && touchPos.x <= 663 && touchPos.y >= 550 && touchPos.y <= 800) {
                // If it's a saved game, load the saved state of this level, else start a new game
                if (isSavedGame) {
                    game.setScreen(new GameScreen1(game) );  // Load saved state for level 1
                } else {
                    game.setScreen(new GameScreen(game));  // Start new game for level 1
                }
            }

            if (touchPos.x >= 751 && touchPos.x <= 1201 && touchPos.y >= 550 && touchPos.y <= 800) {
                if (isSavedGame) {
                    game.setScreen(new GameScreen2(game)); // Load saved state for level 2
                } else {
                    game.setScreen(new GameScreen2(game));  // Start new game for level 2
                }
            }

            if (touchPos.x >= 1283 && touchPos.x <= 1733 && touchPos.y >= 550 && touchPos.y <= 800) {
                if (isSavedGame) {
                    game.setScreen(new GameScreen3(game));  // Load saved state for level 3
                } else {
                    game.setScreen(new GameScreen3(game));  // Start new game for level 3
                }
            }
        }
    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        play1Texture.dispose();
        play2Texture.dispose();
        play3Texture.dispose();
    }
}
