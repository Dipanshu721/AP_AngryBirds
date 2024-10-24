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
    FitViewport viewport;
    Vector2 touchPos;

    public HomeScreen(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("HomeScreen.jpg");
        PlayButton = new Texture("PlayButton.png");
        touchPos= new Vector2();
        viewport = new FitViewport(8, 5);
    }

    @Override
    public void render(float delta) {
        game.getbatch().begin();
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(viewport.getCamera().combined);

        float worldWidth = viewport.getWorldWidth();    // can directly implement as argument.
        float worldHeight = viewport.getWorldHeight();
        game.getbatch().draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        game.getbatch().draw(PlayButton, 5,1,2,2);
        game.getbatch().end();

        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 5 && touchPos.x <= 7 && touchPos.y >= 1 && touchPos.y <= 3) {
                game.setScreen(new LevelScreen(game));
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
