import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;

public class Formateur {
    private Scanner sc;
    private Scanner sc1;
    private LinkedList<Paragraphe> texte;

    // filename est le nom du fichier
    // chemin compris s'il n'est pas dans le meme repertoire
    public Formateur(String filename) {
        sc = null;
        try {
            sc = new Scanner(new File(filename));
            sc.useDelimiter("\n");
        }
        catch(Exception e) {
            System.out.println("Erreur lors d'ouverture fichier:");
            e.printStackTrace();
            System.exit(1);
        }
        texte = new LinkedList<Paragraphe>();
        this.read();                                                    // on lit et on remplit texte avec les paragraphes
    }

    public void read() {
        while (sc.hasNext()) {                                          // s'il y a du contenu à lire, on procède
            Paragraphe p = readParagraphe();                            // lecture du paragraphe
            if (!p.isEmpty()) {                                         // on ajoute le paragraphe lu si et seulement si elle est non vide selon les règles du paragraphe
                texte.add(p);
            }
        }
    }

    private Paragraphe readParagraphe() {
        sc1 = new Scanner(sc.next());                                   // Scanner (2) prend le contenu courant du Scanner (1)
        Paragraphe p = new Paragraphe(new Ligne());                     // Paragraphe que l'on va remplir dans la suite
        while (sc1.hasNext()) {                                             // s'il y a du contenu à lire, on procède
            p.addChaine(new Mot(sc1.next()));                               // par la création d'un nouveau mot
            p.addChaine(new Espace());                                      // auquel on ajoute un espace systématiquement
        }
        p.removeDernierEspace();                                            // Oublions pas d'enlever le dernier espace inutile
        return p;
    }

    public void print() {
        for (int i = 0; i < texte.size(); i++) {
            System.out.println(texte.get(i));
            if (i != texte.size() - 1) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        // Formateur f = new Formateur("./textes/texte");                   All works great! Fair enough!
        // Formateur f = new Formateur("./textes/texteBis");
        // Formateur f = new Formateur("./textes/videBis");
        // Formateur f = new Formateur("./textes/texte");
        f.print();
    }
}
