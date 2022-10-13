public class Exercice2 {
    private void affiche(int[] t) {                         // Affiche un tableau d'entiers t
        System.out.print("[");
        for (int i = 0; i < t.length; i++) {
            if (i == t.length - 1) {                        // le cas du dernier élément
                System.out.print(t[i]);
            } else {
                System.out.print(t[i]);
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private int[] multiplication(int[] t1, int[] t2) {              // retourne un tableau de taille max(len(t1), len(t2)) obtenu
        int[] res = new int[Math.max(t1.length, t2.length)];        // en multipliant t1 et t2 case par case
        for (int i = 0; i < res.length; i++) {
            int t1n = i <= t1.length - 1 ? t1[i] : 1;
            int t2n = i <= t2.length - 1 ? t2[i] : 1;
            res[i] = t1n * t2n;
        }
        return res;
    }

    private int[] split(int[] t) {                          // retourne le tableau obtenu en séparant chacun des chiffres
        String tmp = "";                                    // des entiers du tableau de départ
        for (int i = 0; i < t.length; i++) {
            tmp += String.valueOf(t[i]);
        }
        int[] res = new int[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            res[i] = Character.getNumericValue(tmp.charAt(i));
        }
        return res;
    }

    public static void main(String[] args) {
        Exercice2 e2 = new Exercice2();

        int[] t = {1, 3, 6, 7};
        int[] s = {2, 4, 6};
        e2.affiche(t);
        System.out.println("*");
        e2.affiche(s);
        System.out.println("=");
        e2.affiche(e2.multiplication(t, s));

        System.out.println();
        t = new int[]{2, 12, 36, 7};
        e2.affiche(t);
        System.out.println("Split chiffres : ");
        e2.affiche(e2.split(t));
    }
}
