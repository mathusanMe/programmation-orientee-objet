public class Deplacement {
    private int x0, y0, x1, y1;
    
    public static enum DiagonaleType {
        POSITIVE, NEGATIVE
    }

    public Deplacement(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public int[] getPointDepart() {                     // Getter pour obtenir le point de départ en tant que tableau
        return new int[]{x0, y0};
    }

    public int[] getPointArrivee() {                    // Getter pour obtenir le point d'arrivée en tant que tableau
        return new int[]{x1, y1};
    }

    public char typeDeplacement() {
        if (Math.abs(x0 - x1) == Math.abs(y0 - y1)) {
            return 'd';
        } else if (x0 == x1) {
            return 'h';
        } else if (y0 == y1) {
            return 'v';
        } else if ((Math.abs(x0 - x1) == 1 && Math.abs(y0 - y1) == 2) || (Math.abs(x0 - x1) == 2 && Math.abs(y0 - y1) == 1)) {
            return 'c';
        } else {
            return 'x';
        }
    }

    public int dist() {
        switch (typeDeplacement()) {
            case 'd', 'h', 'v':
                int normeAuCarre = (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
                return (int) Math.sqrt(normeAuCarre);
            default:
                return -1;
        }
    }

    public int distAuCarre() {
        switch (typeDeplacement()) {
            case 'd', 'h', 'v':
                int normeAuCarre = (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
                return normeAuCarre;
            default:
                return -1;
        }
    }

    public DiagonaleType getDiagonaleType() {
        int pente = (y0 - y1) / (x0 - x1);
        if (pente == 1) {
            return DiagonaleType.POSITIVE;
        } else {
            return DiagonaleType.NEGATIVE;
        }
    }

    // public static void main(String[] args) {
    //     System.out.println(new Deplacement(1, 3, 2, 2).getDiagonaleType());
    // }
}
