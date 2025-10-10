package edu.thepower.u1programacionmultiproceso;

import java.io.IOException;
import java.util.Random;

public class U1P03EjecutarSumador {

    private static final String JAVA = "java";
    private static final String CP = "-cp";
    private static final String ClASSPATH = "C:\\Users\\AlumnoAfternoon\\Desktop\\PSP\\PSP\\out\\production\\PSP\\";
    private static final String CLASE = "edu.thepower.u1programacionmultiproceso.U1P03Sumador";

    /*public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(JAVA, CP, ClASSPATH, CLASE, "10", "20");
        try {
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    //ejecutamos cinco procesos en paralelo, los números deben ser aleatorios, (me imagino que habrá que crear 5 ProcessBuilder)
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int nRandom1 = random.nextInt(100);
            int nRandom2 = random.nextInt(100);
            ProcessBuilder pb = new ProcessBuilder(JAVA, CP, ClASSPATH, CLASE, String.valueOf(nRandom1), String.valueOf(nRandom2));
            try {
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                pb.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
