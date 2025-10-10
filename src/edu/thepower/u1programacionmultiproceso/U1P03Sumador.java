package edu.thepower.u1programacionmultiproceso;

public class U1P03Sumador {
    private void sumar(int n1, int n2) {
        int resultado = 0;
        if (n1 > n2) {
            int aux;
            aux = n1;
            n1 = n2;
            n2 = aux;
        }
        for (int i = n1; i <= n2; i++) {
            resultado += i;
        }
        System.out.println("La suma de los números entre " + n1 + " y " + n2 + " es: " + resultado);
    }
    public static void main(String[] args) {
        U1P03Sumador s = new U1P03Sumador();
        s.sumar(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
