public class EinMalEinsV3 {
    public static void main(String[] args) {

        for (int i=1; i<11; i++){ // j * 1-10 = alle Reihen (1er, 2er, ...10er Reihe werden durchgerechnet)
            for (int j=1; j<11; j++) { // 1-10 * i = für Reihe i (zb. 3er-Reihe) werden alle Rechnungen durchgeführt
                System.out.println(j + " x " + i + " = " + (j * i));
            }
        }
    }
}

//alle Reihen von 1-10. Nutze dafür entweder verschachtelte Schleifen oder eine Schleife und ein IF/ELSE.
