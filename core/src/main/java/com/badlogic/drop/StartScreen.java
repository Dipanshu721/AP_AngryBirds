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
        backgroundTexture = new Texture(Gdx.files.internal("startscreen.png"));
        newGame = new Texture(Gdx.files.internal("newgame.png"));
        savedGame = new Texture(Gdx.files.internal("savedgame.png"));
        touchPos= new Vector2();
        viewport = new FitViewport(1946, 1094);
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
        game.getbatch().draw(newGame, 250,550,600,300);
        game.getbatch().draw(savedGame,1050, 550, 600, 300);
        game.getbatch().end();

        if (Gdx.input.justTouched()){ // newgame
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 250 && touchPos.x <= 850 && touchPos.y >= 550 && touchPos.y <= 850) {
                game.setScreen(new GameScreen(game));
            }
        }
       if (Gdx.input.justTouched()){ // savedgame
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 1050 && touchPos.x <= 1650 && touchPos.y >= 550 && touchPos.y <=  850) {
                game.setScreen(new GameScreen(game));
            }
        }

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
