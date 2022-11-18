import java.util.Scanner;
import java.util.Random;

public class MuenzwurfV3 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int headNum = 0;
        int numNum = 0;
        boolean isHead;
        int throwNum;

        //Abfrage der gewünschten Wurf-Anzahl; gefangen in while-Schleife falls Eingabe ungültig
        do {
            System.out.println("Wie oft soll die Münze geworfen werden (ungerade Zahl 1-10)?");
            throwNum = sn.nextInt();
        } while (throwNum <=0 || throwNum >9 || (throwNum%2)==0 );

        //Münzwurf entsprechend der gewünschten Wurfzahl
        for (int i = 0; i<(throwNum); i++){
            isHead = r.nextBoolean();
            if(isHead){
                headNum++;
                System.out.println("Werfe Münze....Kopf");
            }
            else{
                numNum++;
                System.out.println("Werfe Münze....Zahl");
            }

        }

        System.out.println("Summe Kopf: " + headNum);
        System.out.println("Summe Zahl: " + numNum);
        if (headNum>numNum){
            System.out.println("Ich gewinne!");
        }
        else{
            System.out.println("Stefan gewinnt.");
        }
    }
}
