package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WinScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture GoToHomeTexture;
    private Sprite GoToHomeSprite;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Viewport viewport;

    public WinScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("won.png"));
        GoToHomeTexture = new Texture(Gdx.files.internal("gotohome.png"));

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(2048, 1152, camera);

        GoToHomeSprite = new Sprite(GoToHomeTexture);

        GoToHomeSprite.setSize(3, 1);

        GoToHomeSprite.setPosition(3.5f, 2.5f);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        GoToHomeSprite.draw(spriteBatch);
        spriteBatch.end();
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
        spriteBatch.dispose();
        backgroundTexture.dispose();
        GoToHomeTexture.dispose();
    }
}
