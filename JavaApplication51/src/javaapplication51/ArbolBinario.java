package javaapplication51;

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

    // Método para buscar un valor en el árbol
    public Animal buscarAnimalPorAnimal(Animal animal) {
        return buscarRecursivo(raiz, animal.getValor());
    }

    private Animal buscarRecursivo(Animal raiz, int valor) {
        if (raiz == null) {
            return null;
        }
        if (raiz.getValor() == valor) {
            return raiz;
        }
        return valor < raiz.getValor()
                ? buscarRecursivo(raiz.getIzquierda(), valor)
                : buscarRecursivo(raiz.getDerecha(), valor);

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

    // Recorrido de un árbol binario en preOrden
    public void preOrden() {
        preOrdenX(raiz);
    }

    public void preOrdenX(Animal raiz) {
        if (raiz != null) {
            raiz.visitar();
            preOrdenX(raiz.getIzquierda());
            preOrdenX(raiz.getDerecha());
        }
    }
// Recorrido de un árbol binario en inOrdenX

    public void inOrden() {
        inOrdenX(raiz);
    }

    public static void inOrdenX(Animal raiz) {
        if (raiz != null) {
            inOrdenX(raiz.getIzquierda());
            raiz.visitar();
            inOrdenX(raiz.getDerecha());
        }
    }
// Recorrido de un árbol binario en postOrdenX

    public void postOrden() {
        postOrdenX(raiz);
    }

    public static void postOrdenX(Animal raiz) {
        if (raiz != null) {
            postOrdenX(raiz.getIzquierda());
            postOrdenX(raiz.getDerecha());
            raiz.visitar();
        }
    }

    // Método para contar los elementos del árbol
    public int contarElementos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(Animal raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + contarRecursivo(raiz.getIzquierda()) + contarRecursivo(raiz.getDerecha());
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
        if (raiz == animal) {
            return profundidad; // Se encontró el animal, devuelve la profundidad actual
        }
        // Busca en los subárboles izquierdo y derecho
        int profundidadIzquierda = profundidadRecursiva(raiz.getIzquierda(), animal, profundidad + 1);
        int profundidadDerecha = profundidadRecursiva(raiz.getDerecha(), animal, profundidad + 1);
        // Devuelve la profundidad máxima encontrada en ambos subárboles
        return Math.max(profundidadIzquierda, profundidadDerecha);
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
}
