package edu.thepower.u2programacionmultithread;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class U2P04Semaforo implements Runnable {
    private static Long tiempoPrueba = System.currentTimeMillis() + 100;
    private static Semaphore semaforo = new Semaphore(5, true);
    private static AtomicInteger contador = new AtomicInteger();
    //ConcurrentHashMap porque es ThreadSave, sólo accede 1 hilo a la vez
    private static Map<String, Integer> mapa = new ConcurrentHashMap<>() {};

    public static void main(String[] args) {
        List<Thread> lista = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            lista.add(new Thread(new U2P04Semaforo(), "thread_" + i));
            lista.get(i).start();
        }
        for(Thread h: lista) {
            try {
                h.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Uso del semáforo por los threads");
        for(String n: mapa.keySet()) {
            System.out.println("El Thread " + n + " ha usado el semáforo " + mapa.get(n) + " veces.");
        }
    }

    @Override
    public void run() {
        String nombre = "[" + Thread.currentThread().getName() + "]";
        while(System.currentTimeMillis() < tiempoPrueba) {
            try {
                semaforo.acquire();
                mapa.put(nombre, mapa.getOrDefault(nombre, 0) + 1);
                System.out.println("Adquirido semáforo número: " + contador.incrementAndGet());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (contador.get() > 5) {
                throw new RuntimeException("Semáforo sobrepasado");
            }
            contador.decrementAndGet();
            semaforo.release();
            System.out.println("Semáforo liberado");
        }
    }
}
