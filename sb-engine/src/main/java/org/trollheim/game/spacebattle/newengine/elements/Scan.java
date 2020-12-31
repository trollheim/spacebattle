package org.trollheim.game.spacebattle.newengine.elements;


import java.util.List;

public class Scan {

    final List<DetectedObjects> detectedObjects;

    public Scan(List<DetectedObjects> detectedObjects) {
        this.detectedObjects = detectedObjects;
    }
}
