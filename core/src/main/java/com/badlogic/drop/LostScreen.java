package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LostScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture GoToHomeTexture;
    private Sprite GoToHomeSprite;
    private OrthographicCamera camera;
    private Viewport viewport;

    public LostScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("lost.png"));
        GoToHomeTexture = new Texture(Gdx.files.internal("gotohome.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(1946, 1094, camera);

        GoToHomeSprite = new Sprite(GoToHomeTexture);
        GoToHomeSprite.setSize(570, 330);
        GoToHomeSprite.setPosition(700, 500);
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
        GoToHomeSprite.draw(game.getbatch());
        game.getbatch().end();

        if (Gdx.input.isTouched()){
            Vector2 touchPos = new Vector2();

            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 750 && touchPos.x <= 1262 && touchPos.y >= 550 && touchPos.y <= 694) {// go to level screen
                game.setScreen(new HomeScreen(game));
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
        GoToHomeTexture.dispose();
    }
}
