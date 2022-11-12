import java.util.Scanner;

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
