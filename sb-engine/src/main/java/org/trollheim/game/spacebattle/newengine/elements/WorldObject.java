package org.trollheim.game.spacebattle.newengine.elements;

import org.trollheim.commons.math.Point;

public abstract class WorldObject {


    protected WorldObject(Long id, Team team) {
        this.id = id;
        this.team = team;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    protected Point position;

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    protected float size;


    public Long getId() {
        return id;
    }

    protected final Long id;
    protected final Team team;



}
