import java.util.Random;
import java.util.Scanner;
public class MuenzwurfV4 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int headSum = 0;
        int numSum = 0;
        int inputNum;
        int throwSum = 0; //dient nur zur Überprüfung, ob die Summe der Würfe ungerade ist
        boolean isHead;
        boolean isHappy;

        //das Spiel läuft solange isHappy=false; erst wenn isHappy=true (durch Eingabe "true") erfolgt Verkündung des Gewinners.
        do {
            //Die Eingabe der Wurfzahl wird solange wiederholt, bis eine gültige Eingabe erfolgt
            do {
                System.out.println("Wie oft soll die Münze geworfen werden (insgesamt ungerade Zahl 1-10, bisher "+throwSum+" Würfe)?");
                inputNum = sn.nextInt();
            } while (inputNum <= 0 || inputNum > 10 || ((throwSum + inputNum) % 2) == 0);

            throwSum += inputNum; //erst wenn gültiger Input kommt, wird die Summe tatsächlich aktualisiert

            //Münzwurf in gewünschter Anzahl (entsprenchend Eingabe inputNum)
            for (int i = 0; i < (inputNum); i++) {
                isHead = r.nextBoolean();
                if (isHead) {
                    headSum++;
                    System.out.println("Werfe Münze....Kopf");
                } else {
                    numSum++;
                    System.out.println("Werfe Münze....Zahl");
                }

            }
            //Ausgabe des aktuellen Spielstandes nach dem Münzwurf
            System.out.println("Summe Kopf: " + headSum);
            System.out.println("Summe Zahl: " + numSum);

            //Abfrage Zufriedenheit
            System.out.println("Bist du mit dem Ergebnis zufrieden?");
            isHappy = sn.nextBoolean();
        } while(!isHappy);

        //Wenn isHappy==true: Gewinner wird verkündet, Programm endet.
        if (headSum > numSum) {
            System.out.println("Ich gewinne!");
        }
        else {
                System.out.println("Stefan gewinnt.");
        }
    }
}
