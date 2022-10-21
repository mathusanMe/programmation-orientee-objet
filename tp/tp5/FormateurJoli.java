import java.util.Scanner;

public class FormateurJoli extends Formateur {

    private int largeurPage;

    public FormateurJoli(String filename, int largeurPage) {
        super(filename);
        this.largeurPage = largeurPage;
        this.read();                                                    // on lit et on remplit texte avec les paragraphes
    }

    @Override
    public void read() {
        while (sc.hasNext()) {                                          // s'il y a du contenu à lire, on procède
            ParagrapheJoli p = readParagraphe();                        // lecture du paragraphe
            if (!p.isEmpty()) {                                         // on ajoute le paragraphe lu si et seulement si elle est non vide selon les règles du paragraphe
                texte.add(p);
            }
        }
    }

    private ParagrapheJoli readParagraphe() {
        sc1 = new Scanner(sc.next());                                   // Scanner (2) prend le contenu courant du Scanner (1)
        ParagrapheJoli p = new ParagrapheJoli(new Ligne(), largeurPage);                     // Paragraphe que l'on va remplir dans la suite
        while (sc1.hasNext()) {                                             // s'il y a du contenu à lire, on procède
            p.addChaine(new Mot(sc1.next()));                               // par la création d'un nouveau mot
            if (!p.isEmpty()) {
                p.addChaine(new Espace());                                      // auquel on ajoute un espace systématiquement
            }
        }
        p.removeDernierEspace();                                            // Oublions pas d'enlever le dernier espace inutile
        return p;
    }

    public static void main(String[] args) {
        FormateurJoli f = new FormateurJoli("./textes/texte", 40);
        f.print();
    }
}
