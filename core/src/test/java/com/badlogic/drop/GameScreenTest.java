//package com.badlogic.drop;
//
//import static org.mockito.Mockito.*;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.math.Vector2;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.*;
//
//public class GameScreenTest {
//
//    @Mock
//    private Main game;
//
//    @Mock
//    private GameScreen gameScreen;
//
//
//    @Mock
//    private PausedScreen pausedScreen;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        gameScreen = new GameScreen(game);
//        gameScreen.create(); // Initialize the screen, loading textures, etc.
//    }
//
//    @Test
//    public void testPauseButtonClick() {
//        // Set up the input mock
//        Vector2 mockTouchPos = new Vector2(10, 135); // Coordinates for the pause button
//        when(Gdx.input.isTouched()).thenReturn(true);
//        when(Gdx.input.getX()).thenReturn((int) mockTouchPos.x);
//        when(Gdx.input.getY()).thenReturn((int) mockTouchPos.y);
//
//        // Simulate the game screen's render and input handling
//        gameScreen.render(0.016f); // Delta time for the test
//
//        // Verify that the game.setScreen method was called with the PausedScreen
//        verify(game).setScreen(any(PausedScreen.class)); // We expect this to be called
//    }
//
//    @Test
//    public void testResumeButtonClick() {
//        game.setScreen(pausedScreen);
//
//        Vector2 mockTouchPos = new Vector2(10, 135);
//        when(Gdx.input.isTouched()).thenReturn(true);
//        when(Gdx.input.getX()).thenReturn((int) mockTouchPos.x);
//        when(Gdx.input.getY()).thenReturn((int) mockTouchPos.y);
//
//        pausedScreen.render(0.016f);
//
//        // Verify that the game returns to the game screen
//        verify(game).setScreen(gameScreen); // We expect the game to return to the GameScreen
//    }
//}
