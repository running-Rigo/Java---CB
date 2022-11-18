import java.util.Random;
public class CyberSecurityV1 {
    //erlaubte Zeichen:   ABCabc012!
    //mind. 4 Stellen bei Passwort

    public static void main(String[] args) {
        Random r = new Random();
        String[] charactersArr = {"A", "B", "C", "a", "b", "c", "0", "1", "2", "!"};
        int sumOfTrys = 0;
        int smallestTryNum = 10000;
        int biggestTryNum = 0;


        //1000 mal durchlaufen, Durchschnitt der Versuche berechnen;
        for(int x=0; x<1000; x++) {
            String[] password = new String[4];
            String[] randomPW = new String[4];
            boolean pwFound = false;
            int tryNum = 0;

            for (int i = 0; i < randomPW.length; i++) {
                int num = r.nextInt(10);
                randomPW[i] = charactersArr[num];
            }

            /*Anzeige des Passwortes:
            System.out.println("Geheimes PW:");
            System.out.println(randomPW[0] + " " + randomPW[1] + " " + randomPW[2] + " " + randomPW[3]);
             */

            while (pwFound == false) {
                for (int pos1 = 0; pos1 < charactersArr.length; pos1++) {
                    password[0] = charactersArr[pos1];
                    for (int pos2 = 0; pos2 < charactersArr.length; pos2++) {
                        password[1] = charactersArr[pos2];
                        for (int pos3 = 0; pos3 < charactersArr.length; pos3++) {
                            password[2] = charactersArr[pos3];
                            for (int pos4 = 0; pos4 < charactersArr.length; pos4++) {
                                password[3] = charactersArr[pos4];
                                tryNum++;
                                //guck bei jedem PW ob es dem randomPW entspricht:
                                if (password[0].equals(randomPW[0]) && password[1].equals(randomPW[1]) && password[2].equals(randomPW[2]) && password[3].equals(randomPW[3])) {
                                    //System.out.println("Das Passwort lautet:");
                                    //System.out.println(password[0] + " " + password[1] + " " + password[2] + " " + password[3]);
                                    //System.out.println("Anzahl der Versuche: " + tryNum);
                                    sumOfTrys += tryNum; //Anzahl der Versuche werden für Statistik zur Gesamtsumme addiert;
                                    if(tryNum<smallestTryNum){
                                        smallestTryNum = tryNum;
                                    }
                                    if(tryNum>biggestTryNum){
                                        biggestTryNum = tryNum;
                                    }
                                    pwFound = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Im Durchschnitt wurden " + sumOfTrys/1000 + " Versuche gebraucht, um das PW zu knacken.");
        System.out.println("Der schnellste Durchlauf benötigte " + smallestTryNum + " Versuche.");
        System.out.println("Der längste Durchlauf benötigte " + biggestTryNum+ " Versuche.");

    }
}
