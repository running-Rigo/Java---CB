import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class ZahlenRatenV1 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int chosenOption;
        boolean hasStopped = false;

//Hauptmenü des Spiels
        do { //Spiel läuft solange man nicht aussteigt
            showOptions();
            do {
                System.out.println("Welches Level möchtest du spielen? (Gib die Zahl des Levels oder 0 für Abbruch ein)");
                chosenOption = sn.nextInt();
            } while (chosenOption < 0 || chosenOption > 3);

            if(chosenOption==0){
                hasStopped=true;
            }

            //Alle anderen Optionen außer Abbruch = Ausführen der Levels je nach Auswahl;
            else{
                if(chosenOption==1){
                    playLevel1();
                }
                else if(chosenOption==2){
                    playLevel2();
                }
                else {
                    playLevel3();
                }
            }

        }while(!hasStopped);

        System.out.println("Auf Wiedersehen!");
    }
//Methoden:
    public static void showOptions(){
        System.out.println("-------------------");
        System.out.println("ÜBERSICHT:");
        System.out.println("Level 1");
        System.out.println("Level 2");
        System.out.println("Level 3");
        System.out.println("Spiel Abbrechen (0)");
        System.out.println("-------------------");
    }
//Level1
    public static void playLevel1(){
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int trysLeft = 9;
        int tip = 0;
        boolean hasLeft = false;
        boolean newGame = true;
        int randomNum = 1;

    //Hauptschleife: Spiel läuft solange man nicht aussteigt oder verloren hat.
        do {
            if(newGame) {
                System.out.println("Beginne neues Spiel....");
                randomNum = r.nextInt(101);
                trysLeft = 9;
                System.out.println("Generiere Zufallszahl zwischen 0 und 100....");
            }

            System.out.println("Du hast noch " + trysLeft + " Versuche übrig, um die Zahl zu erraten.");
            //Eingabe-Gefängnis
            do {
                System.out.println("Gib einen Tipp ab: ");
                tip = sn.nextInt();
            } while (tip < 0 || tip > 100);

            if (tip == randomNum) {
                newGame = true; //falls nochmal spielen möchte muss neue Zufallszahl generiert & Versuche auf 9 gesetzt werden
                System.out.println("Gratuliere, du hast die Zufallszahl erraten! Die Zahl lautet " + randomNum + ".");
                System.out.println();
                boolean noException;

                do {
                    noException = true;
                    System.out.println("Möchtest du nochmal spielen? (Gib true oder false ein)");

                    try {
                        hasLeft = !sn.nextBoolean();
                    }
                    catch (Exception e){ //falls Problem auftritt:
                        noException = false; //in Schleife halten bei falscher Eingabe
                        sn.nextLine(); //Scanner zurücksetzen für erneute Eingabe
                    }
                } while (!noException);

            }

            //Wenn nicht erraten:
           else{
               newGame = false; //es soll im bisherigen Spiel weiter gespielt werden (gleiche Zufallszahl/Anzahl der Versuche)
               trysLeft--;
               if(tip<randomNum) {
                   System.out.println("Die Zufallszahl ist größer als dein Tipp!");
               }
               else{
                   System.out.println("Die Zufallszahl ist kleiner als dein Tipp!");
               }
            }

           if(trysLeft==0){
               hasLeft = true;
               System.out.println("------------------------------------------------------------------------");
               System.out.println("Du hast keine Versuche mehr übrig. Leider hast du das Spiel verloren....");
           }

        }while(!hasLeft);

    }
