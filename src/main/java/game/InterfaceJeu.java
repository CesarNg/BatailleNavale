package game;

import exception.SaisieErroneeException;
import logic.GameLogic;

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
    private Joueur joueur;
    private Menu menu;
    private Boolean saisiePositionCorrecte;
    private Boolean saisieOrientationCorrecte;
    private GameLogic verifications;



    public InterfaceJeu(Joueur joueur) throws SaisieErroneeException {

        this.joueur = joueur;
        verifications = new GameLogic();
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
        nouveauBateau(Torpilleur.class,torpilleur);

        // Choisir la position du sousMarin
       // nouveauBateau(SousMarin.class,sousMarin);

        // Choisir la position du contreTorpilleur
        //nouveauBateau(ContreTorpilleur.class,contreTorpilleur);

        // Choisir la position du porteAvion
        //nouveauBateau(PorteAvion.class,porteAvion);

        // Choisir la position du croiseur
       // nouveauBateau(Croiseur.class,croiseur);

    }

    public String[][] getChampBataille() {
        return champBataille;
    }


    private void placementBateau(Point point, int taille, int orientation){

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

    private void nouveauBateau(Class<? extends Bateau> classDeNavire,Bateau navire) throws SaisieErroneeException {
        try {
            navire = classDeNavire.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        boolean valide;
        Point pointNavire;
        int orientationNavire = -1;


        pointNavire = new Point();

        do {
            // Choisir la position du Bateau
            saisiePositionCorrecte = false;
            while (!saisiePositionCorrecte){
                try {
                    // Choisir la position du Bateau
                    pointNavire = menu.menuPositionBateau(classDeNavire.getSimpleName());
                    saisiePositionCorrecte = true;
                } catch (SaisieErroneeException e) {
                    saisiePositionCorrecte = false;
                    System.out.println("Erreur :  Entrez un chiffre entre 0 et 9");
                }
            }

            //Choisir l'orientation du Bateau

            saisieOrientationCorrecte = false;

            while (!saisieOrientationCorrecte){
                try {
                    orientationNavire = menu.menuOrientationBateau(classDeNavire.getName());
                    saisieOrientationCorrecte = true;
                } catch (SaisieErroneeException e) {
                    saisieOrientationCorrecte = false;
                    System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
                }
            }


            //Verifier la possibilité
            List<Point> invalidList = verifications.getPositionnementBateau(pointNavire,orientationNavire,navire.taille,joueur);

            if(invalidList.size()==0){
                valide=true;
            }else{
                valide=false;
                System.out.println("Le navire ne peut pas être placé ici, les cases suivantes sont occupé ou invalide:");
                for(int i = 0;i<invalidList.size();i++) {
                    System.out.println("X:"+(int) invalidList.get(i).getX()+"; Y:"+(int) invalidList.get(i).getY());
                }
            }
        }while(!valide);


        //Creation du Bateau
        navire.setPosition(pointNavire);
        navire.setOrientation(orientationNavire);
        //***************************************************

        //placement du Bateau sur la grille
        joueur.ajouterBateau(navire);
        placementBateau(navire.getPosition(),navire.getTaille(),navire.getOrientation());
        menu.afficherChampBataille(champBataille);
        menu.logPositionsBateau(navire);
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
