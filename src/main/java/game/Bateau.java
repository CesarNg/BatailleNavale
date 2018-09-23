package game;

import java.awt.*;
import java.util.ArrayList;

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

    public ArrayList<Point>  getListPoint (){

        ArrayList <Point> positionsList = new ArrayList<Point>();


        switch (getOrientation()){

            case 8 :

                for (int i = getPosition().x ; i > getPosition().x - getTaille() ; i -- ){
                    Point point = new Point();
                    point.x = i;
                    point.y = getPosition().y;

                    positionsList.add(point);
                }

                break;

            case 4:

                for (int i = getPosition().y ; i > getPosition().y - getTaille() ; i -- ){

                    Point point = new Point();
                    point.x = getPosition().x;
                    point.y = i;

                    positionsList.add(point);
                }

            break;

            case 2:

                for (int i = getPosition().x ; i < getPosition().x + getTaille() ; i ++ ){

                    Point point = new Point();
                    point.x = i;
                    point.y = getPosition().y;

                    positionsList.add(point);
                }

                break;

            case 6:

                for (int i = getPosition().y ; i < getPosition().y + getTaille() ; i ++ ){

                    Point point = new Point();
                    point.x = getPosition().x;
                    point.y = i;

                    positionsList.add(point);
                }

                break;
        }


        return positionsList;
    }


    public void deplacement (int orientation, int nbCases){

        switch (orientation){

            case 8 :

                position.x -= nbCases;

                break;

            case 4:

                   position.y -= nbCases;

                break;

            case 2:

                   position.x += nbCases;

                break;

            case 6:

                   position.y += nbCases;

                break;
        }



    }


    public void setPosition(Point position) {
        this.position = position;
    }

    public int getChampTire() {
        return champTire;
    }


    /* 8 si l'orientation du bateau est au nord
       4 si l'orientation du bateau est à l'ouest
       2 si l'orienation du bateau est au sud
       6 si l'orientation du bateau est à l'est*/

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }


    public int getOrientation() {
        return orientation;
    }
}
