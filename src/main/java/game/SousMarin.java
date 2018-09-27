package game;

import game.Bateau;

public class SousMarin extends Bateau {
    public SousMarin(String nom) {
        super(nom, 3,4);
    }
    public SousMarin() {
        super(3,4);
        nom="SousMarin";
    }
}
