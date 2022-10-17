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
        this.read();
    }

    public void read() {
        while (sc.hasNext()) {
            Paragraphe p = readParagraphe();
            if (!p.isEmpty()) {
                texte.add(p);
            }
        }
    }

    private Paragraphe readParagraphe() {
        sc1 = new Scanner(sc.next());
        Paragraphe p = new Paragraphe(new Ligne());
        while (sc1.hasNext()) {
            p.addChaine(new Mot(sc1.next()));
            p.addChaine(new Espace());
        }
        p.removeDernierEspace();
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
        // Formateur f = new Formateur("./textes/texte");                   All works great!
        // Formateur f = new Formateur("./textes/texteBis");
        // Formateur f = new Formateur("./textes/videBis");
        // Formateur f = new Formateur("./textes/texte");
        f.print();
    }
}
