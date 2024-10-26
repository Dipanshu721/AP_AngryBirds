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
        viewport = new FitViewport(2048, 1152, camera);

        GoToHomeSprite = new Sprite(GoToHomeTexture);
        GoToHomeSprite.setSize(2.5f, 1);
        GoToHomeSprite.setPosition(3.2f, 2.5f);
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

            if (touchPos.x >= 3.2f && touchPos.x <= 5.7f && touchPos.y >= 2.5f && touchPos.y <= 3.5f) {// go to level screen
                game.setScreen(new LevelScreen(game));
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
