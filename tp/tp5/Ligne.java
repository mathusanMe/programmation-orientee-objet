import java.util.LinkedList;

public class Ligne extends ChaineCar {
    LinkedList<ChaineCar> ligne;

    public Ligne(ChaineCar... args) {                   // Utilisation de Type... qui est equivalent a Type[] sans la necessite de creer une liste
        this.ligne = new LinkedList<ChaineCar>();

        for (ChaineCar arg : args) {
            ligne.add(arg);
        }
    }

    public int len() {
        int cnt = 0;
        for (int i = 0; i < ligne.size(); i++) {
            cnt += ligne.get(i).len();
        }
        return cnt;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < ligne.size(); i++) {
            res += ligne.get(i).toString();
        }
        return res;
    }

    public boolean isEmpty() {                          // verifie si la ligne est vide (est-ce que seulement des espaces = vide ?)
        boolean boo = true;
        for (int i = 0; i < ligne.size(); i++) {
            boo = boo && ligne.get(i).len() == 0;
        }
        return boo;
    }

    public void addChaine(ChaineCar cc) {
        ligne.add(cc);
    }

    public void removeDernierEspace() {
        if (!ligne.isEmpty() && ligne.getLast() instanceof Espace) {
            ligne.removeLast();
        }
    }

    public boolean dernierEstEspace() {
        if (!ligne.isEmpty() && ligne.getLast() instanceof Espace) {
            return true;
        }
        return false;
    }

    public void justifier(int longueur) {
        int len = this.len();
        if (len >= longueur) {
            return;
        }
        int nombreParEspace = longueur / len;
        int espacesRestants = longueur % len;
        for (int i = 0; i < ligne.size(); i++) {
            ChaineCar curr = ligne.get(i);
            if (curr instanceof Espace) {
                if (espacesRestants > 0) {
                    ((Espace) curr).setSize(nombreParEspace + 1);
                    espacesRestants--;
                } else {
                    ((Espace) curr).setSize(nombreParEspace);
                }
            }
        } 
    }
    
}
