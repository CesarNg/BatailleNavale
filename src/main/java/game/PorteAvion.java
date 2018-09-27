package game;

import game.Bateau;

public class PorteAvion extends Bateau {

    public PorteAvion(String nom) {
        super(nom, 5 , 2);
    }
    public PorteAvion() {
        super(5,2);
        nom="PorteAvion";
    }
}
