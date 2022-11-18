import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
public class MinesweeperV1 {
    public static void main(String[] args) {
        int[][] map = new int[10][10];
                int[] bombNum = {0, -1, -2, -3};
        Random r = new Random();
        Scanner sn = new Scanner(System.in);
        //Array für die Eingabe der X-Koordinate als Buchstabe:
        char[] letterCoordinates = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        // Anlegen der Variable xNum für später;
        int xNum = 0;
        int mineCount=0;
        int freeFields=0;
        int cleanFields=0;
        boolean hasStopped = false;
        String yString="";
        // DecimalFormat f = new DecimalFormat("#0.00"); ev. verwenden wenn Prozent als Double gerechnet & gerundet werden sollen

    //Spiel beginnt:
        //1. Array "Map" wird zufällig befüllt:
        for( int x=0; x<map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                int num = bombNum[r.nextInt(4)];
                map[x][y] = num;
                if(num ==0){
                    mineCount++;
                }
            }
        }
        freeFields = 100-mineCount;
        //Anzeige:
        do {
            System.out.println();
            renderMap(map);
            System.out.println("Du hast bisher " + cleanFields+"/"+freeFields + "("+(100*cleanFields/freeFields)+ "%) der freien Felder gefunden.");
            System.out.println("Es sind noch " + mineCount + " Minen am Spielfeld versteckt.");
            System.out.println();

            //Benutzereingabe Spielfeld:
            System.out.println("Wo willst du nach Minen suchen? Gib ein Spielfeld ein (z.B. A4):");
            String chosenField = sn.nextLine();
            //Auslesen der einzelnen Zeichen der String-Benutzereingabe:
            char xCo = chosenField.charAt(0);
            char yCo = chosenField.charAt(1);

            //Umwandlung des chars in String;

            //Falls 10 als Y-Koordinate eingegeben:
            if(chosenField.charAt(2) == '0'){
                yString = "10";
            }
            else {
                yString = String.valueOf(yCo);
            }

            //Koordinaten im Sinne der Indexes im Array (laut Benutzereingabe):

            int yNum = Integer.parseInt(yString)-1; //Umwandlung des Strings in Integer; -1 damit es Index entspricht

            for(int l=0; l<letterCoordinates.length; l++){ //Umwandlung des chars in Integer, damit es Index entspricht
                if(xCo == letterCoordinates[l])  {
                    xNum = l;
                }
            }

            //System.out.println("Gewählte X-Koordinate: " + xCo + " bzw. Array-Index " + xNum);
            //System.out.println("Gewählte Y-Koordinate: " + yCo + " bzw. Array-Index " + yNum);

            if(map[yNum][xNum]==0){
                map[yNum][xNum] = 1;
                renderMap(map);
                System.out.println("Du bist leider auf eine Mine getappt, du hast verloren!");
                hasStopped = true;
            }
            else if(map[yNum][xNum]<0){
                map[yNum][xNum] = 2;
                System.out.println("Du hast ein freies Feld gefunden.");
                cleanFields++;
            }
        }while(hasStopped==false);

        //Auflösung wenn Spiel beendet:
        renderMap(map);
    }

    public static void renderMap(int[][]map){

        //Hilfsvariablen für die Anzeige des Spielfeldes
        String[] xCoordinates = {"    ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] yCoordinates = {" 1.", " 2.", " 3.", " 4.", " 5.", " 6.", " 7.", " 8.", " 9.", "10."};
        String formatter = "%5s %3s %3s %3s %3s %3s %3s %3s %3s %3s";
        String a="";
        String b="";
        String c="";
        String d="";
        String e="";
        String f="";
        String g="";
        String h="";
        String i="";
        String j="";

        //Anzeige des Spielfelds (a: verdeckt im Spielmodus; b: Auflösung der Zahlverteilung)
        for(int z=0; z<11; z++){
            System.out.print(xCoordinates[z] +"   ");
        }
        System.out.println();
        System.out.println();
        for( int y=0; y<map.length; y++){
            System.out.print(yCoordinates[y]);
            for (int x=0; x<map[0].length; x++){
                if(x==0){
                        if (map[y][x] <= 0) {
                            a = "[ ]";
                        } else if (map[y][x] == 1) {
                            a = " * ";
                        } else {
                            a = " - ";
                        }
                }
                else if(x==1){
                    if(map[y][x] <=0){
                        b="[ ]";
                    }
                    else if(map[y][x] == 1){
                        b = " * ";
                    }
                    else{
                        b = " - ";
                    }
                }
                else if(x==2){
                    if(map[y][x] <=0){
                        c="[ ]";
                    }
                    else if(map[y][x] == 1){
                        c = " * ";
                    }
                    else{
                        c = " - ";
                    }
                }
                else if(x==3){
                    if(map[y][x] <=0){
                        d="[ ]";
                    }
                    else if(map[y][x] == 1){
                        d = " * ";
                    }
                    else{
                        d = " - ";
                    }
                }
                else if(x==4){
                    if(map[y][x] <=0){
                        e="[ ]";
                    }
                    else if(map[y][x] == 1){
                        e = " * ";
                    }
                    else{
                        e = " - ";
                    }
                }
                else if(x==5){
                    if(map[y][x] <=0){
                        f="[ ]";
                    }
                    else if(map[y][x] == 1){
                        f = " * ";
                    }
                    else{
                        f = " - ";
                    }
                }
                else if(x==6){
                    if(map[y][x] <=0){
                        g="[ ]";
                    }
                    else if(map[y][x] == 1){
                        g = " * ";
                    }
                    else{
                        g = " - ";
                    }
                }
                else if(x==7){
                    if(map[y][x] <=0){
                        h="[ ]";
                    }
                    else if(map[y][x] == 1){
                        h = " * ";
                    }
                    else{
                        h = " - ";
                    }
                }
                else if(x==8){
                    if(map[y][x] <=0){
                        i="[ ]";
                    }
                    else if(map[y][x] == 1){
                        i = " * ";
                    }
                    else{
                        i = " - ";
                    }
                }

                else if(x==9){
                    if(map[y][x] <=0){
                        j="[ ]";
                    }
                    else if(map[y][x] == 1){
                        j= " * ";
                    }
                    else{
                        j = " - ";
                    }
                }
            }
            System.out.println(String.format(formatter, a,b,c,d,e,f,g,h,i,j));
        }
        System.out.println("----------------------------------------------");
    }

}
