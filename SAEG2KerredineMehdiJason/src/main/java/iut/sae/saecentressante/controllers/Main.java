package iut.sae.saecentressante.controllers;



import iut.sae.saecentressante.views.MenuFrame;
import java.awt.EventQueue;
import java.io.FileNotFoundException;



/**
 * Classe principale du projet.
 * 
 * @author Mehdi BOURBON
 * @version 1.1
 */
public class Main {



    /**
     * Point d'entree du programme.
     * 
     * @param args Les eventuels arguments passes en ligne de commande.
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        EventQueue.invokeLater(() -> {
            new MenuFrame(new Controller()).setVisible(true);
        });
    }    
}

