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

    public void supprimerBateau(Bateau bateau){
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

    public boolean tir(Joueur adversaire){
        Point cible=null;
        boolean touche=false;

        do{
        try {
            cible = menu.menuSelectionTir();
            System.out.println("Tir sur "+cible);
        } catch (SaisieErroneeException e) {
            e.printStackTrace();
        }
        }while(!logic.isAPortee(this,cible));

        Bateau bateau  = logic.isTirATouche(cible,adversaire);

        if (bateau!=null){
            touche = true;
            boolean coule = bateau.impact();
            if(coule) {
                System.out.println("Le " + bateau.nom + " adverse à été coulé");
                adversaire.supprimerBateau(bateau);
            }else{
                System.out.println("Le " + bateau.nom + " adverse à été touché");
            }
        }else{
            System.out.println("Tir dans l'eau");
            touche = false;
        }


        return touche;
    }

    public boolean Defaite(){
        return listBateau.size()==0;
    }
}