import java.util.Scanner;
import java.util.Random;

public class KinoVerwaltungV2 {
    public static void main(String[] args) {

        String[][] snackData = {
                //Snacknr. Name            Preis Bestand
                {"1",      "Popcorn",       "1.10", "20"},
                {"2",      "Chips",          "2.50", "0"},
                {"3",      "Schokolade",      "4", "4"},
            //  {"4",      "Haribo",         "4.0", "5"},
          //  {"5",      "Apfelringe",      "2.0",  "10"}
        };

        String[][] filmData = {
                //Filmnr   Name              Uhrzeit  Saal  Tickets
                {"1", "Petterson und Findus", "20:30", "3", "0"},
                {"2", "Chase", "19:00", "1", "8"},
                {"3", "Der Passfälscher", "17:00", "4", "2"},
                {"4", "Die Weihnachtshexe", "20:00", "4", "5"}
        };

        Scanner sn = new Scanner(System.in);
        double moneyLeft = 0;
        boolean hasLeft = false;
        boolean validOption = false;
        double [][] costumerData = new double[3][50]; //Reihe 1: [0][0] = moneyLeft; Reihe 2: [1][0-49] = gekaufte Snacks; Reihe 3: [2][0-49] = gekaufte Tickets
        int chosenOption=0;

        //Begrüßung bei Programmstart
        System.out.println("Herzlich willkommen!");

        //Frage nach Geld (so oft bis positiver Betrag eingegeben wird):
        do {
            System.out.println("Wie viel Geld hast du mit? (Du kannst Centbeträge nach dem Komma (,) eingeben; drücke 0, um das Kino zu verlassen)");
            moneyLeft = sn.nextDouble();
            if (moneyLeft == 0) {
                hasLeft = true;
            }
        } while(moneyLeft<0);
        moneyLeft = showTwoDecimals(moneyLeft);  //Falls mehr als 2 Kommastellen eingegeben wird abgeschnitten
        costumerData[0][0] = moneyLeft; //Geld wird an Stelle [0][0] gespeichert.

        //Nutzer im Hauptmenü, muss Option wählen die seinem Geld entspricht
        do { //Nutzer kehrt solange zum Hauptmenü zurück, bis er 6 drückt und hasLeft auf true setzt

            do { //Eingabe-Gefängnis für Auswahl im Hauptmenü
                showOptions();
                chosenOption = sn.nextInt();
            } while (chosenOption < 1 || chosenOption > 7);

            if (chosenOption == 6) {
                hasLeft = true;

            } else if (chosenOption == 1) { //Ticket kaufen
                if (moneyLeft >= 15) {
                    costumerData = handleTickets(costumerData, false, filmData);
                    moneyLeft = costumerData[0][0]; //Restgeld update;
                } else {
                    System.out.println("Um ein Ticket zu kaufen, benötigst du mind. 15€");
                    System.out.println();
                }

            } else if (chosenOption == 2) { //Snack kaufen
                if (moneyLeft >= 1) {
                    costumerData = handleSnacks(costumerData, false, snackData);
                    moneyLeft = costumerData[0][0]; //Restgeld update;
                } else {
                    System.out.println("Um einen Snack zu kaufen, benötigst du mind. 1€");
                    System.out.println();
                }

            } else if (chosenOption == 3) { //Snack essen
                costumerData = handleSnacks(costumerData, true, snackData);


            } else if (chosenOption == 4) { //Film ansehen
                costumerData = handleTickets(costumerData, true, filmData);

            } else if (chosenOption == 5){//Am Gewinnspiel teilnehmen
                if (moneyLeft >= 5) {
                    costumerData[0][0] = handleLotteryTicket(costumerData[0][0]);
                    moneyLeft = costumerData[0][0];
                } else {
                    System.out.println("Um ein Los zu kaufen, benötigst du mind. 5€");
                    System.out.println();
                }


            } else if (chosenOption == 7) {
                System.out.println("Dein Restgeld beträgt: " + showTwoDecimals(moneyLeft) + " €.");
            }
        }while(!hasLeft);
        System.out.println("Auf Wiedersehen!");
    }



    //Methode: Optionen-Anzeige
    public static void showOptions(){
        String[] options = {"1.Tickets kaufen",
                "2.Snacks kaufen",
                "3.Snack essen",
                "4.Film ansehen",
                "5.Beim Gewinnspiel mitmachen (kostet 5€)",
                "6.Kino verlassen",
                "7.Restgeld anzeigen"
        };
        //Anzeige der Optionen
        System.out.println("Was willst du als nächstes tun?");
        for(int i=0; i<options.length; i++){
            System.out.println(options[i]);
        }
    }

