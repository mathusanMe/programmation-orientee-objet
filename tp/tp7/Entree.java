public class Entree {
    private Element element;        // l'élément associé à l'entrée
    private String nom;             // le nom associé à l'élément
    private Dossier parent;         // le dossier parent de l'élément

    public Entree(Dossier p, String n, Element e) {
        parent = p;
        nom = n;
        element = e;
    }

    /**
     * Retourne le élément de l'entrée.
     * @return le élément de l'entrée
     */
    public Element getElement() {
        return element;
    }

    /**
     * Retourne le nom de l'entrée.
     * @return le nom de l'entrée
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set le nom de l'entrée.
     * @param nom le nom de l'entrée
     */
    public void setNom(String nvNom) {
        nom = nvNom;
    }

    /**
     * Retourne description de l'entrée.
     * @return description de l'entrée
     */
    public String toString() {
        if (element == null) {
            return nom + "(" + "entrée vide" + ")";
        } else {
            return nom + " (" + element.getType() + ")";
        }
    }

    /**
     * Supprime l'entrée.
     */
    public void supprimer() {
        parent.supprimer(this);
        parent = null;
    }

    /**
     * Remplace l'élément de l'entrée par un autre.
     * @param e le nouvel élément
     */
    public void remplacer(Element e) {
        if (e instanceof Dossier) {
            if (parent != null) {
                ((Dossier) e).setParent(parent);
            }
        }
        element = e;
    }
}
