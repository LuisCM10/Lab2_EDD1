class Nodo {
    int valor;
    Nodo anterior;
    Nodo siguiente;
    
    public Nodo(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.siguiente = null;
    }
}

class ListaDoblementeEnlazada {
    Nodo cabeza;
    
    public ListaDoblementeEnlazada() {
        this.cabeza = null;
    }
    
    // Método para verificar si un valor existe en la lista
    public boolean contiene(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.valor == valor) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    // Método para eliminar un nodo con un valor específico
    public void eliminar(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.valor == valor) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    cabeza = actual.siguiente;
                }
                
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                }
                return;
            }
            actual = actual.siguiente;
        }
    }
    
    // Método para insertar un valor en orden ascendente
    public void insertarOrdenado(int valor) {
        Nodo nuevo = new Nodo(valor);
        
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        
        // se inserta al inicio
        if (valor < cabeza.valor) {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
            return;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.valor < valor) {
            actual = actual.siguiente;
        }
        
        nuevo.siguiente = actual.siguiente;
        nuevo.anterior = actual;
        
        if (actual.siguiente != null) {
            actual.siguiente.anterior = nuevo;
        }
        
        actual.siguiente = nuevo;
    }
    
    // Método para destruir la lista 
    public void destruir() {
        cabeza = null;
    }
}

class ListaCircular {
    Nodo cabeza;
    
    public ListaCircular() {
        this.cabeza = null;
    }
    
    // Método para eliminar un nodo con un valor específico
    public void eliminar(int valor) {
        if (cabeza == null) return;
        
        Nodo actual = cabeza;
        Nodo previo = null;
        
        do {
            if (actual.valor == valor) {
                if (previo != null) {
                    previo.siguiente = actual.siguiente;
                    if (actual == cabeza) {
                        cabeza = previo.siguiente;
                    }
                } else {
                    // Si es el unico nodo
                    if (actual.siguiente == cabeza) {
                        cabeza = null;
                    } else {
                        Nodo temp = cabeza;
                        while (temp.siguiente != cabeza) {
                            temp = temp.siguiente;
                        }
                        temp.siguiente = cabeza.siguiente;
                        cabeza = cabeza.siguiente;
                    }
                }
                return;
            }
            previo = actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
    }
    
    // Método para destruir la lista
    public void destruir() {
        cabeza = null;
    }
}

public class Main {
    public static void procesarListas(ListaDoblementeEnlazada PTR1, ListaCircular PTR2) {
        if (PTR2.cabeza == null) return;
        
        Nodo actual = PTR2.cabeza;
        do {
            int valor = actual.valor;
            if (PTR1.contiene(valor)) {
                PTR1.eliminar(valor);
                PTR2.eliminar(valor);
            } else {
                PTR1.insertarOrdenado(valor);
            }
            actual = actual.siguiente;
        } while (actual != PTR2.cabeza);
        
        PTR2.destruir();
    }
    
    public static void main(String[] args) {
        // Ejemplo de uso
        ListaDoblementeEnlazada PTR1 = new ListaDoblementeEnlazada();
        PTR1.insertarOrdenado(1);
        PTR1.insertarOrdenado(3);
        PTR1.insertarOrdenado(5);
        
        ListaCircular PTR2 = new ListaCircular();
        Nodo nodo1 = new Nodo(2);
        Nodo nodo2 = new Nodo(3);
        Nodo nodo3 = new Nodo(4);
        
        PTR2.cabeza = nodo1;
        nodo1.siguiente = nodo2;
        nodo2.siguiente = nodo3;
        nodo3.siguiente = nodo1;
        
        procesarListas(PTR1, PTR2);
        
        
        System.out.println("Lista PTR1 después del procesamiento:");
        Nodo actual = PTR1.cabeza;
        while (actual != null) {
            System.out.print(actual.valor + " ");
            actual = actual.siguiente;
        }
        System.out.println();
        
        System.out.println("PTR2 es nula? " + (PTR2.cabeza == null));
    }
}