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

public class PausedScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture ResumeTexture;
    private Texture ExitGameTexture;
    private Sprite ResumeSprite;
    private Sprite ExitGameSprite;

    private OrthographicCamera camera;
    private Viewport viewport;

    public PausedScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("pauseScreen.png"));
        ResumeTexture = new Texture(Gdx.files.internal("resumebutton.png"));
        ExitGameTexture = new Texture(Gdx.files.internal("exitgamebutton.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(2048, 1152, camera);

        ResumeSprite = new Sprite(ResumeTexture);
        ExitGameSprite = new Sprite(ExitGameTexture);

        ResumeSprite.setSize(512, 144);
        ExitGameSprite.setSize(512, 144);

        ResumeSprite.setPosition(400, 650);
        ExitGameSprite.setPosition(1050, 650);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {

        game.getbatch().begin();
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        game.getbatch().setProjectionMatrix(camera.combined);
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        ResumeSprite.draw(game.getbatch());
        ExitGameSprite.draw(game.getbatch());
        game.getbatch().end();

        if (Gdx.input.isTouched()){             // touch for resume
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 400 && touchPos.x <= 912 && touchPos.y >=650 && touchPos.y <= 794) {
                game.setScreen(new GameScreen(game));
            }
        }

        if (Gdx.input.isTouched()){             // touch to exit
            Vector2 touchPos = new Vector2();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (touchPos.x >= 1050 && touchPos.x <= 1652 && touchPos.y >= 650 && touchPos.y <= 794) {
                game.setScreen(new LostScreen(game));
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
        ResumeTexture.dispose();
        ExitGameTexture.dispose();
    }
}
