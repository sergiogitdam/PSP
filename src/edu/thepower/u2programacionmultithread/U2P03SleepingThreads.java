package edu.thepower.u2programacionmultithread;

public class U2P03SleepingThreads implements Runnable {
    @Override
    public void run() {
        //Dormir el Thread de manera indfeinida con Thread.sleep(tiempo de siesta)
        String nombre = "[" + Thread.currentThread().getName() + "]";
        try {
            System.out.println(nombre + " iniciando el despertar del Thread");
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println(nombre + " despertando(1)");
        }

        //Mientras nadie le interrumpa no hace nada
        while (!Thread.interrupted()){}
        System.out.println(nombre + " despertando(2)");

    }
    public static void main(String[] args) {
        Thread thread = new Thread(new U2P03SleepingThreads(), "sleeping thread");
        thread.start();
        String nombreThread = "[" + Thread.currentThread().getName() + "]";

        for (int i = 0; i < 2; i++) {
            System.out.println(nombreThread + " sleeping thread 5 sec");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Despertando al thread");
            thread.interrupt();
        }
    }
}
