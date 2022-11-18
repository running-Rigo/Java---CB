public class GaudiMitStatistikV1 {
    public static void main(String[] args) {

        int bigLetters = 0;
        int smallLetters = 0;
        int numbers = 0;
        int specialSigns = 0;

        String sentence = "Hallo du 123!";
        char[] sentenceArr = sentence.toCharArray();

        for (int i = 0; i< sentenceArr.length; i++){
            //Großbuchstabe?
            if ( 65<=sentenceArr[i] && sentenceArr[i]<=90) {
                bigLetters ++;
            }
            //Kleinbuchstabe?
            else if(97<=sentenceArr[i] && sentenceArr[i]<=122){
                smallLetters ++;
            }
            //Zahl?
            else if(48<=sentenceArr[i] && sentenceArr[i]<=57){
                numbers ++;
            }
            else if(33<=sentenceArr[i] && sentenceArr[i]<=47 || 58<=sentenceArr[i] && sentenceArr[i]<=64 || 91<=sentenceArr[i] && sentenceArr[i]<=96 || 123<=sentenceArr[i] && sentenceArr[i]<=126){
                specialSigns ++;
            }

        }
        System.out.println("Content: " + sentence);
        System.out.println("Großbuchstaben: " + bigLetters);
        System.out.println("Kleinbuchstaben: " + smallLetters);
        System.out.println("Zahlen: " + numbers);
        System.out.println("Sonderzeichen: " + specialSigns);
    }
}
