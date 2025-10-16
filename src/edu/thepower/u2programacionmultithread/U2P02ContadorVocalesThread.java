package edu.thepower.u2programacionmultithread;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class U2P02ContadorVocalesThread implements Runnable{
    //atributos de la clase
    private static final Map<Character, Character> VOCALES;
    static {
        VOCALES = new HashMap();
        VOCALES.put('a', 'á');
        VOCALES.put('e', 'é');
        VOCALES.put('i', 'í');
        VOCALES.put('o', 'ó');
        VOCALES.put('u', 'ú');
    }
    private char vocal;
    private String archivo;
    private String salida;

    //constructor
    public U2P02ContadorVocalesThread(char vocal, String archivo, String salida) {
        this.vocal = vocal;
        this.archivo = archivo;
        this.salida = salida;
    }

    //ejecución del hilo
    @Override
    public void run() {
        //contar vocales
        System.out.println("[" + Thread.currentThread().getName() + "] iniciando  cuenta vocal " + vocal);
        int contador = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.toLowerCase();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == vocal || line.charAt(i) == VOCALES.get(vocal)) {
                        contador++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("archivo no encontrado: " + archivo);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en lectura de archivo: " + archivo);
            throw new RuntimeException(e);
        }
        System.out.println("[" + Thread.currentThread().getName() + "] finalizando  cuenta vocal " + vocal + ", contador de vocales: " + contador);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(salida + vocal + ".txt"))) {
            out.write(String.valueOf(contador));
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + vocal + ".txt");
        }
    }

    public static void main(String[] args) {
        //Coclección para almacenar las referencias a los Threads
        List<Thread> threads = new ArrayList();
        final String ARCHIVO_ENTRADA = "./resources/vocales.txt";
        final String DIR_SALIDA = "./salida/";

        //Creación del directorio Salida
        File directorioSalida = new File("salida");
        if (directorioSalida.mkdir()) {
            System.out.println("El directorio de salida se ha creado correctamente");
        } else {
            System.err.println("El directorio de salida ya existe");
            for (File elemento: directorioSalida.listFiles()) {
                elemento.delete();
            }
        }
        for (char v: VOCALES.keySet()) {
            Thread thread = new Thread(new U2P02ContadorVocalesThread(v, ARCHIVO_ENTRADA, DIR_SALIDA));
            //Añadir hilo a la colección
            threads.add(thread);
            thread.start();
        }
        //Pausa para que luego se puedan leer las vocales, cómo el wait for en los procesos
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //Mostramos por consola la cantidad de vocales totales y la cantidad de vocales por vocal
        int contador = 0;
        for (char v: VOCALES.keySet()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("./salida/" + v + ".txt"));
                int n = Integer.parseInt(br.readLine());
                System.out.println("El número de vocales es " + v + " es " + n);
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
