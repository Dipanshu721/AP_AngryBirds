package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final Main game;
    // Core Textures
    private Texture backgroundTexture;
    private Texture pauseTexture;
    private Texture RetryTexture;
    private Texture SaveTexture;

    private redbird redBird;
    private blackbird blackBird;
    private yellowbird yellowBird;

    private kingPiggy kingPiggy;
    private WoodStructure woodStructure;
    private SteelStructure steelStructure;
    private  IceStructure iceStructure;

    // Utility Objects
    private final Vector2 touchPos = new Vector2();
    private Circle pauseCircle;
    private Circle RetryCircle;
    private Circle SaveCircle;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    private World world; // World is physics simulation environment it holds physics bodies, simulation/movement and interaction in BOX2D
    private Box2DDebugRenderer debugRenderer;
    private float accumulator = 0;

    // Define constants for physics updates
    public static class Constants {
        public static final float TIME_STEP = 1 / 60f; // Fixed time step (60 frames/second)
        public static final int VELOCITY_ITERATIONS = 6; // Velocity solver iterations
        public static final int POSITION_ITERATIONS = 2; // Position solver iterations
    }
    private void doPhysicsStep(float deltaTime) { // this fn controls physics simulation updates with fixed timestep
        float frameTime = Math.min(deltaTime, 0.25f); // Avoid large frame jumps
        accumulator += frameTime;

        while (accumulator >= Constants.TIME_STEP) {
            world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
            accumulator -= Constants.TIME_STEP;
        }
    }
    public GameScreen(Main game) {
        this.game = game;
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        create();
    }

    public void create() {
        // Textures
        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
        pauseTexture = new Texture(Gdx.files.internal("pausebutton.png"));
        Texture slingshotTexture = new Texture(Gdx.files.internal("Slingshot.png"));
        RetryTexture = new Texture(Gdx.files.internal("retry.png"));
        SaveTexture = new Texture(Gdx.files.internal("save.png"));

        // Birds;
        redBird = new redbird();
        blackBird = new blackbird();
        yellowBird = new yellowbird();

        // Pigs and Structures
        kingPiggy = new kingPiggy();
        woodStructure = new WoodStructure();
        steelStructure = new SteelStructure();
        iceStructure = new IceStructure();

        // Camera and Viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(2560, 1440, camera);

        // Circles for buttons
        float radius = 80; // Approximate radius based on button sizes
        pauseCircle = new Circle(80, 1360, radius);
        RetryCircle = new Circle(260, 1360, radius);
        SaveCircle = new Circle(520, 1370, radius);
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
        // Background
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        // Pause Button
        game.getbatch().draw(pauseTexture, 0, 1250, 160, 160);
        // Slingshot
        game.getbatch().draw(new Texture("Slingshot.png"), 410, 160, 200, 200);
        // Retry Button
        game.getbatch().draw(RetryTexture, 180, 1250, 160, 160);
        // Save Button
        game.getbatch().draw(SaveTexture, 340, 1255, 247, 143);

        // Birds
        game.getbatch().draw(redBird.getFace(), 100, 180, 100, 100);
        game.getbatch().draw(blackBird.getFace(), 200, 195, 90, 90);
        game.getbatch().draw(yellowBird.getFace(), 300, 195, 80, 80);

        // Pigs
        game.getbatch().draw(kingPiggy.getFace(), 2100, 290, 100, 100);

        // Structures
        game.getbatch().draw(woodStructure.getFace(), 2000, 190, 100, 100);
        game.getbatch().draw(steelStructure.getFace(), 2100, 190, 100, 100);
        game.getbatch().draw(iceStructure.getFace(), 2200, 190, 100, 100);
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
        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            game.setScreen(new SampleBallScreen(game));
        }

        world.step(1/60f, 6,2); // stepping which updates world objects through time
        // what is does here is update bird trajectory, sr. movement, object falling. TO SAY updating physics each frame
        //velocity and position iterations define quality of simulation
        debugRenderer.render(world, camera.combined); // to render physics bodies

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
        world.dispose();
        debugRenderer.dispose();
    }
}
