package game;


import exception.SaisieErroneeException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {


    public Menu(){

    }

    public Point menuPositionBateau(String nom, Joueur joueur) throws SaisieErroneeException {

        Scanner sc = new Scanner(System.in);
        Point point = new Point();



        try {

            System.out.println(joueur.getNom()+" choisissez les coordonnées X du "+nom);
            point.x = Integer.parseInt(sc.nextLine());

            if(!(point.x < 10 && point.x >= 0))
                throw new SaisieErroneeException("Saisie Erronée");

            System.out.println(joueur.getNom()+" choisissez les coordonnées y du "+nom);
            point.y = Integer.parseInt(sc.nextLine());

            if(!(point.y < 10 && point.y >= 0))
                throw new SaisieErroneeException("Saisie Erronée");

        } catch(NumberFormatException e) {

            throw  new SaisieErroneeException("Saisie Erronée");
        }


        return point;

    }


    public void logPositionsBateau(Bateau bateau){

        System.out.print("positions du "+ bateau.getNom()+" : ");

        for (Point point :bateau.getListPoint()
             ) {

            System.out.printf("(%s,%s)  ",point.x,point.y);

        }

        System.out.println();
    }

    public int menuOrientationBateau(String nom, Joueur joueur) throws SaisieErroneeException {

        Scanner sc = new Scanner(System.in);
        int ch;

        System.out.println(joueur.getNom()+" choisissez l'orientation du "+nom+"\n2 - sud       4 - ouest     6 - est    8 - Nord");

        try {

            ch = Integer.parseInt(sc.nextLine()) ;

            if(ch!=8 && ch!=4 && ch!=2 && ch!=6)
                throw  new SaisieErroneeException("Saisie Erronée");

        } catch(NumberFormatException e) {

            throw  new SaisieErroneeException("Saisie Erronée");
        }




        return ch;

    }

    public int menuSelectionBateau(ArrayList<Bateau> listBateau, Joueur joueur) throws SaisieErroneeException{
        int k=1, ch;
        Scanner sc = new Scanner(System.in);

        System.out.println(joueur.getNom()+" selectionnez le bateau à déplacer");

        System.out.println("0 - Aucun bateau");

        for (Bateau bateau: listBateau
        ) {
            System.out.print(k+" - "+bateau.getNom()+"   ");

            k++;
        }


        try {

            ch = Integer.parseInt(sc.nextLine()) ;

            if(ch < 0 || ch >= k)
                throw new SaisieErroneeException("Saisie Erronée");

        } catch(NumberFormatException e) {

            throw  new SaisieErroneeException("Saisie Erronée");
        }


        return ch;


    }

    public int menuSelectionNbCasesDeplacement(Joueur joueur)throws SaisieErroneeException{

        Scanner sc = new Scanner(System.in);
        int ch;

        System.out.println(joueur.getNom()+" choisissez le nombre de cases de déplacement");

        try {

            ch = Integer.parseInt(sc.nextLine()) ;

            if(ch<0  || ch>2 )
                throw  new SaisieErroneeException("Saisie Erronée");

        } catch(NumberFormatException e) {

            throw  new SaisieErroneeException("Saisie Erronée");
        }

        return ch;
    }

    public Point menuSelectionTir(Joueur joueur) throws SaisieErroneeException {
        Scanner sc = new Scanner(System.in);
        Point point = new Point();

        try {

            System.out.println(joueur.getNom()+" choisissez les coordonnées X du tir");
            point.x = Integer.parseInt(sc.nextLine());

            if(!(point.x < 10 && point.x >= 0))
                throw new SaisieErroneeException("Saisie Erronée");

            System.out.println(joueur.getNom()+" choisissez les coordonnées Y du tir");
            point.y = Integer.parseInt(sc.nextLine());

            if(!(point.y < 10 && point.y >= 0))
                throw new SaisieErroneeException("Saisie Erronée");

        } catch(NumberFormatException e) {

            throw  new SaisieErroneeException("Saisie Erronée");
        }

        return point;
    }

    public void afficherChampBataille(String [][] champBataille){

        int i,j;

        for (i=0;i < champBataille.length;i++){
            for(j=0;j< champBataille[i].length;j++){

                System.out.print(champBataille[i][j ]);
            }
            System.out.println();
        }
    }

}
