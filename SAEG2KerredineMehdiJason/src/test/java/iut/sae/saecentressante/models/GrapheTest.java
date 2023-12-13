package iut.sae.saecentressante.models;



import iut.sae.saecentressante.models.Graphe.Sommet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 * Classe de test de la classe Graphe.
 * 
 * @author BOURBON Mehdi - G2S2A
 * @version 0.8
 */
public class GrapheTest {
    
    
    
// <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">  
    /**
     * Represente un sommet de test.
     */
    private Graphe.Sommet sommetTest;
    
    /**
     * Represente une arete de test.
     */
    private Graphe.Arete areteTest;
    
    /**
     * Represente un graphe de test.
     */
    private Graphe grapheTest;
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="INITIALISATION">
    /**
     * Met en place un graphe de test avant chaque methode.
     * 
     * @see Graphe
     */
    @BeforeEach
    public void initialisation() {
        grapheTest = new Graphe();
        grapheTest.ajSommet(grapheTest.new Sommet("A", SomType.MATER));
        grapheTest.ajSommet(grapheTest.new Sommet("B", SomType.OPE));
        grapheTest.ajSommet(grapheTest.new Sommet("C", SomType.NUTRI));
        grapheTest.ajSommet(grapheTest.new Sommet("D", SomType.OPE));
        grapheTest.ajSommet(grapheTest.new Sommet("E", SomType.MATER));
        grapheTest.ajSommet(grapheTest.new Sommet("F", SomType.NUTRI));
        grapheTest.ajSommet(grapheTest.new Sommet("G", SomType.NUTRI));
        grapheTest.ajSommet(grapheTest.new Sommet("H", SomType.MATER));
        grapheTest.ajSommet(grapheTest.new Sommet("I", SomType.OPE));
        grapheTest.ajSommet(grapheTest.new Sommet("J", SomType.OPE));
        grapheTest.ajSommet(grapheTest.new Sommet("K", SomType.MATER));
        
        grapheTest.ajAdj(grapheTest.getSommetNom("A"),grapheTest.getSommetNom("B"),9,5.5, 12);
        grapheTest.ajAdj(grapheTest.getSommetNom("B"),grapheTest.getSommetNom("E"),1,5.5, 45);
        grapheTest.ajAdj(grapheTest.getSommetNom("A"),grapheTest.getSommetNom("C"),7,51.5, 27);
        grapheTest.ajAdj(grapheTest.getSommetNom("A"),grapheTest.getSommetNom("D"),3,1.5, 1);
        grapheTest.ajAdj(grapheTest.getSommetNom("F"),grapheTest.getSommetNom("J"),5.5,50, 150);
        grapheTest.ajAdj(grapheTest.getSommetNom("J"),grapheTest.getSommetNom("K"),1.5,100, 200);
        grapheTest.ajAdj(grapheTest.getSommetNom("C"),grapheTest.getSommetNom("D"),8,37, 100);
        grapheTest.ajAdj(grapheTest.getSommetNom("A"),grapheTest.getSommetNom("J"),5,20, 10);
        grapheTest.ajAdj(grapheTest.getSommetNom("E"),grapheTest.getSommetNom("F"),2,7.5, 3.9);
        
        sommetTest = grapheTest.new Sommet("test", SomType.NUTRI);
        
        areteTest = grapheTest.new Arete(grapheTest.getSommetNom("D"), grapheTest.getSommetNom("E"), 1, 2, 3);
    }
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="TESTS SOMMETS">    
    /**
     * Teste la fonction getNom().
     * 
     * @see Graphe.Sommet#nom
     */
    @Test
    public void testSommetGetNom() {
        assertEquals("test", sommetTest.getNom());
    }
    
    /**
     * Teste la fonction getTypeG().
     * 
     * @see Graphe.Sommet#type
     */
    @Test
    public void testSommetGetTypeG() {
        assertEquals(SomType.NUTRI, sommetTest.getTypeG());
    }
    
    /**
     * Teste la fonction setNom().
     * 
     * @see Graphe.Sommet#nom
     */
    @Test
    public void testSommetSetNom() {
        sommetTest.setNom("letest");
        assertEquals("letest", sommetTest.getNom());
    }
    
    /**
     * Teste la fonction setTypeG().
     * 
     * @see Graphe.Sommet#type
     */
    @Test
    public void testSommetSetTypeG() {
        sommetTest.setTypeG(SomType.MATER);
        assertEquals(SomType.MATER, sommetTest.getTypeG());
    }
    
