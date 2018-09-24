package game;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private String nom;
    private List<Bateau> listBateau;

    public Joueur(){
        nom = "Joueur";
        listBateau = new ArrayList<Bateau>();
    }

    public Joueur(String n){
        nom = n;
        listBateau = new ArrayList<Bateau>();
    }

    public void ajouterBateau(Bateau bateau){
        listBateau.add(bateau);
    }

    public void supprBateau(Bateau bateau){
        if (listBateau.contains(bateau)) {
            listBateau.remove(bateau);
        }
        if (listBateau.size()==0){
            Defaite();
        }
    }

    public List<Bateau> getListBateau() {
        return listBateau;
    }

    public String getNom(){
        return nom;
    }

    //TODO gerer la defaite
    private void Defaite(){

    }
}