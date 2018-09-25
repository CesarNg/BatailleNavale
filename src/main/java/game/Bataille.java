package game;

public class Bataille {

    private Torpilleur torpilleur;
    private ContreTorpilleur contreTorpilleur;
    private PorteAvion porteAvion;
    private SousMarin sousMarin;
    private Croiseur  croiseur;
    private String [][] champBataille;
    private Joueur joueur1;
    private Joueur joueur2;
    private InterfaceJeu interfaceJeu1;
    private InterfaceJeu interfaceJeu2;




    public Bataille(){

         joueur1 = new Joueur("Cesar");
         interfaceJeu1 = new InterfaceJeu(joueur1);

         joueur1.getListBateau().get(0).deplacement(6,2);

         interfaceJeu1.updateGrid(joueur1);

        // joueur2 = new Joueur("Brice");
        // interfaceJeu2 = new InterfaceJeu(joueur2);

    }

}
