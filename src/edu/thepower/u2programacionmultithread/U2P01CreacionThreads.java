package edu.thepower.u2programacionmultithread;

//Declaración de Thread mediante implementación de interface Runnable
class ThreadImplements implements Runnable{
    @Override
    public void run() {
        System.out.println("El nombre del Thread es: " + Thread.currentThread().getName());
        System.out.println("El ID del Thread es: " + Thread.currentThread().threadId());
    }
}

//Declaración de Thread mediante extensión de la clase padre Thread
public class U2P01CreacionThreads extends Thread{
    //Este es el código que se ejecuta cuándo lanzo el Thread
    @Override
    public void run() {
        System.out.println("El nombre del Thread es: " + Thread.currentThread().getName());
        System.out.println("El ID del Thread es: " + Thread.currentThread().threadId());
    }

    public static void main(String[] args) {
        //Declaración de Thread en main mediante expresión Lambda e instanciación de éste
        Thread t1 = new Thread(() -> {
            System.out.println("El nombre del Thread es: " + Thread.currentThread().getName());
            System.out.println("El ID del Thread es: " + Thread.currentThread().threadId());
        }, "ThreadLambda");

        //Instanicación del Thread de la clase extendida, herencia
        Thread t2 = new Thread(new U2P01CreacionThreads(), "ThreadHerencia");

        //Instanicación del Thread de implements interface Runnable
        for (int i = 0; i < 5; i++) {
            Thread t3 = new Thread(new ThreadImplements(), "ThreadImplements" + i);
            t3.start();
        }

        //Iniciar los Threads que se ejecutan en paralelo
        t1.start();
        t2.start();
    }
}