    /**
     * Teste la fonction toString (parce que je le peux).
     * 
     * @see Graphe.Sommet
     */
    @Test
    public void testSommetToString() {
        assertEquals("Le dispensaire test est un centre nutritionnel.", sommetTest.toString());
    }
// </editor-fold>
    


// <editor-fold defaultstate="collapsed" desc="TESTS ARETES"> 
    /**
     * Teste la fonction getExtremite1().
     * 
     * @see Graphe.Arete#extr1
     */
    @Test
    public void testAreteGetExtremite1() {
        assertEquals(grapheTest.getSommetNom("D"), areteTest.getExtremite1());
    }
    
    /**
     * Teste la fonction getExtremite2().
     * 
     * @see Graphe.Arete#extr2
     */
    @Test
    public void testAreteGetExtremite2() {
        assertEquals(grapheTest.getSommetNom("E"), areteTest.getExtremite2());
    }
    
    /**
     * Teste la fonction getFiab().
     * 
     * @see Graphe.Arete#fiab
     */
    @Test
    public void testAreteGetFiab() {
        assertEquals(areteTest.getFiab(), 1);
    }
    
    /**
     * Teste la fonction getDist().
     * 
     * @see Graphe.Arete#dist
     */
    @Test
    public void testAreteGetDist() {
        assertEquals(areteTest.getDist(), 2);
    }
    
    /**
     * Teste la fonction getDur().
     * 
     * @see Graphe.Arete#dur
     */
    @Test
    public void testAreteGetDur() {
        assertEquals(areteTest.getDur(), 3);
    }
    
    /**
     * Teste 
     */
    @Test
    public void testAreteSetFiab() {
        areteTest.setFiab(4);
        assertEquals(areteTest.getFiab(), 4);
    }
    
    /**
     * 
     */
    @Test
    public void testAreteSetDist() {
        areteTest.setDist(4);
        assertEquals(areteTest.getDist(), 4);
    }
    
    /**
     * 
     */
    @Test
    public void testAreteSetDur() {
        areteTest.setDur(4);
        assertEquals(areteTest.getDur(), 4);
    }
    
    /**
     * 
     */
    @Test
    public void testAreteToString() {
        assertEquals("Arete entre D et E : fiabilite = 10.0%, distance = 2.0, duree = 3.0.", areteTest.toString());
    }
// </editor-fold>



// <editor-fold defaultstate="collapsed" desc="TESTS GRAPHE">
    /**
     * 
     */
    @Test
    public void testAjSommet() {
        grapheTest.ajSommet(sommetTest);
        assertTrue(grapheTest.getSommets().contains(sommetTest));
    }
    
    /**
     * 
     */
    @Test
    public void testAjAdj() {
        assertTrue(grapheTest.ajAdj(grapheTest.getSommetNom("I"), grapheTest.getSommetNom("J"), 4, 5, 6));
    }
    
    /**
     * 
     */
    @Test
    public void testGetNbSommets() {
        assertEquals(grapheTest.getNbSommets(), 11);
    }
    
    /**
     * 
     */
    @Test
    public void testGetNbAretes() {
        assertEquals(grapheTest.getNbAretes(), 9);
    }
    
    /**
     * 
     */
    @Test
    public void testGetSommetPosition() {
        Graphe.Sommet a = grapheTest.new Sommet("A", SomType.MATER);
        assertEquals(grapheTest.getSommetPosition(0).toString(), a.toString());
    }
    
    /**
     * 
     */
    @Test
    public void testGetSommetNom(){
        grapheTest.ajSommet(sommetTest);
        assertEquals(sommetTest, grapheTest.getSommetNom("test"));
    }
    
    /**
     * 
     */
    @Test
    public void testGetPos() {
        assertEquals(grapheTest.getPos(grapheTest.getSommetNom("A")),0);
    }
    
    /**
     * 
     */
    @Test
    public void testSontVoisins() {
        assertTrue(grapheTest.sontVoisins(grapheTest.getSommetNom("A"), grapheTest.getSommetNom("B")));
    }
    
    /**
     * 
     */
    @Test
    public void testSontVoisinsDeux() {
        assertTrue(grapheTest.sontVoisinsDeux(grapheTest.getSommetNom("A"), grapheTest.getSommetNom("E")));
    }
// </editor-fold>
 
}
