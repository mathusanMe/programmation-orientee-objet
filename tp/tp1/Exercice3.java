public class Exercice3 {
    public static void main(String[] args) {
        String res = "";

        for (String arg : args) {
            for (int i = 0; i < arg.length(); i++) {
                switch (arg.charAt(i)) {
                    case 'e':
                        res += 'a';
                        break;
                    case 'E':
                        res += 'A';
                        break;
                    case 'a':
                        res += 'y';
                        break;
                    case 'A':
                        res += 'Y';
                        break;
                    case 'y':
                        res += 'u';
                        break;
                    case 'Y':
                        res += 'U';
                        break;
                    case 'u':
                        res += 'o';
                        break;
                    case 'U':
                        res += 'O';
                        break;
                    case 'o':
                        res += 'i';
                        break;
                    case 'O':
                        res += 'I';
                        break;
                    case 'i':
                        res += 'e';
                        break;
                    case 'I':
                        res += 'E';
                        break;
                    default:
                        res += arg.charAt(i);
                }
            }
            res += ' ';
        }

        System.out.println(res);
    }
}
