package iut.sae.saecentressante.models;



import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;



/**
 * Classe modelisant un graphe a l'aide de deux ArrayLists et de deux classes internes.
 * 
 * @author Mehdi BOURBON
 * @version 1.1
 */
public class Graphe {
    
    

// <editor-fold defaultstate="collapsed" desc="CLASSES INTERNES">
    /**
     * Classe interne modelisant un sommet du graphe.
     * 
     * @author Mehdi BOURBON
     * @see Graphe
     */
    public class Sommet {
        
        
        
        /**
         * Le nom du sommet.
         * 
         * @see Sommet#getNom()
         * @see Sommet#setNom(String)
         */
        private String nom;
        
        /**
         * Le type du sommet.
         * M pour une maternite, O pour un Bloc operatoire, et N pour un Centre nutritionnel.
         * 
         * @see SomType
         * @see Sommet#getTypeG()
         * @see Sommet#setTypeG(String)
         * @see SomType
         */
        private SomType type;

        
        
        /**
         * Constructeur du sommet.
         * 
         * @param nom Le nom du sommet.
         * @param type Le type du sommet : M pour Maternite, O pour Bloc operatoire et N pour Centre nutritionnel.
         * @see Sommet#nom
         * @see Sommet#type
         */
        public Sommet(String nom, SomType type) {
            this.nom = nom;
            this.type = type;
        }
        
        
        
        /**
         * Getter du nom du sommet.
         * 
         * @return Le nom du sommet.
         * @see Sommet#nom
         */
        public String getNom() {return this.nom;}
        
        /**
         * Getter du type du sommet.
         * 
         * @return Le type du sommet.
         * @see Sommet#type
         */
        public SomType getTypeG() {return this.type;}

        /**
         * Setter du nom du sommet.
         * 
         * @param n Le nouveau nom du sommet.
         * @see Sommet#nom
         */
        public void setNom(String n) {this.nom = n;}
        
        /**
         * Setter du type du sommet.
         * 
         * @param t Le nouveau type du sommet.
         * @see Sommet#type
         */
        public void setTypeG(SomType t) {this.type = t;}
        
        /**
         * Affiche le sommet.
         * 
         * @return Un String contenant les informations du sommet.
         * @see Sommet
         * @see Sommet#type
         */
        @Override
        public String toString() {
            String res = null;
            switch(this.type) {
                case MATER -> res = "Le dispensaire " + this.nom + " est une maternite.";
                case OPE -> res = "Le dispensaire " + this.nom + " est un bloc operatoire.";
                case NUTRI -> res = "Le dispensaire " + this.nom + " est un centre nutritionnel.";
                default -> {
                }
            }
            return res;
        }
    }
    
    
    
    /**
     * Classe interne modelisant une arete du graphe.
     * 
     * @author Mehdi BOURBON
     * @see Graphe
     */
    public class Arete {
        
        /**
         * La premiere extremite de l'arete.
         * 
         * @see Sommet
         * @see Arete#getExtremite1()
         */
        private Sommet extr1;
        
        /**
         * La seconde extremite de l'arete.
         * 
         * @see Sommet
         * @see Arete#getExtremite2()
         */
        private Sommet extr2;
        
        /**
         * La fiabilite de l'arete, en % /10.
         */
        private double fiab;
        
        /**
         * La distance, donc la longueur d'une arete, en km.
         */
        private double dist;
        
        /**
         * Le temps necessaire au parcours de l'arete, en min.
         */
        private double dur;
        
        /**
         * Constructeur de l'arete.
         * 
         * @param e1 La premiere extremite de l'arete.
         * @param e2 La seconde extremite de l'arete.
         * @param fiab La fiabilite.
         * @param dist La distance.
         * @param dur La duree.
         * @see Sommet
         * @see Arete#extr1
         * @see Arete#extr2
         * @see Arete#fiab
         * @see Arete#dist
         * @see Arete#dur
         */
        public Arete(Sommet e1, Sommet e2, double fiab, double dist, double dur) {
            this.extr1 = e1;
            this.extr2 = e2;
            this.fiab = fiab;
            this.dist = dist;
            this.dur = dur;
        }
        
        /**
         * Retourne la premiere extremite de l'arete.
         * 
         * @return Le sommet au bout de l'arete.
         * @see Arete#extr1
         */
        public Sommet getExtremite1() {return this.extr1;}
        
