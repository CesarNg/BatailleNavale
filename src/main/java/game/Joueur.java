package game;

import exception.SaisieErroneeException;
import logic.GameLogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private String nom;
    private List<Bateau> listBateau;
    private Menu menu;
    private GameLogic logic;

    public Joueur(){
        nom = "Joueur";
        listBateau = new ArrayList<Bateau>();
        menu = new Menu();
        logic = new GameLogic();
    }

    public Joueur(String n){
        nom = n;
        listBateau = new ArrayList<Bateau>();
        menu = new Menu();
        logic = new GameLogic();
    }

    public void ajouterBateau(Bateau bateau){
        listBateau.add(bateau);
    }

    public void supprBateau(Bateau bateau){
        if (listBateau.contains(bateau)) {
            listBateau.remove(bateau);
        }
    }

    public List<Bateau> getListBateau() {
        return listBateau;
    }

    public String getNom(){
        return nom;
    }

    public boolean tir(Joueur adversaire) throws SaisieErroneeException {
        Point cible;
        boolean touche=false;

        cible = menu.menuSelectionTir();

        while(!logic.isAPortee(this,cible)){
            Bateau bateau  = logic.isTirATouche(cible,adversaire);

            if (bateau!=null){
                touche = true;
                boolean coule = bateau.impact();
                if(coule) {
                    System.out.println("Le " + bateau.nom + "adverse à été coulé");
                }else{
                    System.out.println("Le " + bateau.nom + "adverse à été touché");
                }
            }else{
                System.out.print("Tir dans l'eau");
                touche = false;
            }
        }

        return touche;
    }

    public void deplacerBateau(){

        int choixBateau=-1;
        int orientationNavire = -1;
        int nbCases = -1;
        Bateau bateau;
        Boolean saisieChoixBateauCorrecte;
        Boolean saisieOrientationBateau;
        Boolean saisieNbCasesCorrect;
        Boolean valide;


        saisieChoixBateauCorrecte = false;
        // Choisir le bateau à déplacer
        while (!saisieChoixBateauCorrecte){
            try {
                choixBateau = menu.menuSelectionBateau((ArrayList<Bateau>) listBateau);
                saisieChoixBateauCorrecte = true;
            } catch (SaisieErroneeException e) {
                saisieChoixBateauCorrecte = false;
                System.out.println("Erreur :  Renseignez le bon chiffre qui correspond au bateau");
            }
        }

        if (choixBateau == 0 ){

        }
        else{

            do {

                //Choisir l'orientation du Bateau

                saisieOrientationBateau = false;

                while (!saisieOrientationBateau){
                    try {
                        orientationNavire = menu.menuOrientationBateau(listBateau.get(choixBateau).getNom());
                        saisieOrientationBateau = true;
                    } catch (SaisieErroneeException e) {
                        saisieOrientationBateau = false;
                        System.out.println("Erreur :  Renseignez le bon chiffre qui correspond à l'orientation");
                    }
                }


                // Choisir le nombre de cases
                saisieNbCasesCorrect = false;

                while (!saisieNbCasesCorrect){
                    try {
                        nbCases = menu.menuSelectionNbCasesDeplacement();
                        saisieNbCasesCorrect = true;
                    } catch (SaisieErroneeException e) {
                        saisieNbCasesCorrect = false;
                        System.out.println("Erreur :  Renseignez un chiffre entre 0 et 2");
                    }
                }


                // Immaginer le deplacemnt du bateau choisi
                bateau = listBateau.get(choixBateau);
                Point positionPotentielle = bateau.deplacementPotentielle(orientationNavire,nbCases);

                //verifier le bon placement du bateau

                List<Point> invalidList = logic.getPositionnementBateau(positionPotentielle,orientationNavire,bateau.taille,this);

                if(invalidList.size()==0){
                    valide=true;
                }else{
                    valide=false;
                    System.out.println("Le navire ne peut pas être placé ici, les cases suivantes sont occupé ou invalide:");
                    for(int i = 0;i<invalidList.size();i++) {
                        System.out.println("X:"+(int) invalidList.get(i).getX()+"; Y:"+(int) invalidList.get(i).getY());
                    }
                }

            }while (!valide);

            // Effectuer le deplacement definitif
            bateau.deplacementEffectif(orientationNavire,nbCases);

    }



    }

    public boolean Defaite(){
        return listBateau.size()==0;
    }
}