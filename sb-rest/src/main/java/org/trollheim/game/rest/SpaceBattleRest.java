package org.trollheim.game.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.trollheim.game.service.SpaceBattleService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SpaceBattleRest {

    @Autowired
    private SpaceBattleService spaceBattleService;

    @GetMapping("sketchAPi/load")
    public Map<String, String> load() {
        return Collections.singletonMap("result", spaceBattleService.load());
    }


    @GetMapping("sketchAPi/test")
    public Map<String, List<String>> test() {
        return Collections.singletonMap("result", spaceBattleService.test());
    }

    @PostMapping(value = "sketchAPi/save", consumes = "application/json")
    public HttpEntity<Void> save(@RequestBody Map<String, String> data) {
        spaceBattleService.save(data.get("payload"));
        return new HttpEntity<>(null);
    }

}
