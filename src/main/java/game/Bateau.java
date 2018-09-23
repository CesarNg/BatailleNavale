package game;

import java.awt.*;

public abstract class Bateau {

    protected String nom;
    protected int taille;
    protected  int pointsVie;
    protected int champTire;
    protected  int orientation;
    protected Point position;


    public  Bateau(String nom, int taille, int champTire){

        this.nom = nom;
        this.champTire = champTire;
        this.taille = taille;
        pointsVie = 2;
        position = new Point();

    }

    public Boolean impact(){

        pointsVie -- ;
        /*
           Retourne true si les points de vie sont à zero et false dans le cas contraire
         */

        if(pointsVie == 0)
            return true;
        else
            return false;
    }

    public String getNom() {
        return nom;
    }

    public int getTaille() {
        return taille;
    }

    public Point getPosition() {
        return position;
    }


    public void setPosition(Point position) {
        this.position = position;
    }

    public int getChampTire() {
        return champTire;
    }


    /* 0 si l'orientation du bateau est au nord
       1 si l'orientation du bateau est à l'ouest
       2 si l'orienation du bateau est au sud
       3 si l'orientation du bateau est à l'est*/

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }


    public int getOrientation() {
        return orientation;
    }
}
