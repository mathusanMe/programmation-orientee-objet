import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

public class Arbre {
    private final Noeud racine;             // racine de l'arbre
    private final String nomFichier;

    public Arbre(String nomFichier) throws FileNotFoundException {
        racine = new Noeud(new File(nomFichier));
        this.nomFichier = nomFichier;
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

    public void supprimer(String extension) throws UnableToDeleteFileException {
        if (racine == null) {
          return;
        }
        List<Noeud> aSupprimer = racine.supprimer(extension, new ArrayList<>());
        for (Noeud n : aSupprimer) {
            Noeud parent = n.getParent();
            if (parent == null) {
                continue;
            }
            String chemin = parent.getChemin();
            File file = new File(chemin + "/" + n.getNom());
            if (file.getParentFile().canWrite()) {
                if (!parent.getFils().contains(n)) {
                    throw new UnableToDeleteFileException("Impossible de supprimer le fichier " + file.getAbsolutePath());
                }
                parent.getFils().remove(n);
            } else {
                throw new UnableToDeleteFileException("Impossible de supprimer le fichier " + file.getAbsolutePath());
            }
        }

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

        public String getNom() {
            return nom;
        }

        public ArrayList<Noeud> getFils() {
            return fils;
        }

        public Noeud getParent() {
            return racine.parcoursEnProfondeur(this);
        }

        private Noeud parcoursEnProfondeur(Noeud aSupprimer) {
            if (fils.contains(aSupprimer)) {
                return this;
            }
            for (Noeud f : this.fils) {
                if (f.fils != null) {
                    Noeud res = f.parcoursEnProfondeur(aSupprimer);
                    if (res != null) {
                        return res;
                    }
                }
            }
            return null;
        }

        public String getChemin() {
            if (this.getParent() == null) {
                return nomFichier;
            }
            return this.getParent().getChemin() + "/" + this.getNom();
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

        private List<Noeud> supprimer(String extension, List<Noeud> aSupprimer) {
            if (repertoire) {
              fils.forEach((Noeud n) -> {
                aSupprimer.addAll(n.supprimer(extension, aSupprimer));
              });
            }
            if (this.nom.endsWith(extension)) {
                aSupprimer.add(this);
            }
            return aSupprimer;
        }
    }

    public static void main(String[] args) {
        try {
            Arbre arbre = new Arbre("/Users/mselvakumar/Downloads/Unwanted");
            // arbre.afficher();
            // arbre.map((String t) -> String.format("%s.blah", t));
            arbre.afficher();
            //arbre.traverser(".png");
            try {
                arbre.supprimer(".jpg");
            } catch (UnableToDeleteFileException e) {
                System.out.println(e.getMessage());
            }
            arbre.afficher();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        }
    }
}