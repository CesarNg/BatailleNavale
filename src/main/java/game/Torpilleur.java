package game;

import game.Bateau;

public class Torpilleur extends Bateau {
    public Torpilleur(String nom) {
        super(nom, 2,5);
    }
    public Torpilleur() {
        super(2,5);
        nom="Torpilleur";
    }
}
