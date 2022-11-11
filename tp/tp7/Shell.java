public class Shell {
    private final Dossier racine;
    private Dossier courant;

    public Shell(Dossier c) {
        courant = c;
        racine = getRacinePour(c);
    }

    /**
     * Retourne le dossier racine du système de fichiers.
     * @param d un dossier quelconque
     * @return le dossier racine du système de fichiers
     */
    private Dossier getRacinePour(Dossier d) {
        if (d.getParent() == null || d.getParent().getElement() == null) {
            return d;
        } else {
            return getRacinePour((Dossier) d.getParent().getElement());
        }
    }

    /**
     * Implementation de la commande cat.
     * @param chemin le chemin du fichier à afficher
     */
    public void cat(String chemin) {
        System.out.println("$ cat " + chemin);

        String[] noms = chemin.split("/");
        Dossier d = courant;
        for (int i = 0; i < noms.length - 1; i++) {
            Entree tmp = d.getEntree(noms[i], false);
            if (tmp == null) {
                System.out.println("cat: " + chemin + ": No such file or directory");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
            } else {
                System.out.println("cat: " + chemin + ": Not a directory");
                return;
            }
        }
        Entree e = d.getEntree(noms[noms.length - 1], false);
        if (e == null) {
            System.out.println("cat: " + chemin + ": No such file or directory");
        } else {
            Element el = e.getElement();
            if (el instanceof Dossier) {
                System.out.println("cat: " + chemin + ": Is a directory");
            } else {
                ((FichierTexte) el).afficher();
            }
        }
    }

    public static void main(String[] args) {
        Dossier rc = new Dossier(null);
        Entree rcEnt = new Entree(null, "Racine", rc);
        Dossier d1 = new Dossier(rcEnt);
        rc.ajouter(d1, "d1");
        FichierTexte f = new FichierTexte("f Texte");
        d1.ajouter(f, "f");
        Shell s = new Shell(rc);
        s.cat("d1/../d1/./f");
    }
}
