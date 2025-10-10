package edu.thepower.u2programacionmultithread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class U2P02ContadorVocalesThread implements Runnable{

    //constructor
    public U2P02ContadorVocalesThread(char vocal, String archivo) {
        this.vocal = vocal;
        this.archivo = archivo;
    }

    //ejecución del hilo
    @Override
    public void run() {
        //contar vocales
    }

    public static void main(String[] args) {
    }

    //en el paréntesis después de implements se meten los dos atributos del constructor por defecto con los argumentos vocal y fichero Thread t3 = new Thread(new ThreadImplements(), "ThreadImplements" + i);
}