    //Methode: Ticket-Handling
    public static double[][] handleTickets(double [][] data, boolean isWatching, String[][] filmData) {
        Scanner sn = new Scanner(System.in);
        boolean hasStopped = false;
        int chosenFilmNumber = 111;
        double moneyLeft = data[0][0];
        int ticketsAmount;
        int watchedFilm = 0;
        String filmName;
        //Variablen für die Kinoprogramm Anzeige (String formatter)
        String a="";
        String b="";
        String c="";
        String d="";
        String e="";

        int[] boughtTicketsArray = new int[data[2].length]; // Zeile 2(=Filme) wird als int-Arr gespeichert
        for (int i=0; i<boughtTicketsArray.length; ++i)
            boughtTicketsArray[i] = (int) data[2][i];

        //Variante Film ansehen:
        if(isWatching){
            do{
                ticketsAmount=0;
                System.out.println("Diese Filmtickets sind verfügbar:");
                for (int i = 0; i < boughtTicketsArray.length; i++) {
                    if (boughtTicketsArray[i] != 0) {
                        int thisFilm = boughtTicketsArray[i];
                        System.out.println(i+1+"." + filmData[thisFilm-1][1]);
                        ticketsAmount++;
                    }
                }
                if (ticketsAmount == 0) {
                    System.out.println("Du hast leider keine Tickets.");
                    System.out.println("---------------------------------------------------------------------");
                } else {
                    System.out.println();
                    do { //Gefängnis für sinnlose Eingaben:
                        System.out.println("Welchen Film möchtest du sehen? (Abbruch mit 0)");
                        watchedFilm = sn.nextInt();
                    } while(watchedFilm!=0 &&(watchedFilm<0|| watchedFilm>ticketsAmount));
                    //Sehen des Films(sofern nicht abgebrochen):
                    if(watchedFilm!=0) {
                        filmName = filmData[boughtTicketsArray[watchedFilm - 1] - 1][1];
                        System.out.println("Du siehst dir  " + filmName + " an, viel Spaß!");
                        boughtTicketsArray[watchedFilm - 1] = 0; //die Filmnr wird beim "ansehen" auf 0 gesetzt.
                        //Jetzt muss der angezeigte Array "verschoben" werden, sodass keine 0 drinnen ist:
                        boughtTicketsArray = watchFilm(boughtTicketsArray);
                    }
                }
            } while (watchedFilm!=0 && ticketsAmount > 0);

        }

        //Variante Ticket kaufen:
        else {
            do { //Wiederholung der Anzeige + Kaufeingabe solange Kunde mind 15€ hat

                //Anzeige der Überschrift für die Filme:
                String formatter = "%10s %22s %10s %6s %12s";
                System.out.println(String.format(formatter,"Filmnr",  "Titel",   "Uhrzeit",    "Saal", "Tickets"));
                System.out.println("-------------------------------------------------------------------");
                //Anzeige der Filme (äußere Schleife):
                for (int i = 0; i < filmData.length; i++) {
                    //Anzeige der Daten für 1 Film (innere Schleife):
                    for (int j = 0; j < filmData[i].length; j++) {
                        if (j == 4) {
                            if (Integer.parseInt(filmData[i][j]) > 0) {
                                e= "auf Lager";
                            } else {
                                e="ausverkauft";
                            }
                        } else if(j == 0) {
                            a=filmData[i][j];
                        }
                        else if(j == 1){
                            b=filmData[i][j];
                        }
                        else if(j == 2){
                            c=filmData[i][j];
                        }
                        else if(j==3){
                            d=filmData[i][j];
                        }
                    }
                    System.out.println(String.format(formatter, a,b,c,d,e));

                }
                System.out.println("-------------------------------------------------------------------");

                if (!hasStopped) {
                    do { // Überprüfung der Kundeneingabe ("Gefängnis")
                        System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen? (Kauf abbrechen mit 0)");
                        chosenFilmNumber = sn.nextInt();
                        if (chosenFilmNumber == 0) {
                            hasStopped = true;
                        }
                    } while (!hasStopped && (chosenFilmNumber > filmData.length || chosenFilmNumber < 0 || Integer.parseInt(filmData[chosenFilmNumber - 1][4]) <= 0));
                }

                //Kaufvorgang
                if (!hasStopped) {
                    System.out.println("Es sind noch " + filmData[chosenFilmNumber - 1][4] + " Tickets verfügbar. Wie viele möchtest du kaufen?");
                    //Eingabe muss ein validBuy sein, damit der Vorgang fortgesetzt wird.
                    boolean validBuy = false;
                    do {
                        validBuy = false;
                        int ticketNum = sn.nextInt();
                        int buySum = ticketNum * 15; //Kosten des potenziellen Kaufs je nach Ticketzahl
                        if (moneyLeft >= buySum && ticketNum != 0 && ticketNum <= Integer.parseInt(filmData[chosenFilmNumber - 1][4])) {
                            moneyLeft -= buySum;
                            System.out.println("Du kaufst " + ticketNum + " Ticket(s) um " + buySum + "Euro, und hast jetzt noch " + showTwoDecimals(moneyLeft) + " Euro übrig.");
                            validBuy = true;
                            filmData[chosenFilmNumber - 1][4] = String.valueOf((Integer.parseInt(filmData[chosenFilmNumber - 1][4]) - ticketNum));
                            for (int i = 1; i <= ticketNum; i++) {
                                boughtTicketsArray = addTicket(Integer.parseInt(filmData[chosenFilmNumber - 1][0]), boughtTicketsArray);

                            }
                        } else {
                            System.out.println("Dein Geld reicht nicht, oder es sind nicht genug Tickets verfügbar. Gib eine andere Ticketzahl ein!");
                        }
                    } while (!validBuy);
                }
            } while (moneyLeft >= 15 && !hasStopped);
            data[0][0] = showTwoDecimals(moneyLeft);
        }
        double[] boughtTicketsDouble = new double[boughtTicketsArray.length]; //Umwandlung des int Arr in double Arr
        for(int i=0; i<boughtTicketsArray.length; i++) {
            boughtTicketsDouble[i] = boughtTicketsArray[i];
        }
        data[2] = boughtTicketsDouble; //Integration der neuen Ticketdaten in den double Arr "data"
        return data;
    }


