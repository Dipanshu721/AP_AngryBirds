package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture play1Texture;
    private Texture play2Texture;
    private Texture play3Texture;

    private Viewport viewport;

    public LevelScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("levelscreen.png"));
        play1Texture = new Texture(Gdx.files.internal("1.png"));
        play2Texture = new Texture(Gdx.files.internal("2.png"));
        play3Texture = new Texture(Gdx.files.internal("3.png"));

        viewport = new FitViewport(1946, 1094);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(viewport.getCamera().combined);

        game.getbatch().begin();
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        game.getbatch().draw(play1Texture, 213, 550, 450, 250);
        game.getbatch().draw(play2Texture, 751, 550, 450, 250);
        game.getbatch().draw(play3Texture, 1283, 550, 450, 250);
        game.getbatch().end();

        handleInput();
    }

    private void handleInput() {
        Vector2 touchPos = new Vector2();
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if Level 1 button is clicked
            if (touchPos.x >= 213 && touchPos.x <= 663 && touchPos.y >= 550 && touchPos.y <= 800) {
                game.setScreen(new StartScreen(game));
            }
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        play1Texture.dispose();
        play2Texture.dispose();
        play3Texture.dispose();
    }
}
