public class GaudiMitArraysV2b {
    public static void main(String[] args) {
        int howMuch = -3;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] originalAlphabetArr = alphabet.toCharArray(); //bleibt unverändert
        char[] alphabetArr = alphabet.toCharArray();

        if (howMuch >= 0) { //rechts verschieben
            for (int i = 0; i < (alphabetArr.length - howMuch); i++) {
                //Buchstabe um howMuch nach rechts verschieben
                alphabetArr[i + howMuch] = (char) (65 + i);
            }
            //links mit auffüllen mit den hinteren Buchstaben des Alphabets
            for (int j = 0; j < howMuch; j++) {
                alphabetArr[j] = (char) (90 -howMuch + j+1);
            }
        }
        else{ //links verschieben
            howMuch = -howMuch; //positiver howMuch Wert
            for (int i = 0; i < (alphabetArr.length - howMuch); i++) {
                //Buchstabe um howMuch nach links
                alphabetArr[i] = (char) (65 + howMuch + i);
            }
            for(int j = 0; j < howMuch; j++) {
                alphabetArr[originalAlphabetArr.length - howMuch + j] = (char) (65+j) ;
            }
        }

        for (char c : alphabetArr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
