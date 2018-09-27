package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.Bateau;
import game.Joueur;

public class GameLogic {

	public List<Point> getPositionnementBateau(Point point, int orientation, int longueur, Joueur joueur) {
		List<Point> pointsInvalides = new ArrayList<Point>();

		// On a besoin des points valides
		// pour comparer après (si bateau existe déjà dans la case (x,y))
		List<Point> pointsValides = new ArrayList<Point>();

		// Calcul des points en fonction de l'orientation et de la longueur
		int xDelta, yDelta;
		xDelta = yDelta = 0;

		boolean xDeltaChanged, yDeltaChanged;
		xDeltaChanged = yDeltaChanged = false;

		switch (orientation) {
		case 6: // Est
			yDelta = (point.y + longueur) - 1;
			yDeltaChanged = true;
			break;
		case 4: // Ouest
			yDelta = (point.y - longueur) + 1;
			yDeltaChanged = true;
			break;
		case 8: // Nord
			xDelta = (point.x - longueur) + 1;
			xDeltaChanged = true;
			break;
		case 2: // Sud
			xDelta = (point.x + longueur) - 1;
			xDeltaChanged = true;
			break;

		default:
			break;
		}

		// Étape 1 : on vérifie le hors-zone
		// Étape 2 : si pas de hors-zone,
		// on vérifie si on ne place pas un bateau sur une case occupée

		// Check de xDelta
		if (xDeltaChanged) {
			int derniereValeur = 0;

			// On va checker le hors-zone supérieur à MAX => 10
			if (xDelta >= 10) {
				int end = xDelta - 10; // combien d'itérations faire
				int i = 0;

				// On ajoute tous les points hors-zone dans la liste (SUD)
				while (i < (end + 1)) {
					pointsInvalides.add(new Point(xDelta - i, point.y));
					derniereValeur = xDelta - i;
					i++;
				}

				// On ajoute les points valides (SUD)
				end = derniereValeur - point.x;
				i = 0;
				while (i < end) {
					pointsValides.add(new Point(point.x + i, point.y));
					i++;
				}

			} else if (xDelta <= -1) { // Hors-zone < MAX (0)
				// valeur absolue car on sort de la zone dès inférieur à MIN =>0
				int end = xDelta * (-1);
				int i = 0;

				// On ajoute les points valides (NORD)
				while (i < end) {
					pointsInvalides.add(new Point(xDelta + i, point.y));
					derniereValeur = xDelta + i;
					i++;
				}

				// Ajout des points valides (NORD)
				end = derniereValeur - point.x;
				end = end * (-1); // pour avoir la valeur absolue
				i = 0;
				while (i < end) {
					pointsValides.add(new Point(point.x - i, point.y));
					i++;
				}
			} else {
				// Cas : pas de hors-zone (que des points valides)
				// on va check le NORD ou SUD
				int i = 0;

				if (orientation == 8) { // NORD
					while (i < longueur) {
						pointsValides.add(new Point(point.x - i, point.y));
						i++;
					}
				} else if (orientation == 2) { // SUD
					while (i < longueur) {
						pointsValides.add(new Point(point.x + i, point.y));
						i++;
					}
				}
			}
		}

		// Même check pour yDelta
		if (yDeltaChanged) {
			int derniereValeur = 0;
			if (yDelta >= 10) {
				int end = yDelta - 10; // combien d'itérations faire
				int i = 0;

				// On ajoute tous les points hors-zone dans la liste (EST)
				while (i < (end + 1)) {
					pointsInvalides.add(new Point(point.x, yDelta - i));
					derniereValeur = yDelta - i;
					i++;
				}

				// On ajoute les points valides (EST)
				end = derniereValeur - point.y;
				i = 0;
				while (i < end) {
					pointsValides.add(new Point(point.x, point.y - i));
					i++;
				}
			} else if (yDelta <= -1) {
				// valeur absolue car on sort de la zone dès inférieur à MIN =>0
				int end = yDelta * (-1);
				int i = 0;

				// Ajout des valides invalides (OUEST)
				while (i < end) {
					pointsInvalides.add(new Point(point.x, yDelta + i));
					derniereValeur = yDelta + i;
					i++;
				}

				end = derniereValeur - point.y;
				end = end * (-1); // valeur absolue
				i = 0;

				// Ajout des points valides (OUEST)
				while (i < end) {
					pointsValides.add(new Point(point.x, point.y + i));
					i++;
				}
			} else {
				// Cas : pas de hors-zone (que des points valides)
				// on va check l'EST ou l'OUEST
				int i = 0;

				if (orientation == 6) { // EST
					while (i < longueur) {
						pointsValides.add(new Point(point.x, point.y + i));
						i++;
					}
				} else if (orientation == 4) { // OUEST
					while (i < longueur) {
						pointsValides.add(new Point(point.x, point.y - i));
						i++;
					}
				}
			}
		}

		// Cas 2 : Point (x,y) + longueur se trouve dans la liste des
		// points actuels du joueur (càd => bateau occupe déjà cette case)
		for (Point pointValide : pointsValides) {
			for (Bateau bateau : joueur.getListBateau()) {
				for (Point pointBateauExistant : bateau.getListPoint()) {
					if (arePointsEquals(pointValide, pointBateauExistant)) {
						pointsInvalides.add(new Point(pointValide.x, pointValide.y));
					}
				}
			}
		}

		return pointsInvalides;
	}