        /**
         * Retourne la seconde extremite de l'arete.
         * 
         * @return L'autre sommet au bout de l'arete.
         * @see Arete#extr2
         */
        public Sommet getExtremite2() {return this.extr2;}
        
        /**
         * Retourne la fiabilite de l'arete.
         * 
         * @return La fiabilite.
         * @see Arete#fiab
         */
        public double getFiab() {return fiab;}
        
        /**
         * Retourne la distance de l'arete (sa longueur).
         * 
         * @return La distance.
         * @see Arete#dist
         */
        public double getDist() {return dist;}
        
        /**
         * Retourne la duree necessaire pour parcourir l'arete.
         * 
         * @return La duree.
         * @see Arete#dur
         */
        public double getDur() {return dur;}
        
        /**
         * Modifie la fiabilite de l'arete.
         * 
         * @param fiab La nouvelle fiabilite.
         * @see Arete#fiab
         */
        public void setFiab(double fiab) {this.fiab = fiab;}
        
        /**
         * Modifie la distance de l'arete (sa longueur).
         * 
         * @param dist La nouvelle distance.
         * @see Arete#dist
         */
        public void setDist(double dist) {this.dist = dist;}
        
        /**
         * Modifie la duree necessaire pour parcourir l'arete.
         * 
         * @param dur La nouvelle duree.
         * @see Arete#dur
         */
        public void setDur(double dur) {this.dur = dur;}
        
        /**
         * Affiche l'arete.
         * 
         * @return Un String contenant les informations de l'arete.
         */
        @Override
        public String toString() {
            return "Arete entre " + this.extr1.nom + " et " + this.extr2.nom + " : fiabilite = " + this.fiab*10 + "%, distance = " + this.dist + ", duree = " + this.dur + ".";
        }
    }
// </editor-fold>
    
    

// <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">
    /**
     * La liste des sommets composant le graphe.
     * 
     * @see Sommet
     */
    private List<Sommet> sommets;
    
    /**
     * La liste des aretes composant le graphe.
     * 
     * @see Arete
     */
    private List<Arete> aretes;
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="CONSTRUCTEUR">    
    /**
     * Constructeur du graphe. Il est toujours initialise a vide.
     * 
     * @see Graphe#sommets
     * @see Graphe#aretes
     */
    public Graphe(){
        this.sommets = new ArrayList<>();
        this.aretes = new ArrayList<>();
    }
// </editor-fold>    
    
    
    
// <editor-fold defaultstate="collapsed" desc="GETTERS">    
    /**
     * Renvoie la liste des sommets du graphe.
     * 
     * @return La liste des sommets.
     */
    public List<Sommet> getSommets() {return this.sommets;}
    
    /**
     * Renvoie la liste des aretes du graphe.
     * 
     * @return La liste des aretes. 
     */
    public List<Arete> getAretes() {return this.aretes;}   
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="ADDERS">
    /**
     * Ajoute un sommet au graphe, directement a partir d'un sommet.
     * 
     * @param som Sommet a ajouter.
     * @see Sommet
     * @see Graphe#sommets
     */
    public void ajSommet(Sommet som){this.sommets.add(som);}
    
    /**
     * Ajoute une arete au graphe, avec les sommets correspondants.
     * 
     * @param origine Le sommet 1.
     * @param arrivee Le sommet 2.
     * @param fiab La fiabilite.
     * @param dist La distance.
     * @param dur La duree.
     * @return Un boolean indiquant si tout s'est bien passe.
     * @see Arete
     * @see Sommet
     * @see Graphe#aretes
     */
    public boolean ajAdj(Sommet origine, Sommet arrivee, double fiab, double dist, double dur){
        boolean res = false;
        
        if(this.sommets.contains(origine) && this.sommets.contains(arrivee)) {
            this.aretes.add(new Arete(origine, arrivee, fiab, dist, dur));
            res = true;
        }
        
        return res;
    }
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="METHODES DE RECUPERATION DES DONNEES DU GRAPHE">
    /**
     * Retourne l'ordre du graphe.
     * 
     * @return Le nombre de sommets du graphe.
     */
    public int getNbSommets(){
        return this.sommets.size();
    }
    
    /**
     * Retourne le nombre d'aretes du graphe.
     * 
     * @return Le nombre d'aretes du graphe.
     */
    public int getNbAretes(){
       return this.aretes.size();
    }
    
