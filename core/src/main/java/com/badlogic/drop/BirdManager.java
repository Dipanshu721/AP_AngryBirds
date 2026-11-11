//package com.badlogic.drop;
//
//import com.badlogic.gdx.scenes.scene2d.Stage;
//
//import java.util.List;
//
//public class BirdManager {
//    private List<Bird> birds;
//    private int currentBirdIndex;
//    private Stage stage;
//
//    public BirdManager(List<Bird> birds, Stage stage) {
//        this.birds = birds;
//        this.currentBirdIndex = 0;
//        this.stage = stage;
//        initializeFirstBird();
//    }
//
//    private void initializeFirstBird() {
//        if (!birds.isEmpty()) {
//            Bird firstBird = birds.get(0);
//            firstBird.setPosition(50, 40);  // Initial slingshot position
//            Slingshot slingshot = new Slingshot(firstBird, this);
//            stage.addActor(slingshot);
//        }
//    }
//
//    public void launchNextBird() {
//        currentBirdIndex++;
//        if (currentBirdIndex < birds.size()) {
//            Bird nextBird = birds.get(currentBirdIndex);
//            nextBird.setPosition(50, 40);
//            Slingshot slingshot = new Slingshot(nextBird, this);
//            stage.addActor(slingshot);
//        }
//    }
//
//    public boolean areAllBirdsUsed() {
//        return currentBirdIndex >= birds.size();
//    }
//}
