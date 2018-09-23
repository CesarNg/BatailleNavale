package logic;

import java.awt.Point;
import java.util.List;

import game.Joueur;

public class GameLogic {

	public List<Point> getPositionnementBateau(Point point, int orientation, String[][] champBataille) {
		// Lors du placement d'un bateau sur un point (x,y)
		// On retourne soit une liste vide (null)
		// ou une liste de points invalides (hors-zone)
		
		//  / \
	   //  / ! \  => On pourrait passer en paramètres juste un "Bateau" & une grille
	  //  /_____\ => mais, on ne crée le bateau qu'une fois qu'on est sûr que tous les vérif. sont faites
	 //			  => d'où l'utilitité de cette fonction	
		return null;

	}

	public boolean isAPortee(Joueur joueur, String[][] champBataille, Point point) {
		// On récupère les portées possibles de chaque bateau => donc une liste de points (x,y)
		// On parcourt la liste de ces portées (x,y) et si le Point (x,y) passé en paramètre
		// se trouve dans la liste => return true
		// sinon => return false
		
		return false;
	}
	
	public boolean isTirATouche(Point point, Joueur joueurAdverse) {
		// Récupération des points (x,y) des bateaux du joueur adverse
		// On parcourt la liste de ces points 
		// et on compare avec le point passé en paramètre
		
		return false;
	}
}
