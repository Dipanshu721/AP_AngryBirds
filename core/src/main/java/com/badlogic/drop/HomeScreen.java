package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HomeScreen implements Screen {

    private final Main game;
    private Texture backgroundTexture;
    private Texture playButtonTexture;
    private Texture exitButtonTexture;
    private FitViewport viewport;
    private Vector2 touchPos;

    public HomeScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("firstscreen.png");
        playButtonTexture = new Texture("PlayButton.png");
        exitButtonTexture = new Texture("ExitButton.png");
        touchPos = new Vector2();
        viewport = new FitViewport(1280, 715);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(viewport.getCamera().combined);

        game.getbatch().begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        game.getbatch().draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        game.getbatch().draw(playButtonTexture, 850, 220, 243.25f, 136.75f);
        game.getbatch().draw(exitButtonTexture, 850, 70, 243.25f, 136.75f);
        game.getbatch().end();

        touchPos.set(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(touchPos);

        if (Gdx.input.isTouched() &&
            touchPos.x >= 850 && touchPos.x <= 1093.25f && touchPos.y >= 220 && touchPos.y <= 356.75f) {
            game.setScreen(new StartScreen(game));
        } else if (Gdx.input.isTouched() &&
            touchPos.x >= 850 && touchPos.x <= 1093.25f && touchPos.y >= 70 && touchPos.y <= 206.75f) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}
