
package javaapplication51;


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

    public Animal buscarAnimal(String nombre) {
    return buscarRecursivo(raiz, nombre);
}

private Animal buscarRecursivo(Animal raiz, String nombre) {
    if (raiz == null) {
        return null;
    }
    if (raiz.getNombre().equalsIgnoreCase(nombre)) {
        return raiz;
    }
    Animal encontradoIzquierda = buscarRecursivo(raiz.getIzquierda(), nombre);
    if (encontradoIzquierda != null) {
        return encontradoIzquierda;
    }
    return buscarRecursivo(raiz.getDerecha(), nombre);
}

}

