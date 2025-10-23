package edu.thepower.u2programacionmultithread;

class CuentaCorriente {
    private float saldo;

    public CuentaCorriente(float saldo) {
        this.saldo = saldo;
    }


    public void retirar(float cantidad) {
        if(saldo >= cantidad) {
            saldo -= cantidad;
        }
    }

    public void ingresar(float cantidad) {
        saldo += cantidad;
    }

    public float getSaldo() {
        return saldo;
    }
}
public class U2P05DeadlockCuentaCorriente {
    public static void transferencia(CuentaCorriente origen, CuentaCorriente destino, float cantidad) {
        CuentaCorriente cuenta1 = origen.hashCode() < destino.hashCode() ? origen: destino;
        CuentaCorriente cuenta2 = origen.hashCode() < destino.hashCode() ? destino: origen;
        synchronized (cuenta1) {
            synchronized (cuenta2) {
                origen.retirar(cantidad);
                destino.ingresar(cantidad);
            }
        }
    }

    public static void main(String[] args) {
        CuentaCorriente cc1 = new CuentaCorriente(100_000);
        CuentaCorriente cc2 = new CuentaCorriente(100_000);
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1_000; i++) {
                transferencia(cc1, cc2, 10);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1_000; i++) {
                transferencia(cc2, cc1, 20);
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
        System.out.println("Saldo cc1: " + cc1.getSaldo());
        System.out.println("Saldo cc2: " + cc2.getSaldo());
        System.out.println("Saldo total cuentas: " + (cc1.getSaldo() + cc2.getSaldo()));
    }
}
