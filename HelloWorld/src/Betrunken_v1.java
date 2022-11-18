import java.util.Scanner;
public class Betrunken_v1 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);

        System.out.println("Wie viel Bier hast du getrunken?");
        int beer = sn.nextInt();
        System.out.println("Wie viele Shots hast du getrunken?");
        int shots = sn.nextInt();

        if (beer == 0 && shots == 0){
            System.out.println("Du bist überhaupt nicht betrunken.");
        }
        else if ( (beer + shots) <= 2){
            System.out.println("Du bist leicht betrunken.");
        }
        else if (beer <= 6 && shots == 0){
            System.out.println("Du bist betrunken");
        }
        else{
            System.out.println("Du bist hoffnungslos betrunken.");
        }

    }
}
