Dossier.java                                                                                        000644  000765  000024  00000004723 14333670755 015046  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         import java.util.*;

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
        if (parent == null) {
            return;
        }
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
                                             Editable.java                                                                                       000644  000765  000024  00000000436 14333366762 015144  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         import java.util.Scanner;

public interface Editable {

    /**
     * Edite l'élément.
     * @param sc le scanner à utiliser pour lire les données
     * @param echo indique si les données lues doivent être affichées
     */
    public void editer(Scanner sc, boolean echo);
}
                                                                                                                                                                                                                                  Element.java                                                                                        000644  000765  000024  00000000436 14332674451 015020  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         abstract class Element {
    /**
     * Retourne le type de l'élément.
     * @return le type de l'élément
     */
    public abstract String getType();

    /**
     * Affiche l'élément.
     */
    public String toString() {
        return "fichier de type" + getType();
    }
}                                                                                                                                                                                                                                  Entree.java                                                                                         000644  000765  000024  00000003046 14333656576 014662  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         public class Entree {
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          EntreeSpeciale.java                                                                                 000644  000765  000024  00000001120 14333231253 016274  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         public class EntreeSpeciale extends Entree {
    public EntreeSpeciale(Dossier p, String nom, Element e) {
        super(p, nom, e);
    }

    /**
     * Affiche un message d'erreur plutôt que de supprimer l'entrée spéciale.
     */
    public void supprimer() {
        System.out.println("Impossible de supprimer l'entrée spéciale " + getNom());
    }

    /**
     * Affiche un message d'erreur plutôt que de remplacer l'entrée spéciale.
     */
    public void remplacer(Element e) {
        System.out.println("Impossible de remplacer l'entrée spéciale " + getNom());
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                FichierTexte.java                                                                                   000644  000765  000024  00000001746 14333702242 016006  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         import java.util.Scanner;

public class FichierTexte extends Element implements Affichable, Editable {
    private String contenu;             // contenu du fichier

    public FichierTexte(String contenu) {
        this.contenu = contenu;
    }
    
    public String getType() {
        return "texte";
    }

    public String getContenu() {
        return contenu;
    }

    public void afficher() {
        System.out.println(contenu);
    }

    public void editer(Scanner sc, boolean echo) {
        System.out.println("Entrez le texte du fichier (terminez par une ligne contenant seulement un point)");
        contenu = "";
        String curr = sc.nextLine();
        boolean first = true;
        while (!curr.equals(".")) {
            if (!first) {
                contenu += "\n";
            }
            contenu += curr;
            if (echo) {
                System.out.println(curr);
            }
            curr = sc.nextLine();
            first = false;
        }
    }
}
                          Shell.java                                                                                          000644  000765  000024  00000042277 14333702646 014506  0                                                                                                    ustar 00mselvakumar                     staff                           000000  000000                                                                                                                                                                         import java.util.Scanner;

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
        boolean test = tmp == null;
        if (test) {
            tmp = new Entree(d, noms[noms.length - 1], new FichierTexte(""));
        }
        if (tmp.getElement() instanceof FichierTexte) {
            FichierTexte f = (FichierTexte) tmp.getElement();
            f.editer(scanner, false);
            if (test) {
                d.ajouter(f, noms[noms.length - 1]);
            }
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
            Entree nvEntree = new Entree(dossiers[1], nomDossiers[0], null);
            Element nvElement = null;
            if (src.getElement() instanceof Dossier) {
                nvElement = new Dossier(nvEntree);
            } else {
                nvElement = new FichierTexte(((FichierTexte) src.getElement()).getContenu());
            }
            nvEntree.remplacer(nvElement);
            dossiers[1].ajouter(nvElement, nomDossiers[1]);
        } else {
            System.out.println("cp: " + cheminSrc + ": Permission denied");
            return;
        }
    }

    public void start() {
        System.out.println("Enter 'quit' to stop.");
        scanner = new Scanner(System.in);
        String curr = scanner.nextLine();
        while (!curr.equals("quit")) {
            String[] args = curr.split(" ");
            switch (args[0]) {
                case "cat":
                    if (args.length == 2) {
                        cat(args[1]);
                    } else {
                        System.out.println("Usage: cat <file>");
                    }
                    break;
                case "ls":
                    if (args.length > 2) {
                        System.out.println("ls: too many arguments");
                    } else if (args.length == 1) {
                        ls("");
                    } else {
                        ls(args[1]);
                    }
                    break;
                case "cd":
                    if (args.length > 2) {
                        System.out.println("cd: too many arguments");
                    } else if (args.length == 1) {
                        cd("/");
                    } else {
                        cd(args[1]);
                    }
                    break;
                case "mkdir":
                    if (args.length > 2) {
                        System.out.println("mkdir: too many arguments");
                    } else if (args.length == 1) {
                        System.out.println("mkdir: missing operand");
                    } else {
                        mkdir(args[1]);
                    }
                    break;
                case "rm":
                    if (args.length > 2) {
                        System.out.println("rm: too many arguments");
                    } else if (args.length == 1) {
                        System.out.println("rm: missing operand");
                    } else {
                        rm(args[1]);
                    }
                    break;
                case "ed":
                    if (args.length > 2) {
                        System.out.println("ed: too many arguments");
                    } else if (args.length == 1) {
                        System.out.println("ed: missing operand");
                    } else {
                        ed(args[1]);
                    }
                    break;
                case "cp":
                    if (args.length > 3) {
                        System.out.println("cp: too many arguments");
                    } else if (args.length == 1) {
                        System.out.println("cp: missing operand");
                    } else if (args.length == 2) {
                        System.out.println("cp: missing destination file operand after '" + args[1] + "'");
                    } else {
                        cp(args[1], args[2]);
                    }
                    break;
                default:
                    System.out.println("Command not found");
            }
            curr = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        Dossier racine = new Dossier(null);
        Shell shell = new Shell(racine);
        // shell.ls("");
        // shell.mkdir("test");
        // shell.ls("");
        // shell.ls("test");
        // shell.ed("test/a");
        // shell.ls("test");
        // shell.cp("test", "test2");
        // shell.ed("test2/a");
        // shell.cd("test");
        // shell.cat("a");
        // shell.cat("../test2/a");

        shell.start();
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 