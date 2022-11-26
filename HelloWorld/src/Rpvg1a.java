import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Scanner;
public class Rpvg1a {
    public static void main(String[] args) {
        String[][] choices = {
                // parentID  ID     choiceTxt                  resultTxt                               moveToID
                { "-1" ,       "0",   "Du stehst in einer Bar"                             },
                { "0",         "1",    "Geh in die Wildnis",     "Du bist in der Wildnis angekommen",      },
                { "0",         "2",    "Trink ein Getränk",      "Du lässt dir ein Getränk schmecken",   "0" },
                { "1",         "3",    "Kämpf gegen das Monster", "Das Monster ist ein harter Gegner, aber du besiegst es", "1"},
                { "1",         "4",    "Lauf vor dem Monster davon", "Du läufst wie ein Feigling zurück zur Bar", "0"  }

        };

        ArrayList<Integer> viableChoices = new ArrayList<>(0);



        int currentChoice = 0;
        int chosenOption; //Eingabe durch Benutzer
        Scanner sn = new Scanner(System.in);
        boolean hasStopped = false;

        System.out.println(choices[0][2]);
        do {
            //Anzeige der Optionen zur aktuellen ID:

            System.out.println("-".repeat(50));
            for (int i = 0; i < choices.length; i++) {
                if (Integer.parseInt(choices[i][0]) == currentChoice) {
                    System.out.println(i+".) " + choices[i][2]);
                    viableChoices.add(i);
                }
            }
            System.out.println("-".repeat(50));
            //Eingabe der gewünschten Option
            do {
                System.out.println("Was willst du als nächstes tun? (Gib die Zahl der gewünschten Option ein / mit 9 verlässt du das Spiel)");
                chosenOption = sn.nextInt();
                if(chosenOption == 9){
                    hasStopped = true;
                }
            } while (!hasStopped && !viableChoices.contains(chosenOption));

            if(!hasStopped) {
                currentChoice = chosenOption;
                //Anzeige des resultTxt
                System.out.println(choices[currentChoice][3]);
                //Ev. move to ID
                try {
                    currentChoice = Integer.parseInt(choices[currentChoice][4]);
                } catch (Exception ignored) {
                }
            }

        }while(!hasStopped);

        System.out.println("Auf Wiedersehen!");

    }

}
