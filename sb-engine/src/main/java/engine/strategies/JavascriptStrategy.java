package engine.strategies;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.ShipStrategy;
import engine.objects.ship.Systems;
import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.trollheim.commons.math.Vector2d;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;


public class JavascriptStrategy implements ShipStrategy {

    private static final String FUNCTION = "execRound";
    private final ScriptEngine engine;
    private String name;
    final NashornScriptEngineFactory fac = new NashornScriptEngineFactory();

    public JavascriptStrategy(String name, String script) throws ScriptException {
        engine = fac.getScriptEngine(new ClassFilter() {
            @Override
            public boolean exposeToScripts(String s) {
                System.out.println(s);
                return false;
            }
        });
//		engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(script);
        this.name = name;

    }

    @Override
    public Queue<Action> getActions(ShipStatus status) {
        try {
            ScriptObjectMirror json = (ScriptObjectMirror) engine.eval("("+status.asString()+")");

            return extract(((Invocable) engine).invokeFunction(FUNCTION, json));
        } catch (ScriptException | NoSuchMethodException e) {
            //TODO
            e.printStackTrace();
        }
        return new ArrayBlockingQueue<Action>(5);
    }

    @Override
    public String getName() {
        return name;
    }

    private Queue<Action> extract(Object eval) {

        ScriptObjectMirror result = ((ScriptObjectMirror) eval);
        List<Action> actions = result.entrySet().stream().map(item -> extractAction(item.getKey(), item.getValue())).collect(Collectors.toList());

        return new LinkedList<>(actions);
    }

    private Action extractAction(String systemname, Object eval) {
        if (systemname == null) {
            return null;
        }
        Systems system = Systems.find(systemname.toLowerCase());
        if (eval == null || system == null) {
            return null;
        }
        ScriptObjectMirror result = ((ScriptObjectMirror) eval);

        int power = (int) result.get("power");
        Object optional = result.get("direction");
        if (optional != null) {
            float x = ((Number) ((ScriptObjectMirror) optional).get("x")).floatValue();
            float y = ((Number) ((ScriptObjectMirror) optional).get("y")).floatValue();
            Vector2d direction = new Vector2d(x, y);
            optional = direction;
        }
        return new Action(system, power, optional);
    }
}

