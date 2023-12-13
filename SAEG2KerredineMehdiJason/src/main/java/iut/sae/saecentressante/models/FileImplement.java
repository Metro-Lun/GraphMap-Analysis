package iut.sae.saecentressante.models;



import iut.sae.saecentressante.views.GraphPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 * Classe implementant les fichiers dans le programme.
 * 
 * @author Jason GUGLIELMETTO
 * @version 1.1
 */
public class FileImplement {
    
    
    
// <editor-fold defaultstate="collapsed" desc="ATTRIBUTS">
    /**
     * Tableau contenant la liste d'adjacence du graphe provenant du fichier.
     * 
     * @see Graphe
     */
    private static ArrayList<String> tabAdj;
    
    /**
     * Tableau de tableaux contenant les successeurs du graphe provenant du fichier.
     * 
     * @see Graphe
     */
    private static ArrayList<ArrayList<Integer>> tabSucc;
// </editor-fold>
    
    
    
// <editor-fold defaultstate="collapsed" desc="METHODES">    
    /**
     * Charge le fichier d'adjacents dans une structure de donnees.
     * 
     * @param fileAdjName Le nom du fichier contenant la liste d'adjacence.
     * @throws FileNotFoundException 
     * @see FileImplement#tabAdj
     * @see FileNotFoundException
     */
    private static void loadAdj(FileReader fileAdj) throws FileNotFoundException{
        Scanner adj = new Scanner(fileAdj);
        String filesLine;
        //Variables de parcours de String et ArrayList<>
        int j, i;
        
        tabAdj = new ArrayList<>();
        
        // Verifie si il existe une ligne suivante.
        while(adj.hasNextLine()){
            j = 0;
            i = 0;
            filesLine = adj.nextLine();
            
            // Verifie si la ligne a recuperer n'est pas un commentaire.
            if(!filesLine.substring(0, 2).equals("//")){
                // Parcours la ligne jusqu'a arriver apres le type du dispensaire.
                while(i != 2){
                    if(filesLine.charAt(j) == ';'){
                        i++;
                    }
                    j++;
                }
                j--;
                
                while(j < filesLine.length()){
                    if(filesLine.length() > j){
                        // Supprime les liens vide
                        if(filesLine.substring(j-3,j).equals(";0;")){
                            filesLine = filesLine.substring(0,j-2) + filesLine.substring(j,filesLine.length());
                        } else {
                            j++;
                        }
                    }
                }
                // Supprime les deux dernier caracteres qunad ils sont egals a ";0".
                if(filesLine.substring(filesLine.length()-2, filesLine.length()).equals(";0")){
                    filesLine = filesLine.substring(0, filesLine.length()-2);
                }
                tabAdj.add(filesLine);
            }
        }
        adj.close();
    }
    
