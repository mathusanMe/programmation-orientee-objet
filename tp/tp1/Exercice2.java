public class Exercice2 {
    private static void affiche(int[] t) {
        System.out.print("[");
        
        for (int i = 0; i < t.length - 1; i++) {
            System.out.print(t[i]);
            System.out.print(", ");
        }

        if (t.length > 0) {
            System.out.print(t[t.length - 1]);
        }

        System.out.print("]");
    }

    private static int[] multiplication(int[] t1, int[] t2) {
        int[] res = new int[Math.max(t1.length, t2.length)];

        for (int i = 0; i < res.length; i++) {
            int t1n = i <= t1.length - 1 ? t1[i] : 1;
            int t2n = i <= t2.length - 1 ? t2[i] : 1;
            res[i] = t1n * t2n;
        }

        return res;
    }

    private static int[] split(int[] t) {
        String tmp = "";
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
        int[] t = {1, 2, 3, 4};
        affiche(t);
        
        int[] s = {11, 21, 41};
        affiche(multiplication(t, s));

        affiche(split(s));
    }
}
