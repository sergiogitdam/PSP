package edu.thepower.u1programacionmultiproceso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class U1P04EjecutarContadorVocal {
    private static final String JAVA = "java";
    private static final String CP = "-cp";
    private static final String ClASSPATH = "C:\\Users\\AlumnoAfternoon\\Desktop\\PSP\\PSP\\out\\production\\PSP\\";
    private static final String CLASE = "edu.thepower.u1programacionmultiproceso.U1P04ContadorVocal";
    private static final String[] VOCALES = {"a", "e", "i", "o", "u"};
    private static final String ARCHIVO = "./resources/vocales.txt";
    private static final String SALIDA = "./salida/";
    private static final String EXTENSION = ".txt";
    public static void main(String[] args) {
        List<Process> procesos = new ArrayList<>();
        //Creación del directorio Salida
        File directorioSalida = new File("salida");
        if (directorioSalida.mkdir()) {
            System.out.println("El directorio de salida se ha creado correctamente");
        } else {
            System.err.println("El directorio de salida ya existe");
        }

        String[] vocales = {"a", "e", "i", "o", "u"};
        for (int i = 0; i < VOCALES.length ; i++) {
            ProcessBuilder pb = new ProcessBuilder(JAVA, CP, ClASSPATH, CLASE, VOCALES[i], ARCHIVO);
            try {
                pb.redirectOutput(new File(SALIDA + VOCALES[i] + EXTENSION));
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                procesos.add(pb.start());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("FInalizado el conteo de vocales");
        for (Process proceso : procesos) {
            try {
                proceso.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //Mostramos por consola la cantidad de vocales totales y la cantidad de voacles por vocal
        int contador = 0;
        for (int j = 0; j < VOCALES.length; j++) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(SALIDA + VOCALES[j] + EXTENSION));
                int n = Integer.parseInt(br.readLine());
                System.out.println("El número de vocales es " + VOCALES[j] + " es " + n);
                contador += n;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.err.println("El archivo no contenía un número");
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Número de vocales totales en el archivo: " + contador);
    }
}
