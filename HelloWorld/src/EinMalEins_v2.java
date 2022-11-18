import java.util.Scanner;
public class EinMalEins_v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        System.out.println("Welche Reihe soll ausgegeben werden?");
        int row = sc.nextInt();
        System.out.println("Die " + row + "er-Reihe wird für dich berechnet:");

        for(int i=1; i<=10; i++) {
            System.out.println(i + " x " + row + " = " + (i * row));
        }
    }
}

