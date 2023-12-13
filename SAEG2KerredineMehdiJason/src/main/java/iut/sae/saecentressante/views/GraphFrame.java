package iut.sae.saecentressante.views;



import iut.sae.saecentressante.controllers.IGraphe;
import iut.sae.saecentressante.models.FileImplement;
import iut.sae.saecentressante.models.Graphe;
import iut.sae.saecentressante.models.GraphePerso;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 * JFrame qui affiche le graphe + les fonctionnalites a droite.
 * 
 * @author Mehdi BOURBON
 * @version 1.1
 */
public class GraphFrame extends javax.swing.JFrame {
    
    
    
    /**
     * Represente le graphe a traiter.
     * 
     * @see Graphe
     */
    private Graphe graphe;
    
    /**
     * Represente le GraphPanel a integrer.
     * 
     * @see GraphPanel
     */
    private GraphPanel panel;
    
    /**
     * Represente l'objet Controller.
     * 
     * @see IGraphe
     * @see Controller
     */
    private IGraphe igr;

    
    
    /**
     * Constructeur d'une GraphFrame.
     * 
     * @param graphe Le graphe a integrer.
     * @param igr Un objet IGraphe qui va faire le lien entre cette JFrame et les methodes du graphe.
     */
    public GraphFrame(Graphe graphe, IGraphe igr) {
        this.initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);       // reste en mode plein ecran par defaut

        // hop une jolie icone
        this.setIconImage(new ImageIcon(".\\src\\main\\java\\iut\\sae\\saecentressante\\assets\\ambulance.png").getImage());
        
        this.igr = igr;
        this.graphe = graphe;
        this.panel = new GraphPanel(graphe);
        this.panel.setPreferredSize(this.graphPanel.getSize());
        this.graphPanel.add(this.panel);
        this.graphPanel.revalidate();
        
        // creation des models de JComboBox de choix du sommet : tres casse-pieds puisque sinon les modeles se repercutent...
        String listeSommets[] = new String[graphe.getNbSommets() + 1];
        listeSommets[0] = "Sommet...";
        int i;
        
        for(i = 1 ; i < graphe.getNbSommets()+1 ; i++) {
            listeSommets[i] = graphe.getSommetPosition(i-1).getNom();
        }
        
        this.nodeComboBox.setModel(new DefaultComboBoxModel(listeSommets));
        this.node1ComboBox.setModel(new DefaultComboBoxModel(listeSommets));
        this.node2ComboBox.setModel(new DefaultComboBoxModel(listeSommets));
        
        
        // model pour les types
        String listeTypes[] = new String[]{"Type...", "maternité", "bloc opératoire", "centre nutritionnel"};
        