	public boolean isAPortee(Joueur joueur, Point point) {
		// On récupère les portées possibles de chaque bateau => donc une liste
		// de points (x,y)
		// On parcourt la liste de ces portées (x,y) et si le Point (x,y) passé
		// en paramètre se trouve dans la liste => return true
		// sinon => return false

		List<Point> porteesBateaux = new ArrayList<Point>();

		for (Bateau bateau : joueur.getListBateau()) {
			// Calcul des portées et retour des valeurs dans un tableau
			porteesBateaux.addAll(calculPortees(bateau.getListPoint(), bateau.getChampTire()));
		}

		// On compare notre point avec le tableau de valeurs (points)
		for (Point pointComparaison : porteesBateaux) {
			if (arePointsEquals(point, pointComparaison))
				return true;
		}

		return false;
	}

	public List<Point> calculPortees(List<Point> pointsBateau, int champTirBateau) {
		List<Point> porteesBateau = new ArrayList<Point>();

		// On calcule la portée de chaque point individuellement
		for (Point point : pointsBateau) {
			porteesBateau.addAll(getPorteesPoint(point, champTirBateau));
		}

		return porteesBateau;
	}

	public List<Point> getPorteesPoint(Point point, int champTirBateau) {
		List<Point> pointPortees = new ArrayList<Point>();

		// En fonction du champ de tir on va aller chercher "i" points
		// vers le NORD & EST & SUD & OUEST
		int x = point.x;
		int y = point.y;
		int i = 1;

		while (i <= champTirBateau) {
			int xTheorique, yTheorique;

			// NORD
			xTheorique = x - i;
			if (!isCoordHorsZone(xTheorique)) {
				pointPortees.add(new Point(xTheorique, y));
			}

			// SUD
			xTheorique = x + i;
			if (!isCoordHorsZone(xTheorique)) {
				pointPortees.add(new Point(xTheorique, y));
			}

			// EST
			yTheorique = y + i;
			if (!isCoordHorsZone(yTheorique)) {
				pointPortees.add(new Point(x, yTheorique));
			}

			// OUEST
			yTheorique = y - i;
			if (!isCoordHorsZone(yTheorique)) {
				pointPortees.add(new Point(x, yTheorique));
			}
			i++;
		}

		// Et on ajoute le point lui même
		pointPortees.add(point);

		return pointPortees;
	}

	private boolean isCoordHorsZone(int coord) {
		if (coord < 0 || coord > 9)
			return true;

		return false;
	}

	public Bateau isTirATouche(Point point, Joueur joueurAdverse) {
		if (point == null || joueurAdverse == null)
			return null;

		// Récupération des bateaux du joueur adverse
		List<Bateau> bateaux = joueurAdverse.getListBateau();

		// Pour chaque bateau => récupèration de ses points
		// et comparaison avec notre point
		// on retourne la bateau qui a été touché
		for (Bateau bateau : bateaux) {
			for (Point pointComparaison : bateau.getListPoint()) {
				if (arePointsEquals(point, pointComparaison))
					return bateau; // Bateau est sur la case => touché
			}
		}

		return null;
	}

	private boolean arePointsEquals(Point point, Point pointComparaison) {
		if (point == null || pointComparaison == null)
			return false;

		if ((point.x == pointComparaison.x) && (point.y == pointComparaison.y))
			return true;

		return false;
	}

	public List<Point> isDeplacementPossible(Joueur joueur, int orientation, int nbCase, Bateau bateau) {
		List<Point> pointsNonDisponibles = new ArrayList<Point>();
		List<Point> pointsBateaux = bateau.getListPoint();
		List<Point> pointsTheoriques = new ArrayList<Point>();
		List<Bateau> bateauxSansActuelBateau = joueur.getListBateau();

		// On va aller supprimer notre bateau de la liste
		// pour ne pas entrer en conflit pour la recherche de points
		Iterator<Bateau> it = bateauxSansActuelBateau.iterator();

		while (it.hasNext()) {
			Bateau bat = it.next();
			if (bat.getNom().equals(bateau.getNom()))
				it.remove();
		}

		// En fonction de l'orientation
		// on déplace le bateau théoriquement

		switch (orientation) {
		case 8: // NORD
			for (Point point : pointsBateaux) {
				int xTheorique = point.x - 1;
				if (!isCoordHorsZone(xTheorique))
					pointsTheoriques.add(new Point(xTheorique, point.y));
			}

			break;
		case 2: // SUD
			for (Point point : pointsBateaux) {
				int xTheorique = point.x + 1;
				if (!isCoordHorsZone(xTheorique))
					pointsTheoriques.add(new Point(xTheorique, point.y));
			}
			break;

		case 6: // EST
			for (Point point : pointsBateaux) {
				int yTheorique = point.y + 1;
				if (!isCoordHorsZone(yTheorique))
					pointsTheoriques.add(new Point(point.x, yTheorique));
			}
			break;

		case 4: // OUEST
			for (Point point : pointsBateaux) {
				int yTheorique = point.y - 1;
				if (!isCoordHorsZone(yTheorique))
					pointsTheoriques.add(new Point(point.x, yTheorique));
			}
			break;

		default:
			break;
		}

		// On vérifie que les points ne s'entrecroisent pas
		// avec d'autres points (bateaux) du joueur
		for (Bateau bat : bateauxSansActuelBateau) {
			for (Point pointBateauxReel : bat.getListPoint()) {
				for (Point pointTheorique : pointsTheoriques) {
					if (arePointsEquals(pointBateauxReel, pointTheorique)) {
						pointsNonDisponibles.add(new Point(pointTheorique.x, pointTheorique.y));
					}
				}
			}
		}

		return pointsNonDisponibles;
	}
}
