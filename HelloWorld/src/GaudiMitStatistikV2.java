import com.sun.tools.jconsole.JConsoleContext;

import java.util.Random;

public class GaudiMitStatistikV2 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr100 = new int[100];
        for(int i=0; i<arr100.length; i++){
            arr100[i] = r.nextInt(100);
        }

        //Print originalen Zufalls-Array;
        for(int p=0; p<arr100.length; p++){
            System.out.print(arr100[p] + " ");
        }
        System.out.println();


        //Sortieren(große Schleife): finde für den nächsten Index die niedrigste Zahl
        for(int j=0; j<arr100.length; j++){
            int smallestNum = arr100[j];
            //Sortieren(kleine Schleife): finde niedrigste Zahl (für Index 0)
            for(int i = j; i<arr100.length; i++){
                if(smallestNum > arr100[i]){
                    smallestNum = arr100[i];
                    arr100[i] = arr100[j];
                    arr100[j] = smallestNum;
                    i = j-1;
                }
            }
            //Print für jede Stelle das Ergebnis;
            for(int z=0; z<arr100.length; z++){
                System.out.print(arr100[z] + " ");
            }
            System.out.println();

        }




    }
}