        this.nodeTypeComboBox.setModel(new DefaultComboBoxModel(listeTypes));
        this.neighborsTypeComboBox.setModel(new DefaultComboBoxModel(listeTypes));
        this.compareTypeComboBox.setModel(new DefaultComboBoxModel(listeTypes));
        this.compareNeighborsComboBox.setModel(new DefaultComboBoxModel(listeTypes));
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent w){
                int res = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir quitter ?\nVous risquez de perdre vos données.", "Fermer la fenêtre", JOptionPane.OK_CANCEL_OPTION);
                if(res == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        
    }

    /**
     * Methode de generation des composants.
     * 
     * NE PAS MODIFIER !!! (hehe je l'ai fait quand meme)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel() {
            public void paintComponent(Graphics g) {
                try {
                    Image img = ImageIO.read(new File(".\\src\\main\\java\\iut\\sae\\saecentressante\\assets\\fond_ambulance.png"));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException ex) {
                    Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        graphScrollPane = new javax.swing.JScrollPane();
        graphPanel = new javax.swing.JPanel();
        optPanel = new javax.swing.JPanel();
        optionsScrollPane = new javax.swing.JScrollPane();
        optionsPanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        graphLabel = new javax.swing.JLabel();
        graphButton = new javax.swing.JButton();
        complexLabel = new javax.swing.JLabel();
        complexButton = new javax.swing.JButton();
        typeLabel = new javax.swing.JLabel();
        allTypesButton = new javax.swing.JButton();
        nodeTypeLabel = new javax.swing.JLabel();
        nodeTypeButton = new javax.swing.JButton();
        nodeTypeComboBox = new javax.swing.JComboBox<>();
        lessLabel = new javax.swing.JLabel();
        lessSpinner = new javax.swing.JSpinner();
        percentLabel = new javax.swing.JLabel();
        lessButton = new javax.swing.JButton();
        nodePanel = new javax.swing.JPanel();
        nodeComboBox = new javax.swing.JComboBox<>();
        distLabel = new javax.swing.JLabel();
        distComboBox = new javax.swing.JComboBox<>();
        neighborsButton = new javax.swing.JButton();
        nodeInfoLabel = new javax.swing.JLabel();
        nodeSeparator1 = new javax.swing.JSeparator();
        atLabel = new javax.swing.JLabel();
        neighborsByTypeButton = new javax.swing.JButton();
        allNeighborsLabel = new javax.swing.JLabel();
        typeNeighborsLabel = new javax.swing.JLabel();
        neighborsTypeComboBox = new javax.swing.JComboBox<>();
        pathPanel = new javax.swing.JPanel();
        node1Label = new javax.swing.JLabel();
        node1ComboBox = new javax.swing.JComboBox<>();
        node2Label = new javax.swing.JLabel();
        node2ComboBox = new javax.swing.JComboBox<>();
        pathSeparator1 = new javax.swing.JSeparator();
        fiabLabel = new javax.swing.JLabel();
        fiabButton = new javax.swing.JButton();
        fastLabel = new javax.swing.JLabel();
        quickButton = new javax.swing.JButton();
        pathSeparator2 = new javax.swing.JSeparator();
        sameLabel = new javax.swing.JLabel();
        sameButton = new javax.swing.JButton();
        compareNeighborsComboBox = new javax.swing.JComboBox<>();
        compareLabel = new javax.swing.JLabel();
        compareTypeComboBox = new javax.swing.JComboBox<>();
        compareButton = new javax.swing.JButton();
        graphMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newGraphMenuItem = new javax.swing.JMenuItem();
        fileSeparator1 = new javax.swing.JPopupMenu.Separator();
        loadNewMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        fileSeparator2 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        goBackMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Affichage du graphe");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        mainPanel.setLayout(new java.awt.BorderLayout());

        graphPanel.setPreferredSize(new Dimension(1500, 1000));
        graphPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        graphScrollPane.setViewportView(graphPanel);

        mainPanel.add(graphScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        optPanel.setLayout(new java.awt.BorderLayout());

        optionsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        optionsScrollPane.setToolTipText("");

        optionsPanel.setMaximumSize(new java.awt.Dimension(400, 835));
        optionsPanel.setPreferredSize(new java.awt.Dimension(400, 688));
        optionsPanel.setLayout(new java.awt.GridBagLayout());

        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations générales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        infoPanel.setForeground(new java.awt.Color(157, 157, 157));

        infoPanel.setPreferredSize(new java.awt.Dimension(400, 182));

        graphLabel.setText("Graphe");

        graphButton.setText("Afficher");
        graphButton.setToolTipText("");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        complexLabel.setText("Complexité algorithmique");

        complexButton.setText("Afficher");
        complexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complexButtonActionPerformed(evt);
            }
        });

        typeLabel.setText("Sommets par type");

        allTypesButton.setText("Afficher");
        allTypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTypesButtonActionPerformed(evt);
            }
        });

        nodeTypeLabel.setText("Sommets de type");

        nodeTypeButton.setText("Afficher");
        nodeTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeTypeButtonActionPerformed(evt);
            }
        });

        nodeTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "maternité", "bloc opératoire", "centre nutritionnel" }));

        lessLabel.setText("Chemins moins fiables que");

        lessSpinner.setModel(new javax.swing.SpinnerNumberModel(80.0, 0.0, 100.0, 0.1));

        percentLabel.setText("%");

        lessButton.setText("Afficher");
        lessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(graphLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(graphButton))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(complexLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(complexButton))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoPanelLayout.createSequentialGroup()
                                .addComponent(nodeTypeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nodeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(typeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nodeTypeButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(allTypesButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addComponent(lessLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lessButton)))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(graphLabel)
                            .addComponent(graphButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(complexLabel)
                        .addGap(6, 6, 6))
                    .addComponent(complexButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(allTypesButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nodeTypeLabel)
                    .addComponent(nodeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nodeTypeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lessLabel)
                    .addComponent(lessSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percentLabel)
                    .addComponent(lessButton)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        optionsPanel.add(infoPanel, gridBagConstraints);

        nodePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations sur un sommet", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        nodePanel.setPreferredSize(new java.awt.Dimension(400, 166));

        nodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        distLabel.setText("Voisins de...");

        distComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distance...", "1-distance", "2-distance" }));

        neighborsButton.setText("Afficher");
        neighborsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neighborsButtonActionPerformed(evt);
            }
        });

        nodeInfoLabel.setText("Cliquez sur un sommet pour obtenir son nom et son type.");

        atLabel.setText("à...");

        neighborsByTypeButton.setText("Afficher");
        neighborsByTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neighborsByTypeButtonActionPerformed(evt);
            }
        });

        allNeighborsLabel.setText("Tous les voisins");

        typeNeighborsLabel.setText("Voisins d'un type précis");

        neighborsTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "maternité", "bloc opératoire", "centre nutritionnel" }));

        javax.swing.GroupLayout nodePanelLayout = new javax.swing.GroupLayout(nodePanel);
        nodePanel.setLayout(nodePanelLayout);
        nodePanelLayout.setHorizontalGroup(
            nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nodeSeparator1)
                    .addGroup(nodePanelLayout.createSequentialGroup()
                        .addGroup(nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(nodePanelLayout.createSequentialGroup()
                                .addComponent(distLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(atLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(distComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(nodePanelLayout.createSequentialGroup()
                                .addComponent(allNeighborsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(neighborsButton))
                            .addGroup(nodePanelLayout.createSequentialGroup()
                                .addComponent(nodeInfoLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(nodePanelLayout.createSequentialGroup()
                                .addComponent(typeNeighborsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(neighborsTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(neighborsByTypeButton)))
                        .addContainerGap())))
        );
        nodePanelLayout.setVerticalGroup(
            nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nodeInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(distLabel)
                    .addComponent(nodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atLabel)
                    .addComponent(distComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allNeighborsLabel)
                    .addComponent(neighborsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(neighborsByTypeButton)
                    .addComponent(neighborsTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeNeighborsLabel))
                .addGap(20, 20, 20))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        optionsPanel.add(nodePanel, gridBagConstraints);

        pathPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations sur deux sommets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        pathPanel.setPreferredSize(new java.awt.Dimension(400, 312));

        node1Label.setText("Sommet de départ");

        node1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        node2Label.setText("Sommet d'arrivée");

        node2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fiabLabel.setText("Chemin le plus fiable entre ces sommets");

        fiabButton.setText("Afficher");
        fiabButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabButtonActionPerformed(evt);
            }
        });

        fastLabel.setText("Chemin le plus rapide entre ces sommets");

        quickButton.setText("Afficher");
        quickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickButtonActionPerformed(evt);
            }
        });

        sameLabel.setText("Ont-ils des voisins communs ?");

        sameButton.setText("Afficher");
        sameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sameButtonActionPerformed(evt);
            }
        });

        compareNeighborsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "maternité", "bloc opératoire", "centre nutritionnel" }));

        compareLabel.setText("Quel sommet a le plus de dispensaires d'un type à 2-distance ?");

        compareTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "maternité", "bloc opératoire", "centre nutritionnel" }));

        compareButton.setText("Afficher");
        compareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pathPanelLayout = new javax.swing.GroupLayout(pathPanel);
        pathPanel.setLayout(pathPanelLayout);
        pathPanelLayout.setHorizontalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pathSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pathPanelLayout.createSequentialGroup()
                        .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pathSeparator2)
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addComponent(fiabLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fiabButton))
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addComponent(fastLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(quickButton))
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(node1Label)
                                    .addComponent(node1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(node2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(node2Label))
                                .addGap(0, 142, Short.MAX_VALUE))
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addComponent(sameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compareNeighborsComboBox, 0, 133, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sameButton))
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addComponent(compareLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pathPanelLayout.createSequentialGroup()
                                .addComponent(compareTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(compareButton)))
                        .addContainerGap())))
        );
        pathPanelLayout.setVerticalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(node1Label)
                    .addComponent(node2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(node1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(node2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fiabLabel)
                    .addComponent(fiabButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fastLabel)
                    .addComponent(quickButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sameLabel)
                    .addComponent(sameButton)
                    .addComponent(compareNeighborsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compareLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compareTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compareButton))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 49;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 0, 0);
        optionsPanel.add(pathPanel, gridBagConstraints);

        optionsScrollPane.setViewportView(optionsPanel);

        optPanel.add(optionsScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(optPanel, java.awt.BorderLayout.EAST);

        fileMenu.setText("Fichier");

        newGraphMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        newGraphMenuItem.setText("Nouveau...");
        newGraphMenuItem.setEnabled(false);
        fileMenu.add(newGraphMenuItem);
        fileMenu.add(fileSeparator1);

        loadNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        loadNewMenuItem.setText("Charger un autre graphe");
        loadNewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadNewMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadNewMenuItem);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Charger un graphe perso");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem3);
        fileMenu.add(fileSeparator2);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.setText("Enregistrer en .png");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        goBackMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        goBackMenuItem.setText("Revenir à l'accueil");
        goBackMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBackMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(goBackMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.setText("Quitter");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        graphMenuBar.add(fileMenu);

        editMenu.setText("Edition");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Recharger...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Modifier le graphe");
        jMenuItem4.setEnabled(false);
        editMenu.add(jMenuItem4);

        graphMenuBar.add(editMenu);

        setJMenuBar(graphMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Invoque une JOptionPane qui affiche le graphe.
     * 
     * @param evt Un ActionEvent. 
     */
    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        JOptionPane.showMessageDialog(this, this.igr.afficherGraphe(this.graphe), "Graphe", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_graphButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche la complexite de Floyd-Warshall.
     * 
     * @param evt Un ActionEvent. 
     */
    private void complexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complexButtonActionPerformed
        JOptionPane.showMessageDialog(this, this.igr.afficherComplexite(), "Complexité algorithmique", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_complexButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche les chemins les moins fiables.
     * 
     * @param evt Un ActionEvent. 
     */
    private void lessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessButtonActionPerformed
        JOptionPane.showMessageDialog(this, this.igr.afficherCheminsMoinsFiables(this.panel, (double)this.lessSpinner.getValue()/10), "Chemins les moins fiables", JOptionPane.INFORMATION_MESSAGE);
        this.panel.reinit();        // Remet les listes à zero
        this.panel.repaint();
    }//GEN-LAST:event_lessButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche le chemin le plus rapide entre deux sommets.
     * 
     * @param evt Un ActionEvent. 
     */
    private void quickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickButtonActionPerformed
        // cas ou un sommet (ou les deux) n'a pas ete selectionne
        if(node1ComboBox.getSelectedItem().equals("Sommet...") || node2ComboBox.getSelectedItem().equals("Sommet...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un sommet de départ et d'arrivée.", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        // cas ou les deux sommets selectionnes sont identiques
        else if(node1ComboBox.getSelectedItem().equals(node2ComboBox.getSelectedItem())){
            JOptionPane.showMessageDialog(this, "Veuillez choisir deux sommets distincts.", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        // cas normal
        else {
            JOptionPane.showMessageDialog(this, this.igr.afficherAlgo(this.panel, (String)this.node1ComboBox.getSelectedItem(), (String)this.node2ComboBox.getSelectedItem(), "durdist"), "Chemin le plus rapide", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_quickButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche le chemin le plus fiable entre deux sommets.
     * 
     * @param evt Un ActionEvent. 
     */
    private void fiabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabButtonActionPerformed
        // cas ou un sommet (ou les deux) n'a pas ete selectionne
        if(node1ComboBox.getSelectedItem().equals("Sommet...") || node2ComboBox.getSelectedItem().equals("Sommet...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un sommet de départ et d'arrivée.", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        // cas ou les deux sommets selectionnes sont identiques
        else if(node1ComboBox.getSelectedItem().equals(node2ComboBox.getSelectedItem())){
            JOptionPane.showMessageDialog(this, "Veuillez choisir deux sommets distincts.", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        // cas normal
        else {
            JOptionPane.showMessageDialog(this, this.igr.afficherAlgo(this.panel, (String)this.node1ComboBox.getSelectedItem(), (String)this.node2ComboBox.getSelectedItem(), "fiab"), "Chemin le plus fiable", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_fiabButtonActionPerformed

    /**
     * Invoque une JOptionPane qui compare deux sommets a 2-distance.
     * 
     * @param evt Un ActionEvent. 
     */
    private void compareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareButtonActionPerformed
        if(this.node1ComboBox.getSelectedItem().equals("Sommet...") || this.node2ComboBox.getSelectedItem().equals("Sommet...") || this.node1ComboBox.getSelectedItem().equals(this.node2ComboBox.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir deux sommets distincts.", "Attention", JOptionPane.WARNING_MESSAGE);;
        } else if(this.compareTypeComboBox.getSelectedItem().equals("Type...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un type de sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, this.igr.afficherComparaison(this.panel, this.graphe, (String)this.node1ComboBox.getSelectedItem(), (String)this.node2ComboBox.getSelectedItem(), (String)this.compareTypeComboBox.getSelectedItem()), "Comparaison a 2-distance", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_compareButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche les voisins types d'un sommet.
     * 
     * @param evt Un ActionEvent. 
     */
    private void neighborsByTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neighborsByTypeButtonActionPerformed
        // si pas de sommet selectionne
        if(this.nodeComboBox.getSelectedItem().equals("Sommet...")) JOptionPane.showMessageDialog(this, "Veuillez choisir un sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        // si pas de distance selectionnee
        else if(this.distComboBox.getSelectedItem().equals("Distance...")) JOptionPane.showMessageDialog(this, "Veuillez choisir un type de distance.", "Attention", JOptionPane.WARNING_MESSAGE);
        // si pas de type selectionne
        else if(this.neighborsTypeComboBox.getSelectedItem().equals("Type...")) JOptionPane.showMessageDialog(this, "Veuillez choisir un type de sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        // sinon OK pour 1-dist
        else if(this.distComboBox.getSelectedItem().equals("1-distance")){
            JOptionPane.showMessageDialog(this, this.igr.afficherVoisinsTypeDe(this.panel, this.graphe, (String)this.nodeComboBox.getSelectedItem(), (String)this.distComboBox.getSelectedItem(), (String)this.neighborsTypeComboBox.getSelectedItem()), "Voisins typés à 1-distance", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
        // sinon OK pour 2-dist
        else if(this.distComboBox.getSelectedItem().equals("2-distance")){
            JOptionPane.showMessageDialog(this, this.igr.afficherVoisinsTypeDe(this.panel, this.graphe, (String)this.nodeComboBox.getSelectedItem(), (String)this.distComboBox.getSelectedItem(), (String)this.neighborsTypeComboBox.getSelectedItem()), "Voisins typés à 2-distance", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_neighborsByTypeButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche les sommets du graphe tries par type.
     * 
     * @param evt Un ActionEvent. 
     */
    private void allTypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTypesButtonActionPerformed
        JOptionPane.showMessageDialog(this, this.igr.afficherTousTypes(this.graphe), "Sommets du graphe par type", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_allTypesButtonActionPerformed

    private void nodeTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nodeTypeButtonActionPerformed
        if(this.nodeTypeComboBox.getSelectedItem().equals("Type...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un type de sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, this.igr.afficherUnType(this.panel, this.graphe, (String)this.nodeTypeComboBox.getSelectedItem()), "Sommets du graphe par type", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_nodeTypeButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche les voisins d'un sommet.
     * 
     * @param evt Un ActionEvent. 
     */
    private void neighborsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neighborsButtonActionPerformed
        if(this.nodeComboBox.getSelectedItem().equals("Sommet...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else if(this.distComboBox.getSelectedItem().equals("Distance...")){
            JOptionPane.showMessageDialog(this, "Veuillez choisir un type de distance.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, this.igr.afficherVoisinsDe(this.panel, this.graphe, (String)this.distComboBox.getSelectedItem(), (String)this.nodeComboBox.getSelectedItem()), "Voisins à 2-distance", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_neighborsButtonActionPerformed

    /**
     * Invoque une JOptionPane qui affiche les voisins communs a deux sommets.
     * 
     * @param evt Un ActionEvent. 
     */
    private void sameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sameButtonActionPerformed
        if(this.node1ComboBox.getSelectedItem().equals("Sommet...") || this.node2ComboBox.getSelectedItem().equals("Sommet...") || this.node1ComboBox.getSelectedItem().equals(this.node2ComboBox.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir deux sommets distincts.", "Attention", JOptionPane.WARNING_MESSAGE);;
        } else if(this.compareNeighborsComboBox.getSelectedItem().equals("Type...")) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un type de sommet.", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, this.igr.afficherVoisinsCommunsType(this.panel, this.graphe, (String)this.node1ComboBox.getSelectedItem(), (String)this.node2ComboBox.getSelectedItem(), (String)this.compareNeighborsComboBox.getSelectedItem()), "Comparaison a 2-distance", JOptionPane.INFORMATION_MESSAGE);
            this.panel.reinit();
            this.panel.repaint();
        }
    }//GEN-LAST:event_sameButtonActionPerformed

    /**
     * Charge un autre graphe et l'ouvre a la place de l'ancien.
     * 
     * @param evt Un ActionEvent.
     * @see GraphPanel
     * @see Graphe
     */
    private void loadNewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadNewMenuItemActionPerformed
        try {
            Graphe g = FileImplement.loadGraphe();
            this.dispose();
            new GraphFrame(g, this.igr).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur dans le chargement des fichiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loadNewMenuItemActionPerformed

    /**
     * Charge une nouvelle JFrame avec le graphe perso charge dedans.
     * 
     * @param evt Un ActionEvent.
     * @see GraphePerso
     */
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        new GraphFrame(new GraphePerso(), this.igr).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Enregistre le graphe au format PNG.
     * 
     * @param evt Un ActionEvent.
     */
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        this.igr.enregistrerGraphe(this.panel);
    }//GEN-LAST:event_saveMenuItemActionPerformed

    /**
     * Permet de revenir au menu d'accueil.
     * 
     * @param evt Un ActionEvent.
     * @see MenuFrame
     * @see IGraphe
     * @see Controller
     */
    private void goBackMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBackMenuItemActionPerformed
        this.dispose();
        new MenuFrame(this.igr).setVisible(true);
    }//GEN-LAST:event_goBackMenuItemActionPerformed

    /**
     * Quitte.
     * 
     * @param evt Un ActionEvent.
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Êtes vous sûr de vouloir quitter ?\nVous risquez de perdre vos données.", "Fermer la fenêtre", JOptionPane.OK_CANCEL_OPTION);
        if(res == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * Recharge la page (la remplace par une autre JFrame, donc).
     * 
     * @param evt Un ActionEvent.
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        new GraphFrame(this.graphe, this.igr).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel allNeighborsLabel;
    private javax.swing.JButton allTypesButton;
    private javax.swing.JLabel atLabel;
    private javax.swing.JButton compareButton;
    private javax.swing.JLabel compareLabel;
    private javax.swing.JComboBox<String> compareNeighborsComboBox;
    private javax.swing.JComboBox<String> compareTypeComboBox;
    private javax.swing.JButton complexButton;
    private javax.swing.JLabel complexLabel;
    private javax.swing.JComboBox<String> distComboBox;
    private javax.swing.JLabel distLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel fastLabel;
    private javax.swing.JButton fiabButton;
    private javax.swing.JLabel fiabLabel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileSeparator1;
    private javax.swing.JPopupMenu.Separator fileSeparator2;
    private javax.swing.JMenuItem goBackMenuItem;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JMenuBar graphMenuBar;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JScrollPane graphScrollPane;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JButton lessButton;
    private javax.swing.JLabel lessLabel;
    private javax.swing.JSpinner lessSpinner;
    private javax.swing.JMenuItem loadNewMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton neighborsButton;
    private javax.swing.JButton neighborsByTypeButton;
    private javax.swing.JComboBox<String> neighborsTypeComboBox;
    private javax.swing.JMenuItem newGraphMenuItem;
    private javax.swing.JComboBox<String> node1ComboBox;
    private javax.swing.JLabel node1Label;
    private javax.swing.JComboBox<String> node2ComboBox;
    private javax.swing.JLabel node2Label;
    private javax.swing.JComboBox<String> nodeComboBox;
    private javax.swing.JLabel nodeInfoLabel;
    private javax.swing.JPanel nodePanel;
    private javax.swing.JSeparator nodeSeparator1;
    private javax.swing.JButton nodeTypeButton;
    private javax.swing.JComboBox<String> nodeTypeComboBox;
    private javax.swing.JLabel nodeTypeLabel;
    private javax.swing.JPanel optPanel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JScrollPane optionsScrollPane;
    private javax.swing.JPanel pathPanel;
    private javax.swing.JSeparator pathSeparator1;
    private javax.swing.JSeparator pathSeparator2;
    private javax.swing.JLabel percentLabel;
    private javax.swing.JButton quickButton;
    private javax.swing.JButton sameButton;
    private javax.swing.JLabel sameLabel;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel typeNeighborsLabel;
    // End of variables declaration//GEN-END:variables
}
