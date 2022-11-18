public class Primzahl {
    public static void main(String[] args) {
        int n = 11;
        boolean primNum = true;
        for(int k=2; k<n; k++){
            if(n%k == 0){
                primNum = false;
                k = n;
            }
        }

        if(primNum == false){
            System.out.println(n + " ist leider keine Primzahl.");
        }
        else{
            System.out.println(n + " ist eine Primzahl, juhu.");
        }
    }
}
