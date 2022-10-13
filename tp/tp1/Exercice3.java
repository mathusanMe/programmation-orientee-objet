public class Exercice3 {
    public static void main(String[] args) {
        String res = "";

        for (String arg : args) {
            for (int i = 0; i < arg.length(); i++) {
                res += switch (arg.charAt(i)) {
                    case 'e' -> 'a';
                    case 'E' ->'A';
                    case 'a' -> 'y';
                    case 'A' -> 'Y';
                    case 'y' -> 'u';
                    case 'Y' -> 'U';
                    case 'u' -> 'o';
                    case 'U' -> 'O';
                    case 'o' -> 'i';
                    case 'O' -> 'I';
                    case 'i' -> 'e';
                    case 'I' -> 'E';
                    default -> arg.charAt(i);
                };
            }
            res += ' ';
        }

        System.out.println(res);
    }
}
