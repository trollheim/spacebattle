package engine.strategies;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.ShipStrategy;
import engine.objects.ship.Systems;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.trollheim.commons.math.Vector2d;

import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class JavascriptStrategy implements ShipStrategy {

    private static final String FUNCTION = "execRound";
    private final Value function;
    private String name;
    Context jsContext = Context.newBuilder("js").allowAllAccess(false).allowIO(false).build();
    public JavascriptStrategy(String name, String script) throws ScriptException {
        function = jsContext.eval("js", script);


        this.name = name;

    }

    @Override
    public Queue<Action> getActions(ShipStatus status) {

        var script = FUNCTION + "(" + status.asString() + ");";
        return extract(jsContext.eval("js", script));

    }

    @Override
    public String getName() {
        return name;
    }

    private Queue<Action> extract(Value eval) {
        var result = eval.as(Map.class);
        var actions = result.entrySet().stream().map(this::extractAction).toList();
        return new LinkedList<>(actions);

    }

    private Action extractAction(Object object) {
        Map.Entry entry = (Map.Entry) object;

        return extractAction(entry.getKey().toString(), entry.getValue());

    }

    private Action extractAction(String systemname, Object eval) {
        if (systemname == null) {
            return null;
        }
        Systems system = Systems.find(systemname.toLowerCase());
        if (eval == null || system == null) {
            return null;
        }
        Map result = ((Map) eval);
//
        int power = (int) result.get("power");
        Object optional = result.get("direction");
        if (optional != null) {
            float x = ((Number) ((Map) optional).get("x")).floatValue();
            float y = ((Number) ((Map) optional).get("y")).floatValue();
            Vector2d direction = new Vector2d(x, y);
            optional = direction;
        }
        return new Action(system, power, optional);
    }


}