//Level 2
    public static void playLevel2(){
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int trysLeft = 9;
        int tip = 0;
        boolean hasLeft = false;
        boolean newGame = true;
        int randomNum = 1;
        int[] oldTips = new int[9];

    //Hauptschleife: Spiel läuft solange man nicht aussteigt oder verloren hat.
        do {
            if(newGame) { //Neustart des Spiels
                System.out.println("Beginne neues Spiel....");
                randomNum = r.nextInt(101); //neue Zufallszahl
                trysLeft = 9; //volle Anzahl an Versuchen
                Arrays.fill(oldTips, -1); //Old-Tipps array wird auf Anfang gesetzt (alle Indexe auf -1 damit unsichtbar)
                System.out.println("Generiere Zufallszahl zwischen 0 und 100....");
            }
            if(!newGame) { //oldTips werden nur angezeigt wenn schon welche vorhanden sind
                showOldTips(oldTips);
            }
            System.out.println("Du hast noch " + trysLeft + " Versuche übrig, um die Zahl zu erraten.");

            do { //Eingabe-Gefängnis für sinnvolle Tips
                System.out.println("Gib einen Tipp (Zahl zw. 0 und 100) ab: ");
                tip = sn.nextInt();
            } while (tip < 0 || tip > 100);

            oldTips[9-trysLeft] = tip; //Hinzufügen des tip zum Array oldTips

            if (tip == randomNum) {
                newGame = true; //falls nochmal spielen möchte muss neue Zufallszahl generiert & Versuche auf 9 gesetzt werden
                System.out.println("Gratuliere, du hast die Zufallszahl erraten! Die Zahl lautet " + randomNum);
                System.out.println();
                boolean noException;

                do {
                    noException = true;
                    System.out.println("Möchtest du nochmal spielen? (Gib true oder false ein)");

                    try {
                        hasLeft = !sn.nextBoolean();
                    }
                    catch (Exception e){ //falls Problem auftritt:
                        noException = false; //in Schleife halten bei falscher Eingabe
                        sn.nextLine(); //Scanner zurücksetzen für erneute Eingabe
                    }
                } while (!noException);
            }

            //Wenn nicht erraten:
            else{
                newGame = false; //es soll im bisherigen Spiel weiter gespielt werden (gleiche Zufallszahl/Anzahl der Versuche)
                trysLeft--;
                int difference = tip-randomNum; //Differenz zur Zufallszahl wird berechnet
                if (difference<0){
                    difference = difference*(-1);
                }

                if(difference<=3) { //Ausgabe der Tipps je nach Differenz zur Zufallszahl
                    System.out.println("Fast da! Nur 1-3 daneben.");
                }
                else if(difference<=10){
                    System.out.println("Relativ nahe! (4-10 daneben)");
                }
                else if(difference<=20){
                    System.out.println("Nicht ganz so weit weg. (11-20 daneben)");
                }
                else{
                    System.out.println("Weit weg! (über 20 daneben)");
                }
            }

            if(trysLeft==0){
                hasLeft = true;
                System.out.println("------------------------------------------------------------------------");
                showOldTips(oldTips);
                System.out.println("Du hast keine Versuche mehr übrig. Leider hast du das Spiel verloren....");
            }
        }while(!hasLeft);
    }
