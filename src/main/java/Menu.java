import Bateau.Bateau;

import java.awt.*;
import java.util.Scanner;

public class Menu {


    public Menu(){

    }

    public Point menuPositionBateau(String nom){

        Scanner sc = new Scanner(System.in);
        Point point = new Point();
        System.out.println("Choisissez les coordonnées X du "+nom);
        point.x = sc.nextInt();
        System.out.println("Choisissez les coordonnées y du "+nom);
        point.y = sc.nextInt();

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

    public int menuOrientationBateau(String nom){

        Scanner sc = new Scanner(System.in);

        System.out.println("Choisissez l'orientation du "+nom+"\n 8 - Nord     4 - ouest     2 - sud     6 - est");
        int ch = sc.nextInt();

        return ch;

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
