package engine.objects.ship;


import org.trollheim.commons.math.Vector2d;

public final class Action {
    public Systems getSystem() {
        return system;
    }

    public int getPower() {
        return power;
    }

    public Object getOptional() {
        return optional;
    }

    public Action(Systems actionType, int power, Object optional) {

        this.system = actionType;
        this.power = power;
        this.optional = optional;
    }


    private final Systems system;
    private final int power;
    private final Object optional;

    public static Action Move(Vector2d direction, int power) {
        return new Action(Systems.Engines, power, direction);
    }

    public static Action Attack(Vector2d direction, int power) {
        return new Action(Systems.Weapons, power, direction);
    }

    public static Action setShield(int power) {
        return new Action(Systems.Shields, power, null);
    }

    public static Action setScanners(int power) {
        return new Action(Systems.Scanners, power, null);
    }

    public static Action repair(int power) {
        return new Action(Systems.Repair, power, null);
    }

}
