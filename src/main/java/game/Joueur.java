package game;

public class Joueur {

    private String Nom;
    private int Score;
    private Boolean victoire;

    public Joueur(){
        Score = 0;
        Nom = "Joueur";
        victoire = false;
    }

    public Joueur(String n){
        Score = 0;
        Nom = n;
        victoire = false;
    }

    public String getNom(){
        return Nom;
    }

    //Ajout de points au score
    public void adScore(int sc){
        Score += sc;
    }

    //Retire des points au score
    public void removeScore(int sc){
        Score -= sc;
    }

    public int getScore(){
        return Score;
    }
    public Boolean getVictoire(){
        return victoire;
    }
}