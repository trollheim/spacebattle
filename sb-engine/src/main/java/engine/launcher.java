package engine;

import engine.objects.ship.ShipStrategy;
import engine.simulation.Simulation;
import engine.strategies.Lurker;
import engine.strategies.Speeder;
import engine.views.SimpleConsoleView;

import java.security.Permission;
import java.util.Arrays;
import java.util.List;

public class launcher {

    static {
        final SecurityManager securityManager = new SecurityManager() {
            public void checkPermission(Permission permission) {
                // System.err.println(permission.getName()
                // +" "+permission.getActions());
                if (permission.getName() != null
                        && permission.getName().startsWith("exitVM")) {
                    throw new SecurityException();
                }
            }

        };
        System.setSecurityManager(securityManager);
    }

    public static void main(String[] args) {

        Simulation simulaton = new Simulation(new SimpleConsoleView());
		List<ShipStrategy> strategies = Arrays.asList(new Lurker(), new Speeder()// ,
				// new
				// Lurker(),
				// new
				// Speeder()
		);
        simulaton.runSimulation( strategies);
		System.out.println("done");
    }

}
