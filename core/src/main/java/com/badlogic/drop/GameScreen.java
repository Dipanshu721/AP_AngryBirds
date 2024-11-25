//package com.badlogic.drop;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Circle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.*;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//public class GameScreen implements Screen {
//    private final Main game;
//    // Core Textures
//    private Texture backgroundTexture;
//    private Texture pauseTexture;
//    private Texture RetryTexture;
//    private Texture SaveTexture;
//
//    private redbird redBird;
//    private blackbird blackBird;
//    private yellowbird yellowBird;
//
//    private kingPiggy kingPiggy;
//    private WoodStructure woodStructure;
//    private SteelStructure steelStructure;
//    private  IceStructure iceStructure;
//
//    // Utility Objects
//    private final Vector2 touchPos = new Vector2();
//    private Circle pauseCircle;
//    private Circle RetryCircle;
//    private Circle SaveCircle;
//
//    // Camera and Viewport
//    private OrthographicCamera camera;
//    private Viewport viewport;
//
//    private World world; // World is physics simulation environment it holds physics bodies, simulation/movement and interaction in BOX2D
//    private Box2DDebugRenderer debugRenderer;
//    private float accumulator = 0;
//
//    // Define constants for physics updates
//    public static class Constants {
//        public static final float TIME_STEP = 1 / 60f; // Fixed time step (60 frames/second)
//        public static final int VELOCITY_ITERATIONS = 6; // Velocity solver iterations
//        public static final int POSITION_ITERATIONS = 2; // Position solver iterations
//    }
//    private void doPhysicsStep(float deltaTime) { // this fn controls physics simulation updates with fixed timestep
//        float frameTime = Math.min(deltaTime, 0.25f); // Avoid large frame jumps
//        accumulator += frameTime;
//
//        while (accumulator >= Constants.TIME_STEP) {
//            world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
//            accumulator -= Constants.TIME_STEP;
//        }
//    }
//    public GameScreen(Main game) {
//        this.game = game;
//        world = new World(new Vector2(0, -10), true);
//        debugRenderer = new Box2DDebugRenderer();
//        create();
//    }
//
//    public void create() {
//        // Textures
//        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
//        pauseTexture = new Texture(Gdx.files.internal("pausebutton.png"));
//        Texture slingshotTexture = new Texture(Gdx.files.internal("Slingshot.png"));
//        RetryTexture = new Texture(Gdx.files.internal("retry.png"));
//        SaveTexture = new Texture(Gdx.files.internal("save.png"));
//
//        // Birds;
//        redBird = new redbird();
//        blackBird = new blackbird();
//        yellowBird = new yellowbird();
//
//        // Pigs and Structures
//        kingPiggy = new kingPiggy();
//        woodStructure = new WoodStructure();
//        steelStructure = new SteelStructure();
//        iceStructure = new IceStructure();
//
//        // Camera and Viewport
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(2560, 1440, camera);
//
//        // Circles for buttons
//        float radius = 80; // Approximate radius based on button sizes
//        pauseCircle = new Circle(80, 1360, radius);
//        RetryCircle = new Circle(260, 1360, radius);
//        SaveCircle = new Circle(520, 1370, radius);
//    }
//
//
//    @Override
//    public void show() {
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height, true);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(Color.BLACK);
//        viewport.apply();
//        game.getbatch().setProjectionMatrix(camera.combined);
//
//        game.getbatch().begin();
//        // Background
//        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        // Pause Button
//        game.getbatch().draw(pauseTexture, 0, 1250, 160, 160);
//        // Slingshot
//        game.getbatch().draw(new Texture("Slingshot.png"), 410, 160, 200, 200);
//        // Retry Button
//        game.getbatch().draw(RetryTexture, 180, 1250, 160, 160);
//        // Save Button
//        game.getbatch().draw(SaveTexture, 340, 1255, 247, 143);
//
//        // Birds
//        game.getbatch().draw(redBird.getFace(), 100, 180, 100, 100);
//        game.getbatch().draw(blackBird.getFace(), 200, 195, 90, 90);
//        game.getbatch().draw(yellowBird.getFace(), 300, 195, 80, 80);
//
//        // Pigs
//        game.getbatch().draw(kingPiggy.getFace(), 2100, 290, 100, 100);
//
//        // Structures
//        game.getbatch().draw(woodStructure.getFace(), 2000, 190, 100, 100);
//        game.getbatch().draw(steelStructure.getFace(), 2100, 190, 100, 100);
//        game.getbatch().draw(iceStructure.getFace(), 2200, 190, 100, 100);
//        game.getbatch().end();
//
//        if (Gdx.input.isTouched()){
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
//            viewport.unproject(touchPos);
//
//            if (pauseCircle.contains(touchPos.x, touchPos.y)) {
//                game.setScreen(new PausedScreen(game));
//            }
//        }
//        if (Gdx.input.isTouched()){
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
//            viewport.unproject(touchPos);
//
//            if (RetryCircle.contains(touchPos.x, touchPos.y)) {
//                game.setScreen(new GameScreen(game));
//            }
//
//            if (SaveCircle.contains(touchPos.x, touchPos.y)) {
//                game.setScreen(new GameScreen(game));
//            }
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            game.setScreen(new WinScreen(game));
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
//            game.setScreen(new SampleBallScreen(game));
//        }
//
//        world.step(1/60f, 6,2); // stepping which updates world objects through time
//        // what is does here is update bird trajectory, sr. movement, object falling. TO SAY updating physics each frame
//        //velocity and position iterations define quality of simulation
//        debugRenderer.render(world, camera.combined); // to render physics bodies
//
//    }
//
//    @Override
//    public void hide() {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void dispose() {
//        backgroundTexture.dispose();
//        pauseTexture.dispose();
//        redBird.dispose();
//        blackBird.dispose();
//        yellowBird.dispose();
//        kingPiggy.dispose();
//        world.dispose();
//        debugRenderer.dispose();
//    }
//}
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

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final Main game;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    // Textures for UI and game objects
    private Texture backgroundTexture;
    private Texture pauseTexture;
    private Texture retryTexture;
    private Texture saveTexture;

    private Texture redBirdTexture;
    private Texture blackBirdTexture;
    private Texture yellowBirdTexture;
    private Texture kingPiggyTexture;
    private Texture normalPiggyTexture;
    private Texture woodStructureTexture;
    private Texture iceStructureTexture;
    private Texture steelStructureTexture;

    // Birds
    private List<Bird> birds;

    // Pigs
    private List<Piggy> piggies;

    // Structures
    private List<Structure> structures;

    // Utility objects for buttons
    private Circle pauseCircle;
    private Circle retryCircle;
    private Circle saveCircle;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(Main game) {
        this.game = game;
        create();
    }

    public void create() {
        // Load textures
        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
        pauseTexture = new Texture(Gdx.files.internal("pausebutton.png"));
        retryTexture = new Texture(Gdx.files.internal("retry.png"));
        saveTexture = new Texture(Gdx.files.internal("save.png"));
        redBirdTexture = new Texture(Gdx.files.internal("redbird.png"));
        blackBirdTexture = new Texture(Gdx.files.internal("blackbird.png"));
        yellowBirdTexture = new Texture(Gdx.files.internal("yellowbird.png"));
        kingPiggyTexture = new Texture(Gdx.files.internal("kingpiggy.png"));
        normalPiggyTexture = new Texture(Gdx.files.internal("normalpiggy.png"));
        woodStructureTexture = new Texture(Gdx.files.internal("wood.png"));
        iceStructureTexture = new Texture(Gdx.files.internal("glass.png"));
        steelStructureTexture = new Texture(Gdx.files.internal("stone.png"));

        // Setup camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(2560, 1440, camera);
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();

        // Initialize button areas
        float radius = 80;
        pauseCircle = new Circle(80, 1360, radius);
        retryCircle = new Circle(260, 1360, radius);
        saveCircle = new Circle(520, 1370, radius);

        // Initialize birds
        assembleBirds();

        // Initialize pigs
        assemblePigs();

        // Initialize structures
        assembleStructures();

        createGround();
    }

    private void assembleBirds() {
        birds = new ArrayList<>();
        birds.add(new redbird(100, 205, world));
        birds.add(new blackbird(200, 205, world));
        birds.add(new yellowbird(300, 205, world));
    }

    private void assemblePigs() {
        piggies = new ArrayList<>();
        piggies.add(new normalPiggy(2025, 670, world));
        piggies.add(new normalPiggy(2025, 950, world));
    }

    private void assembleStructures() {
        structures = new ArrayList<>();
        structures.add(new WoodStructure(1950, 350, 40, 300, world));
        structures.add(new WoodStructure(2160, 350, 40, 300, world));
        structures.add(new WoodStructure(1950, 650, 250, 40, world));
        structures.add(new WoodStructure(1950, 650, 40, 300, world));
        structures.add(new WoodStructure(2160, 650, 40, 300, world));
        structures.add(new WoodStructure(1950, 950, 250, 40, world));

        structures.add(new SteelStructure(1700, 290, 250, 60, world));
        structures.add(new SteelStructure(1950, 290, 250, 60, world));
        structures.add(new SteelStructure(2200, 290, 250, 60, world));
        structures.add(new SteelStructure(1700, 230, 250, 60, world));
        structures.add(new SteelStructure(1950, 230, 250, 60, world));
        structures.add(new SteelStructure(2200, 230, 250, 60, world));
    }


    private void createGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(1280, 200); // Center horizontally, small height at bottom

        Body groundBody = world.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(1280, 0); // Width = 2560, Height = 2 (half-dimensions)

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundBody.createFixture(groundFixture);
        groundShape.dispose();
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
        world.step(1 / 60f, 6, 2);
        game.getbatch().begin();

        // Draw background
