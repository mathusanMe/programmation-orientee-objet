import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

public class Arbre {
    private final Noeud racine;             // racine de l'arbre

    public Arbre(String nomFichier) throws FileNotFoundException {
        racine = new Noeud(new File(nomFichier));
    }

    /**
     * Affiche l'arbre.
    */
    public void afficher() {
        if (racine == null) {
            return;
        }
        racine.afficher(0);
    }

    /**
     * Applique la transformation t à tous les noms de fichiers de l'arbre.
     * @param t la transformation à appliquer
    */
    public void map(StringTransformation<String> t) {
        if (this.racine == null) {
          return;
        }
        this.racine.map(t);
    }

    /**
     * Affiche la liste des noms de fichiers de l'arbre avec la meme extension.
     * @param extension l'extension des fichiers à afficher
    */
    public void traverser(String extension) {
        if (racine == null) {
          return;
        }
        racine.traverser(extension);
    }

    private class Noeud {
        private String nom;                     // nom du fichier
        private final int taille;               // taille du fichier
        private boolean repertoire;             // vrai si le fichier est un répertoire
        private ArrayList<Noeud> fils;          // fils du noeud

        public Noeud(File file) throws FileNotFoundException {
            if (file.exists()) {                
                nom = file.getName();
                taille = (int) file.length();
                if (file.isDirectory()) {
                    repertoire = true;
                    fils = new ArrayList<>();
                    File[] filsTmpTab = file.listFiles();
                    if (filsTmpTab == null) {
                        return;
                    }
                    for (File filsTmp : filsTmpTab) {
                        fils.add(new Noeud(filsTmp));
                    }
                }
                return;
            }
            throw new FileNotFoundException();
        }
        
        private void afficher(int niveau) {
            System.out.print(" ".repeat(niveau * 2));
            System.out.println(String.format("%s [%d]", nom, taille));
            if (fils == null) {
                return;
            }
            for (Noeud f : fils) {
                if (f == null) {
                    continue;
                }
                f.afficher(niveau + 1);
            }
        }

        private void map(StringTransformation<String> t) {
            if (repertoire) {
              fils.forEach((Noeud n) -> n.map(t));
              return;
            }
            this.nom = t.transf(this.nom);
        }

        private void traverser(String extension) {
            if (repertoire) {
              fils.forEach((Noeud n) -> n.traverser(extension));
              return;
            }
            if (this.nom.endsWith(extension)) {
              System.out.println(this.nom);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Arbre arbre = new Arbre("/Users/mselvakumar/Downloads/Unwanted");
            // arbre.afficher();
            // arbre.map((String t) -> String.format("%s.blah", t));
            arbre.afficher();
            //arbre.traverser(".png");
            arbre.supprimer(".png");
            arbre.afficher();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        }
    }
}