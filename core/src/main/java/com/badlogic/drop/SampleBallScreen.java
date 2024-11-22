package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SampleBallScreen implements Screen {
    private final Main game;
    private World world; // Physics simulation world
    private Body ballBody;
    private Body groundBody;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    public SampleBallScreen(Main game) {
        this.game = game;
        create();
    }

    private void create() {
        // Initialize world with gravity
        world = new World(new Vector2(0, -9.8f), true);

        // Debug renderer to visualize physics bodies
        debugRenderer = new Box2DDebugRenderer();

        // Setup camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(16, 9, camera); // 16:9 aspect ratio

        // Create ball body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(1f, 7.0f); // Place above the ground
        ballBody = world.createBody(bodyDef);

        // Define the ball's shape and physics
        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(0.5f); // Radius in meters

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f; // Make it bouncy

        assert ballBody != null;
        ballBody.createFixture(fixtureDef);
        ballShape.dispose(); // Clean up shape

        //ground
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(camera.viewportWidth / 2, 0.75f); // Half the height of the ground
        groundBody = world.createBody(bodyDef);

        fixtureDef.friction =.5f;
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth/2, 0.75f);

        assert groundBody != null;
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

    }

    @Override
    public void show() {
        // No additional initialization needed
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK); // Clear screen
        viewport.apply();

        // Update physics simulation
        world.step(1 / 60f, 6, 2);

        // Render debug information
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void hide() {
        // Cleanup or pause logic if needed
    }

    @Override
    public void pause() {
        // Pause-specific logic if needed
    }

    @Override
    public void resume() {
        // Resume-specific logic if needed
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}
