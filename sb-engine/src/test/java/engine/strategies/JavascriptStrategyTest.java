package engine.strategies;

import engine.objects.ship.Action;
import engine.objects.ship.ShipStatus;
import engine.objects.ship.Systems;
import org.junit.Assert;
import org.junit.Test;
import org.trollheim.commons.math.Vector2d;

import javax.script.ScriptException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class JavascriptStrategyTest {
    //engine

    @Test
    public void getActions() throws ScriptException {
        String script = "var execRound = function(params){\nreturn {'engines': {'power' : 100,'direction': {'x':1,'y':1.0}} };\n}";
        JavascriptStrategy strategy = new JavascriptStrategy("js", script);
        Queue<Action> result = strategy.getActions(new ShipStatus(100, 100, 100, Collections.emptyList()));
        List<Action> list = result.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Assert.assertEquals(1, list.size());
        Action action = list.get(0);
        Assert.assertEquals(Systems.Engines, action.getSystem());
        Assert.assertEquals(100, action.getPower());
        assertEquals(new Vector2d(1,1),action.getOptional());

    }
}