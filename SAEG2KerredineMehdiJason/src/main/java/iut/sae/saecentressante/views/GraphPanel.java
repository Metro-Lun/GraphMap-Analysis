package iut.sae.saecentressante.views;



import iut.sae.saecentressante.models.Graphe;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * JPanel affichant le graphe visuellement.
 * 
 * @author Mehdi BOURBON, Kerredine BABA
 * @version 1.1
 */
public class GraphPanel extends JPanel {
    
    
    
// <editor-fold defaultstate="collapsed" desc="CONSTANTES">
    /**
     * Le diamètre d'un point.
     */
    private final static int DIAMETRE = 25;
    
    /**
     * L'espace minimum possible entre deux sommets.
     */
    private final static int ESPACEVITAL = 40;
    
    /**
     * La coordonnee X minimum pour un sommet.
     */
    private final static int XMIN = 10;
    
    /**
     * La coordonnee Y minimum pour un sommet.
     */
    private final static int YMIN = 10;
    
    /**
     * La coordonnee X maximum pour un sommet.
     */
    private final static int XMAX = 1490;
    
    /**
     * La coordonnee Y maximum pour un sommet.
     */
    private final static int YMAX = 950;
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">
    /**
     * Le graphe qu'il faudra dessiner.
     * 
     * @see Graphe
     */
    private Graphe graphe;
    
    /**
     * La liste des coordonnees de chaque sommet.
     * 
     * @see java.awt.Point
     */
    private List<Point> coords;

    /**
     * La liste des sommets selectionnes (pratique pour l'affichage)
     * 
     * @see Graphe.Sommet
     * @see Graphe#sommets
     */
    private List<Boolean> selectedNodes;
    
    /**
     * La liste des aretes selectionnes (pratique pour l'affichage)
     * 
     * @see Graphe.Arete
     * @see Graphe#aretes
     */
    private List<Boolean> selectedLinks;
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="GETTERS">
    /**
     * Renvoie le graphe du Panel.
     * 
     * @return Le graphe.
     * @see Graphe
     */
    public Graphe getGraphe() {return this.graphe;}
    
    /**
     * Renvoie la liste des sommets selectionnes du graphe.
     * 
     * @return La liste des sommets selectionnes.
     * @see Graphe.Sommet
     */
    public List<Boolean> getSelectedNodes() {return this.selectedNodes;}
    
    /**
     * Renvoie la liste des aretes selectionnes du graphe.
     * 
     * @return la liste des aretes selectionnees.
     * @see Graphe.Arete
     */
    public List<Boolean> getSelectedLinks() {return this.selectedLinks;}
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="CONSTRUCTEUR">    
    /**
     * Constructeur du JPanel du graphe.
     *
     * @param graphe Le graphe à dessiner.
     */
    public GraphPanel(Graphe graphe){
        int i;
        this.graphe = graphe;
        this.coords = this.genererCoordsAlea();
        

        // mise a zero des sommets selectionnes (utile pour l'affichage en jaune lorsque presse)
        this.selectedNodes = new ArrayList<>();
        for(i = 0 ; i < this.graphe.getSommets().size() ; i++) {
            this.selectedNodes.add(Boolean.FALSE);
        }
        
        // mise a zero de la liste des aretes selectionnees
        this.selectedLinks = new ArrayList<>();
        for(i = 0 ; i < this.graphe.getAretes().size()  ; i++) {
            this.selectedLinks.add(Boolean.FALSE);
        }
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int i;
                // verifier si sommet : cela risque de devenir une fonction
                for(Graphe.Sommet som : graphe.getSommets()) {
                    i = graphe.getPos(som);

                    if(clicEstDansSommet(e, som)) {
                        selectedNodes.set(i, Boolean.TRUE);
                        repaint();
                        JOptionPane.showMessageDialog(null, som.toString(), "Informations sur le sommet " + som.getNom(), JOptionPane.INFORMATION_MESSAGE);
                        selectedNodes.set(i, Boolean.FALSE);
                        repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for(int i = 0 ; i < graphe.getSommets().size() ; i++) {
                    selectedNodes.set(i, Boolean.FALSE);
                }
                repaint();
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                int i, n;
                Point temp;

                for(Graphe.Sommet som : graphe.getSommets()) {
                    // combien de sommets sont selectionnes ?
                    n = 0;
                    for(boolean bool : selectedNodes) {if(bool) n++;}

                    i = graphe.getPos(som);
                    if(clicEstDansSommet(e, som) && n == 1 && selectedNodes.get(i) && e.getX() < XMAX && e.getY() < YMAX && e.getX() > XMIN && e.getY() > YMIN) {
                        temp = coords.get(i);
                        temp.translate((int) (e.getX() - temp.getX()), (int) (e.getY() - temp.getY()));
                        coords.set(i, temp);
                        repaint();
                    } else if (clicEstDansSommet(e, som) && n == 0 && !selectedNodes.get(i) && e.getX() < XMAX && e.getY() < YMAX && e.getX() > XMIN && e.getY() > YMIN) {
                        // quand on change de point : avec MouseReleased tout se reinitialise
                        selectedNodes.set(i, Boolean.TRUE);
                        coords.set(i, new Point(e.getX(), e.getY()));
                        repaint();
                    }
                }
            }
        });
    }
// </editor-fold>
    
    

