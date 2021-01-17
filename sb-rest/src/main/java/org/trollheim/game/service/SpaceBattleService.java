package org.trollheim.game.service;

import engine.simulation.Simulation;
import engine.strategies.JavascriptStrategy;
import engine.strategies.Speeder;
import engine.views.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.trollheim.game.data.models.AppUser;
import org.trollheim.game.data.models.UserStrategy;
import org.trollheim.game.data.repository.AppUserRepository;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpaceBattleService {

    @Autowired
    AppUserRepository appUserRepository;

    Map<String, String> db = new HashMap<>();


    public String load() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        List<UserStrategy> strategies = user.getStrategies();
        if (strategies.isEmpty()){
            return "";
        }

        return strategies.get(0).getSource();
    }

    public void save(String payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        List<UserStrategy> strategies = user.getStrategies();
        final UserStrategy strategy;
        if (strategies.isEmpty()){
            strategy = new UserStrategy();
            strategy.setName("default");

            strategies.add(strategy);
        }else {
            strategy = strategies.get(0);
        }

        strategy.setSource(payload);
        appUserRepository.save(user);
    }

    public List<String> test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        String script = load();
        if (script.trim().isEmpty()) {
            return Arrays.asList("Error - no script"); //TODO err
        }
        try {
            JavascriptStrategy playerStrategy = new JavascriptStrategy(user.getUsername(), script);

            ReportView view = new ReportView();
            Simulation simulation = new Simulation(view);
            simulation.runSimulation(Arrays.asList(playerStrategy, new Speeder()));
            return view.getPages();
        } catch (ScriptException e) {
            return Arrays.asList("Error - loading script "+e.getMessage());
        }


    }
}
