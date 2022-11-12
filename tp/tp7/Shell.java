import java.util.Scanner;

public class Shell {
    private final Dossier racine;
    private Dossier courant;
    private Scanner scanner;

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
        
        if (chemin.equals("")) {
            chemin = ".";
        }

        Dossier prevCourant = courant;

        if (chemin.equals("/")) {
            courant = racine;
            courant.afficher();
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
            racine.ajouter(new Dossier(new Entree(null, chemin, racine)), racine.getNomLibre());
            return;
        }
        
        Dossier tmpCourant = courant;
        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            tmpCourant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = tmpCourant;
        Entree dEntree = new Entree(null, chemin, racine);
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

    /**
     * Implementation de la commande mv.
     * @param cheminSrc le chemin du fichier/dossier à déplacer
     * @param cheminDst le chemin du dossier de destination
     */
    public void mv(String cheminSrc, String cheminDst) {
        System.out.println("$ mv " + cheminSrc + " " + cheminDst);

        String[] chemins = new String[] {cheminSrc, cheminDst};
        Dossier[] dossiers = new Dossier[2];
        String[] nomDossiers = new String[2];
        Dossier tmpCourant = courant;

        for (int k = 0; k < chemins.length; k++) {
            String chemin = chemins[k];
            if (chemin.equals("/")) {
                if (k == 0) {
                    return;
                }
                courant = racine;
                dossiers[1] = courant;
                nomDossiers[1] = "";
            } else {
                boolean cheminAbsolu = chemin.startsWith("/");

                if (cheminAbsolu) {
                    courant = racine;
                }

                String[] noms = chemin.split("/");
                Dossier d = courant;
                for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length - 1; i++) {
                    if (noms[i].equals("..") && d == racine) {
                        continue;
                    }

                    Entree tmp = d.getEntree(noms[i], false);
                    if (tmp == null) {
                        System.out.println("mv: " + chemin + ": No such file or directory");
                        return;
                    }
                    if (tmp.getElement() instanceof Dossier) {
                        d = (Dossier) tmp.getElement();
                    } else {
                        System.out.println("mv: " + chemin + ": Not a directory");
                        return;
                    }
                }
                dossiers[k] = d;
                nomDossiers[k] = noms[noms.length - 1];
            }
            courant = tmpCourant;
        }
        Entree src = dossiers[0].getEntree(nomDossiers[0], false);
        if (dossiers[0] == dossiers[1]) {
            src.setNom(nomDossiers[1]);
            return;
        } else {
            dossiers[1].ajouter(src.getElement(), nomDossiers[0]);
            src.supprimer();
        }
    }

    /**
     * Implementation de la commande rm.
     * @param chemin le chemin du fichier/dossier à supprimer
     */
    public void rm(String chemin) {
        System.out.println("$ rm " + chemin);

        if (chemin.equals("/")) {
            return;
        }

        Dossier tmpCourant = courant;
        boolean cheminAbsolu = chemin.startsWith("/");

        if (cheminAbsolu) {
            tmpCourant = racine;
        }

        String[] noms = chemin.split("/");
        Dossier d = tmpCourant;
        Entree tmpEntree = new Entree(null, chemin, racine);
        for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length; i++) {
            if (noms[i].equals("..") && d == racine) {
                continue;
            }

            Entree tmp = d.getEntree(noms[i], false);
            if (tmp == null) {
                System.out.println("rm: " + chemin + ": No such file or directory");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
                tmpEntree = tmp;
            } else {
                System.out.println("rm: " + chemin + ": Not a directory");
                return;
            }
        }
        tmpEntree.supprimer();
    }

    /**
     * Implementation de la commande ed.
     * @param chemin le chemin du fichier à éditer
     */
    public void ed(String chemin) {
        System.out.println("$ ed " + chemin);

        scanner = new Scanner(System.in);

        if (chemin.equals("/")) {
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
                System.out.println("ed: " + chemin + ": No such file or directory");
                return;
            }
            if (tmp.getElement() instanceof Dossier) {
                d = (Dossier) tmp.getElement();
            } else {
                System.out.println("ed: " + chemin + ": Not a directory");
                return;
            }
        }
        Entree tmp = d.getEntree(noms[noms.length - 1], false);
        if (tmp == null) {
            tmp = new Entree(d, noms[noms.length - 1], new FichierTexte(""));
        }
        if (tmp.getElement() instanceof FichierTexte) {
            FichierTexte f = (FichierTexte) tmp.getElement();
            f.editer(scanner, false);
            d.ajouter(f, noms[noms.length - 1]);
        } else {
            System.out.println("ed: " + chemin + ": Not a file");
        }
    }

    /**
     * Implementation de la commande cp.
     * @param cheminSrc le chemin du fichier/dossier à copier
     * @param cheminDst le chemin du dossier de destination
     */
    public void cp(String cheminSrc, String cheminDst) {
        System.out.println("$ cp " + cheminSrc + " " + cheminDst);

        String[] chemins = new String[] {cheminSrc, cheminDst};
        Dossier[] dossiers = new Dossier[2];
        String[] nomDossiers = new String[2];
        Dossier tmpCourant = courant;

        for (int k = 0; k < chemins.length; k++) {
            String chemin = chemins[k];
            if (chemin.equals("/")) {
                if (k == 0) {
                    return;
                }
                courant = racine;
                dossiers[1] = courant;
                nomDossiers[1] = "";
            } else {
                boolean cheminAbsolu = chemin.startsWith("/");

                if (cheminAbsolu) {
                    courant = racine;
                }

                String[] noms = chemin.split("/");
                Dossier d = courant;
                for (int i = (!cheminAbsolu ? 0 : 1); i < noms.length - 1; i++) {
                    if (noms[i].equals("..") && d == racine) {
                        continue;
                    }

                    Entree tmp = d.getEntree(noms[i], false);
                    if (tmp == null) {
                        System.out.println("cp: " + chemin + ": No such file or directory");
                        return;
                    }
                    if (tmp.getElement() instanceof Dossier) {
                        d = (Dossier) tmp.getElement();
                    } else {
                        System.out.println("cp: " + chemin + ": Not a directory");
                        return;
                    }
                }
                dossiers[k] = d;
                nomDossiers[k] = noms[noms.length - 1];
            }
            courant = tmpCourant;
        }

        Entree src = dossiers[0].getEntree(nomDossiers[0], false);
        if (src.getElement() != racine) {
            dossiers[1].ajouter(src.getElement(), nomDossiers[1]);
        } else {
            System.out.println("cp: " + cheminSrc + ": Permission denied");
            return;
        }
    }

    public static void main(String[] args) {
        Dossier racine = new Dossier(null);
        Entree racineEntree = new Entree(null, "Racine", racine);
        Shell shell = new Shell(racine);
        shell.ls("");
        shell.mkdir("test");
        shell.ls("");
        shell.ls("test");
        shell.ed("test/a");
        shell.cp("test", "test2");
        shell.ed("test2/a");
        shell.cd("test");
        shell.cat("a");
        shell.cat("../test2/a");
    }
}