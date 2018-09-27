package game;

import game.Bateau;

public class ContreTorpilleur extends Bateau {
    public ContreTorpilleur(String nom) {
        super(nom, 3,2);
    }
    public ContreTorpilleur() {
        super(3,2);
        nom="ContreTorpilleur";
    }
}