    /**
     * Affiche le graphe.
     * 
     * @return Une liste des sommets du graphe, avec pour chaque sommet la liste des aretes le concernant.
     */
    @Override
    public String toString() {
        String s = "";
        int cpt = 0;
        
        for(Sommet som : this.sommets){
            s += som.nom;
            
            switch(som.type) {
                case MATER -> s += " (maternite)  -->  ";
                case OPE -> s += " (bloc ope)  -->  ";
                case NUTRI -> s += " (centre nutri)  -->  ";
                default -> {
                }
            }
            
            // affichage des voisins s'il y en a
            for(Arete art : this.aretes){
                if(art.extr1.equals(som)){
                    s += '\t' + art.extr2.nom + " (fiab = " + art.fiab + " | dist = " + art.dist + " | duree = " + art.dur + "), ";
                    cpt++;
                } else if(art.extr2.equals(som)){
                    s += '\t' + art.extr1.nom + " (fiab = " + art.fiab + " | dist = " + art.dist + " | duree = " + art.dur + "), ";
                    cpt++;
                }
            }
            
            if(cpt == 0){
                s += " n'a pas de voisins.";
            } else {
                s = s.substring(0, s.length() - 2) + ".";
            }
            
            if(!som.equals(this.sommets.get(this.sommets.size() - 1))){     // en gros, si ce n'est pas le dernier,
                s += '\n';
            }
        }
        return s;
    }
    
    /**
     * Renvoie le sommet en position pos.
     * 
     * @param pos La position du sommet.
     * @return Le sommet en position pos.
     * @see Graphe#sommets
     */
    public Sommet getSommetPosition(int pos){
        // renvoie le sommet en position K (a partir de 0)
        return(this.sommets.get(pos));
    }
    
    /**
     * Renvoie le sommet de nom nom.
     * @param nom Le nom recherche.
     * @return Graphe#sommets
     */
    public Sommet getSommetNom(String nom){
        Sommet res = null, temp;
        boolean trouve = false;
        Iterator<Sommet> iterSommets = this.sommets.iterator();
        
        while(iterSommets.hasNext() && !trouve){
            temp = iterSommets.next();
            if(temp.nom.equals(nom)){
                res = temp;
                trouve = true;
            }
        }
        return res;
    }

    /**
     * Renvoie la position du sommet m.
     * 
     * @param m Le sommet dont on cherche la position.
     * @return La position du sommet.
     * @see Graphe#sommets
     */
    public int getPos(Sommet m){
        int i;
        
        for(i = 0 ; i < this.sommets.size() ; i++) {
            if(this.sommets.get(i).equals(m)) {
                break;
            }
        }
        
        return i;
    }

    /**
     * Renvoie les aretes liees au sommet m.
     * 
     * @param m Le sommet dont on recherche les voisins.
     * @return Une liste d'aretes avec m comme extremite.
     * @see Graphe#aretes
     * @see Arete
     */
    public List<Arete> getVoisinsDe(Sommet m){
        List<Arete> res = new ArrayList<>();
        for(Arete art : this.aretes){
            if(art.extr1.equals(m) || art.extr2.equals(m)) res.add(art);
        }
        return res;
    }
    
    /**
     * Determine si deux sommets sont voisins.
     * 
     * @param a Le premier sommet.
     * @param b Le deuxieme sommet.
     * @return Un booleen indiquant si les deux sommets sont voisins.
     * @see Graphe#aretes
     * @see Arete
     */
    public boolean sontVoisins(Sommet a, Sommet b){
        boolean trouve = false;
        for(Arete art : this.aretes){
            if((art.extr1.equals(a) && art.extr2.equals(b)) || (art.extr1.equals(b) && art.extr2.equals(a))){
                trouve = true;
                break;
            }
        }
        return trouve;
    }
    
