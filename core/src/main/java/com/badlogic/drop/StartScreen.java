package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StartScreen implements Screen {

    private final Main game;
    Texture backgroundTexture;
    Texture newGame;
    Texture savedGame;
    Texture Settings;       // to implement sound settings (ie, increase volume for bonus)
    FitViewport viewport;
    Vector2 touchPos;

    public StartScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
        newGame = new Texture(Gdx.files.internal("PlayButton.png"));
        savedGame = new Texture(Gdx.files.internal("PlayButton.png"));
        touchPos= new Vector2();
        viewport = new FitViewport(2560, 1440);
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
        game.getbatch().draw(newGame, 1000,850,450,200);
        game.getbatch().draw(savedGame,1000, 400, 450, 200);
        game.getbatch().end();

        if (Gdx.input.isTouched()){ // newgame
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 1000 && touchPos.x <= 1450 && touchPos.y >= 850 && touchPos.y <= 1050) {
                game.setScreen(new GameScreen(game));
            }
        }
//        if (Gdx.input.isTouched()){ // savedgame
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
//            viewport.unproject(touchPos);
//
//            if (touchPos.x >= 1000 && touchPos.x <= 1450 && touchPos.y >= 400 && touchPos.y <= 650) {
//            // nothing as of now
//            }
//        }

    }

    @Override
    public void resize(int x, int y) {
        viewport.update(x,y,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        savedGame.dispose();
        newGame.dispose();
    }
}