//        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Draw buttons
        game.getbatch().draw(pauseTexture, 0, 1250, 160, 160);
        game.getbatch().draw(retryTexture, 180, 1250, 160, 160);
        game.getbatch().draw(saveTexture, 340, 1255, 247, 143);

        // Render birds
        for (Bird bird : birds) {
            bird.render(game.getbatch());
        }

// Render pigs
        for (Piggy piggy : piggies) {
            piggy.render(game.getbatch());
        }

// Render structures
        for (Structure structure : structures) {
            structure.render(game.getbatch());
        }

        game.getbatch().end();

        handleInput();
        // Render physics bodies
        debugRenderer.render(world, camera.combined);
    }


    private void handleInput() {
        // Handle touch input
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (pauseCircle.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new PausedScreen(game));
            }
            if (retryCircle.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game));
            }
            if (saveCircle.contains(touchPos.x, touchPos.y)) {
                //need to  Handle save logic here
                Gdx.app.log("Save", "Game saved!");
            }
        }

        // Win screen shortcut for testing
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.setScreen(new WinScreen(game));
        }
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        pauseTexture.dispose();
        retryTexture.dispose();
        saveTexture.dispose();

        for (Bird bird : birds) {
            bird.dispose();
        }

        for (Piggy piggy : piggies) {
            piggy.dispose();
        }

        for (Structure structure : structures) {
            structure.dispose();
        }
    }

    @Override
    public void show() {
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
}
