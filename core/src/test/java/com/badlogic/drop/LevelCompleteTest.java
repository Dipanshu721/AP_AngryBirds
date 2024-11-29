//package com.badlogic.drop;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LevelCompleteTest {
//    private World world;
//    private List<Piggy> piggies;
//
//    @Before
//    public void setup() {
//        // Initialize the Box2D world
//        world = new World(new Vector2(0, -9.8f), true);
//        piggies = new ArrayList<>();
//
//        // Add some piggies to the list
//        piggies.add(new normalPiggy(195, 45, world)); // Piggy 1
//    }
//
//    @Test
//    public void testCheckWin_AllPiggiesDestroyed() {
//        // Simulate all piggies being destroyed
//        for (Piggy piggy : piggies) {
//            piggy.setDestroyed(true);
//        }
//
//        // Verify win condition
//        assertTrue(LevelComplete.checkWin(piggies));
//    }
//
//    @Test
//    public void testCheckWin_SomePiggiesRemaining() {
//        // Simulate only one piggy being destroyed
//        piggies.get(0).setDestroyed(true);
//        piggies.get(1).setDestroyed(false);
//
//        // Verify win condition
//        assertFalse(LevelComplete.checkWin(piggies));
//    }
//
//    @Test
//    public void testCheckWin_NoPiggiesDestroyed() {
//        // Simulate no piggies being destroyed
//        for (Piggy piggy : piggies) {
//            piggy.setDestroyed(false);
//        }
//
//        // Verify win condition
//        assertFalse(LevelComplete.checkWin(piggies));
//    }
//}
