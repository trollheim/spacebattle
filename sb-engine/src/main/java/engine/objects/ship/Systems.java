package engine.objects.ship;

public enum Systems {
    Engines, Shields, Scanners, Weapons, Repair;


    public static Systems find(String systemName) {
        for (Systems system : Systems.values()) {
            if (system.name().toLowerCase().equals(systemName)) {
                return system;
            }

        }
        return null;
    }
}
