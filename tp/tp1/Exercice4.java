import java.util.Random;
import java.util.Scanner;

public class Exercice4 {
    private static int question(int maxTentatives) {
        Scanner sc = new Scanner(System.in);

        Random rd = new Random();
        int p = rd.nextInt(9) + 1;
        int q = rd.nextInt(9) + 1;

        System.out.print(p + " * " + q + " = ");
        int res = p * q;
        int i = 1;
        while (i <= maxTentatives && sc.nextInt() != res) {
            if (i < maxTentatives) {
                System.out.println("Il vous reste " + (maxTentatives - i) + " tentative(s)");
                System.out.print(p + " * " + q + " = ");
            }
            i++;
        }

        return i;      
    }

    private static int evaluation(int n) {
        int bonneRep = 0;
        for (int i = 0; i < n; i++) {
            bonneRep += question(1) == 1 ? 1 : 0;
        }

        return bonneRep;    
    }

    public static void main(String[] args) {
        System.out.println("Note : " + evaluation(3));
    }
}
