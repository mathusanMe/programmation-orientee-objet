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

        if (chemin.equals("/")) {
            System.out.println("cat: /: Is a directory");
            return;
        }
        
        Dossier tmpCourant = courant;
        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            tmpCourant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = tmpCourant;
        for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length - 1; i++) {
            if (noms[i].equals("..") && d == racine) {
                continue;
            }

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

    /**
     * Implementation de la commande cd.
     * @param chemin le chemin du dossier à visiter
     */
    public void cd(String chemin) {
        System.out.println("$ cd " + chemin);

        if (chemin.equals("/")) {
            courant = racine;
            return;
        }

        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            courant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = courant;
        for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length; i++) {
            if (noms[i].equals("..") && d == racine) {
                continue;
            }

            Entree tmp = d.getEntree(noms[i], false);
            if (tmp == null) {
                System.out.println("cd: " + chemin + ": No such file or directory");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
            } else {
                System.out.println("cd: " + chemin + ": Not a directory");
                return;
            }
        }
        courant = d;
    }

    /**
     * Implementation de la commande ls.
     * @param chemin le chemin du dossier à lister
     */
    public void ls(String chemin) {
        System.out.println("$ ls " + chemin);
        
        Dossier prevCourant = courant;

        if (chemin.equals("/")) {
            courant = racine;
            return;
        }

        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            courant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = courant;
        for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length; i++) {
            if (noms[i].equals("..") && d == racine) {
                continue;
            }

            Entree tmp = d.getEntree(noms[i], false);
            if (tmp == null) {
                System.out.println("ls: " + chemin + ": No such file or directory");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
            } else {
                System.out.println("ls: " + chemin + ": Not a directory");
                return;
            }
        }
        d.afficher();
        courant = prevCourant;
    }

    /**
     * Implementation de la commande mkdir.
     * @param chemin le chemin du dossier à créer
     */
    public void mkdir(String chemin) {
        System.out.println("$ mkdir " + chemin);

        if (chemin.equals("/")) {
            racine.ajouter(new Dossier(null), racine.getNomLibre());
            return;
        }
        
        Dossier tmpCourant = courant;
        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            tmpCourant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = tmpCourant;
        Entree dEntree = null;
        for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length - 1; i++) {
            if (noms[i].equals("..") && d == racine) {
                continue;
            }

            Entree tmp = d.getEntree(noms[i], false);
            if (tmp == null) {
                System.out.println("mkdir: " + chemin + ": Invalid path");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
                dEntree = tmp;
            } else {
                System.out.println("mkdir: " + chemin + ": Invalid path");
                return;
            }
        }
        d.ajouter(new Dossier(dEntree), noms[noms.length - 1]);
    }

    public static void main(String[] args) {
        Dossier rc = new Dossier(null);
        Entree rcEnt = new Entree(null, "Racine", rc);
        Dossier d1 = new Dossier(rcEnt);
        rc.ajouter(d1, "d1");
        FichierTexte f = new FichierTexte("f Texte");
        d1.ajouter(f, "f");
        Shell s = new Shell(rc);
        s.cd("/.././d1");
        s.cat("f");
        s.ls("/.");
        s.mkdir("/./d1/d2");
        s.ls("/./d1");
    }
}
