package org.trollheim.game.spacebattle.newengine.elements;

public class Boost {

    int scan = 0;

    public void reset(){
        scan = 0;
    }

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }
}
