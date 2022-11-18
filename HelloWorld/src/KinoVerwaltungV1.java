import java.util.Scanner;
public class KinoVerwaltungV1 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int moneyLeft = 0;
        int chosenFilmNumber=5;
        boolean hasStopped = false;
        String[][] filmData = {
                //Filmnr   Name              Uhrzeit  Saal  Tickets
                {"1", "Petterson und Findus", "20:30", "3", "0"},
                {"2", "Chase               ", "19:00", "1", "8"},
                {"3", "Der Passfälscher    ", "17:00", "4", "2"},
                {"4", "Die Weihnachtshexe  ", "20:00", "4", "5"}
        };

        // Frage nach Geld
        do { //Kund kann nur weiter im Prozess, wenn er mind. 15€ hat.
            if (moneyLeft > 0) {
                System.out.println("Gib bitte einen höheren Geldbetrag ein.");
            }
            System.out.println("Wie viel Geld hast du mit?(ein Ticket kostet 15€; drücke 0 für Abbruch)");
            moneyLeft = sn.nextInt();
            if (moneyLeft == 0) {
                hasStopped = true;
            }

        } while (moneyLeft < 15 && moneyLeft != 0);

        do { //Wiederholung der Anzeige + Kaufeingabe solange Kunde mind 15€ hat
            if (!hasStopped) {

                //Anzeige der Überschrift für das Kinoprogramm:
                System.out.println("Filmnr   Filmname                   Uhrzeit     Saal     Restplätze");
                System.out.println("-------------------------------------------------------------------");

                //Anzeige der Filme (äußere Schleife):
                for (int i = 0; i < filmData.length; i++) {
                    //Anzeige der Daten für 1 Film(innere Schleife):
                    for (int j = 0; j < filmData[i].length; j++) {
                        if (j == 4) {
                            if (Integer.parseInt(filmData[i][j]) > 0) {
                                System.out.print("verfügbar");
                            } else {
                                System.out.print("ausgebucht");
                            }
                        } else {
                            System.out.print(filmData[i][j] + "        ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("-------------------------------------------------------------------");

            }




            if(!hasStopped) {
                do { // Überprüfung der Kundeneingabe ("Gefängnis")
                    System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen? (Abbruch mit 0)");
                    chosenFilmNumber = sn.nextInt();
                    if (chosenFilmNumber == 0) {
                        hasStopped = true;
                    }
                } while (!hasStopped && (chosenFilmNumber > filmData.length || chosenFilmNumber<0||Integer.parseInt(filmData[chosenFilmNumber - 1][4]) <= 0));
            }

            //Kaufvorgang
            if (!hasStopped) {
                System.out.println("Es sind noch " + filmData[chosenFilmNumber - 1][4] + " Tickets verfügbar. Wie viele möchtest du kaufen?");
               //Eingabe muss ein validBuy sein, damit der Vorgang fortgesetzt wird.
                boolean validBuy = false;
                do {
                    //Methode Integer.parseInt(„25“) einen String in einen int umwandeln, und mit der Methode String.valueOf(23) einen int in einen String
                    int ticketNum = sn.nextInt();
                    int buySum = ticketNum * 15; //Kosten des potenziellen Kaufs je nach Ticketzahl
                    if (moneyLeft >= buySum && ticketNum<=Integer.parseInt(filmData[chosenFilmNumber-1][4])) {
                        moneyLeft -= buySum;
                        System.out.println("Du kaufst " + ticketNum + " Ticket(s) um " + buySum + "Euro, und hast jetzt noch " + moneyLeft + " Euro übrig.");
                        validBuy = true;
                        filmData[chosenFilmNumber-1][4] = String.valueOf((Integer.parseInt(filmData[chosenFilmNumber-1][4])-ticketNum));
                    }
                    else{
                        System.out.println("Dein Geld reicht nicht, oder es sind nicht genug Tickets verfügbar. Gib eine andere Ticketzahl ein!");
                    }
                } while(!validBuy);

            }
        } while (moneyLeft >= 15 && !hasStopped);


        //Anzeige dass Kauf gestoppt weil zu wenig Geld.
        if (moneyLeft < 15 && !hasStopped) {
            System.out.println("Dein Geld (" + moneyLeft + " €) reicht nicht mehr, um weitere Tickets zu kaufen.");
        }

        //Abbruch mit 0
        System.out.println("Auf Wiedersehen!");

        }

    }

