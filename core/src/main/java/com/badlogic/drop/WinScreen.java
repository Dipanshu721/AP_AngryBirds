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
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        GoToHomeSprite.draw(spriteBatch);
        spriteBatch.end();

        if (Gdx.input.isTouched()){
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 780 && touchPos.x <= 1292 && touchPos.y >= 650 && touchPos.y <= 794) {// enter level 1 game
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
        spriteBatch.dispose();
        backgroundTexture.dispose();
        GoToHomeTexture.dispose();
    }
}
