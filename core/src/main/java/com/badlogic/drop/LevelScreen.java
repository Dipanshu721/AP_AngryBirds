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

    public LevelScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("LEVELS.png"));
        play1Texture = new Texture(Gdx.files.internal("1.png"));
        play2Texture = new Texture(Gdx.files.internal("2.png"));
        play3Texture = new Texture(Gdx.files.internal("3.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(1946, 1094, camera);

        play1Sprite = new Sprite(play1Texture);
        play2Sprite = new Sprite(play2Texture);
        play3Sprite = new Sprite(play3Texture);

        play1Sprite.setSize(150, 150);
        play2Sprite.setSize(150, 150);
        play3Sprite.setSize(150, 150);

        play1Sprite.setPosition(480, 600);
        play2Sprite.setPosition(960, 600);
        play3Sprite.setPosition(1420, 600);
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

        if (Gdx.input.isTouched()){
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 480 && touchPos.x <= 630 && touchPos.y >= 600 && touchPos.y <= 750) {// enter level 1 game
                game.setScreen(new StartScreen(game));
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
