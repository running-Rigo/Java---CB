import java.util.Random;
public class GaudiMitArraysV1 {
    public static void main(String[] args) {
        Random r = new Random();

        //Aufgabe 1
        int[] num10 = {5,6,2,1,9,6,5,3,11,32};

        //Aufgabe 2
        int[] num100 = new int[100];
        for(int i = 0; i<num100.length; i++) {
            num100[i] = r.nextInt(100);
        }

        //Aufgabe 3
        boolean[] bool10 = new boolean[10];
        for(int i = 0; i < bool10.length; i++){

            if(i==0){
                bool10[i] = true;
            }
            else bool10[i] = !bool10[i - 1];
        }
    }
}
