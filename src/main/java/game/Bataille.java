package game;

import exception.SaisieErroneeException;

public class Bataille {

    private String [][] champBataille;
    private Joueur joueur1;
    private Joueur joueur2;
    private InterfaceJeu interfaceJeu1;
    private InterfaceJeu interfaceJeu2;




    public Bataille(){
        boolean fin =false;;

        joueur1 = new Joueur("Cesar");
        try {
            interfaceJeu1 = new InterfaceJeu(joueur1);
        } catch (SaisieErroneeException e) {
            e.printStackTrace();
        }

        //joueur1.getListBateau().get(0).deplacement(6,2);

        //interfaceJeu1.updateGrid(joueur1);

        joueur2 = new Joueur("Brice");
        try {
            interfaceJeu2 = new InterfaceJeu(joueur2);
        } catch (SaisieErroneeException e) {
            e.printStackTrace();
        }

        while(!fin){
            Boolean j1Touche=false,j2Touche=false;

            if (!joueur1.Defaite()&&!joueur2.Defaite()) {
                System.out.println("Tour du joueur 1");
                interfaceJeu1.updateGrid(joueur1);
                j2Touche = joueur1.tir(joueur2);
            }else fin=true;

            if (!joueur1.Defaite()&&!joueur2.Defaite()) {
                System.out.println("Tour du joueur 2");
                interfaceJeu1.updateGrid(joueur2);
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
