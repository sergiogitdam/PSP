package edu.thepower.repasoprimerexamen;

import java.util.Random;

class CuentaCorriente {
    private float saldo;

    public CuentaCorriente(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    //Se podría crear una clase extra que devolviese un boolean, pero lo hacemos así para simplificarlo
    public void retirarSaldo(float importe) {
        if(importe < saldo) {
            this.saldo -= importe;
        }
    }

    public void igresarSaldo(float importe) {
        this.saldo += importe;
    }
}

public class DeadLockCuentaCorriente2 {

    public static synchronized  void trasnferir(CuentaCorriente origen, CuentaCorriente destino, float importe) {
        CuentaCorriente primeraCuentaABloquear = origen.hashCode() < destino.hashCode() ? origen : destino;
        CuentaCorriente segundaCuentaABloquear = origen.hashCode() < destino.hashCode() ? destino : origen;
        synchronized (primeraCuentaABloquear) {
            synchronized (segundaCuentaABloquear) {
                origen.retirarSaldo(importe);
                destino.igresarSaldo(importe);
            }
        }
    }

    public static void main(String[] args) {
        //Esto es sólo para ver lo de los enteros
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            System.out.println("Número aleatorio: " + rand.nextInt(1, 11));
        }
        CuentaCorriente cc1 = new CuentaCorriente(100_000);
        CuentaCorriente cc2 = new CuentaCorriente(100_000);

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1_000; i++) {
                trasnferir(cc1, cc2, 10);
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1_000; i++) {
                trasnferir(cc2, cc1, 20);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Saldo final cuenta 1: " +  cc1.getSaldo());
        System.out.println("Saldo final cuenta 2: " +  cc2.getSaldo());
        System.out.println("Saldo final cuenta total: " +  (cc1.getSaldo() + cc2.getSaldo()));
    }
}
