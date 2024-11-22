package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LostScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture goToHomeTexture;

    private Viewport viewport;

    public LostScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("lost.png"));
        goToHomeTexture = new Texture(Gdx.files.internal("gotohome.png"));

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
        game.getbatch().draw(goToHomeTexture, 700, 500, 570, 330);
        game.getbatch().end();

        handleInput();
    }

    private void handleInput() {
        Vector2 touchPos = new Vector2();
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if "Go to Home" button is clicked
            if (touchPos.x >= 700 && touchPos.x <= 1270 && touchPos.y >= 500 && touchPos.y <= 830) {
                game.setScreen(new HomeScreen(game));
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
        goToHomeTexture.dispose();
    }
}
