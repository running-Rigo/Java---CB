import java.util.Scanner;
public class TaschenrechnerV3 {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        char operator = 'n';

        //Rechner Abfrage läuft so oft/solange bis 'x' eingegeben wird:
        while (operator != 'x') {
            //Zuerst nur Operator-Abfrage:
            System.out.println("Gib den Operator (+, -, *, /, %) ein:");
            operator = sn.next().charAt(0);
            //Überprüfung ob Operator gültig, bevor Zahlen abgefragt werden:
            if(operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%'){
                //Abfrage von Zahl a und Zahl b
                System.out.println("Bitte gib die erste Zahl ein:");
                int a = sn.nextInt();
                System.out.println("Gib nun die zweite Zahl ein:");
                int b = sn.nextInt();

                //Ausgabe der Rechnung + Ergebnis
                switch (operator) {
                    case '+':
                        System.out.println(a + " + " + b + " = " + (a + b));
                        break;

                    case '-':
                        System.out.println(a + " - " + b + " = " + (a - b));
                        break;

                    case '*':
                        System.out.println(a + " * " + b + " = " + (a * b));
                        break;

                    case '/':
                        System.out.println(a + " / " + b + " = " + ((double) a / b));
                        break;

                    case '%':
                        System.out.println(a + " % " + b + " = " + (a % b));
                        break;
                }

            }

        }

    }
}
