import java.util.Scanner;

public class BalanceadorSimbolos {
       
    private static class Pila {
        private int tope;
        private char[] elementos;
        
        public Pila() {
            this.tope = -1;
            this.elementos = new char[100]; // Tamaño máximo de la pila
        }
        
        public void push(char c) {
            tope++;
            elementos[tope] = c;
        }
        
        public char pop() {
            if (tope == -1) {
                throw new RuntimeException("Pila vacía");
            }
            char valor = elementos[tope];
            tope--;
            return valor;
        }
        
        public boolean tieneElementos() {
            return tope != -1;
        }
    }
    
    public static boolean estaBalanceada(String expresion) {
        Pila pila = new Pila();
        
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            
            // Si es símbolo de apertura, lo agregamos a la pila
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            } 
            // Si es símbolo de cierre
            else if (c == ')' || c == ']' || c == '}') {
                // Verificamos si la pila tiene elementos
                if (!pila.tieneElementos()) {
                    return false;
                }
                
                char top = pila.pop();
                
                // Verificamos si coinciden los símbolos
                if (!coinciden(top, c)) {
                    return false;
                }
            }    
        }
        
        // Verificamos si la pila tiene elementos
        return !pila.tieneElementos();
    }
    
    private static boolean coinciden(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
               (apertura == '[' && cierre == ']') ||
               (apertura == '{' && cierre == '}');
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese una expresión para verificar balanceo:");
        System.out.println("Símbolos válidos: (), [], {}");
        String expresion = scanner.nextLine();
        
        if (expresion.isEmpty()) {
            System.out.println("Error: No se ingresó ninguna expresión.");
            return;
        }
        
        if (estaBalanceada(expresion)) {
            System.out.println("Balanceada");
        } else {
            System.out.println("No balanceada");
        }
        
        scanner.close();
    }
}