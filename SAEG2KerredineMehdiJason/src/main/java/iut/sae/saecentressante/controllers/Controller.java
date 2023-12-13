package iut.sae.saecentressante.controllers;



import iut.sae.saecentressante.models.FileImplement;
import iut.sae.saecentressante.models.Graphe;
import iut.sae.saecentressante.models.GraphePerso;
import iut.sae.saecentressante.models.SomType;
import static iut.sae.saecentressante.models.SomType.*;
import iut.sae.saecentressante.views.GraphPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



/**
 * Classe chargee de faire le lien entre la GrapheFrame et le Graphe.
 *
 * @author Mehdi BOURBON
 * @version 1.1
 * @see IGraphe
 * @see Graphe
 */
public class Controller implements IGraphe {



    /**
     * Renvoie une JScrollPane avec le graphe de maniere texttuelle.
     * 
     * @param g Le graphe a afficher.
     * @return Une JScrollPane contenant le graphe textuel.
     * @see Graphe
     */
    @Override
    public JScrollPane afficherGraphe(Graphe g) {
        JTextArea zoneTexte = new JTextArea(g.toString());
        zoneTexte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(zoneTexte);  
        scrollPane.setPreferredSize(new Dimension(700, 500));
        return scrollPane;
    }

    /**
     * Renvoie une JScrollPane avec les chemins les moins fiables (seuil de fiabilite defini par l'utilisateur).
     * 
     * @param gp Le GraphPanel d'affichage.
     * @param min La fiabilite minimum.
     * @return Une JScrollPane avec la liste des aretes concernees.
     * @see GraphePanel
     * @see Graphe.Arete#fiab
     */
    @Override
    public JScrollPane afficherCheminsMoinsFiables(GraphPanel gp, double min) {
        Graphe g = gp.getGraphe();
        int i;
        Graphe.Arete temp;
        
        for(i = 0 ; i < g.getAretes().size() ; i++) {
            temp = g.getAretes().get(i);
            if(temp.getFiab() < min) gp.getSelectedLinks().set(i, Boolean.TRUE);
        }
        
        gp.repaint();
        
        // si trop de resultats, utilisation d'une JDialog incluant un JTextArea ou l'on peut scroller
        JTextArea zoneTexte = new JTextArea(g.afficherCheminsMoinsFiables(min));
        zoneTexte.setEditable(false);       // sinon on peut ecrire dedans, c'est stupide
        JScrollPane scrollPane = new JScrollPane(zoneTexte);  
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        return scrollPane;
    }

    /**
     * Renvoie une JScrollPane avec les sommets du graphe par type.
     * 
     * @param g Le graphe contenant les sommets en question.
     * @return Une JScrollPane contenant ces informations.
     * @see Graphe
     * @see Graphe.Sommet#type
     */
    @Override
    public JScrollPane afficherTousTypes(Graphe g) {
        JTextArea zoneTexte = new JTextArea(g.afficherParType());
        zoneTexte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(zoneTexte);  
        scrollPane.setPreferredSize(new Dimension(500, 200));
        return scrollPane;
    }
    
