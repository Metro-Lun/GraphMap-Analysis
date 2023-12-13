package iut.sae.saecentressante.models;



/**
 * Classe representant un graphe specifique.
 * 
 * @author Mehdi BOURBON
 * @version 1.1
 */
public class GraphePerso extends Graphe {
    
    
    
    /**
     * Constructeur d'un graphe personnalise, construit par notre groupe.
     * 
     * @see Graphe
     */
    public GraphePerso() {
        
        // initialisation des listes du graphe a vide
        super();
        
        // creation des sommets
        Graphe.Sommet s = this.new Sommet("s", SomType.MATER);
        Graphe.Sommet a = this.new Sommet("a", SomType.OPE);
        Graphe.Sommet b = this.new Sommet("b", SomType.NUTRI);
        Graphe.Sommet c = this.new Sommet("c", SomType.OPE);
        Graphe.Sommet d = this.new Sommet("d", SomType.MATER);
        Graphe.Sommet e = this.new Sommet("e", SomType.OPE);
        Graphe.Sommet f = this.new Sommet("f", SomType.MATER);
        Graphe.Sommet g = this.new Sommet("g", SomType.MATER);
        Graphe.Sommet h = this.new Sommet("h", SomType.MATER);
        Graphe.Sommet i = this.new Sommet("i", SomType.MATER);
        Graphe.Sommet j = this.new Sommet("j", SomType.OPE);
        Graphe.Sommet k = this.new Sommet("k", SomType.MATER);
        Graphe.Sommet l = this.new Sommet("l", SomType.NUTRI);
        Graphe.Sommet m = this.new Sommet("m", SomType.MATER);
        Graphe.Sommet n = this.new Sommet("n", SomType.MATER);
        Graphe.Sommet o = this.new Sommet("o", SomType.NUTRI);
        Graphe.Sommet p = this.new Sommet("p", SomType.MATER);
        Graphe.Sommet q = this.new Sommet("q", SomType.MATER);
        Graphe.Sommet r = this.new Sommet("r", SomType.MATER);
        Graphe.Sommet t = this.new Sommet("t", SomType.MATER);
        Graphe.Sommet u = this.new Sommet("u", SomType.MATER);
        Graphe.Sommet v = this.new Sommet("v", SomType.OPE);
        Graphe.Sommet w = this.new Sommet("w", SomType.MATER);
        Graphe.Sommet x = this.new Sommet("x", SomType.OPE);
        Graphe.Sommet y = this.new Sommet("y", SomType.MATER);
        Graphe.Sommet z = this.new Sommet("z", SomType.NUTRI);
        Graphe.Sommet zero = this.new Sommet("0", SomType.OPE);
        Graphe.Sommet un = this.new Sommet("1", SomType.NUTRI);
        Graphe.Sommet deux = this.new Sommet("2", SomType.OPE);
        Graphe.Sommet trois = this.new Sommet("3", SomType.NUTRI);
        Graphe.Sommet quatre = this.new Sommet("4", SomType.MATER);
        Graphe.Sommet cinq = this.new Sommet("5", SomType.MATER);
        Graphe.Sommet six = this.new Sommet("6", SomType.MATER);
        Graphe.Sommet sept = this.new Sommet("7", SomType.MATER);
        Graphe.Sommet huit = this.new Sommet("8", SomType.MATER);
        Graphe.Sommet neuf = this.new Sommet("9", SomType.MATER);
        

        // ajout des sommets au graphe
        this.ajSommet(s);
        this.ajSommet(a);
        this.ajSommet(b);
        this.ajSommet(c);
        this.ajSommet(d);
        this.ajSommet(e);
        this.ajSommet(f);
        this.ajSommet(g);
        this.ajSommet(h);
        this.ajSommet(i);
        this.ajSommet(j);
        this.ajSommet(k);
        this.ajSommet(l);
        this.ajSommet(m);
        this.ajSommet(n);
        this.ajSommet(o);
        this.ajSommet(p);
        this.ajSommet(q);
        this.ajSommet(r);
        this.ajSommet(t);
        this.ajSommet(u);
        this.ajSommet(v);
        this.ajSommet(w);
        this.ajSommet(x);
        this.ajSommet(y);
        this.ajSommet(z);
        this.ajSommet(zero);
        this.ajSommet(un);
        this.ajSommet(deux);
        this.ajSommet(trois);
        this.ajSommet(quatre);
        this.ajSommet(cinq);
        this.ajSommet(six);
        this.ajSommet(sept);
        this.ajSommet(huit);
        this.ajSommet(neuf);
        
        // ajout de liens
        this.ajAdj(s, a, 8.0, 5.0, 3.0);
        this.ajAdj(s, d, 2.0, 3.0, 3.0);
        this.ajAdj(a, d, 4.0, 12.0, 10.0);
        this.ajAdj(a, c, 1.0, 4.0, 6.0);
        this.ajAdj(a, b, 5.0, 1.0, 1.0);
        this.ajAdj(b, c, 2.0, 5.0, 4.0);
        this.ajAdj(d, c, 2.0, 3.0, 9.0);
        this.ajAdj(d, e, 6.0, 5.0, 4.0);
        this.ajAdj(d, f, 1.0, 2.0, 7.0);
        this.ajAdj(c, f, 3.0, 17.0, 4.0);
        this.ajAdj(e, f, 7.0, 2.0, 3.0);
        this.ajAdj(d, m, 10.0, 13.0, 24.0);
        this.ajAdj(m, l, 8.9, 7.0, 8.0);
        this.ajAdj(l, d, 5.0, 10.0, 15.0);
        this.ajAdj(l, e, 5.0, 10.0, 15.0);
        this.ajAdj(k, l, 4.7, 12.0, 10.0);
        this.ajAdj(k, e, 6.0, 15.0, 3.0);
        this.ajAdj(g, j, 9.5, 3.0, 0.9);
        this.ajAdj(g, i, 2.0, 6.0, 23.0);
        this.ajAdj(i, j, 4.0, 23.0, 27.5);
        this.ajAdj(h, b, 1.9, 11.0, 24.0);
        this.ajAdj(c, h, 7.4, 5.8, 9.6);
        this.ajAdj(c, g, 4.5, 45.0, 23.0);
        this.ajAdj(h, g, 9.0, 16.0, 10.0);
        this.ajAdj(e, i, 8.7, 4.0, 3.0);
        this.ajAdj(k, f, 4.5, 12.0, 27.1);
        this.ajAdj(f, i, 6.0, 5.0, 6.0);
        this.ajAdj(i, k, 5.0, 12.0, 26.0);
        this.ajAdj(p, l, 4.7, 20.5, 10.0);
        this.ajAdj(p, k, -7.0, 4.5, 23.0);
        this.ajAdj(h, q, 8.0, 11.0, 38.0);
        this.ajAdj(q, v, 9.0, 5.3, 12.2);
        this.ajAdj(w, l, 5.9, 12.0, 27.0);
        this.ajAdj(w, p, 5.4, 48.0, 56.0);
        this.ajAdj(w, quatre, 2.0, 9.1, 17.0);
        this.ajAdj(p, x, 7.0, 6.4, 12.0);
        this.ajAdj(trois, quatre, 1.2, 12.0, 12.0);
        this.ajAdj(x, trois, 10.0, 11.0, 13.0);
        this.ajAdj(n, y, 7.8, 15.0, 36.3);
        this.ajAdj(q, r, 3.0, 4.0, 12.5);
        this.ajAdj(cinq, deux, 0.5, 10.6, 27.2);
        this.ajAdj(quatre, x, 5.4, 36.0, 27.4);
        this.ajAdj(n, p, 7.8, 15.4, 6.5);
        this.ajAdj(x, y, 1.0, 1.0, 1.0);
        this.ajAdj(n, k, 2.7, 23.5, 3.0);
        this.ajAdj(j, t, 1.2, 4.5, 20.0);
        this.ajAdj(t, sept, 7.0, 22.0, 10.0);
        this.ajAdj(t, deux, -4.5, 17.0, 32.6);
        this.ajAdj(q, g, 9.6, 17.0, 35.2);
        this.ajAdj(y, trois, 5.2, 17.0, 6.9);
        this.ajAdj(sept, trois, 7.0, 3.0, 10.0);
        this.ajAdj(sept, deux, 0.5, 1.0, 0.8);
        this.ajAdj(huit, j, 7.0, 5.6, 9.3);
        this.ajAdj(v, z, 8.5, 7.3, 19.2);
        this.ajAdj(t, un, 2.7, 20.01, 6.3);
        this.ajAdj(cinq, un, 6.5, 15.0, 23.0);
        this.ajAdj(cinq, six, 10.0, 4.4, 12.0);
        this.ajAdj(deux, trois, 5.5, 2.2, 3.3);
        this.ajAdj(deux, six, 8.8, 2.2, 6.6);
        this.ajAdj(zero, six, 6.0, 6.0, 6.0);
        this.ajAdj(un, six, 4.8, 8.6, 13.5);
        this.ajAdj(u, z, 5.9, 10.4, 9.3);
        this.ajAdj(r, j, 4.7, 18.6, 1.8);
        this.ajAdj(q, j, 8.8, 46.5, 23.2);
        this.ajAdj(un, deux, 5.6, 12.0, 13.5);
        this.ajAdj(zero, u, 9.4, 17.0, 26.0);
        this.ajAdj(r, u, 10.0, 3.0, 2.4);
        this.ajAdj(t, u, 8.0, 4.5, 6.8);
        this.ajAdj(deux, u, -1.0, 17.6, 4.9);
        this.ajAdj(sept, y, 9.8, 12.7, 8.4);
        this.ajAdj(t, huit, 0.2, 2.6, 37.9);
        this.ajAdj(r, v, 3.0, 5.7, 8.8);
        this.ajAdj(r, huit, 7.8, 17.6, 16.7);
        this.ajAdj(huit, u, 2.1, 5.6, 12.3);
        this.ajAdj(zero, un, 1.1, 12.0, 14.0);
        this.ajAdj(z, r, 9.0, 7.8, 6.4);
        this.ajAdj(neuf, zero, 9.0, 3.0, 7.0);
        this.ajAdj(z, neuf, 3.4, 17.2, 19.0);
        this.ajAdj(o, i, 3.9, 4.0, 15.2);
        this.ajAdj(o, j, 8.5, 6.5, 4.9);
        this.ajAdj(o, k, 10.0, 15.0, 47.0);
        this.ajAdj(o, t, 9.6, 31.0, 26.5);
        this.ajAdj(o, y, 4.2, 17.35, 18.0);
        this.ajAdj(o, n, 6.5, 23.0, 35.4);
        this.ajAdj(o, sept, 7.0, 17.0, 27.0);
    }
    
}
