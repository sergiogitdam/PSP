package edu.thepower.u2programacionmultithread;

public class U2P05DeadLockCorregido {
    //implementar Runnubale, introducir el código de los thread en run(), sustituir Thread t1 y t2 por la clase prinicpal
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();
    public static void main(String[] args) {
        //El Thread t1 bloquea los objetos en este orden: obj1 y obj2
        Thread t1 = new Thread(() -> {
            synchronized (obj1){
                System.out.println("t1: dentro de bloque obj1");
                synchronized (obj2){
                    System.out.println("t1: dentro de bloque obj2");
                }
            }
        });
        //El Thread t2 bloquea los objetos en este orden: obj2 y obj1
        Thread t2 = new Thread(() -> {
            synchronized (obj1){
                System.out.println("t2: dentro de bloque obj1");
                synchronized (obj2){
                    System.out.println("t2: dentro de bloque obj2");
                }
            }
        });
        t1.start();
        t2.start();
    }
}