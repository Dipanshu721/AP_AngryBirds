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

public class GameScreen2 implements Screen {
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

    public GameScreen2(Main game) {
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

        // create ground
        createGround();
    }

    private void assembleBirds() {
        birds = new ArrayList<>();
        birds.add(new redbird(100, 555, world));
        birds.add(new blackbird(200, 205, world));
        birds.add(new yellowbird(300, 205, world));
    }

    private void assemblePigs() {
        piggies = new ArrayList<>();
        piggies.add(new normalPiggy(1930, 200, world));
        piggies.add(new normalPiggy(1930, 400, world));
    }

    private void assembleStructures() {
        structures = new ArrayList<>();
        // Adding stone structures
        structures.add(new SteelStructure(1800, 255, 45.5f, 150f, world));
        structures.add(new SteelStructure(2050, 255, 45.5f, 150f, world));
        structures.add(new SteelStructure(1925, 342, 300f, 45.5f, world));

        // Adding wood structures
        structures.add(new WoodStructure(1800, 500, 45.5f, 150f, world));
        structures.add(new WoodStructure(2050, 500, 45.5f, 150f, world));
        structures.add(new WoodStructure(1925, 555, 300f, 45.5f, world));
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
