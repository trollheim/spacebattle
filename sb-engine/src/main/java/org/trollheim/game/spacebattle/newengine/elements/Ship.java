package org.trollheim.game.spacebattle.newengine.elements;

public class Ship extends WorldObject {

    public Ship(Long id, Team team,ShipStrategy shipStrategy) {
        super(id, team);
        this.team = team;
        this.shipStrategy=shipStrategy;
    }

    private final Team team;

    public ShipStrategy getShipStrategy() {
        return shipStrategy;
    }

    private final ShipStrategy shipStrategy;
    public Boost getBoost() {
        return boost;
    }

    private final Boost boost = new Boost();

    public Team getTeam() {
        return team;
    }


}
