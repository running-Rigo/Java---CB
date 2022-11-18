public class GaudiMitArraysV2 {
    public static void main(String[] args) {
        int howMuch = -5;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] originalAlphabetArr = alphabet.toCharArray(); //bleibt unverändert
        char[] alphabetArr = alphabet.toCharArray();

        if (howMuch >= 0) { //rechts verschieben
            for (int i = 0; i < (alphabetArr.length - howMuch); i++) {
                //Buchstabe um howMuch nach rechts verschieben
                alphabetArr[i + howMuch] = originalAlphabetArr[i];
            }
            for (int j = 0; j < howMuch; j++) {
                alphabetArr[j] = originalAlphabetArr[originalAlphabetArr.length - howMuch + j];
            }
        }
        else{ //links verschieben
            howMuch = howMuch+(-2)*howMuch;
            for (int i = 0; i < (alphabetArr.length - howMuch); i++) {
                //Buchstabe um howMuch nach links
                alphabetArr[i] = originalAlphabetArr[i+howMuch];
            }
            for(int j = 0; j < howMuch; j++) {
                alphabetArr[originalAlphabetArr.length - howMuch + j] = originalAlphabetArr[j] ;
            }
        }

        for (char c : alphabetArr) {
            System.out.println(c);
        }
    }
}