//Level 3
    public static void playLevel3(){
    //Variablen:
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        int tip;
        int difference;

        int randomNum = 1;
        int roundNum = 1;
        int[] oldTips = new int[100]; //max. 100 mal raten für 101 mögliche Zahlen

        boolean hasLeft = false;
        boolean newGame = true;
        boolean playerHasTurn = true;
        boolean[] tabuNums = new boolean[101]; //Index steht für die jeweilige Zahl, true = Zahl bereits ausgeschlossen;

    //Hauptschleife: Spiel läuft solange man nicht aussteigt oder verloren hat.
        do {
        //Nur wenn: Neustart des Spiels
            if(newGame) {
            //Zurücksetzen diverser Variablen:
                randomNum = r.nextInt(101); //neue Zufallszahl
                roundNum = 1; //Runde Nr.1
                Arrays.fill(oldTips, -1); //Old-Tipps array wird auf Anfang gesetzt
                Arrays.fill(tabuNums, false); //Tabu-Nums array wird auf Anfang (alle false) gesetzt
            //Anzeige bei Neustart:
                System.out.println("Beginne neues Spiel....");
                System.out.println("Generiere Zufallszahl zwischen 0 und 100....");
                playerHasTurn = r.nextBoolean(); //Zufällige Auswahl wer beginnt
            //Auswahl des Startspielers per Zufall:
                if(!playerHasTurn){
                    System.out.println("Der Computer beginnt");
                }
                else{
                    System.out.println("Der menschliche Spieler beginnt");
                }
            }

        //Anzeige vorheriger Tips (oldTips) sofern vorhanden
            if(!newGame) {
                showOldTips(oldTips);
            }

        //Wenn: menschlicher Spieler (player) ist am Zug (Zahl tip wird durch Eingabe von Benutzer gewählt)
            if(playerHasTurn) {
                do { //Eingabe-Gefängnis für sinnvolle Tips
                    System.out.println("Gib einen Tipp ab! (Zahl zwischen 0-100; mit 111 verlässt du das Level ");
                    tip = sn.nextInt();
                } while ((tip < 0 || tip > 100)&& tip!=111);
                if(tip == 111){
                    hasLeft = true;
                }
            }
        //Sonst: Computer ist am Zug (Zahl tip wird durch Methode makeComputerGuess ermittelt)
            else{
                tip = makeComputerGuess(tabuNums); //Methode benötigt den Array der bereits ausgeschlossenen Zahlen
                System.out.println("Der Computer wählt die Zahl " + tip + ".");
            }

       //Hinzufügen des Tips zum Array oldTips
            oldTips[roundNum -1] = tip;

        //Nur wenn: Spiel wurde NICHT durch "111" beendet:
            if(!hasLeft) {

            //Wenn: Zufallszahl wurde erraten
                if (tip == randomNum) {
                    newGame = true; //(damit Codeblock "Neustart" ausgeführt wird falls Spieler nochmal spielt)
                    System.out.println("Die Zufallszahl wurde erraten. Die Zahl lautet " + randomNum);
                    System.out.println();
                    boolean noException;
                //Wenn: Spieler hat Zahl erraten -> Gewonnen = Spieler kann entscheiden ober nochmal spielen möchte
                    if (playerHasTurn) {
                        do {
                            noException = true;
                            System.out.println(" Gratuliere, du hast gewonnen! Möchtest du nochmal spielen? (Gib true oder false ein)");

                            try {
                                hasLeft = !sn.nextBoolean();
                            } catch (Exception e) { //falls Problem auftritt:
                                noException = false; //in Schleife halten bei falscher Eingabe
                                sn.nextLine(); //Scanner zurücksetzen für erneute Eingabe
                            }
                        } while (!noException);
                    }
                //Sonst: Computer hat Zahl erraten -> Verloren = Zurück zum Hauptmenü
                    else {
                        System.out.println("Leider hast du diesmal gegen den Computer verloren.");
                        hasLeft = true;
                    }

                }

            //Sonst: Zufallszahl wurde nicht erraten
                else {
                    newGame = false; //es soll im bisherigen Spiel weiter gespielt werden (gleiche Zufallszahl/Anzahl der Versuche)
                    roundNum++;
                    difference = tip - randomNum; //Differenz zur Zufallszahl wird berechnet
                    if (difference < 0) {
                        difference = difference * (-1);
                    }

                    tabuNums = handleTabuNumbers(tip, tabuNums, difference);

                    if (difference <= 3) { //Ausgabe der Tipps je nach Differenz zur Zufallszahl
                        System.out.println("Fast da! Nur 1-3 daneben.");
                    } else if (difference <= 10) {
                        System.out.println("Relativ nahe! (4-10 daneben)");
                    } else if (difference <= 20) {
                        System.out.println("Nicht ganz so weit weg. (11-20 daneben)");
                    } else {
                        System.out.println("Weit weg! (über 20 daneben)");
                    }
                }
                playerHasTurn = !playerHasTurn; // im nächsten Zug ist der jeweils andere Spieler an der Reihe
            }
        }while(hasLeft == false);
    }

    public static void showOldTips(int[]oldTips){
        System.out.println("Die bisherigen Tipps lauten:");
        for (int i=0; i<oldTips.length; i++){
            if(oldTips[i]>=0) {
                System.out.print(oldTips[i] + " ");
            }
        }
        System.out.println();
    }

    public static int makeComputerGuess (boolean[]tabuNums){
        Random r = new Random();
        int number;
        boolean isFreeNumber = true;

        do{
            isFreeNumber = true;
            number = r.nextInt(101); //Zufallszahl generieren
            if(tabuNums[number]){ //falls der Index true ist, heißt das die Zahl ist tabu
                isFreeNumber = false;
            }
        } while(!isFreeNumber);
        return number;
    }

    public static boolean[] handleTabuNumbers(int tip, boolean[] tabuNums, int difference){
        tabuNums[tip] = true; // bisherige Tips sind immer tabu

        if(difference>20){ //Differenz-Bereich über 20
            for (int i = tip+20; i>tip; i--){ //alle Zahlen bis tip +20 werden tabu
                if(i<=100) { //aufpassen dass i im vorhandenen Index-Bereich 0-100 liegt!
                    tabuNums[i] = true;
                }
            }
            for (int i= tip-20; i<tip; i++){ //alle Zahlen bis tip -20 werden tabu
                if(i>=0) {
                    tabuNums[i] = true;
                }
            }
        }
        else if(difference <21 && difference>10) { //Differenz-Bereich 11-20
            for (int i = tip + 10; i > tip; i--) { //alle Zahlen bis tip +10 werden tabu
                if(i<=100) {
                    tabuNums[i] = true;
                }
            }
            for (int i = tip - 10; i < tip; i++) { //alle Zahlen bis tip -10 werden tabu
                if(i>=0) {
                    tabuNums[i] = true;
                }
            }
            for (int i = tip + 21; i < tabuNums.length; i++) { // alle Zahlen die weiter als +20 entfert sind, werden tabu
                if(i<=100) {
                    tabuNums[i] = true;
                }
            }
            for (int i = tip - 21; i >= 0; i--) { // alle Zahlen die weiter als -20 entfert sind, werden tabu
                if(i>=0) {
                    tabuNums[i] = true;
                }
            }
        }

         else if(difference <11 && difference>3) { //Differenz-Bereich 4-10
                for (int i = tip + 3; i > tip; i--) { //alle Zahlen bis tip +3 werden tabu
                    if(i<=100) {
                        tabuNums[i] = true;
                    }
                }
                for (int i = tip - 3; i < tip; i++) { //alle Zahlen bis tip -3 werden tabu
                    if(i>=0) {
                        tabuNums[i] = true;
                    }
                }
                for (int i = tip + 11; i < tabuNums.length; i++) { // alle Zahlen die weiter als +10 entfert sind, werden tabu
                    if(i<=100) {
                        tabuNums[i] = true;
                    }
                }
                for (int i = tip - 11; i >= 0; i--) { // alle Zahlen die weiter als -10 entfert sind, werden tabu
                    if(i>=0) {
                        tabuNums[i] = true;
                    }
                }
            }

        else if(difference <4 && difference>0) { //Differenz-Bereich 1-3

            for (int i = tip + 4; i < tabuNums.length; i++) { // alle Zahlen die weiter als +3 entfert sind, werden tabu
                if(i<=100) {
                    tabuNums[i] = true;
                }
            }
            for (int i = tip - 4; i >= 0; i--) { // alle Zahlen die weiter als -3 entfert sind, werden tabu
                if(i>=0) {
                    tabuNums[i] = true;
                }
            }
        }
        return tabuNums;
    }

}