    /**
     * Charge le fichier d'adjacents dans une structure de donnees.
     * 
     * @param fileSuccName Le nom du fichier contenant la liste des voisins.
     * @throws FileNotFoundException
     * @see FileImplement#tabSucc
     * @see FileNotFoundException
     */
    private static void loadSucc(FileReader fileSucc) throws FileNotFoundException{
        ArrayList<Integer> lineSucc = new ArrayList<>();
        tabSucc = new ArrayList<>();
        String filesLine;
        Scanner succ = new Scanner(fileSucc);
        // variable en vracs. Plus de precisions lors de leurs utilisations.
        int i, j;
        
        
        while(succ.hasNextLine()){
            
            // Ligne du fichier a charger.
            filesLine = succ.nextLine();
            
            j = 0;
            
            // Supprime le nom du dispensaire pour ne garder que les successeurs.
                // Ici i et j servent a suprimer les ";" en trop dans la ligne des successeurs
            while(filesLine.charAt(j) != ';'){
                j++;
            }
            filesLine = filesLine.substring(j+1);
            i = filesLine.length()-1;
            
            // Suprime les  ";" tant que i est egal a ";".
            while(filesLine.charAt(i) == ';'){
                filesLine = filesLine.substring(0, i);
                i--;
            }
            j = -1;
            i = 0;
            
                //Ici i et j servent a recuperer les nombres entre les ";" dans la ligne des successeurs
            while(j < filesLine.length()-1){
                j++;
                
                while((filesLine.charAt(j) != ';') && (j < filesLine.length()-1)){
                    j++;
                }
                
                // Transforme le dernier caractere en Integer.
                if((j == filesLine.length()-1)&&(i == j)){
                    lineSucc.add(Integer.valueOf((filesLine.substring(j))));
                }
                // Transforme les derniers caracteres en Integer.
                else if((j == filesLine.length()-1)&&(i != j)){
                    lineSucc.add(Integer.valueOf((filesLine.substring(i))));
                }
                // Transforme les caracteres entre i et j (j exclu) en Integer. Ici nous ne sommes pas a la fin de la ligne.
                else {
                    lineSucc.add(Integer.valueOf((filesLine.substring(i, j))));
                    i = j + 1;
                }
            }
            // trie la ligne dans l'ordre croissant avant d'ecrire la ligne dans le tableau.
            Collections.sort(lineSucc);
            tabSucc.add(lineSucc);
            
            // Ecrase l'ancienne ligne par une nouvelle vierge.
            lineSucc = new ArrayList<>();
        }
        succ.close();
    }
    
    /**
     * Charge les structures de donnees dans un graphe.
     * 
     * @return Un graphe.
     * @throws FileNotFoundException 
     * @see FileImplement#tabAdj
     * @see FileImplement#tabSucc
     * @see FileNotFoundException
     */
    public static Graphe loadGraphe() throws FileNotFoundException{
        
        // Numero de la ligne qui est en cours de traitement.
        int line = 0;
        
        // Position du Caractere a analyser.
        int charLine;
        int cptLien;
        
        // variables en vrac. Plus de precision lors de leur utilisation.
        int i, j;
        
        // permet de rajouter des int dans une arete.
        Integer fiab, dist, dur;
        
        Graphe dispensaire = null;
        
        Graphe.Sommet newSommet = null;
        
        
        // Ligne de tabSucc qui est traitee
        List<Integer> lineSucc = new ArrayList<>();
        
        // Variable a true tant qu'il parcourt le nom du sommet
        boolean nomDisp;
        
        JFileChooser fileAdjName = new JFileChooser();
        fileAdjName.setDialogTitle("Ouvrir le fichier d'adjacences");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*.csv)", "csv");
        fileAdjName.setFileFilter(filter);
        int result = fileAdjName.showSaveDialog(null);
        
        FileReader fileAdj = new FileReader(fileAdjName.getSelectedFile());
        
        JFileChooser fileSuccName = new JFileChooser();
        fileSuccName.setDialogTitle("Ouvrir le fichier de successeurs");
        fileSuccName.setFileFilter(filter);
        result = fileSuccName.showSaveDialog(null);
        
        FileReader fileSucc = new FileReader(fileSuccName.getSelectedFile());
        
