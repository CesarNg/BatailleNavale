package game;

import exception.SaisieErroneeException;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class InterfaceJeu {

    private Torpilleur torpilleur;
    private ContreTorpilleur contreTorpilleur;
    private PorteAvion porteAvion;
    private SousMarin sousMarin;
    private Croiseur  croiseur;
    private String [][] champBataille;
    private Point pointTorpilleur;
    private int orientationTorpilleur;
    private Point pointContreTorpilleur;
    private int orientationContreTorpilleur;
    private Point pointPorteAvion;
    private int orientationPorteAvion;
    private Point pointCroiseur;
    private int orientationCroiseur;
    private Point pointSousMarin;
    private int orientationSousMarin;
    private Joueur joueur;
    private Menu menu;
    private Boolean saisiePositionCorrecte;
    private Boolean saisieOrientationCorrecte;




    public InterfaceJeu(Joueur joueur){

        this.joueur = joueur;

        //Création de la grille

        int i,j,k=0;
        champBataille = new String[10][10];

        for (i=0;i < champBataille.length;i++){
            for(j=0;j< champBataille[i].length;j++){

                     champBataille[i][j]= "O";


            }

        }
       //*******************************************************

         menu = new Menu();
        saisiePositionCorrecte = false;

       // Choisir la position du torpilleur

        while (!saisiePositionCorrecte){
            try {
                pointTorpilleur = menu.menuPositionBateau("Torpilleur");
                saisiePositionCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisiePositionCorrecte = false;
                System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
            }
        }
        //********************************************


        //Choisir l'orientation du torpilleur

        saisieOrientationCorrecte = false;
        torpilleur = new Torpilleur("Torpilleur");

        while (!saisieOrientationCorrecte){
            try {
                orientationTorpilleur = menu.menuOrientationBateau(torpilleur.getNom());
                saisieOrientationCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieOrientationCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
            }
        }


        //Creation du torpilleur
        torpilleur.setPosition(pointTorpilleur);
        torpilleur.setOrientation(orientationTorpilleur);
        //***************************************************

        //placement du torpilleur sur la grille
        joueur.ajouterBateau(torpilleur);
        placementBateau(torpilleur.getPosition(),torpilleur.getTaille(),torpilleur.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(torpilleur);



        // Choisir la position du sousMarin
        saisiePositionCorrecte = false;
        sousMarin = new SousMarin("SousMarin");

        while (!saisiePositionCorrecte){
            try {
                pointSousMarin = menu.menuPositionBateau(sousMarin.getNom());
                saisiePositionCorrecte = true;

            } catch (SaisieErroneeException e) {
                saisiePositionCorrecte = false;
                System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
            }
        }




        //Choisir l'orientation du SousMarin

        saisieOrientationCorrecte = false;

        while (!saisieOrientationCorrecte){
            try {
                orientationSousMarin = menu.menuOrientationBateau(sousMarin.getNom());
                saisieOrientationCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieOrientationCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
            }
        }

        //Creation du SousMarin
        sousMarin.setPosition(pointSousMarin);
        sousMarin.setOrientation(orientationSousMarin);
        //***************************************************

        //placement du sousMarin sur la grille
        joueur.ajouterBateau(sousMarin);
        placementBateau(sousMarin.getPosition(),sousMarin.getTaille(),sousMarin.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(sousMarin);



        // Choisir la position du contreTorpilleur
        saisiePositionCorrecte = false;
        contreTorpilleur = new ContreTorpilleur("contreTorpilleur");

        while (!saisiePositionCorrecte){
            try {
                pointContreTorpilleur = menu.menuPositionBateau(contreTorpilleur.getNom());
                saisiePositionCorrecte = true;

            } catch (SaisieErroneeException e) {
                saisiePositionCorrecte = false;
                System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
            }
        }

        //Choisir l'orientation du contreTorpilleur

        saisieOrientationCorrecte = false;

        while (!saisieOrientationCorrecte){
            try {
                orientationContreTorpilleur = menu.menuOrientationBateau(contreTorpilleur.getNom());
                saisieOrientationCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieOrientationCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
            }
        }


        //Creation du contreTorpilleur
        contreTorpilleur.setPosition(pointContreTorpilleur);
        contreTorpilleur.setOrientation(orientationContreTorpilleur);
        //***************************************************

        //placement du contreTorpilleur sur la grille
        joueur.ajouterBateau(contreTorpilleur);
        placementBateau(contreTorpilleur.getPosition(),contreTorpilleur.getTaille(),contreTorpilleur.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(contreTorpilleur);



        // Choisir la position du porteAvion

        saisiePositionCorrecte = false;
        porteAvion = new PorteAvion("porteAvion");

        while (!saisiePositionCorrecte){
            try {
                pointPorteAvion = menu.menuPositionBateau(porteAvion.getNom());
                saisiePositionCorrecte = true;

            } catch (SaisieErroneeException e) {
                saisiePositionCorrecte = false;
                System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
            }
        }



        //Choisir l'orientation du porteAvion

        saisieOrientationCorrecte = false;

        while (!saisieOrientationCorrecte){
            try {
                orientationPorteAvion = menu.menuOrientationBateau(porteAvion.getNom());
                saisieOrientationCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieOrientationCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
            }
        }

        //Creation du porteAvion
        porteAvion.setPosition(pointPorteAvion);
        porteAvion.setOrientation(orientationPorteAvion);
        //***************************************************

        //placement du porteAvion sur la grille
        joueur.ajouterBateau(porteAvion);
        placementBateau(porteAvion.getPosition(),porteAvion.getTaille(),porteAvion.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(porteAvion);



        // Choisir la position du croiseur

        saisiePositionCorrecte = false;
        croiseur = new Croiseur("croiseur");

        while (!saisiePositionCorrecte){
            try {
                pointCroiseur = menu.menuPositionBateau(croiseur.getNom());
                saisiePositionCorrecte = true;

            } catch (SaisieErroneeException e) {
                saisiePositionCorrecte = false;
                System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
            }
        }




        //Choisir l'orientation du croiseur

        saisieOrientationCorrecte = false;

        while (!saisieOrientationCorrecte){
            try {
                orientationCroiseur = menu.menuOrientationBateau(croiseur.getNom());
                saisieOrientationCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieOrientationCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
            }
        }

        //Creation du croiseur
        croiseur.setPosition(pointCroiseur);
        croiseur.setOrientation(orientationCroiseur);
        //***************************************************

        //placement du croiseur sur la grille
        joueur.ajouterBateau(croiseur);
        placementBateau(croiseur.getPosition(),croiseur.getTaille(),croiseur.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(croiseur);


    }

    public String[][] getChampBataille() {
        return champBataille;
    }


    public void placementBateau(Point point, int taille, int orientation){

        int i;

        switch (orientation){

            case 8:

                for (i= point.x ;i > point.x - taille ;i--){

                    champBataille[i][point.y]= "\033[31m"+"X" + "\033[00m";

                }

                break;

            case 4:

                for (i= point.y ;i > point.y - taille ;i--){

                    champBataille[point.x][i]= "\033[31m" + "X"+ "\033[00m";

                }

                break;

            case 2:

                for (i= point.x ;i < point.x + taille ;i++){

                    champBataille[i][point.y]= "\033[31m" +"X"+ "\033[00m";

                }

                break;

            case 6:

                for (i= point.y ;i < point.y + taille ;i++){

                    champBataille[point.x][i]= "\033[31m"+"X"+ "\033[00m";

                }

                break;
        }


    }

    public void updateGrid (Joueur joueur){

        int i,j;
        boolean pointPresent;
        List<Point> allPoints = new ArrayList<Point>();

        // recuperer toutes les positions des bateaux du joueur

        for (Bateau bateau: joueur.getListBateau()
             ) {
                  allPoints.addAll(bateau.getListPoint());

        }

        //**********************************************


        //Replacer toutes les positions dans la grille dans la grille
        for (i=0;i < champBataille.length;i++){
            for(j=0;j< champBataille[i].length;j++){

                pointPresent = false;

                for (Point point : allPoints
                     ) {

                    if(point.x == i && point.y == j)
                        pointPresent = true;
                }

                if(pointPresent)
                     champBataille[i][j]= "\033[31m"+"X" + "\033[00m";
                else
                    champBataille[i][j]= "O";

            }

        }
        //****************************************************************

        menu.afficherChampBataille(champBataille);

    }


    public ContreTorpilleur getContreTorpilleur() {
        return contreTorpilleur;
    }

    public Torpilleur getTorpilleur() {
        return torpilleur;
    }

    public SousMarin getSousMarin() {
        return sousMarin;
    }

    public Croiseur getCroiseur() {
        return croiseur;
    }

    public PorteAvion getPorteAvion() {
        return porteAvion;
    }
}
