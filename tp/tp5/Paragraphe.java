import java.util.LinkedList;

public class Paragraphe extends ChaineCar {
    LinkedList<Ligne> lignes;

    public Paragraphe(Ligne ligne) {
        lignes = new LinkedList<Ligne>();
        this.lignes.add(ligne);
    }

    public int len() {
        int cnt = 0;
        for (int i = 0; i < lignes.size(); i++) {
            cnt += lignes.get(i).len();
        }
        return cnt;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < lignes.size(); i++) {
            res += lignes.get(i).toString();                     // \n permet de faire les sauts de lignes où il faut!
        }
        return res;
    }

    public boolean isEmpty() {
        boolean boo = true;
        for (int i = 0; i < lignes.size(); i++) {
            boo = boo && lignes.get(i).len() == 0;
        }
        return boo;
    }

    public void addChaine(ChaineCar cc) {
        lignes.getLast().addChaine(cc);
    }

    public void addLigne() {
        lignes.add(new Ligne());
    }

    public void removeDernierEspace() {
        if (!lignes.isEmpty()) {
            lignes.getLast().removeDernierEspace();
        }
    }
}