    /**
     * Determine si deux sommets sont voisins a 2-distance.
     * @param a Le premier sommet.
     * @param b Le second sommet.
     * @return Un String indiquant si les sommets sont voisins a 2-distance.
     * @see Arete
     */
    public boolean sontVoisinsDeux(Sommet a, Sommet b){  
        // principe : on recupere les voisins de A et de B, et on prend ceux qui sont communs. S'il y en a != de A et de B alors true sinon false

        boolean res = false;
        List<Sommet> listA = new ArrayList<>(), listB = new ArrayList<>();
        
        if(!a.equals(b)){
            // voisins de A
            for(Arete art : this.getVoisinsDe(a)){
                if(art.extr1.equals(a)) listA.add(art.extr2);
                else if(art.extr2.equals(a)) listA.add(art.extr1);
            }

            // voisins de B
            for(Arete art : this.getVoisinsDe(b)){
                if(art.extr1.equals(b)) listB.add(art.extr2);
                else if(art.extr2.equals(b)) listB.add(art.extr1);
            }

            // intersection : on evite A et B en voisins (puisque directs) : besoin de parcourir une seule liste
            for(Sommet somA : listA){
                if(listB.contains(somA) && !somA.equals(a) && !somA.equals(b)){
                    res = true;
                    break;
                }
            }
        }
        
        return res;
    }
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="METHODES D'AFFICHAGE DE DONNEES">
    /**
     * Affiche tous les sommets du graphe, par type.
     * 
     * @return Un String avec tous les sommets listes par type.
     * @see Sommet
     * @see Graphe#sommets
     */
    public String afficherParType() {
        String m = "- Maternites : ", o = "- Blocs operatoires : ", n = "- Centres nutritionnels : ";
        int cptM = 0, cptO=  0, cptN = 0;
        for(Sommet som : this.sommets){
            switch(som.type){
                case MATER -> {
                    if(cptM == 0) m += som.nom;      // permet un affichage plus propre
                    else m += ", " + som.nom;
                    cptM++;
                }
                case OPE -> {
                    if(cptO == 0) o += som.nom;      // permet un affichage plus propre egalement
                    else o += ", " + som.nom;
                    cptO++;
                }
                case NUTRI -> {
                    if(cptN == 0) n += som.nom;      // permet un affichage plus propre la aussi
                    else n += ", " + som.nom;
                    cptN++;
                }
                default -> {
                }
            }
        }
        
        return "Il y a " + cptM + " maternite(s), " + cptO + " bloc(s) operatoire(s) et " + cptN + " centre(s) nutritionnel(s) :" + '\n' + m + '\n' + o + '\n' + n;
    }
    
    /**
     * Affiche les aretes dont la fiabilite est inferieure a celle passee en parametre.
     * 
     * @param fiabMin Le seuil de fiabilite.
     * @return Un String avec toutes les aretes pas assez fiables.
     * @see Arete
     * @see Arete#fiab
     * @see Graphe#aretes
     */
    public String afficherCheminsMoinsFiables(double fiabMin){
        int cpt = 0;
        String s = "";
        
        for(Arete art : this.aretes){
            if(art.fiab < fiabMin){
                if(art.extr1.nom.compareTo(art.extr2.nom) <= 0){
                    s += "\n- Le chemin entre " + art.extr1.nom + " et " + art.extr2.nom + " est fiable a " + (art.fiab)*10 + "%.";
                } else {
                    s += "\n- Le chemin entre " + art.extr2.nom + " et " + art.extr1.nom + " est fiable a " + (art.fiab)*10 + "%.";
                }
                cpt++;
            }
        }
        
        return cpt + " chemins sur " + this.aretes.size() +" ne sont pas assez fiables (moins de " + fiabMin*10 + " %) :" + s;
    }
    
