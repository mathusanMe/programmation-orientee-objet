public class Mediatheque {
    public static void main(String[] args) {
        LongMetrage lm = new LongMetrage("Princess Momonoke", 90, "Hayao Miyazaki");
        System.out.println(lm);

        Livre l = new Livre("Fables", "La Fontaine", 200);
        System.out.println(l);
    }
}