package iut.sae.saecentressante.controllers;



import iut.sae.saecentressante.models.Graphe;
import iut.sae.saecentressante.models.GraphePerso;
import iut.sae.saecentressante.views.GraphPanel;
import javax.swing.JScrollPane;



/**
 * Interface etablissant le lien entre les modeles et les vues.
 * 
 * @author Mehdi BOURBON
 * @version 1.1
 */
public interface IGraphe {
    
    
    
    /**
     * Renvoie une JScrollPane avec le graphe de maniere texttuelle.
     * 
     * @param g Le graphe a afficher.
     * @return Une JScrollPane contenant le graphe textuel.
     * @see Graphe
     */
    public JScrollPane afficherGraphe(Graphe g);
    
    /**
     * Renvoie une JScrollPane avec les chemins les moins fiables (seuil de fiabilite defini par l'utilisateur).
     * 
     * @param gp Le GraphPanel d'affichage.
     * @param min La fiabilite minimum.
     * @return Une JScrollPane avec la liste des aretes concernees.
     * @see GraphePanel
     * @see Graphe.Arete#fiab
     */
    public JScrollPane afficherCheminsMoinsFiables(GraphPanel gp, double min);
    
    /**
     * Renvoie une JScrollPane avec les sommets du graphe par type.
     * 
     * @param g Le graphe contenant les sommets en question.
     * @return Une JScrollPane contenant ces informations.
     * @see Graphe
     * @see Graphe.Sommet#type
     */
    public JScrollPane afficherTousTypes(Graphe g);
    
    /**
     * Renvoie un petit texte informatif sur la complexite en temps de l'algo de Floyd-Warshall.
     * 
     * @return Un texte sur la complexite en question.
     * @see Graphe
     */
    public String afficherComplexite();
    
    /**
     * Renvoie une liste des voisins d'un sommet.
     * 
     * @param gp Le GraphPanel pour selectionner les chemins.
     * @param g Le graphe a traiter.
     * @param dist Le type de distance : 1 ou 2.
     * @param som Le sommet dont on cherche les voisins.
     * @return La liste des voisins.
     * @see Graphe
     * @see GraphPanel
     * @see Graphe.Sommet
     * @see Graphe.Arete#dist
     */
    public String afficherVoisinsDe(GraphPanel gp, Graphe g, String dist, String som);
    
    /**
     * Renvoie la liste des voisins d'un sommet (avec un type precis).
     * 
     * @param gp Le GraphPanel pour selectionner les chemins.
     * @param g Le graphe a traiter.
     * @param som Le sommmet dont on cherche les voisins.
     * @param dist Le type de distance : 1 ou 2.
     * @param selection Le type de sommet en question.
     * @return La liste des voisins.
     * @see Graphe
     * @see GraphPanel
     * @see Graphe.Sommet
     * @see Graphe.Arete#dist
     * @see Graphe.Arete#type
     */
    public String afficherVoisinsTypeDe(GraphPanel gp, Graphe g, String som, String dist, String selection);
    
    /**
     * Renvoie le meilleur chemin entre deux sommets, selon l'algo choisi.
     * 
     * Cette methode est commune a deux evenements de la GraphFrame.
     * 
     * @param gp le GraphPanel pour selectionner les chemins.
     * @param node1 Le nom du sommet de depart.
     * @param node2 Le nom du sommet d'arrivee.
     * @param typeAlgo Le type d'algorithme (fiabilite ou distance-duree).
     * @return Le chemin et les ponderations associees.
     * @see Graphe
     * @see GraphPanel
     * @see Graphe.Sommet
     */
    public String afficherAlgo(GraphPanel gp, String node1, String node2, String typeAlgo);
    
    /**
     * Renvoie les sommets d'un type precis.
     * 
     * @param gp Le GraphPanel associe.
     * @param g Le Graphe a trater.
     * @param selection Le type en question.
     * @return La liste des sommets du graphe d'un type precis.
     * @see Graphe
     * @see GraphPanel
     * @see Graphe.Sommet#type
     */
    public String afficherUnType(GraphPanel gp, Graphe g, String selection);
    
    /**
     * Renvoie un texte affichant, entre deux dispensaires, le dispensaire ayant le plus de voisins d'un type precis a 2-distance.
     * 
     * @param gp Le GraphPanel pour selectionner les chemins.
     * @param g Le Graphe a traiter.
     * @param node1 Le premier sommet.
     * @param node2 Le second sommet.
     * @param selection Le type en question.
     * @return Le texte avec le sommet, son nombre de voisins et ses voisins.
     * @see Graphe
     * @see GraphPanel
     * @see Graphe.Sommet
     * @see Graphe.Sommet#type
     */
    public String afficherComparaison(GraphPanel gp, Graphe g, String node1, String node2, String selection);
    
    /**
     * Renvoie la liste des voisins communs a deux dispensaires (type choisi par l'utilisateur).
     * 
     * @param gp Le GraphPanel pour selectionner les chemins.
     * @param g Le Graphe a traiter.
     * @param node1 Le premier sommet.
     * @param node2 Le second sommet.
     * @param selection Le type en question.
     * @return La liste des eventuels voisins communs.
     * @see GraphPanel
     * @see Graphe
     * @see Graphe.Sommet
     * @see Graphe.Sommet#type
     */
    public String afficherVoisinsCommunsType(GraphPanel gp, Graphe g, String node1, String node2, String selection);
    
    /**
     * Renvoie un GraphePerso.
     * 
     * @return Un graphe perso.
     * @see GraphePerso
     * @see Graphe
     */
    public Graphe returnGraphePerso();
    
    /**
     * Enregistre un graphe en PNG.
     * 
     * @param gp Le GraphPanel contenant le graphe.
     * @see FileImplement
     * @see Graphe
     * @see GraphPanel
     */
    public void enregistrerGraphe(GraphPanel gp);
}
