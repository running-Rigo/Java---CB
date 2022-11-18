public class CleanThis {
    public static void main(String[] args) {
        int age = 18;
        boolean withAdult = true;
        boolean isVIP = true;
        if (age>=16 || age>=4 && withAdult) {           //Eintritt laut Alter erlaubt?
            if(age>=18) //Bändchen-Vergabe laut Alter
                System.out.println("Grünes Band");
            else if(age>=16)
                System.out.println("Gelbes Band");
            else
                System.out.println("Rotes Band");
            //ViP-Bändchen Vergabe
            if (isVIP)
                System.out.println("Goldenes Band dazu!");
        }
        else                                         //falls Alter keinen Zutritt erlaubt
            System.out.println("Kein Zutritt.");
    }
}

