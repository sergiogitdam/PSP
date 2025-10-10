package edu.thepower.u2programacionmultithread;

public class U2P00ThreadTesting {
    public static void main(String[] args) {
        System.out.println("El nombre del Thread es: " + Thread.currentThread().getName());
        System.out.println("El ID del Thread es: " + Thread.currentThread().threadId());
        System.out.println("La prioridad del Thread es: " + Thread.currentThread().getPriority());
        System.out.println("La estado del Thread es: " + Thread.currentThread().getState());
        System.out.println("El grupo del Thread es: " + Thread.currentThread().getThreadGroup());
    }
}