    /**
     * Renvoie un petit texte informatif sur la complexite en temps de l'algo de Floyd-Warshall.
     * 
     * @return Un texte sur la complexite en question.
     * @see Graphe
     */
    @Override
    public String afficherComplexite() {
        return Graphe.complexiteFiab();
    }
    
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
    @Override
    public String afficherVoisinsDe(GraphPanel gp, Graphe g, String dist, String som) {
        Graphe.Sommet m = g.getSommetNom(som);
        List<Graphe.Arete> list = g.getVoisinsDe(m);
        List<Graphe.Sommet> selectSommets = new ArrayList<>();
        String s;
        int i;
        
        selectSommets.add(m);

        if(dist.equals("1-distance")) {
            s = "Le dispensaire " + m.getNom() + " relie ";

            // traitement des aretes reliees a ce sommet
            for(Graphe.Arete art : list){
                if(art.getExtremite1().equals(m)){
                    s += art.getExtremite2().getNom() + ", ";
                    selectSommets.add(g.getSommetNom(art.getExtremite2().getNom()));
                } else if(art.getExtremite2().equals(m)){
                    s += art.getExtremite1().getNom() + ", ";
                    selectSommets.add(g.getSommetNom(art.getExtremite1().getNom()));
                }
            }

            // coloriage des sommets
            for(Graphe.Sommet sm : selectSommets) {
                gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
            }

            // coloriage des aretes : on parcourt les chemins voisins pour retrouver leur indice dans le graphe
            for(Graphe.Arete art : list){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }

            gp.repaint();
            
            return s.substring(0, s.length() - 2) + ".";
            
        } else {    // 2-distance
            List<Graphe.Sommet> selectSommets2 = new ArrayList<>();
            s = "Le dispensaire " + m.getNom() + " relie ";

            // traitement des aretes reliees a ce sommet
            for(Graphe.Arete art : list){
                if(art.getExtremite1().equals(m)) selectSommets.add(g.getSommetNom(art.getExtremite2().getNom()));
                else if(art.getExtremite2().equals(m)) selectSommets.add(g.getSommetNom(art.getExtremite1().getNom()));
            }

            // traitement des aretes reliees aux voisins : les voisins sont representes par selectSommets
            for(Graphe.Sommet sm : selectSommets) {
                for(Graphe.Arete art : g.getVoisinsDe(sm)) {
                    list.add(art);
                    // on va aussi verifier qu'on ne reprend pas l'origine ni un sommet deja present
                    if((!art.getExtremite1().equals(m) || !art.getExtremite2().equals(m)) && art.getExtremite1().equals(sm) && !selectSommets2.contains(art.getExtremite2())) {
                        // extr1 = le premier voisin => on prend le deubxieme
                        s += art.getExtremite2().getNom() + ", ";
                        selectSommets2.add(g.getSommetNom(art.getExtremite2().getNom()));
                    } else if((!art.getExtremite1().equals(m) || !art.getExtremite2().equals(m)) && art.getExtremite2().equals(sm) && !selectSommets2.contains(art.getExtremite1())) {
                        // et vice versa
                        s += art.getExtremite1().getNom() + ", ";
                        selectSommets2.add(g.getSommetNom(art.getExtremite1().getNom()));
                    }
                }
            }

            // coloriage des sommets
            for(Graphe.Sommet sm : selectSommets2) {
                gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
            }

            // coloriage des aretes : on parcourt les chemins voisins pour retrouver leur indice dans le graphe
            for(Graphe.Arete art : list){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }

            gp.repaint();
            
            return s.substring(0, s.length() - 2) + ".";
        }
    }

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
    @Override
    public String afficherVoisinsTypeDe(GraphPanel gp, Graphe g, String som, String dist, String selection) {
        SomType type = null;
        Graphe.Sommet s = g.getSommetNom(som);                   // sommet a traiter
        List<Graphe.Sommet> selectSommets = new ArrayList<>();      // liste des sommets a colorier
        List<Graphe.Arete> selectAretes = new ArrayList<>(), selectAretes1 = new ArrayList<>();        // listes des aretes a colorier
        int i;
        
        // outils pour l'affichage textuel
        String partie1 = "Le dispensaire " + s.getNom() + " est lie a ";
        String partie2 = "";
        int cpt = 0;
        
        switch(selection) {
                case "maternite" -> type = SomType.MATER;
                case "bloc operatoire" -> type = SomType.OPE;
                case "centre nutritionnel" -> type = SomType.NUTRI;
                default -> {
            }
        }
        
        switch(dist) {
            case "1-distance" -> {
                // si le sommet voisin correspond au type donne, on retient l'arete et le sommet
                for(Graphe.Arete art : g.getVoisinsDe(s)) {
                    
                    if(art.getExtremite1().equals(s) && art.getExtremite2().getTypeG().equals(type)) {
                        // ajout graphique
                        selectSommets.add(art.getExtremite2());
                        selectAretes.add(art);
                        // ajout textuel
                        partie2 += art.getExtremite2().getNom() + ", ";
                        cpt++;
                    } else if(art.getExtremite2().equals(s) && art.getExtremite1().getTypeG().equals(type)) {
                        // ajout graphique
                        selectSommets.add(art.getExtremite1());
                        selectAretes.add(art);
                        // ajout textuel
                        partie2 += art.getExtremite1().getNom() + ", ";
                        cpt++;
                    }
                }

                // coloriage du ou des sommets
                selectSommets.add(s);
                for(Graphe.Sommet sm : selectSommets) {
                    gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
                }

                // si aucun sommet n'a ete selectionne, pas d'aretes a afficher
                if(cpt > 0){
                    // coloriage des aretes : on parcourt les chemins voisins pour retrouver leur indice dans le graphe
                    for(Graphe.Arete art : selectAretes){
                        for(i = 0 ; i < g.getAretes().size() ; i++) {
                            if(art.equals(g.getAretes().get(i))) {
                                gp.getSelectedLinks().set(i, Boolean.TRUE);
                            }
                        }
                    }
                }
                

                // affichage des donnees de maniere textuelle

                switch(type){
                    case MATER -> partie1 = "Les maternites reliant " + s.getNom() + " sont : ";
                    case OPE -> partie1 = "Les blocs operatoires reliant " + s.getNom() + " sont : ";
                    case NUTRI -> partie1 = "Les centres nutritionnels reliant " + s.getNom() + " sont : ";
                    default -> {}
                }

                // dans le cas ou il n'y a qu'un seul sommet, on ameliore l'affichage
                switch (cpt) {
                    case 0 -> partie1 = "Le dispensaire " + s.getNom() + " ne possede pas de voisins de ce type.";
                    case 1 -> {
                        switch(type){
                            case MATER -> partie1 = "La seule maternite qui relie le dispensaire " + s.getNom() + " est ";
                            case OPE -> partie1 = "Le seul bloc operatoire qui relie le dispensaire " + s.getNom() + " est ";
                            case NUTRI -> partie1 = "Le seul centre nutritionnel qui relie le dispensaire " + s.getNom() + " est ";
                            default -> {}
                        }
                        partie2 = partie2.substring(0, partie2.length() - 2) + ".";
                    }
                    default -> partie2 = partie2.substring(0, partie2.length() - 2) + ".";
                }
            }
            
            case "2-distance" -> {
                // stockage des voisins intermediaires
                List<Graphe.Sommet> voisins = new ArrayList<>(), voisins2 = new ArrayList<>();
                
                for(Graphe.Arete art : g.getVoisinsDe(s)) {
                    if(art.getExtremite1().equals(s)) voisins.add(art.getExtremite2());
                    else if(art.getExtremite2().equals(s)) voisins.add(art.getExtremite1());
                }
                
                // place aux sommets suivants
                for(Graphe.Sommet sm : voisins) {
                    for(Graphe.Arete art : g.getVoisinsDe(sm)) {
                        if(!art.getExtremite1().equals(s) && !art.getExtremite2().equals(s)) {
                            if(art.getExtremite1().equals(sm) && art.getExtremite2().getTypeG().equals(type) && !selectSommets.contains(art.getExtremite2())) {
                                // ajout graphique
                                selectSommets.add(art.getExtremite2());
                                selectAretes.add(art);
                                voisins2.add(sm);
                                // ajout textuel
                                partie2 += art.getExtremite2().getNom() + ", ";
                                cpt++;
                            } else if(art.getExtremite2().equals(sm) && art.getExtremite1().getTypeG().equals(type) && !selectSommets.contains(art.getExtremite1())) {
                                // ajout graphique
                                selectSommets.add(art.getExtremite1());
                                selectAretes.add(art);
                                voisins2.add(sm);
                                // ajout textuel
                                partie2 += art.getExtremite1().getNom() + ", ";
                                cpt++;
                            }
                        }
                    }
                }
                
                // ajout des aretes 1-dist
                for(Graphe.Arete art : g.getVoisinsDe(s)) {
                    // pour chaque voisin, s'il est contenu dans les aretes a 2-distance alors on enregistre art
                    if(voisins2.contains(art.getExtremite1()) || voisins2.contains(art.getExtremite2())) {
                        selectAretes1.add(art);
                    }
                }
                
                // coloriage des sommets
                selectSommets.add(s);       // ajout du premier sommet au dernier moment pour qu'on le voie mais que ça ne perturbe pas l'algorithme
                for(Graphe.Sommet sm : selectSommets) {
                    gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
                }

                if(cpt > 0) {
                    // coloriage des aretes
                    for(Graphe.Arete art : selectAretes){
                        for(i = 0 ; i < g.getAretes().size() ; i++) {
                            if(art.equals(g.getAretes().get(i))) {
                                gp.getSelectedLinks().set(i, Boolean.TRUE);
                            }
                        }
                    }
                    for(Graphe.Arete art : selectAretes1){
                        for(i = 0 ; i < g.getAretes().size() ; i++) {
                            if(art.equals(g.getAretes().get(i))) {
                                gp.getSelectedLinks().set(i, Boolean.TRUE);
                            }
                        }
                    }
                }
                
                
                // affichage des donnees de maniere textuelle

                switch(type){
                    case MATER -> partie1 = "Les maternites reliant " + s.getNom() + " sont : ";
                    case OPE -> partie1 = "Les blocs operatoires reliant " + s.getNom() + " sont : ";
                    case NUTRI -> partie1 = "Les centres nutritionnels reliant " + s.getNom() + " sont : ";
                    default -> {}
                }

                // dans le cas ou il n'y a qu'un seul sommet, on ameliore l'affichage
                switch (cpt) {
                    case 0 -> partie1 = "Le dispensaire " + s.getNom() + " ne possede pas de voisins de ce type.";
                    case 1 -> {
                        switch(type){
                            case MATER -> partie1 = "La seule maternite qui relie le dispensaire " + s.getNom() + " est ";
                            case OPE -> partie1 = "Le seul bloc operatoire qui relie le dispensaire " + s.getNom() + " est ";
                            case NUTRI -> partie1 = "Le seul centre nutritionnel qui relie le dispensaire " + s.getNom() + " est ";
                            default -> {}
                        }
                        partie2 = partie2.substring(0, partie2.length() - 2) + ".";
                    }
                    default -> partie2 = partie2.substring(0, partie2.length() - 2) + ".";
                }
            }
            
            default -> {}
        }
        

        gp.repaint();
        return partie1 + partie2;
        
    }
    
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
    @Override
    public String afficherAlgo(GraphPanel gp, String node1, String node2, String typeAlgo) {
        Graphe g = gp.getGraphe();
        String str = "";     // contenu de l'algo choisi

        if(typeAlgo.equals("fiab")) str = g.floydWarshall(g.getSommetNom(node1), g.getSommetNom(node2));
        else if(typeAlgo.equals("durdist")) str = g.dijkstra(g.getSommetNom(node1), g.getSommetNom(node2));
        String str2 = str.split(":")[1];

        // ajout a une liste de sommets dans le but de surligner les aretes concernees dans la suite
        List<Graphe.Sommet> sommetsChemin = new ArrayList<>();

        int i, j;
        boolean unSens, lautreSens;
        String recup = "";

        // enlever les eventuels espaces au debut
        while(str2.charAt(0) == ' ') str2 = str2.substring(1, str2.length());

        // parcourir le String restant et recuperer les sommets
        for(i = 0 ; i < str2.length() ; i++){
            

            // si c'est un point, on a fini de traiter la partie utile du String
            if(str2.charAt(i) == '.') {
                gp.getSelectedNodes().set(g.getPos(g.getSommetNom(recup)), Boolean.TRUE);
                sommetsChemin.add(g.getSommetNom(recup));
                gp.repaint();
                break;
            }

            
            // si c'est un espace, on n'en prend pas compte
            else if(str2.charAt(i) == ' ') continue;

            // si c'est une virgule, on a un sommet : on va le selectionner dans le graphe
            else if(str2.charAt(i) == ',') {
                gp.getSelectedNodes().set(g.getPos(g.getSommetNom(recup)), Boolean.TRUE);
                sommetsChemin.add(g.getSommetNom(recup));
                gp.repaint();
                recup = "";
            }

            // sinon, il faut continuer a recuperer le nom du sommet (peut être compose de plusieurs caracteres)
            else if(str2.charAt(i) != ',') recup += str2.charAt(i);
        }

        for(i = 0 ; i < sommetsChemin.size()-1 ; i++) {
            // selection des aretes a colorier
            for(j = 0 ; j < g.getAretes().size() ; j++) {
                Graphe.Arete art = g.getAretes().get(j);
                unSens = art.getExtremite1().equals(sommetsChemin.get(i)) && art.getExtremite2().equals(sommetsChemin.get(i+1));
                lautreSens = art.getExtremite1().equals(sommetsChemin.get(i+1)) && art.getExtremite2().equals(sommetsChemin.get(i));
                if(unSens || lautreSens) gp.getSelectedLinks().set(j, Boolean.TRUE);
            }
        }

        return str;
    }

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
    @Override
    public String afficherUnType(GraphPanel gp, Graphe g, String selection) { 
        // selection du type
        
        SomType type;
        
        type = switch (selection) {
            case "maternite" -> SomType.MATER;
            case "bloc operatoire" -> SomType.OPE;
            default -> SomType.NUTRI;
        };
        

        String s = "", res;
        
        // ecriture du texte de la JDialog
        int cpt = 0;
        for(Graphe.Sommet som : g.getSommets()){
            if(som.getTypeG().equals(type)){
                if(cpt == 0) s += som.getNom();
            else s += ", " + som.getNom();
            cpt++;
            }
        }
        res = switch(type) {
            case MATER -> "Il y a " + cpt + " maternites : " + s + ".";
            case OPE -> "Il y a " + cpt + " blocs operatoires : " + s + ".";
            case NUTRI -> "Il y a " + cpt + " centres nutritionnels : " + s + ".";
            default -> "erreur";
        };

        String str2 = res.split(":")[1];
        String recup = "";
        int i;

        // enlever les eventuels espaces au debut
        while(str2.charAt(0) == ' ') str2 = str2.substring(1, str2.length());
            
        // parcourir le String restant et recuperer les sommets
        for(i = 0 ; i < str2.length() ; i++){

            // si c'est un point, on a fini de traiter la partie utile du String
            if(str2.charAt(i) == '.') {
                gp.getSelectedNodes().set(g.getPos(g.getSommetNom(recup)), Boolean.TRUE);
                gp.repaint();
                break;
            }

            // si c'est un espace, on n'en prend pas compte
            else if(str2.charAt(i) == ' ') continue;        // apparemment cette ligne est inutile, pourtant le prog ne fonctionne pas sans... a examiner plus tard

            // si c'est une virgule, on a un sommet : on va le selectionner dans le graphe
            else if(str2.charAt(i) == ',') {
                gp.getSelectedNodes().set(g.getPos(g.getSommetNom(recup)), Boolean.TRUE);
                gp.repaint();
                recup = "";
            }

            // sinon, il faut continuer a recuperer le nom du sommet (peut être compose de plusieurs caracteres)
            else if(str2.charAt(i) != ',') recup += str2.charAt(i);
        }
        
        return res;
    }

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
    @Override
    public String afficherComparaison(GraphPanel gp, Graphe g, String node1, String node2, String selection) {
        // principe : on prend les voisins de A et B, on prend les voisins de ces voisins.
        // ensuite avec ces 2-voisins on regarde leur type et on instancie un compteur
        
        SomType type = null;
        
        switch(selection) {
                case "maternite" -> type = SomType.MATER;
                case "bloc operatoire" -> type = SomType.OPE;
                case "centre nutritionnel" -> type = SomType.NUTRI;
                default -> {}
        }
        
        Graphe.Sommet a = g.getSommetNom(node1), b = g.getSommetNom(node2);
        
        List<Graphe.Arete> voisA = g.getVoisinsDe(a), voisB = g.getVoisinsDe(b);            // listes des aretes liees a A et B
        List<Graphe.Sommet> voisA1dist = new ArrayList<>(), voisB1dist = new ArrayList<>();     // listes des sommets voisins de A puis de B
        
        List<Graphe.Sommet> selectSommetsA = new ArrayList<>(), selectSommetsB = new ArrayList<>(); // listes des sommets concernes
        List<Graphe.Arete> selectAretesA = new ArrayList<>(), selectAretesB = new ArrayList<>();    // listes des aretes concernees 1-dist
        List<Graphe.Arete> selectAretesA2 = new ArrayList<>(), selectAretesB2 = new ArrayList<>();   // listes des aretes concernees 2-dist
        
        // parcours sur les voisins de A
        for(Graphe.Arete art : voisA){
            if(art.getExtremite1().equals(a)) voisA1dist.add(art.getExtremite2());
            else if(art.getExtremite2().equals(a)) voisA1dist.add(art.getExtremite1());
        }
        
        // parcours sur les voisins de B
        for(Graphe.Arete art : voisB){
            if(art.getExtremite1().equals(b)) voisB1dist.add(art.getExtremite2());
            else if(art.getExtremite2().equals(b)) voisB1dist.add(art.getExtremite1());
        }
        
        // pour chaque voisin de chaque voisin de A, on va regarder s'il en existe du type recherche. Si oui alors ajout, sinon non
        for(Graphe.Sommet som : voisA1dist){
            
            for(Graphe.Arete art : g.getVoisinsDe(som)) {
                // verifier que A n'est pas dans l'arete sinon c'est 1-distance
                if(!art.getExtremite1().equals(a) && !art.getExtremite2().equals(a)){
                    
                    if(art.getExtremite1().equals(som) && art.getExtremite2().getTypeG().equals(type) && !selectSommetsA.contains(art.getExtremite2())){
                        selectSommetsA.add(art.getExtremite2());
                        selectAretesA2.add(art);
                        
                    } else if(art.getExtremite2().equals(som) && art.getExtremite1().getTypeG().equals(type) && !selectSommetsA.contains(art.getExtremite1())){
                        selectSommetsA.add(art.getExtremite1());
                        selectAretesA2.add(art);
                    }
                }
            }
        }
        
        // meme chose pour B
        for(Graphe.Sommet som : voisB1dist){
            
            for(Graphe.Arete art : g.getVoisinsDe(som)) {
                // verifier que B n'est pas dans l'arete sinon c'est 1-distance
                if(!art.getExtremite1().equals(b) && !art.getExtremite2().equals(b)){
                    
                    if(art.getExtremite1().equals(som) && art.getExtremite2().getTypeG().equals(type) && !selectSommetsB.contains(art.getExtremite2())){
                        selectSommetsB.add(art.getExtremite2());
                        selectAretesB2.add(art);
                        
                    } else if(art.getExtremite2().equals(som) && art.getExtremite1().getTypeG().equals(type) && !selectSommetsB.contains(art.getExtremite1())){
                        selectSommetsB.add(art.getExtremite1());
                        selectAretesB2.add(art);
                    }
                }
            }
        }
        
        // selon le nombre de sommets recenses dans chacune des listes, on choisira celle qui est la plus grande
        String s = "Le dispensaire relie au plus de ", strType = "";
        
        switch(type){
            case MATER -> strType = "maternites";
            case OPE -> strType = "blocs operatoires";
            case NUTRI -> strType = "centres nutritionnels";
            default -> {}
        }
        
        s += strType;
        
        if(selectSommetsA.size() > selectSommetsB.size()) {
            // il faut rajouter les aretes 1-distances concernees par les voisins 2-dist : donc si des extremites concordent...
            for(Graphe.Arete art : g.getVoisinsDe(a)) {
                // pour chaque voisin de A, s'il est contenu dans les aretes a 2-distance alors on enregistre art
                for(Graphe.Arete art2 : selectAretesA2) {
                    if((art2.getExtremite1().equals(art.getExtremite1()) || art2.getExtremite1().equals(art.getExtremite2()) || art2.getExtremite2().equals(art.getExtremite1()) || art2.getExtremite2().equals(art.getExtremite2()))){
                        selectAretesA.add(art);
                    }
                }
            }
            
            s += " est " + a.getNom() + ", avec " + selectSommetsA.size() + " voisin(s) a 2-distance :\n";
            
            for(Graphe.Sommet som : selectSommetsA) {
                s += som.getNom() + ", ";
            }
            
            s = s.substring(0, s.length()-2) + ".";
            
            int i;
            
            // coloriage des sommets
            for(Graphe.Sommet sm : selectSommetsA) {
                gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
            }

            // coloriage des aretes
            for(Graphe.Arete art : selectAretesA){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }
            for(Graphe.Arete art : selectAretesA2){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }
            
            gp.repaint();
            
            return s;
            
        } else if(selectSommetsA.size() < selectSommetsB.size()) {
            // il faut traiter les aretes en trop dans selectAretesB egalement
            for(Graphe.Arete art : g.getVoisinsDe(b)) {
                // pour chaque voisin de A, s'il est contenu dans les aretes a 2-distance alors on enregistre art
                for(Graphe.Arete art2 : selectAretesB2) {
                    if(art2.getExtremite1().equals(art.getExtremite1()) || art2.getExtremite1().equals(art.getExtremite2()) || art2.getExtremite2().equals(art.getExtremite1()) || art2.getExtremite2().equals(art.getExtremite2())){
                        selectAretesB.add(art);
                    }
                }
            }
            
            s += " est " + b.getNom() + ", avec " + selectSommetsB.size() + " voisin(s) a 2-distance :\n";
            
            for(Graphe.Sommet som : selectSommetsB) {
                s += som.getNom() + ", ";
            }
            
            s = s.substring(0, s.length()-2) + ".";
            
            int i;
            
            // coloriage des sommets
            for(Graphe.Sommet sm : selectSommetsB) {
                gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
            }

            // coloriage des aretes
            for(Graphe.Arete art : selectAretesB){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }
            for(Graphe.Arete art : selectAretesB2){
                for(i = 0 ; i < g.getAretes().size() ; i++) {
                    if(art.equals(g.getAretes().get(i))) {
                        gp.getSelectedLinks().set(i, Boolean.TRUE);
                    }
                }
            }
            
            gp.repaint();
            
            return s;
            
            
            
        } else return "Les dispensaires " + a.getNom() + " et " + b.getNom() + " sont relies au meme nombre de " + strType + " a 2-distance, c'est-a-dire " + selectSommetsA.size() + ".";        
    }

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
    @Override
    public String afficherVoisinsCommunsType(GraphPanel gp, Graphe g, String node1, String node2, String selection) {
        SomType type = null;
        switch(selection) {
                case "maternite" -> type = SomType.MATER;
                case "bloc operatoire" -> type = SomType.OPE;
                case "centre nutritionnel" -> type = SomType.NUTRI;
                default -> {}
        }
        
        Graphe.Sommet a = g.getSommetNom(node1), b = g.getSommetNom(node2);
        
        List<Graphe.Sommet> voisA = new ArrayList<>(), voisB = new ArrayList<>();     // listes des sommets voisins de A puis de B
        
        List<Graphe.Sommet> selectSommets = new ArrayList<>();                        // liste des sommets a surligner dans le Panel
        List<Graphe.Arete> selectAretes = new ArrayList<>();                          // listes des aretes a surligner dans le Panel
        
        String s = "Les dispensaires " + a.getNom() + " et " + b.getNom() + " sont tous les deux relies\na ", temp = "";
        
        
        // recuperation des sommets interessants de A
        for(Graphe.Arete art : g.getVoisinsDe(a)) {
            if(art.getExtremite1().equals(a) && art.getExtremite2().getTypeG().equals(type)) {
                voisA.add(art.getExtremite2());
            } else if(art.getExtremite2().equals(a) && art.getExtremite1().getTypeG().equals(type)) {
                voisA.add(art.getExtremite1());
            }
        }
        
        // recuperation des sommets interessants de B
        for(Graphe.Arete art : g.getVoisinsDe(b)) {
            if(art.getExtremite1().equals(b) && art.getExtremite2().getTypeG().equals(type)) {
                voisB.add(art.getExtremite2());
            } else if(art.getExtremite2().equals(b) && art.getExtremite1().getTypeG().equals(type)) {
                voisB.add(art.getExtremite1());
            }
        }
        
        // recherche de voisins communs
        for(Graphe.Sommet ax : voisA) {
            for(Graphe.Sommet bx : voisB) {
                if(g.getSommetNom(ax.getNom()).equals(g.getSommetNom(bx.getNom()))){
                    selectSommets.add(ax);
                    temp += ax.getNom() + ", ";
                }
            }
        }
        
        // recuperation des aretes concernees (A ou B)
        for(Graphe.Sommet som : selectSommets) {
            for(Graphe.Arete art : g.getVoisinsDe(som)) {
                if(art.getExtremite1().equals(a) || art.getExtremite1().equals(b) || art.getExtremite2().equals(a) || art.getExtremite2().equals(b)) {
                    selectAretes.add(art);
                }
            }
        }
        
        if(selectSommets.isEmpty()) {
            // cas ou pas de voisins
            s = "Les dispensaires " + a.getNom() + " et " + b.getNom() + " ne sont pas relies a un même dispensaire de type " + selection + ".";
        } else {
            // remplissage du String
            s += selectSommets.size() + " dispensaire(s) de type " + selection + " : " + temp;
            s = s.substring(0, s.length()-2) + ".";
        }
        
        int i;
            
        // coloriage des sommets
        selectSommets.add(a);
        selectSommets.add(b);
        for(Graphe.Sommet sm : selectSommets) {
            gp.getSelectedNodes().set(g.getPos(sm), Boolean.TRUE);
        }

        // coloriage des aretes
        for(Graphe.Arete art : selectAretes){
            for(i = 0 ; i < g.getAretes().size() ; i++) {
                if(art.equals(g.getAretes().get(i))) {
                    gp.getSelectedLinks().set(i, Boolean.TRUE);
                }
            }
        }

        gp.repaint();
        
        return s;
    }
    
    /**
     * Renvoie un GraphePerso.
     * 
     * @return Un graphe perso.
     * @see GraphePerso
     * @see Graphe
     */
    @Override
    public Graphe returnGraphePerso() {
        return new GraphePerso();
    }
    
    /**
     * Enregistre un graphe en PNG.
     * 
     * @param gp Le GraphPanel contenant le graphe.
     * @see FileImplement
     * @see Graphe
     * @see GraphPanel
     */
    @Override
    public void enregistrerGraphe(GraphPanel gp) {
        FileImplement.saveGraphe(gp);
    }
}