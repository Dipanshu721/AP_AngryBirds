package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PausedScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture ResumeTexture;
    private Texture ExitGameTexture;

    private Viewport viewport;

    public PausedScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Load textures
        backgroundTexture = new Texture(Gdx.files.internal("pausedscreen.png"));
        ResumeTexture = new Texture(Gdx.files.internal("resumebutton.png"));
        ExitGameTexture = new Texture(Gdx.files.internal("exitgamebutton.png"));

        // Set up viewport
        viewport = new FitViewport(1946, 1094);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        // Clear screen and apply viewport
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(viewport.getCamera().combined);

        // Draw all textures
        game.getbatch().begin();
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        game.getbatch().draw(ResumeTexture, 250, 525, 600, 300); // Resume Button
        game.getbatch().draw(ExitGameTexture, 1050, 525, 600, 300); // Exit Button
        game.getbatch().end();

        // Input handling
        handleInput();
    }

    private void handleInput() {
        Vector2 touchPos = new Vector2();
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Resume button
            if (touchPos.x >= 250 && touchPos.x <= 850 && touchPos.y >= 525 && touchPos.y <= 825) {
                game.setScreen(new GameScreen(game));
            }

            // Exit button
            if (touchPos.x >= 1050 && touchPos.x <= 1650 && touchPos.y >= 525 && touchPos.y <= 825) {
                game.setScreen(new LostScreen(game));
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
        ResumeTexture.dispose();
        ExitGameTexture.dispose();
    }
}