        // Verifie si le fichier d'adjacents a ete selectionne
        if(result == JFileChooser.APPROVE_OPTION){
            
            // Verifie si le fichier de successeurs a ete selectionne
            if(result == JFileChooser.APPROVE_OPTION){
                
                dispensaire = new Graphe();
            
                loadAdj(fileAdj);
        
                // Charge les dispensaires en tant que Sommets dans un graphe.
                while (line < tabAdj.size()){
                    nomDisp = true;
                    charLine = 0;
            
                    // Parcourt le nom du dispensaire jusqu'a arriver a un ";".
                    while(nomDisp){
                        if(tabAdj.get(line).charAt(charLine) == ';'){
                            nomDisp = false;
                        } else {
                            charLine++;
                        }
                    }
                    // Creation d'un nouveau Sommet dans le Graphe
                    // et traitement du type
                    switch(tabAdj.get(line).charAt(charLine+1)){
                        case 'M' -> newSommet = dispensaire.new Sommet(tabAdj.get(line).substring(0, charLine), SomType.MATER);
                        case 'O' -> newSommet = dispensaire.new Sommet(tabAdj.get(line).substring(0, charLine), SomType.OPE);
                        case 'N' -> newSommet = dispensaire.new Sommet(tabAdj.get(line).substring(0, charLine), SomType.NUTRI);
                    }
                    dispensaire.ajSommet(newSommet);
                    line++;
                }
                
                loadSucc(fileSucc);
            
                line = 0;
        
                // Charge les routes entre les dispensaires en tant qu'aretes dans le graphe.
                while (line < tabAdj.size()){
                    lineSucc = tabSucc.get(line);
                    cptLien = 0;
                    i = 0;
                    j = 0;
            
                    // Passe le nom et le type du dispensaire.
                    while(i != 2){
                        if(tabAdj.get(line).charAt(j) == ';'){
                            i++;
                        }
                        j++;
                    }

                    charLine = j + 1;
            
                    // Compare si tous les Sommets ont ete traites.
                    while(cptLien < lineSucc.size()){
                        //Condition pour ne traiter Les Sommets qu'une seule fois.
                        if(lineSucc.get(cptLien) <= line+1) {
                    
                            // Recupere la fiabilite de la route entre les deux sommets.
                            while (tabAdj.get(line).charAt(charLine) != ',') {
                                charLine++;
                            }
                            // Convertit le String recupere en Integer.
                            fiab = Integer.valueOf(tabAdj.get(line).substring(j, charLine).trim());
                            j = charLine + 1;
                            charLine++;
                    
                            // Recupere la distance entre les deux sommets.
                            while (tabAdj.get(line).charAt(charLine) != ',') {
                                charLine++;
                            }
                            // Convertit le String recupere en Integer.
                            dist = Integer.valueOf(tabAdj.get(line).substring(j, charLine).trim());
                            j = charLine + 1;
                            charLine++;
                            
                            // recupere la duree du trajet entre les deux sommets.
                            while ((tabAdj.get(line).length() > charLine) && (tabAdj.get(line).charAt(charLine) != ';')) {
                                charLine++;
                            }
                            // Recupere plusieurs caracteres.
                            if ((tabAdj.get(line).length() > charLine) && (tabAdj.get(line).charAt(charLine) == ';')) {
                                // Convertit le String recupere en Integer.
                                dur = Integer.valueOf(tabAdj.get(line).substring(j, charLine).trim());
                                j = charLine + 1;
                            }
                            // Recupere un seul caractere
                            else {
                                // Convertit le String recupere en Integer.
                                dur = Integer.valueOf(tabAdj.get(line).substring(j).trim());
                            }
                            // Recupere le Sommet dont les routes sont traitees depuis le Graphe.
                            newSommet = dispensaire.getSommetPosition(lineSucc.get(cptLien) - 1);
                            
                    
                            // Cree une arete entre deux sommets du graphe.
                            dispensaire.ajAdj(dispensaire.getSommetPosition(line), newSommet, fiab, dist, dur);
                        }
                        cptLien++;
                    }
                    line++;
                }
            }
        }
        return dispensaire;
    }

    /**
     * Sauvegarde un graphe sous forme de fichier PNG.
     * 
     * @param gp Le GraphPanel a "imprimer".
     * @see GraphPanel
     */
    public static void saveGraphe(GraphPanel gp) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer...");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images (*.png)", "png");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        
        File saveFile = fileChooser.getSelectedFile();
        
        String sf = saveFile.getName();
        
        if(!sf.substring(sf.length()-4, sf.length()).equals(".csv")) {
            saveFile = new File(saveFile.toString() + ".png");
        }
        
        BufferedImage image = new BufferedImage(gp.getWidth(),gp.getHeight(),BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setColor(Color.WHITE);
        gp.paint(graphics2D);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(image,"PNG", saveFile);
            
            } catch (IOException e1) {
                return;
            }
        }
    }
// </editor-fold>    

}







