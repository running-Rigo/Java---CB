import java.util.Scanner;
 class Taschenrechner_v2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Abfrage von Zahl a, Zahl b und der gewünschten Rechenoperation:
        System.out.println("Bitte gib die erste Zahl ein:");
        int a = sc.nextInt();
        System.out.println("Gib nun die zweite Zahl ein:");
        int b = sc.nextInt();
        System.out.println("Du hast die Zahlen " + a + " und " + b + " eingegeben. Welche Rechenoperation möchtest du durchführen? Gib den Operator (+, -, *, /, %) ein:");
        sc.nextLine();
        char operator = sc.next().charAt(0);

        switch (operator) {
            case '+':
                System.out.println(a + " + " + b + " = " + (a+b));
                break;

            case '-':
                System.out.println(a + " - " + b + " = " + (a-b));
                break;

            case '*':
                System.out.println(a + " * " + b + " = " + (a*b));
                break;

            case '/':
                System.out.println(a + " / " + b + " = " + ((double)a/b));
                break;

            case '%':
                System.out.println(a + " % " + b + " = " + (a%b));
                break;

            default:
                System.out.println("Falscher Operator!");
        }



    }
}