    //Methode: Snack handling
    public static double[][] handleSnacks(double[][] data, boolean isEating, String[][] snackData){
        double moneyLeft = data[0][0];
        Scanner sn = new Scanner(System.in);
        boolean hasStopped = false;
        int chosenSnackNumber = 111;
        int snacksAmount = 0;
        int eatenSnack=0;
        String snackName="";
        //Variablen für die Ausgabe der Snack-Daten in formatierten Strings
        String a="";
        String b="";
        String c="";
        String d="";

        int[] boughtSnacksArr = new int[data[1].length]; //eindimensionaler int Array wird ersellt
        for(int i = 0; i<boughtSnacksArr.length; i++){
            boughtSnacksArr[i] = (int) data[1][i];
        }


        //falls Kunde Snack essen möchte:
        if(isEating) { //Kunde möchte Snack essen
            do{
                snacksAmount=0;
                System.out.println("Diese Snacks sind verfügbar:");
                for (int i = 0; i < boughtSnacksArr.length; i++) {
                    if (boughtSnacksArr[i] != 0) {
                        int thisSnack = boughtSnacksArr[i];
                        System.out.println(i+1+"." + snackData[thisSnack-1][1]);
                        snacksAmount++;
                    }
                }
                if (snacksAmount == 0) {
                    System.out.println("Du hast leider keine Snacks.");
                    System.out.println("---------------------------------------------------------------------");
                } else {
                    System.out.println();
                    do {
                        System.out.println("Welchen Snack möchtest du essen? (Abbruch mit 0)");
                        eatenSnack = sn.nextInt();
                    } while(eatenSnack!=0 && (eatenSnack<0 || eatenSnack>snacksAmount));
                    //Essen des Snacks sofern nicht abgebrochen:
                    if(eatenSnack!=0) {
                        snackName = snackData[boughtSnacksArr[eatenSnack - 1] - 1][1];
                        System.out.println("Du isst 1 Stück " + snackName + ". Mampf, lecker :-P");
                        boughtSnacksArr[eatenSnack - 1] = 0; //die Snacknr wird beim "essen" durch 0 ersetzt.
                        //Jetzt muss der angezeigte Array "verschoben" werden, sodass keine 0 drinnen ist:
                        boughtSnacksArr = eatSnack(boughtSnacksArr);
                    }
                }
            } while (eatenSnack!=0 && snacksAmount > 0);
        }

        //Snack kaufen
        else if (!isEating){
            do { //Wiederholung der Anzeige + Kaufeingabe solange Kunde mind 1€ hat

                //Anzeige der Überschrift für die Snacks:
                String formatter = "%10s %16s %8s %18s";
                System.out.println(String.format(formatter,"Snacknr",  "Snackname",   "Preis",    "Verfügbarkeit"));
                System.out.println("----------------------------------------------------------------");

                //Anzeige der Snacks (äußere Schleife):
                for (int i = 0; i < snackData.length; i++) {
                    //Anzeige der Daten für 1 Snack (innere Schleife):
                    for (int j = 0; j < snackData[i].length; j++) {
                        if (j == 3) {
                            if (Integer.parseInt(snackData[i][j]) > 0) {
                                d = "auf Lager";
                            } else {
                                d ="ausverkauft";
                            }
                        }
                     else if(j == 0) {
                        a=snackData[i][j];
                    }
                    else if(j == 1){
                        b=snackData[i][j];
                    }
                    else if(j == 2){
                        c=snackData[i][j];
                    }
                }
                System.out.println(String.format(formatter, a,b,c,d));
                    }
                System.out.println("-------------------------------------------------------------------");

                //Eingabe Snack-Nr
                if (!hasStopped) {
                    do { // Überprüfung der Kundeneingabe ("Gefängnis")
                        System.out.println("Welchen (lagernden) Snack möchtest du kaufen? (Beachte den Preis; Kauf abbrechen mit 0)");
                        chosenSnackNumber = sn.nextInt();
                        if (chosenSnackNumber == 0) {
                            hasStopped = true;
                        }
                    } while (!hasStopped && (chosenSnackNumber > snackData.length || chosenSnackNumber < 0 || moneyLeft < Double.parseDouble(snackData[chosenSnackNumber - 1][2]) || Integer.parseInt(snackData[chosenSnackNumber - 1][3]) <= 0));
                }

                //Kaufvorgang
                if (!hasStopped) {
                    System.out.println("Es sind noch " + snackData[chosenSnackNumber - 1][3] + " Stück " + snackData[chosenSnackNumber - 1][1] + " verfügbar. Wie viele möchtest du kaufen?");
                    //Eingabe muss ein validBuy sein, damit der Vorgang fortgesetzt wird.
                    boolean validBuy = false;
                    do {
                        validBuy = false;
                        int snackNum = sn.nextInt();
                        double buySum = showTwoDecimals(snackNum * Double.parseDouble(snackData[chosenSnackNumber - 1][2])); //Kosten des potenziellen Kaufs je nach Snackart & Anzahl
                        if (moneyLeft >= buySum && snackNum != 0 && snackNum <= Integer.parseInt(snackData[chosenSnackNumber - 1][3])) {
                            int bigMoney = (int) (moneyLeft*100); //Rechnung erfolgt mit Int, damit keine Ungenauigkeit entsteht
                            int bigBuySum = (int) (buySum*100);
                            bigMoney -= bigBuySum;
                            moneyLeft = (double)bigMoney/100;
                            System.out.println("Du kaufst " + snackNum + " Snacks um " + buySum + " Euro, und hast jetzt noch " + moneyLeft + " Euro übrig.");
                            validBuy = true;
                            snackData[chosenSnackNumber - 1][3] = String.valueOf((Integer.parseInt(snackData[chosenSnackNumber - 1][3]) - snackNum));
                            for (int i = 1; i <= snackNum; i++) {
                                boughtSnacksArr = addSnack(Integer.parseInt(snackData[chosenSnackNumber - 1][0]), boughtSnacksArr);
                            }
                        } else {
                            System.out.println("Dein Geld reicht nicht, oder es sind nicht genug Snacks dieser Art verfügbar. Gib eine andere Anzahl ein!");
                        }
                    } while (!validBuy);
                }
            } while (moneyLeft >= 1 && !hasStopped);
            data[0][0] = moneyLeft;
        }

        for( int i = 0; i<boughtSnacksArr.length; i++){
            data[1][i] = (double) boughtSnacksArr[i];
        }
        return data;
    }

