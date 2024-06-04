
package javaapplication51;


public class Nodo {
    String nombre;
    int habilidad;
    Nodo izquierda, derecha;

    public Nodo(String nombre, int habilidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.izquierda = null;
        this.derecha = null;
    }
}

