import java.util.Random;

class Informations {
    private int vitalite;
    private int force;
    private int agilite;
    
    public Informations(int v, int f, int a) {
        vitalite = v;
        force = f;
        agilite = a;
    }

    public Informations(Informations inf) {
        vitalite = inf.vitalite;
        force = inf.force;
        agilite = inf.agilite;
    }

    // GETTERS

    public int getVitalite() {
        return vitalite;
    }

    public int getForce() {
        return force;
    }

    public int getAgilite() {
        return agilite;
    }

    // SETTERS

    public void setVitalite(int v) {
        vitalite = v;
    }

    public void setForce(int f) {
        force = f;
    }

    public void setAgilite(int a) {
        agilite = a;
    }

    public String toString() {
        return "Ma vitalité est de " + vitalite + ", ma force est de " + force + " et mon agilité est de " + agilite + "!";
    }
}

class Personnage {
    private String nom;
    private Informations etatInitial;
    private Informations etatActuel;

    public Personnage(String nom, int v, int f, int a) {
        this.nom = nom;
        etatInitial = new Informations(v, f, a);
        etatActuel = new Informations(etatInitial);
    }

    public String toString() {
        return nom + " : " + etatActuel.toString();
    }

    public boolean estVivant() {
        return etatActuel.getVitalite() > 0;
    }

    public void rebirth() {
        etatActuel.setVitalite(etatInitial.getVitalite());
    }

    public void attaque(Personnage def) {
        Random rd = new Random();
        int n = rd.nextInt(Math.max(1, etatActuel.getForce() - def.etatActuel.getForce()));

        if (def.etatActuel.getAgilite() <= etatActuel.getAgilite()) {
            def.etatActuel.setVitalite(def.etatActuel.getVitalite() - n);
        } else {
            def.etatActuel.setVitalite(def.etatActuel.getVitalite() - (int) n / 2);
            def.etatActuel.setAgilite((int) def.etatActuel.getAgilite() * (2 / 3));
        }
    }

    public void lutteIter(Personnage def) {                                             // fonction itérative
        boolean monTour = true;
        while (etatActuel.getVitalite() > 0 && def.etatActuel.getVitalite() > 0) {
            if (monTour) {
                attaque(def);
                
            } else {
                def.attaque(this);
            }
            System.out.println("----------------------");
            System.out.println(nom + " attaque!");
            System.out.println("----------------------");
            System.out.println(this);
            System.out.println(def);
        }
        if (etatActuel.getVitalite() <= 0) {
            System.out.println("J'ai perdu!");
        } else {
            System.out.println("J'ai gagné!");
        }
    }

    public void lutteAux(Personnage def, boolean monTour) {                                 // fonction auxiliaire pour la récursion
        if (etatActuel.getVitalite() < 0 || def.etatActuel.getVitalite() < 0) {
            return;
        }

        if (monTour) {
            attaque(def);
        } else {
            def.attaque(this);
        }
        System.out.println("----------------------");
        System.out.println(nom + " attaque!");
        System.out.println("----------------------");
        System.out.println(this);
        System.out.println(def);

        if (etatActuel.getVitalite() > 0 && def.etatActuel.getVitalite() > 0) {
            lutteAux(def, !monTour);
        }
    }

    public void lutteRec(Personnage def) {                                                      // fonction récursive
        lutteAux(def, true);

        if (etatActuel.getVitalite() <= 0) {
            System.out.println("J'ai perdu!");
        } else {
            System.out.println("J'ai gagné!");
        }
    }
}

class Combat {

    public static void main(String[] args) {
        Personnage p1 = new Personnage("Luffy", 200, 200, 500);
        Personnage p2 = new Personnage("Ener", 100, 100, 100);

        System.out.println("Combat itérative : ");
        p1.lutteIter(p2);

        System.out.println();

        p1.rebirth();                               // soigner les personnages
        p2.rebirth();

        System.out.println("Combat récursive : ");
        p1.lutteRec(p2);
    }
}