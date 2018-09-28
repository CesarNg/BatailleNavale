package game;

import exception.SaisieErroneeException;

import java.util.Scanner;

public class Bataille {

    private String [][] champBataille;
    private Joueur joueur1;
    private Joueur joueur2;
    private InterfaceJeu interfaceJeu1;
    private InterfaceJeu interfaceJeu2;
    private Scanner sc = new Scanner(System.in);




    public Bataille(){
        boolean fin =false;;

        joueur1 = new Joueur("Joueur 1");
        try {
            interfaceJeu1 = new InterfaceJeu(joueur1);
            System.out.print("Tapez une touche pour continuer\n");
            sc.nextLine();
        } catch (SaisieErroneeException e) {
            e.printStackTrace();
        }


        joueur2 = new Joueur("Joueur 2");
        try {
            interfaceJeu2 = new InterfaceJeu(joueur2);
            System.out.print("Tapez une touche pour continuer\n");
            sc.nextLine();
        } catch (SaisieErroneeException e) {
            e.printStackTrace();
        }

        Boolean j1Touche=true,j2Touche=true;
        while(!fin){

            if (!joueur1.Defaite()&&!joueur2.Defaite()) {
                System.out.println("Tour du joueur 1");
                interfaceJeu1.updateGrid(joueur1);

                if (!j1Touche){
                    joueur1.deplacerBateau();
                    interfaceJeu1.updateGrid(joueur1);
                    System.out.print("Tapez une touche pour continuer\n");
                    sc.nextLine();
                }

                j2Touche = joueur1.tir(joueur2);
            }else fin=true;

            if (!joueur1.Defaite()&&!joueur2.Defaite()) {
                System.out.println("Tour du joueur 2");
                interfaceJeu1.updateGrid(joueur2);

                if (!j2Touche){
                    joueur2.deplacerBateau();
                    interfaceJeu2.updateGrid(joueur2);
                    System.out.print("Tapez une touche pour continuer\n");
                    sc.nextLine();
                }

                j1Touche = joueur2.tir(joueur1);
            }else fin=true;

        }

        if (joueur1.Defaite()){
            System.out.println("Joueur 2 à gangé! Bravo "+joueur2.getNom());
        }else{
            System.out.println("Joueur 1 à gangé! Bravo "+joueur1.getNom());
        }

    }

}