    /**
     * Indique quels sont les voisins d'un type donne communs a deux sommets.
     * 
     * @param a Le premier sommet.
     * @param b Le second sommet.
     * @param type Le type recherche.
     * @return Un String indiquant les eventuels voisins communs du type donne.
     * @see Graphe#aretes
     * @see Arete
     * @see Sommet#type
     */
    public String voisinsCommunsType(Sommet a, Sommet b, SomType type){
        String s, res = "", strType = "";
        List<Sommet> listA = new ArrayList<>(), listB = new ArrayList<>(), listRes = new ArrayList<>();
        
        // voisins de A
        for(Arete art : this.getVoisinsDe(a)){
            if(art.extr1.equals(a)) listA.add(art.extr2);
            else if(art.extr2.equals(a)) listA.add(art.extr1);
        }

        // voisins de B
        for(Arete art : this.getVoisinsDe(b)){
            if(art.extr1.equals(b)) listB.add(art.extr2);
            else if(art.extr2.equals(b)) listB.add(art.extr1);
        }
        
        // intersection : on evite A et B en voisins (puisque directs) : besoin de parcourir une seule liste
        for(Sommet somA : listA){
            if(listB.contains(somA) && !somA.equals(a) && !somA.equals(b) && somA.type == type){
                listRes.add(somA);
            }
        }
        
        // affichage final
        if(listRes.isEmpty()){
            switch(type){
                case MATER -> res = "Les dispensaires " + a.nom + " et " + b.nom + " n'ont pas de maternite voisine en commun.";
                case OPE -> res = "Les dispensaires " + a.nom + " et " + b.nom + " n'ont pas de bloc operatoire voisin en commun.";
                case NUTRI -> res = "Les dispensaires " + a.nom + " et " + b.nom + " n'ont pas de centre nutritionnel voisin en commun.";
                default -> {
                }
            }
        } else {
            switch(type){
            case MATER -> strType = "maternites";
            case OPE -> strType = "blocs operatoires";
            case NUTRI -> strType = "centres nutritionnels";
            default -> {
                }
            }
            
            s = "Les " + strType + " voisin.e.s des dispensaires " + a.nom + " et " + b.nom + " sont : ";
            
            for(Sommet som : listRes){
                s += som.nom + ", ";
            }
            
            res = s.substring(0, s.length()-2) + ".";
        }
        
        return res;
    }
    

// </editor-fold>
    
 
    
// <editor-fold defaultstate="collapsed" desc="ALGORITHMES DES MEILLEURS CHEMINS">
    /**
     * Algorithme de Dijkstra qui parcourt le graphe a la recherche du chemin le plus rapide entre deux sommets.
     * 
     * @param origine Le sommet de depart.
     * @param arrivee Le sommet d'arrivee.
     * @return Un String indiquant le chemin le plus rapide en duree et en distance.
     * @see Graphe#sommets
     * @see Arete#dur
     * @see Arete#dist
     */
    public String dijkstra(Sommet origine, Sommet arrivee){
        // il faudra verifier que origine et arrivee sont dans le graphe ! (fonction pour cela)
        
        // nouveaux tableaux : F pour les sommets a traiter, precs pour le precedent, dDurees et dDists pour les valuations
        int n = this.sommets.size();
        List<Sommet> F = new ArrayList<>();
        Sommet[] precs = new Sommet[n];
        double[] dDurees = new double[n];
        double[] dDists = new double[n];
        
        // remplissage des degres de valuation et de la liste des sommets a traiter : 0 a l'origine, sinon + l'infini
        for(Sommet som : this.sommets){
            if(som.equals(origine)){        // on part du sommet d'origine, logiquement
                dDurees[this.getPos(som)] = 0.0;
                dDists[this.getPos(som)] = 0.0;
            } else {
                dDurees[this.getPos(som)] = Double.POSITIVE_INFINITY;
                dDists[this.getPos(som)] = Double.POSITIVE_INFINITY;
            }
            
            F.add(som);
            precs[this.getPos(som)] = null;         // remplissage de la liste des precedents avec pour l'instant du vide
        }
        
        // si min vaut 11, c'est parce que l'on raisonne en terme de fiabilites, donc de probabilites (et comme on multipliera une fiabilite par 10...)
        double minDurees, minDists;      // degre de valuation le plus petit (avec Dijkstra, les valuations sont positives)
        Sommet aSuivre;          // pour stocker le prochain sommet a traiter
        int i;

        // recherche du degre de valuation le plus petit (avec Dijkstra, les valuations sont positives et les distances/durees negatives n'ont pas de sens)        
        while(!F.isEmpty()){
            aSuivre = F.get(0);         // initialisation au premier sommet contenu dans F : si le minimum ne change pas, on prendra celui-la
            
            minDurees = Double.POSITIVE_INFINITY;
            minDists = Double.POSITIVE_INFINITY;
            // i sert a iterer et a trouver la bonne position de sommet dans this.sommets
            for(i = 0 ; i < n ; i++){
                if(dDurees[i] < minDurees && F.contains(this.sommets.get(i)) ) {      // attention aux sommets deja traites !
                    minDurees = dDurees[i];
                    minDists = dDists[i];
                    aSuivre = this.getSommetPosition(i);
                }
                // si completement egal au minimum on choisira selon la distance
                else if(dDurees[i] == minDurees && F.contains(this.sommets.get(i))) {       
                    if(dDists[i] < minDists){
                        minDurees = dDurees[i];
                        minDists = dDists[i];
                        aSuivre = this.getSommetPosition(i);
                    }
                }
            }

            F.remove(aSuivre);      // on traite ce sommet

            int ind_i = this.getPos(aSuivre), ind_j;
            for(Arete art : this.getVoisinsDe(aSuivre)){
                if(art.extr1.equals(aSuivre)){
                    if(F.contains(this.getSommetNom(art.extr2.nom))){
                        // ici, S_i vaut aSuivre et S_j vaut le sommet dont le nom est adj.nom
                        // on prend comme valuation la duree : si les resultats sont identiques, on fera appel a la distance
                        ind_j = this.getPos(art.extr2);

                        // procedure de relachement
                        if(dDurees[ind_j] > (dDurees[ind_i] + art.dur)){
                            dDurees[ind_j] = dDurees[ind_i] + art.dur;
                            dDists[ind_j] = dDists[ind_i] + art.dist;
                            precs[ind_j] = aSuivre;
                        } else if(dDurees[ind_i] == (dDurees[ind_j] + art.dur)){        // appel a la distance
                            if(dDists[ind_j] > dDists[ind_i] + art.dist){
                                dDurees[ind_j] = dDurees[ind_i] + art.dur;
                                dDists[ind_j] = dDists[ind_i] + art.dist;
                                precs[ind_j] = aSuivre;
                            }
                        }
                    }
                } else if(art.extr2.equals(aSuivre)) {
                    // même chose si c'est l'autre bout qui est concerne
                    if(F.contains(art.extr1)){
                        // ici, S_i vaut aSuivre et S_j vaut le sommet dont le nom est adj.nom
                        // on prend comme valuation la duree : si les resultats sont identiques, on fera appel a la distance
                        ind_j = this.getPos(art.extr1);

                        // procedure de relâchement
                        if(dDurees[ind_j] > (dDurees[ind_i] + art.dur)){
                            dDurees[ind_j] = dDurees[ind_i] + art.dur;
                            dDists[ind_j] = dDists[ind_i] + art.dist;
                            precs[ind_j] = aSuivre;
                        } else if(dDurees[ind_i] == (dDurees[ind_j] + art.dur)){        // appel a la distance
                            if(dDists[ind_j] > dDists[ind_i] + art.dist){
                                dDurees[ind_j] = dDurees[ind_i] + art.dur;
                                dDists[ind_j] = dDists[ind_i] + art.dist;
                                precs[ind_j] = aSuivre;
                            }
                        }
                    }
                }
            }
        }
                
        String reponse = "", listeSommets = "";
        int indice = this.getPos(arrivee);
        Sommet recup;

        reponse += "Le chemin le plus rapide entre " + origine.nom + " et " + arrivee.nom + " est :";
        
        do{
            recup = precs[indice];

            // pour un affichage plus correct
            if(recup.equals(origine)) listeSommets = " " + recup.nom + listeSommets;
            else listeSommets = ", " + recup.nom + listeSommets;

            indice = this.getPos(recup);

        } while(!recup.equals(origine));

        listeSommets += ", " + arrivee.nom + ".";

        return reponse + listeSommets + '\n' + "La duree du trajet sera alors de " + dDurees[this.getPos(arrivee)] + " minutes, pour un trajet de " + dDists[this.getPos(arrivee)] + " kilometres.";
    }

    
    /**
     * Algorithme de Floyd-Warshall qui parcourt le graphe a la recherche du chemin le plus fiable entre deux sommets.
     * 
     * @param origine Le sommet de depart.
     * @param arrivee Le sommet d'arrivee.
     * @return Un String indiquant le chemin le plus fiable.
     * @see Graphe#sommets
     * @see Arete#fiab
     */
    public String floydWarshall(Sommet origine, Sommet arrivee){
        int i, j, k;
        int n = this.sommets.size();
        
        double[][] W = new double[n][n];
        double[][] Wsuiv = new double[n][n];
        
        Sommet[][] precs = new Sommet[n][n];
        Sommet[][] precsSuiv = new Sommet[n][n];
        
        // on part d'une matrice de probabilites : 1 pour la diagonale (pas de boucles) mais 0 pour le reste si pas de valuations
        for(i = 0 ; i < n ; i++){
            W[i][i] = 1.0;
        }
        
        for(Sommet som : this.sommets){
            // on prend les voisins pour modifier W
            
            for(Arete art : this.getVoisinsDe(som)){
                if(art.extr1.equals(som)){
                    if(this.getPos(som) != this.getPos(this.getSommetNom(art.extr2.nom))) {       // si on est dans W[i][i] on reste a 0 donc on evite ce cas
                        W[this.getPos(som)][this.getPos(this.getSommetNom(art.extr2.nom))] = art.fiab/10;
                        precs[this.getPos(som)][this.getPos(this.getSommetNom(art.extr2.nom))] = this.getSommetNom(som.nom);
                    }
                } else {
                    if(this.getPos(som) != this.getPos(this.getSommetNom(art.extr1.nom))) {
                        W[this.getPos(som)][this.getPos(this.getSommetNom(art.extr1.nom))] = art.fiab/10;
                        precs[this.getPos(som)][this.getPos(this.getSommetNom(art.extr1.nom))] = this.getSommetNom(som.nom);
                    }
                }
            }
            
        }
            
        for(k=0 ; k < n ; k++){
            for(i=0 ; i < n ; i++){
                for(j=0 ; j < n ; j++){
                    // comme on ne peut pas mettre +l'infini, il faut savoir le remplacer au besoin a l'aide de precs (si c'est null ou pas).
                    // si il y a une valuation alors il y a forcement un precedent, on verifie donc juste precs
                    if(   (precs[i][k] != null && precs[k][j] != null)   &&   ((W[i][j] < (W[i][k] * W[k][j]))   ||   (W[i][j] == 0.0 && precs[i][j] == null && i != j))) {
                        Wsuiv[i][j] = W[i][k] * W[k][j];
                        precsSuiv[i][j] = precs[k][j];
                    }
                    else {
                        Wsuiv[i][j] = W[i][j];
                        precsSuiv[i][j] = precs[i][j];
                    }
                }
            }

            // preparation a l'iteration suivante
            W = Wsuiv;
            precs = precsSuiv;
        }
        
        double[] valsW = W[this.getPos(origine)];
        Sommet[] valsPrecs = precs[this.getPos(origine)];

        String reponse = "Le chemin le plus fiable entre " + origine.nom + " et " + arrivee.nom + " est :", listeSommets = "";

        int indice = this.getPos(arrivee);
        Sommet recup;

        do{
            recup = valsPrecs[indice];

            // pour un affichage plus correct
            if(recup.equals(origine)) listeSommets = " " + recup.nom + listeSommets;
            else listeSommets = ", " + recup.nom + listeSommets;

            indice = this.getPos(recup);

        } while(!recup.equals(origine));

        listeSommets += ", " + arrivee.nom + ".";
        
        // servira a arrondir le pourcentage
        DecimalFormat df = new DecimalFormat("##.#");

        return reponse + listeSommets + '\n' + "La fiabilite sera alors de " + df.format(valsW[this.getPos(arrivee)]*100) + " %.";
    }
    
