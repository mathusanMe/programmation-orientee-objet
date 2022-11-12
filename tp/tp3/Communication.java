import java.util.Scanner;

public class Communication {
    private Scanner scanner;

    public Communication() {
        scanner = new Scanner(System.in);
    }
    
    public Deplacement demanderDeplacement(boolean joueur) {
        int[] pC = new int[4];
        for (int i = 0; i < 4; i++) {
            pC[i] = scanner.nextInt();
        }
        return new Deplacement(pC[0], pC[1], pC[2], pC[3]);
    }
}