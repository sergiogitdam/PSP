package edu.thepower.u1programacionmultiproceso;

import java.io.*;

public class U1P02EjecutarProcesoJava {

    private static final String JAVA = "java";
    private static final String VERSION = "-version";

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder(JAVA, VERSION);
        //1. Redirect hace que el proceso hijo redirige la consola del proceso padre
        //Output hace redirija los típicos mensajes
        //pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        //Output hace redirija los típicos errores y las cosas de la VM de Java, cómo en estee caso java -version
        //pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        //2. Mediante Buffer (canal entre el proceso que se lanza y el nuevo que se ejecuta)
        /*try {
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            /*br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            //System.err muestra en rojo, sirve para mostrar errores
            System.err.println("Error al iniciar el proceso");
            e.printStackTrace();
        }*/
        //3. Volcar salida a fichero
        pb.redirectOutput(new File("./resources/salida.txt"));
        pb.redirectError(new File("./resources/error.txt"));
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
