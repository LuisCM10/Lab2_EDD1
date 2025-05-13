class Nodo {
    int fila, columna, valor;
    Nodo siguiente;

    Nodo(int f, int c, int v) {
        fila = f;
        columna = c;
        valor = v;
        siguiente = null;
    }
}

public class MatrizDispersaPotencia {
    
    public static Nodo multiplicar(Nodo A, Nodo B, int tam) {
        Nodo resultado = null;
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                int suma = 0;
                for (int k = 0; k < tam; k++) {
                    int a = obtenerValor(A, i, k);
                    int b = obtenerValor(B, k, j);
                    suma += a * b;
                }
                if (suma != 0)
                    resultado = insertarNodo(resultado, i, j, suma);
            }
        }
        return resultado;
    }

    // Este metodo potencia una matriz dispersa A a la potencia n
    public static Nodo elevarPotencia(Nodo A, int n, int tam) {
        Nodo resultado = copiarLista(A);  
        for (int i = 1; i < n; i++) {
            resultado = multiplicar(resultado, A, tam);
        }
        return resultado;
    }

    // Inserta un nodo al final de la lista
    public static Nodo insertarNodo(Nodo cabeza, int fila, int col, int valor) {
        Nodo nuevo = new Nodo(fila, col, valor);
        if (cabeza == null) return nuevo;
        Nodo actual = cabeza;
        while (actual.siguiente != null) actual = actual.siguiente;
        actual.siguiente = nuevo;
        return cabeza;
    }

    // Obtiene el valor de la matriz en [fila][col]
    public static int obtenerValor(Nodo cabeza, int fila, int col) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.fila == fila && actual.columna == col)
                return actual.valor;
            actual = actual.siguiente;
        }
        return 0;
    }

    // Copia una lista
    public static Nodo copiarLista(Nodo original) {
        Nodo copia = null;
        Nodo actual = original;
        while (actual != null) {
            copia = insertarNodo(copia, actual.fila, actual.columna, actual.valor);
            actual = actual.siguiente;
        }
        return copia;
    }

    // Imprime la matriz en formato disperso
    public static void imprimir(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("(" + actual.fila + ", " + actual.columna + ", " + actual.valor + ")");
            actual = actual.siguiente;
        }
    }

    public static void main(String[] args) {
        Nodo matriz = null;
        matriz = insertarNodo(matriz, 0, 0, 1);
        matriz = insertarNodo(matriz, 0, 1, 2);
        matriz = insertarNodo(matriz, 1, 1, 3);
        matriz = insertarNodo(matriz, 2, 2, 4);

        int n = 3; // potencia
        int tam = 3; 

        Nodo resultado = elevarPotencia(matriz, n, tam);
        imprimir(resultado);
    }
}
