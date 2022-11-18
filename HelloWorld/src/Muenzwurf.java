import java.util.Random;

public class Muenzwurf {
    public static void main(String[] args) {
        Random r = new Random();
        boolean coinSide; // true = Kopf, false = Zahl

        coinSide = r.nextBoolean(); //Zufalls-Boolean wird generiert
        if(coinSide){
            System.out.println("Die Münze ist auf dem Kopf gelandet, ich gewinne!");
        }
        else{
            System.out.println("Die Münze ist auf Zahl gelandet, Stefan gewinnt das Ticket.");
        }








    }
}