    //Methode Snack zu Array hinzufügen:

    public static int[] addSnack(int newSnack, int[] boughtSnacks) {
        //Falls an einer Stelle im Array 0 steht, wird die nr. des neu gekauften Snacks eingetragen
        for (int i = 0; i < boughtSnacks.length; i++) {
            if (boughtSnacks[i] != 0) {
            } else {
                boughtSnacks[i] = newSnack;
                i = boughtSnacks.length;
            }
        }
        return boughtSnacks;
    }
       // einstweilige Anzeige der gekauften Snacks(Nr!) zu Überprüfungszwecken
        /*for(int i = 0; i<boughtSnacks.length; i++){
            if(boughtSnacks[i] != 0){
                System.out.print(boughtSnacks[i] + ", ");
            }
        }
        System.out.println();
        return boughtSnacks;
    }

 */


    public static int[] addTicket(int newTicket, int[] boughtTickets){
        //Falls an einer Stelle im Array 0 steht, wird die nr. des neu gekauften Tickets eingetragen
        for(int i=0; i<boughtTickets.length; i++){
            if(boughtTickets[i]!=0){
            }
            else{
                boughtTickets[i] = newTicket;
                i=boughtTickets.length;
            }
        }
        return boughtTickets;
    }


    //Methode eatSnackk "räumt" den Snacksarray auf, sodass er von vorne aufgefüllt wird:
    public static int[] eatSnack(int[] snacksArray){
        for(int i = 0; i<snacksArray.length-1; i++) {
            if (snacksArray[i] == 0 && snacksArray[i + 1] != 0) {
                for (int j = i; j < snacksArray.length - 1; j++) {
                    snacksArray[j] = snacksArray[j + 1];
                }
                snacksArray[snacksArray.length - 1] = 0;
                i = snacksArray.length;
            }
        }
            return snacksArray;
        }

