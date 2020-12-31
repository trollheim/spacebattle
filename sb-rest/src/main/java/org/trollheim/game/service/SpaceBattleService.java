package org.trollheim.game.service;

import engine.simulation.Simulation;
import engine.strategies.JavascriptStrategy;
import engine.strategies.Speeder;
import engine.views.ReportView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpaceBattleService {


    Map<String, String> db = new HashMap<>();


    public String load() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return db.getOrDefault(authentication.getName(), "");
    }

    public void save(String payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        db.put(currentPrincipalName, payload);
    }

    public List<String> test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String script = db.getOrDefault(authentication.getName(), "");
        if (script.trim().isEmpty()) {
            return Arrays.asList("Error - no script"); //TODO err
        }
        try {
            JavascriptStrategy playerStrategy = new JavascriptStrategy(authentication.getName(), script);

            ReportView view = new ReportView();
            Simulation simulation = new Simulation(view);
            simulation.runSimulation(Arrays.asList(playerStrategy, new Speeder()));
            return view.getPages();
        } catch (ScriptException e) {
            return Arrays.asList("Error - loading script "+e.getMessage());
        }


    }
}
