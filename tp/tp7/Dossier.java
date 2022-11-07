import java.util.ArrayList;

public class Dossier {
    private List<Entree> entrees;   // les entrées du dossier
    private Entree parent;          // le dossier parent du dossier

    public Dossier(Entree p) {
        entrees = new ArrayList<Entree>();
        parent = p;
    }
}
