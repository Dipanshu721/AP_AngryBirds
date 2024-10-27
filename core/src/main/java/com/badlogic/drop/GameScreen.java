package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final Main game;
    private Texture backgroundTexture;
    private Texture pauseTexture;
    private Sprite pauseSprite;
    private Sprite SlingshotSprite;
    private final Vector2 touchPos = new Vector2();
    private Circle pauseCircle;
    private redbird redBird;
    private blackbird blackBird;
    private yellowbird yellowBird;
    private Sprite redbirdSprite;
    private Sprite blackbirdSprite;
    private Sprite yellowbirdSprite;
    private kingPiggy kingPiggy;
    private Sprite kingPigSprite;
    private WoodStructure woodStructure;
    private SteelStructure steelStructure;
    private IceStructure iceStructure;
    private Sprite iceSprite;
    private Sprite steelSprite;
    private Sprite woodSprite;
    private Texture RetryTexture;
    private Sprite RetrySprite;
    private Circle RetryCircle;
    private Texture SaveTexture;
    private Sprite SaveSprite;
    private Circle SaveCircle;

    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(Main game) {
        this.game = game;
        create();
    }

    public void create(){
        redBird = new redbird();
        redbirdSprite = new Sprite(redBird.getFace());
        blackBird = new blackbird();
        blackbirdSprite = new Sprite(blackBird.getFace());
        yellowBird = new yellowbird();
        yellowbirdSprite = new Sprite(yellowBird.getFace());

        kingPiggy = new kingPiggy();
        kingPigSprite = new Sprite(kingPiggy.getFace());

        woodStructure = new WoodStructure();
        steelStructure = new SteelStructure();
        iceStructure = new IceStructure();
        woodSprite = new Sprite(woodStructure.getFace());
        steelSprite = new Sprite(steelStructure.getFace());
        iceSprite = new Sprite(iceStructure.getFace());

        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
        pauseTexture = new Texture(Gdx.files.internal("pausebutton.png"));
        Texture slingshotTexture = new Texture(Gdx.files.internal("Slingshot.png"));
        RetryTexture = new Texture(Gdx.files.internal("retry.png"));
        SaveTexture = new Texture(Gdx.files.internal("save.png"));

        camera = new OrthographicCamera();
        viewport = new FitViewport(2560, 1440, camera);

        pauseSprite = new Sprite(pauseTexture);
        SlingshotSprite = new Sprite(slingshotTexture);
        RetrySprite =  new Sprite(RetryTexture);
        SaveSprite =  new Sprite(SaveTexture);

        pauseSprite.setSize(160, 160);
        SlingshotSprite.setSize(200, 200);
        RetrySprite.setSize(160,160);
        SaveSprite.setSize(247,143);

        RetrySprite.setPosition(180,1250);
        SaveSprite.setPosition(340,1255);
        pauseSprite.setPosition(0, 1250);
        SlingshotSprite.setPosition(410, 160);

        redbirdSprite.setPosition(100, 180);
        redbirdSprite.setSize(100, 100);
        blackbirdSprite.setPosition(200,195);
        blackbirdSprite.setSize(90,90);
        yellowbirdSprite.setPosition(300,195);
        yellowbirdSprite.setSize(80,80);

        kingPigSprite.setPosition(2100,290);
        kingPigSprite.setSize(100, 100);

        woodSprite.setSize(100,100);
        woodSprite.setPosition(2000,190);
        steelSprite.setSize(100,100);
        steelSprite.setPosition(2100,190);
        iceSprite.setSize(100,100);
        iceSprite.setPosition(2200,190);

        float radius = pauseSprite.getWidth()/2;
        pauseCircle = new Circle(pauseSprite.getX() + radius,pauseSprite.getY()+radius, radius);
        RetryCircle = new Circle(RetrySprite.getX() + radius, RetrySprite.getY()+radius, radius);
        SaveCircle = new Circle(SaveSprite.getX() + radius, SaveSprite.getY()+radius, radius);
    }

    @Override
    public void show() {
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
        pauseSprite.draw(game.getbatch());
        SlingshotSprite.draw(game.getbatch());
        RetrySprite.draw(game.getbatch());
        SaveSprite.draw(game.getbatch());

        redbirdSprite.draw(game.getbatch());
        blackbirdSprite.draw(game.getbatch());
        yellowbirdSprite.draw(game.getbatch());

        kingPigSprite.draw(game.getbatch());

        steelSprite.draw(game.getbatch());
        iceSprite.draw(game.getbatch());
        woodSprite.draw(game.getbatch());

        game.getbatch().end();

        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (pauseCircle.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new PausedScreen(game));
            }
        }

        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (RetryCircle.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game));
            }

            if (SaveCircle.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.setScreen(new WinScreen(game));
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
        pauseTexture.dispose();
        redBird.dispose();
        blackBird.dispose();
        yellowBird.dispose();
        kingPiggy.dispose();
    }
}