    public static int[] watchFilm(int[] filmsArray){
        for(int i = 0; i<filmsArray.length-1; i++){
            if(filmsArray[i]==0 && filmsArray[i+1]!=0){
                for(int j=i; j<filmsArray.length-1; j++){
                    filmsArray[j] = filmsArray[j+1];
                }
                filmsArray[filmsArray.length-1] = 0;
                i=filmsArray.length;
            }
        }
        return filmsArray;
    }

    public static double handleLotteryTicket(double moneyLeft){
        boolean hasStopped = false;
        Scanner sn= new Scanner(System.in);
        String wantsLotteryString = "";

        while(!hasStopped  && moneyLeft>=5) {

            do{
                System.out.println("Möchtest du ein Los um 5€ kaufen? (Antworte mit ja oder nein)");
                wantsLotteryString = sn.nextLine();


            } while (! wantsLotteryString.equals("ja") && ! wantsLotteryString.equals("nein"));
            if (wantsLotteryString.equals("ja")){
                moneyLeft = playLottery(moneyLeft);
            }
            else{
                hasStopped=true;
            }
        }
        if(moneyLeft<5){
            System.out.println("Dein Geld reicht leider nicht mehr aus für ein weiteres Los.");
            System.out.println();
        }
        return moneyLeft;
    }

    public static double playLottery(double moneyLeft){
        Random r = new Random();
        int drawnNum = r.nextInt(200);
        System.out.println("Du machst beim Gewinnspiel mit!");
        System.out.println("Deine Glückszahl lautet: " + drawnNum);
        boolean isLucky = false;

        //Berechnung Fibonacci:
        int a=1;
        int b=1;
        int c=0;
        System.out.println("Überprüfe die Gewinnzahlen....");
        System.out.println();
        //Überprüfe auf Fibonacci-Zahlen:
        for(int i=0; c<drawnNum; i++) {
            c = a + b;
            System.out.println(a + "+" + b + "=" + c);
            a = b;
            b = c;
            if (drawnNum == c) {
                System.out.println("Gratuliere! " + drawnNum + " ist eine Fibonacci-Nummer! Du gewinnst 20€!");
                //Rechnung mit Int damit genau:
                int bigMoney = (int) (moneyLeft*100);
                bigMoney += 2000;
                moneyLeft = (double)bigMoney/100;
                isLucky = true;
            }
        }
        // Ist die Zahl durch Zehn teilbar?
        if(drawnNum%10==0 && isLucky==false){
            System.out.println("Herzlichen Glückwunsch! " +drawnNum + " ist durch 10 teilbar, du gewinnst 20€!");
            //Rechnung mit Int damit genau:
            int bigMoney = (int) (moneyLeft*100);
            bigMoney += 2000;
            moneyLeft = (double)bigMoney/100;
        }
        //Falls nicht gewonnen:
        else if(isLucky==false){
            System.out.println("Leider gewinnst du diesmal nicht. Dir werden 5€ für das Los berechnet.");
            //Rechnung mit Int damit genau:
            int bigMoney = (int) (moneyLeft*100);
            bigMoney -= 500;
            moneyLeft = (double)bigMoney/100;
        }
        return moneyLeft;
    }

    public static double showTwoDecimals(double num){
        int number = (int) (num*100);
        num = (double) number/100;
        return (num);
    }

}





