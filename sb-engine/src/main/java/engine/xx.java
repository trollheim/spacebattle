package engine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;



public class xx {

    public static void print(Object o){
        System.out.println(o);

    }
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("nashorn");

        System.out.println(engine.getContext().getScopes());
        engine.eval("i=0;");
        System.out.println(engine.getContext().getScopes());
        Object result = engine.eval("var f = function(){var xx =0; return 11;}");
        System.out.println(engine.getContext().getScopes());
        System.out.println(result);
        System.out.println(engine2.eval("f()"));
        System.out.println(engine.getContext().getBindings(100).keySet());
        System.out.println(engine.getContext().getBindings(200).keySet());

    }
}

