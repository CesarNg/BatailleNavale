package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.Bateau;
import game.Joueur;
import game.PorteAvion;
import game.SousMarin;
import game.Torpilleur;
import logic.GameLogic;

public class GameLogicTest {
	GameLogic logic;
	Joueur joueur;
	private Bateau sousMarin;
	private Bateau torpilleur;
	private Bateau porteAvion;

	@Before
	public void setUp() throws Exception {
		logic = new GameLogic();
		joueur = new Joueur();

		sousMarin = new SousMarin();
		torpilleur = new Torpilleur();
		porteAvion = new PorteAvion();

		// Ajout de la position et orientation
		sousMarin.setPosition(new Point(4, 2));
		sousMarin.setOrientation(6);

		torpilleur.setPosition(new Point(6, 0));
		torpilleur.setOrientation(2);

		porteAvion.setPosition(new Point(0, 4));
		porteAvion.setOrientation(2);

		joueur.ajouterBateau(sousMarin);
	}

	@Test
	public void testTailleListeBateauJoueurEqualsUn() {
		assertEquals(1, joueur.getListBateau().size());
	}

	@Test
	public void testGetPorteesPointRetourneTailleListeEqualsACinqPourUnPoint() {
		Point point = new Point(4, 4);
		List<Point> listPoints = logic.getPorteesPoint(point, 1);

		assertEquals(5, listPoints.size());
	}

	@Test
	public void testCalculPorteesAvecUnPointEtChampTirEqualsAUnRetourneListDeTailleEqualsACinq() {
		Point pointNORD = new Point(3, 4);

		List<Point> listePointsTheoriques = new ArrayList<Point>();
		listePointsTheoriques.add(pointNORD);

		List<Point> retourPortees = logic.calculPortees(listePointsTheoriques, 1);
		assertEquals(5, retourPortees.size());
	}

	@Test
	public void testCalculPorteesAvecUnPointEtChampTirEqualsADeuxRetourneListDeTailleEqualsANeuf() {
		Point pointNORD = new Point(3, 4);

		List<Point> listePointsTheoriques = new ArrayList<Point>();
		listePointsTheoriques.add(pointNORD);

		List<Point> retourPortees = logic.calculPortees(listePointsTheoriques, 2);
		assertEquals(9, retourPortees.size());
	}

	@Test
	public void testGetListPointEqualsATrois() {
		List<Point> listePointsBateauJoueur = sousMarin.getListPoint();
		assertEquals(3, listePointsBateauJoueur.size());
	}

	@Test
	public void testDeplacementVersDirectionESTDeUneCase() {
		joueur.ajouterBateau(torpilleur);
		List<Point> pointsInvalides = logic.isDeplacementPossible(joueur, 6, 1, torpilleur);
		assertEquals(0, pointsInvalides.size());
	}

	@Test
	public void testDeplacementTorpilleurVersDirectionOUESTDeUneCase() {
		joueur.ajouterBateau(torpilleur);
		List<Point> pointsInvalides = logic.isDeplacementPossible(joueur, 4, 1, torpilleur);
		assertEquals(2, pointsInvalides.size());
	}

	@Test
	public void testDeplacementPorteAvionDirectionESTImpossibleBateauDejaExistant() {
		joueur.ajouterBateau(porteAvion);

		List<Point> pointsInvalides = logic.isDeplacementPossible(joueur, 4, 1, porteAvion);
		System.out.println(pointsInvalides);
		assertEquals(1, pointsInvalides.size());
	}

}
