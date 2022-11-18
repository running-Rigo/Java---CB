public class TischComputer {
    public static void main(String[] args) {
        int z1 = 10;
        int z2;
        while (z1 > -10) {
             for (z2 = 1; z2 < 10; z2 = z2 * 2) {
                System.out.println(z1 + "/" + z2);
                }
            z1 = z1  - z2;
            System.out.println(z1 + "" + z2);
            }
    }
}
