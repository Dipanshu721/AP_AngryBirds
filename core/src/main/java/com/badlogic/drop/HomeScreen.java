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
    Texture backgroundTexture;
    Texture PlayButton;
    Texture ExitButton;
    FitViewport viewport;
    Vector2 touchPos;

    public HomeScreen(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("firstscreen.png");
        PlayButton = new Texture("PlayButton.png");
        ExitButton = new Texture("ExitButton.png");
        touchPos= new Vector2();
        viewport = new FitViewport(1280, 715);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(viewport.getCamera().combined);
        game.getbatch().begin();
        float worldWidth = viewport.getWorldWidth();    // can directly implement as argument.
        float worldHeight = viewport.getWorldHeight();
        game.getbatch().draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        game.getbatch().draw(PlayButton, 850,220,243.25f,136.75f);
        game.getbatch().draw(ExitButton,850, 70, 243.25f, 136.75f);
        game.getbatch().end();

        if (Gdx.input.isTouched()){ // startgame
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 850 && touchPos.x <= 1093.25f && touchPos.y >= 220 && touchPos.y <= 356.75f) {
                game.setScreen(new LevelScreen(game));
            }
        }

        if (Gdx.input.isTouched()){ // exitgame
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 850 && touchPos.x <= 1093.25f && touchPos.y >= 70 && touchPos.y <= 206.75f) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        PlayButton.dispose();
    }
}
