package MetodosArbol;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {

    private Animal raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertarAnimal(Animal animal) {
        raiz = insertarRecursivo(raiz, animal);
    }

    private Animal insertarRecursivo(Animal raiz, Animal animal) {
        if (raiz == null) {
            raiz = animal;
            return raiz;
        }

        if (animal.getValor() < raiz.getValor()) {
            raiz.setIzquierda(insertarRecursivo(raiz.getIzquierda(), animal));
        } else if (animal.getValor() > raiz.getValor()) {
            raiz.setDerecha(insertarRecursivo(raiz.getDerecha(), animal));
        }
        return raiz;
    }


    public Animal buscarAnimalPorNombre(String nombre) {
        return buscarRecursivoPorNombre(raiz, nombre);
    }

    private Animal buscarRecursivoPorNombre(Animal raiz, String nombre) {
        if (raiz == null) {
            return null;
        }
        if (raiz.getNombre().equals(nombre)) {
            return raiz;
        }
        Animal encontrado = buscarRecursivoPorNombre(raiz.getIzquierda(), nombre);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarRecursivoPorNombre(raiz.getDerecha(), nombre);
    }

    public Animal buscarAnimalPorValor(int valor) {
        return buscarRecursivoPorValor(raiz, valor);
    }

    private Animal buscarRecursivoPorValor(Animal raiz, int valor) {
        if (raiz == null) {
            return null;
        }
        if (raiz.getValor() == valor) {
            return raiz;
        }
        Animal encontrado = buscarRecursivoPorValor(raiz.getIzquierda(), valor);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarRecursivoPorValor(raiz.getDerecha(), valor);
    }

    

    // Método para obtener todos los valores del árbol
    public List<Integer> obtenerValores() {
        List<Integer> valores = new ArrayList<>();
        obtenerValoresRecursivo(raiz, valores);
        return valores;
    }

    private void obtenerValoresRecursivo(Animal raiz, List<Integer> valores) {
        if (raiz != null) {
            obtenerValoresRecursivo(raiz.getIzquierda(), valores);
            valores.add(raiz.getValor());
            obtenerValoresRecursivo(raiz.getDerecha(), valores);
        }
    }

    //Obtener todos los animales del arbol
    public List<Animal> obtenerAnimales() {
        List<Animal> animales = new ArrayList<>();
        obtenerAnimalesRecursivo(raiz, animales);
        return animales;
    }

    private void obtenerAnimalesRecursivo(Animal raiz, List<Animal> animales) {
        if (raiz != null) {
            obtenerAnimalesRecursivo(raiz.getIzquierda(), animales);
            animales.add(raiz);
            obtenerAnimalesRecursivo(raiz.getDerecha(), animales);
        }
    }

    //Metodo de profundidad
   public int profundidadAnimal(Animal animal) {
    return profundidadRecursiva(raiz, animal, 0);
}

private int profundidadRecursiva(Animal raiz, Animal animal, int profundidad) {
    if (raiz == null) {
        return -1; // Si no se encuentra el animal, devuelve -1
    }
    if (raiz.equals(animal)) {
        return profundidad; // Se encontró el animal, devuelve la profundidad actual
    }
    int profundidadIzquierda = profundidadRecursiva(raiz.getIzquierda(), animal, profundidad + 1);
    if (profundidadIzquierda != -1) { // Si se encontró en el subárbol izquierdo
        return profundidadIzquierda;
    }
    // Si no se encontró en el subárbol izquierdo, buscar en el derecho
    return profundidadRecursiva(raiz.getDerecha(), animal, profundidad + 1);
}
//Metodo de altura

public int alturaAnimal(Animal animal) {
    return alturaRecursiva(animal);
}

private int alturaRecursiva(Animal nodo) {
    if (nodo == null) {
        return -1; // La altura de un árbol vacío es -1
    }
    int alturaIzquierda = alturaRecursiva(nodo.getIzquierda());
    int alturaDerecha = alturaRecursiva(nodo.getDerecha());
    return Math.max(alturaIzquierda, alturaDerecha) + 1;
}

    //Imprimir arbol
    
    public void imprimirArbolGrafico() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        System.out.println("Imprimiendo el árbol:");
        imprimirGraficoRecursivo(raiz, "", true);
    }

    private void imprimirGraficoRecursivo(Animal animal, String prefijo, boolean esIzquierdo) {
        if (animal != null) {
            System.out.println(prefijo + (esIzquierdo ? "├── " : "└── ") + animal.getNombre());
            imprimirGraficoRecursivo(animal.getIzquierda(), prefijo + (esIzquierdo ? "│   " : "    "), true);
            imprimirGraficoRecursivo(animal.getDerecha(), prefijo + (esIzquierdo ? "│   " : "    "), false);
        }
    }
    
    public void imprimirArbol() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(Animal nodo, int nivel) {
        if (nodo != null) {
            imprimirRecursivo(nodo.getIzquierda(), nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    "); // Ajusta el espaciado según sea necesario para una mejor visualización
            }
            if (nodo.getNombre().isEmpty()) {
                System.out.print("[ ]");
            } else {
                System.out.print("[" + nodo.getNombre() + " (" + nodo.getValor() + ")]");
            }
            System.out.println();
            imprimirRecursivo(nodo.getDerecha(), nivel + 1);
        }
    }
}
