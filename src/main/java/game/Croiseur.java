package game;

import game.Bateau;

public class Croiseur extends Bateau {
    public Croiseur(String nom) {
        super(nom, 4,2);
    }
    public Croiseur() {
        super(4,2);
        nom="Croiseur";
    }
}
