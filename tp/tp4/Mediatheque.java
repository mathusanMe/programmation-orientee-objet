import java.util.ArrayList;

public class Mediatheque {
    private ArrayList<Media> baseDeDonnees;

    public Mediatheque() {
        baseDeDonnees = new ArrayList<Media>();
    }

    public void ajouter(Media doc) {
        boolean found = false;
        int i = 0;
        while (i < baseDeDonnees.size() && !found && baseDeDonnees.get(i).plusPetit(doc)) {
            i++;
        }
        baseDeDonnees.add(i, doc);
    }

    public String toString() {
        String s = "";
        for (Media m : baseDeDonnees) {
            s += m.toString() + "\n";
        }
        return s;
    }

    public void tousLesLivres() {
        for (Media m : baseDeDonnees) {
            if (m instanceof Livre) {
                System.out.println(m);
            }
        }
    }
        
    public static void main(String[] args) {
        LongMetrage lm = new LongMetrage("Princesse Mononoké", 90, "Hayao Miyazaki");
        // System.out.println(lm);

        // System.out.println();

        Livre l = new Livre("Fables", "La Fontaine", 200);
        Livre l1 = new Livre("Le Germinal", "Zola", 400);
        // System.out.println(l);

        // System.out.println();

        BandeDessinee bd = new BandeDessinee("Titeuf", "Zep");
        // System.out.println(bd);

        // System.out.println();

        Manga mg = new Manga("One Piece", "Eiichiro Oda", true);
        // System.out.println(mg);

        // System.out.println();

        Image img = new Image("Albert Einstein - I have no special talents. I am only passionately curious.", 60, 100);
        // System.out.println(img);

        Mediatheque m = new Mediatheque();
        // m.ajouter(img);                                  
        // m.ajouter(l1);
        // m.ajouter(l);
        // m.ajouter(lm);
        // m.ajouter(bd);
        // m.ajouter(mg);
        // System.out.println("-------------------------");
        // System.out.println("La médiatheque");
        // System.out.println(m);
        /* 
            ^^^ Dans ce cas precis, ca ne fonctionne pas (Q.1, Q.3). On obtient : ^^^


            La médiatheque
            0. Princesse Mononoké
            Duree (en minutes) : 90.0
            Realisateur : Hayao Miyazaki
            3. Titeuf
            Dessinateur : Zep
            4. One Piece
            Dessinateur : Eiichiro Oda
            Sens Inversé : true
            1. Fables
            Auteur : La Fontaine
            Nombre de Pages : 200
            2. Le Germinal
            Auteur : Zola
            Nombre de Pages : 400
            5. Albert Einstein - I have no special talents. I am only passionately curious.
            Largeur (en cm) : 60
            Hauteur (en cm) : 100
        */
        m.ajouter(l1);
        m.ajouter(img);
        m.ajouter(l);
        m.ajouter(lm);
        m.ajouter(bd);
        m.ajouter(mg);

        System.out.println("-------------------------");
        System.out.println("La médiatheque");
        System.out.println(m);
        
        System.out.println("-------------------------");
        System.out.println("Tous les livres");
        m.tousLesLivres();
    }
}