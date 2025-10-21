package edu.thepower.u2programacionmultithread;

import java.util.concurrent.locks.ReentrantLock;

public class U2P04CondicionDeCarreraLock {
    private static int contador = 0;
    //los objetos de tipo ReentraLock nos permiten implementar controles sobre
    private static ReentrantLock candado = new ReentrantLock();

    public static void incrementarContador(int num) {
        System.out.println("Entrando en incrementarContador");
        candado.lock();
        try {
            contador += num;
        } finally {
            candado.unlock();
        }

        System.out.println("Saliendo de incrementarContador");
        contador += num;
    }

    public static int getContador() {
        System.out.println("Entrando en getContador");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saliendo de getContador");
        return contador;
    }
    public static void main(String[] args) {
        final int ITERACIONES = 1_000_000;
        final int VALOR = 10;
        //primer thread, bucle que incrementa el valor con la función incrementarContador
        /*
        System.out.println("Incrementando contador");
        Thread threadIncrementador = new Thread(() -> {
            for (int i = 0; i < ITERACIONES; i++) {
                incrementarContador(VALOR);
            }
            System.out.println("Acabando incremento");
        },"ThreadLamba");
        System.out.println("Decrementando contador");
        Thread threadDrecementador = new Thread(() -> {
            for (int i = 0; i < ITERACIONES; i++) {
                incrementarContador(-VALOR);;
            }
            System.out.println("Acabando decremento");
        },"ThreadLamba");

        threadIncrementador.start();
        threadDrecementador.start();

        try {
            threadIncrementador.join();
            threadDrecementador.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El valor final del contador es: " + getContador());
        */
        Thread accesor1 = new Thread(() ->{
            getContador();
        });

        Thread accesor2 = new Thread(() ->{
            getContador();
        });

        accesor1.start();
        accesor2.start();

    }
}