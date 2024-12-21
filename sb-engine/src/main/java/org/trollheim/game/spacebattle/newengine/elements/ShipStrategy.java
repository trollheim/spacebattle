package org.trollheim.game.spacebattle.newengine.elements;

import java.util.List;

public interface ShipStrategy {

    List<Action> execute(Scan scan);

}
