import java.util.Scanner;

public class FichierTexte extends Element implements Affichable, Editable {
    private String contenu;             // contenu du fichier

    public FichierTexte(String contenu) {
        this.contenu = contenu;
    }
    
    public String getType() {
        return "texte";
    }

    public void afficher() {
        System.out.println(contenu);
    }

    public void editer(Scanner sc, boolean echo) {
        contenu = "";
        String curr = sc.nextLine();
        while (!curr.equals(".")) {
            contenu += curr + "\n";
            if (echo) {
                System.out.println(curr);
            }
        }
    }
}
