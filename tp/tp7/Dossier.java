import java.util.*;

public class Dossier extends Element implements Affichable {
    private List<Entree> entrees;   // les entrées du dossier
    private Entree parent;          // le dossier parent du dossier
    private static int untitled = 1; // le nombre de dossiers sans nom

    public Dossier(Entree p) {
        entrees = new ArrayList<Entree>();
        parent = p;
        entrees.add(new EntreeSpeciale((parent != null ? (Dossier) parent.getElement() : null), "." , this));
        if (parent != null) {
            entrees.add(new EntreeSpeciale(null, ".." , parent.getElement()));
        }
        
    }

    /**
     * Retourne le type de l'élément.
     * @return le type de l'élément
     */
    public String getType() {
        return "dossier";
    }

    public Entree getParent() {
        return parent;
    }

    /**
     * Retourne l'entrée correspondant au nom donné.
     * @param nom le nom de l'entrée à chercher
     * @param creer indique si l'entrée doit être créée si elle n'existe pas
     * @return l'entrée correspondant au nom donné
     */
    public Entree getEntree(String nom, boolean creer) {
        for (Entree e : entrees) {
            if (e.getNom().equals(nom)) {
                return e;
            }
        }
        if (creer) {
            Entree e = new Entree(this, nom, null);
            entrees.add(e);
            return e;
        } else {
            return null;
        }
    }

    /**
     * Change le dossier parent du dossier.
     * @param p le nouveau dossier parent
     */
    public void setParent(Dossier p) {
        Entree tmp = new Entree(this, parent.getNom(), p);
        parent = tmp;
    }

    public String getNomLibre() {
        String nom = "Untitled" + untitled;
        untitled++;
        return nom;
    }

    /**
     * Supprime une entrée du dossier.
     * @param e l'entrée à supprimer
     */
    public void supprimer(Entree e) {
        entrees.remove(e);
    }

    /**
     * Ajoute une entrée au dossier.
     * @param e l'élément à ajouter
     * @param nom le nom de l'entrée
     */
    public void ajouter(Element e, String nom) {
        Entree tmp = new Entree(this, nom, null);
        entrees.add(tmp);
        tmp.remplacer(e);
    }

    /**
     * Affiche le dossier '.' et '..' incluses.
     */
    public void afficher() {
        for (Entree e : entrees) {
            System.out.println(e);
        }
    }
}
