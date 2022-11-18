import java.util.Scanner;
public class BetrunkenV2 {
    public static void main(String[] args) {
        boolean hasLicense;
        Scanner sn = new Scanner(System.in);

        //Abfrage von Alter, Führerschein, Bier und Shots
        System.out.println("Wie alt bist du?");
        int age = sn.nextInt();
        System.out.println("Hast du deinen Führerschein dabei?");
        sn.nextLine();
        String license = sn.next();
        System.out.println("Wie viel Bier hast du getrunken?");
        int bier = sn.nextInt();
        System.out.println("Wie viele Shots hast du getrunken?");
        int shots = sn.nextInt();

        //die Eingabe zur Führerschein-Frage (ev. "ja") wird in Boolean umgewandelt
        if (license.equals("ja") || license.equals("true")){
            hasLicense = true;
        }
        else{
            hasLicense = false;
        }

        //Antwort-Logik:

        if(age>=17) {
            if (hasLicense) {
                if (age < 19 && bier + shots == 0) {
                    System.out.println("Du darfst fahren, da du nüchtern bist.");
                }
                else if (age >= 19 && (bier + shots) <= 2) {
                    System.out.println("Du bist leicht betrunken, darfst aber fahren da du mind. 19 bist.");
                }
                else {
                    System.out.println("Du bist leider zu betrunken, um zu fahren!");
                }
            }
            else {
                System.out.println("Du darfst sowieso nicht fahren, da du deinen Führerschein nicht dabei hast.");
            }
        }
        else{
            System.out.println("Du bist ohnehin zu jung, um zu fahren!");
             }

        }

    }



