package com.badlogic.drop;

import java.util.List;

public class LevelComplete {

    public static boolean checkWin(List<Piggy> piggies) {
        // Check if all pigs are destroyed
        for (Piggy pig : piggies) {
            if (!pig.isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLose(int currentBirdIndex, int totalBirds) {
        // Check if all birds are used up
        return currentBirdIndex >= totalBirds;
    }
}