// <editor-fold defaultstate="collapsed" desc="METHODES CLASSIQUES">    
    /**
     * Genère des coordonnees aleatoires et les stocke dans une liste de points.
     * 
     * @return La liste des points. 
     */
    private ArrayList<Point> genererCoordsAlea() {
        ArrayList<Point> res = new ArrayList<>();
        Random chiffre = new Random();
        int x, y;
        boolean valide;
        
        // ajout des coords des sommets : on fait un for sur chaque sommet pour avoir le bon nombre de coords
        for(Graphe.Sommet som : this.graphe.getSommets()){
            do {
                valide = true;
                
                x = chiffre.nextInt(XMAX - 30) + 15;
                y = chiffre.nextInt(YMAX - 30) + 15;
                
                for(Point pt : res){
                    if(Math.abs(pt.getX() - x) < ESPACEVITAL && Math.abs(pt.getY() - y) < ESPACEVITAL){
                        valide = false;
                    }
                }
                                
            } while(!valide);
            
            // une fois que c'est bon, on ajoute le point
            res.add(new Point(x, y));
        }
        
        return res;
    }
    
    /**
     * Dessine le JPanel.
     * 
     * @param g Un element Graphics
     * @see java.awt.Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        int i;
        Graphe.Sommet somTemp;
        Graphe.Arete artTemp;
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // traçage des arêtes
        for(i = 0 ; i < this.graphe.getAretes().size() ; i++) {
            artTemp = this.graphe.getAretes().get(i);
            
            // si les deux sommets de l'arete sont selectionnes, elle sera coloriee en orange
            if(this.selectedLinks.get(i)) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            
            // on recupère la position du sommet pour la retrouver dans coords avant d'en recuperer le x et le y : ensuite on dessine.
            g.drawLine((int) this.coords.get(this.graphe.getPos(artTemp.getExtremite1())).getX(),
                       (int) this.coords.get(this.graphe.getPos(artTemp.getExtremite1())).getY(),
                       (int) this.coords.get(this.graphe.getPos(artTemp.getExtremite2())).getX(),
                       (int) this.coords.get(this.graphe.getPos(artTemp.getExtremite2())).getY());
            
            String donnees = "[" + artTemp.getFiab()*10 + "%, " + artTemp.getDist() + " km, " + artTemp.getDur() + " min]";
            
            int x = (int) (this.coords.get(this.graphe.getPos(artTemp.getExtremite1())).getX() + (int) this.coords.get(this.graphe.getPos(artTemp.getExtremite2())).getX()) / 2;
            int y = (int) (this.coords.get(this.graphe.getPos(artTemp.getExtremite1())).getY() + (int) this.coords.get(this.graphe.getPos(artTemp.getExtremite2())).getY()) / 2;
            
            g.drawString(donnees, x, y);
            

        }
        
        // traçage des sommets
        for(i = 0 ; i < this.coords.size() ; i++){
            somTemp = this.graphe.getSommets().get(i);

            if(this.selectedNodes.get(i)){
                g.setColor(Color.ORANGE);
            } else {
                switch(somTemp.getTypeG()){
                    case MATER -> g.setColor(Color.CYAN);
                    case OPE -> g.setColor(Color.RED);
                    case NUTRI -> g.setColor(Color.GREEN);
                }
            }
            
            g.setFont(new Font("Helvetica", Font.BOLD, 18));
            FontMetrics metrics = g.getFontMetrics(getFont());
            int stringWidth = metrics.stringWidth(somTemp.getNom());
            int stringHeight = metrics.getHeight();
            
            g.fillOval(this.coords.get(i).x-17, this.coords.get(i).y-17, 35, 35);
            g.setColor(Color.BLACK);
            
            g.drawString(somTemp.getNom(), this.coords.get(i).x - (stringWidth-6), this.coords.get(i).y + stringHeight/2);
        }
    }
    
    /**
     * Indique si l'on a clique sur un sommet.
     * 
     * @param e Evenement de souris.
     * @param som Le sommet en question.
     * @return Un booleen qui indique si l'on a bien clique dans le sommet.
     */
    public boolean clicEstDansSommet(MouseEvent e, Graphe.Sommet som){
        Point pt = this.coords.get(this.graphe.getPos(som)); 
        return (pt.getX()-DIAMETRE/2 <= e.getX() && e.getX() <= pt.getX()+DIAMETRE/2) && (pt.getY()-DIAMETRE/2 <= e.getY() && e.getY() <= pt.getY()+DIAMETRE/2);
    }
    
    /**
     * Reinitialise et vide les listes de noeuds / d'aretes etant selectionnes par une methode IGraphe.
     * 
     * @see IGraphe
     * @see GraphPanel     * 
     */
    public void reinit(){
        int i;
        // mise a zero des sommets selectionnes (utile pour l'affichage en jaune lorsque presse)
        this.selectedNodes = new ArrayList<>();
        for(i = 0 ; i < this.graphe.getSommets().size() ; i++) {
            this.selectedNodes.add(Boolean.FALSE);
        }
        
        // mise a zero de la liste des aretes selectionnees
        this.selectedLinks = new ArrayList<>();
        for(i = 0 ; i < this.graphe.getAretes().size()  ; i++) {
            this.selectedLinks.add(Boolean.FALSE);
        }
    }
// </editor-fold>
}