    /**
     * Affiche les informations sur la complexite de l'algorithme de Floyd-Warshall
     * 
     * @return Les infos sous forme de String.
     */
    public static String complexiteFiab() {
        return "        Le programme charge de determiner le chemin le plus fiable entre deux dispensaires utilise l'algorithme de Floyd-Warshall.\n"
                + "Il a une complexite en temps egale a O(n^3), et sa complexite en espace est egale a O(n^2).\n\n"
                + "        Dans cet algorithme, on utilise des matrices (donc des tableaux de tableaux) implementees sous forme de tableaux statiques.\n"
                + "Quatre matrices sont utilisees : la matrice W et celle des precedents, et la matrice suivante pour chacun des deux.\n"
                + "Cela s'explique par le fait que pour determiner la matrice Wn, il faut conserver la matrice Wn-1.\n\n"
                + "        Il aurait ete possible d'utiliser d'autres structures de donnees (notamment des structures dynamiques type ArrayList et LinkedList),\n"
                + "mais le nombre de sommets etant fini et restant le meme sur l'ensemble de l'algorithme, il n'y aurait pas eu de gain de performance.\n\n"
                + "        La structure de donnees utilisee par le projet pour stocker le graphe est un duo de tableaux dynamiques ArrayList,\n"
                + "une structure dont le parcours est plus aise et demande moins de temps.\n"
                + "        L'insertion d'elements dans un ArrayList demande plus d'efforts, mais comme cela arrive rarement, c'est negligeable.\n";
    }
// </editor-fold>

}


