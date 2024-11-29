package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class GameScreen2 implements Screen, BirdLauncher {
    private final Main game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;
    private CollisionHandler collisionHandler;

    // Textures for UI and game objects
    private Texture backgroundTexture, pauseTexture, retryTexture, saveTexture, nextButtonTexture;
    private Rectangle nextButtonRect;
    private int birdsRemaining;
    private static int pigsRemaining;
    private Bird currentBird;

    private Texture SlingshotTexture;

    // Birds
    private List<Bird> birds;
    // Pigs
    private List<Piggy> piggies;
    // Structures
    private List<Structure> structures;

    // Utility objects for buttons
    private Circle pauseCircle, retryCircle, saveCircle;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen2(Main game) {
        this.game = game;
        create();
    }

    public void create() {
        // Load texture
        backgroundTexture = new Texture(Gdx.files.internal("gamescreen.jpg"));
        pauseTexture = new Texture(Gdx.files.internal("pausebutton.png"));
        retryTexture = new Texture(Gdx.files.internal("retry.png"));
        saveTexture = new Texture(Gdx.files.internal("save.png"));
        Texture redBirdTexture = new Texture(Gdx.files.internal("redbird.png"));
        Texture blackBirdTexture = new Texture(Gdx.files.internal("blackbird.png"));
        Texture yellowBirdTexture = new Texture(Gdx.files.internal("yellowbird.png"));
        Texture kingPiggyTexture = new Texture(Gdx.files.internal("kingpiggy.png"));
        Texture normalPiggyTexture = new Texture(Gdx.files.internal("normalpiggy.png"));
        Texture woodStructureTexture = new Texture(Gdx.files.internal("wood.png"));
        Texture iceStructureTexture = new Texture(Gdx.files.internal("glass.png"));
        Texture steelStructureTexture = new Texture(Gdx.files.internal("stone.png"));
        SlingshotTexture = new Texture(Gdx.files.internal("Slingshot.png"));

        // Setup camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(256, 144, camera);
        world = new World(new Vector2(0, -9.8f), true);
        debugRenderer = new Box2DDebugRenderer();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        collisionHandler = new CollisionHandler();
        world.setContactListener(collisionHandler);

        // Initialize button areas
        float radius = 8;
        pauseCircle  = new Circle(10,  135, radius);
        retryCircle  = new Circle(28, 135, radius);
        saveCircle   = new Circle(44, 135, radius);
        nextButtonTexture = new Texture("next_button.png");
        nextButtonRect = new Rectangle(220, 130, 26, 10); // Adjust position and size

        birds = new ArrayList<>();// Initialize birds
        assembleBirds();
        piggies = new ArrayList<>();// Initialize pigs
        assemblePigs();
        assembleStructures();// Initialize structures

        birdsRemaining = birds.size();
        pigsRemaining = piggies.size();

        for (Bird bird : birds) {
            if (!bird.isLaunched()) {
                bird.setPosition(50, 40);
                Slingshot slingshot = new Slingshot(bird);
                stage.addActor(slingshot);// Add the slingshot for each bird
                birdsRemaining--;
                break;
            }
        }
        createGround();
    }

    private void assembleBirds() {
        birds.add(new yellowbird(35, 25, world));
        birds.add(new blackbird(22,  25, world));
        birds.add(new redbird(8,     25, world));
    }
    private void assemblePigs() {
        piggies = new ArrayList<>();
        piggies.add(new oldPiggy(195, 65, world));
        piggies.add(new normalPiggy(182, 29, world));
        piggies.add(new normalPiggy(208, 29, world));
    }
    private void assembleStructures() {
        structures = new ArrayList<>();
        structures.add(new SteelStructure(170, 29, 6, 30, world));
        structures.add(new SteelStructure(195, 29, 6, 30, world));
        structures.add(new SteelStructure(220, 29, 6, 30, world));

        structures.add(new WoodStructure(195, 60, 60, 4, world));

        structures.add(new SteelStructure(182, 65, 6, 30, world));
        structures.add(new SteelStructure(208, 65, 6, 30, world));

        structures.add(new WoodStructure(195, 90, 30, 4, world));

        structures.add(new SteelStructure(195, 95, 6, 30, world));
    }
    private void createGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(128, 20); // Center horizontally, small height at bottom

        Body groundBody = world.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(128, 0); // Width = 2560, Height = 2 (half-dimensions)

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
        collisionHandler.processQueuedDestruction(world);

        game.getbatch().begin();
        // Draw background
        game.getbatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        // Draw buttons
        game.getbatch().draw(pauseTexture, 0, 125, 16, 16);
        game.getbatch().draw(retryTexture, 18, 125, 16, 16);
        game.getbatch().draw(saveTexture, 34, 125f, 24.7f, 14.3f);
        game.getbatch().draw(SlingshotTexture, 40, 18f, 25, 25);
        game.getbatch().draw(nextButtonTexture, 220, 130, 26, 10);

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
        stage.act(delta);
        stage.draw();

        handleInput();
        debugRenderer.render(world, camera.combined); // Render physics bodies
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
                game.setScreen(new GameScreen2(game));
            }
            if (saveCircle.contains(touchPos.x, touchPos.y)) {
                Gdx.app.log("Save", "Game saved!");
            }
            if (birdsRemaining == 0 || pigsRemaining == 0) {
                if (nextButtonRect.contains(touchPos.x, touchPos.y)) {
                    if (pigsRemaining == 0) {
                        game.setScreen(new WinScreen(game));
                    } else if (birdsRemaining == 0) {
                        game.setScreen(new LostScreen(game));
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {  // Detect `N` key press
            launchNextBird();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.setScreen(new WinScreen(game));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // Check if the current bird has a special ability
            if (currentBird != null && currentBird.isLaunched()) {
                if (currentBird instanceof yellowbird) {
                    ((yellowbird) currentBird).activateSpecialAbility();
                } else if (currentBird instanceof blackbird) {
                    ((blackbird) currentBird).activateSpecialAbility();
                }
            }
        }
    }
    @Override
    public void launchNextBird() {
        if (birdsRemaining > 0) {
            currentBird= birds.get(birds.size() - birdsRemaining);
            currentBird.setPosition(50, 40);
            Slingshot slingshot = new Slingshot(currentBird);
            stage.addActor(slingshot);
            birdsRemaining--;
        }

    }
    public static void pigDestroyed() {
        pigsRemaining--;
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        pauseTexture.dispose();
        retryTexture.dispose();
        saveTexture.dispose();
        nextButtonTexture.dispose();

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
        Gdx.input.setInputProcessor(stage);
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
