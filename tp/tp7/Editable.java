import java.util.Scanner;

public interface Editable {

    /**
     * Edite l'élément.
     * @param sc le scanner à utiliser pour lire les données
     * @param echo indique si les données lues doivent être affichées
     */
    public void editer(Scanner sc, boolean echo);
}
