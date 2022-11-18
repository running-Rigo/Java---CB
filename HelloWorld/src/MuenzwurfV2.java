import java.util.Random;
import java.util.Scanner;
public class MuenzwurfV2 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int headNum = 0;
        int numNum = 0;
        boolean isHead;

        System.out.println("Wie oft soll die Münze geworfen werden?");
        int throwNum = sn.nextInt();
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
        else if(headNum==numNum)
        {
            System.out.println("Gleichstand!");
        }
        else{
            System.out.println("Stefan gewinnt.");
        }
    }
}
