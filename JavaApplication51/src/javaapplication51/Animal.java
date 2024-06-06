
package javaapplication51;


public class Animal {
    private String nombre;
    private String habilidad;
    private int valor;
    private Animal izquierda, derecha;

    public Animal(String nombre, String habilidad, int valor) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public int getValor() {
        return valor;
    }

    public Animal getIzquierda() {
        return izquierda;
    }

    public Animal getDerecha() {
        return derecha;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setIzquierda(Animal izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(Animal derecha) {
        this.derecha = derecha;
    }
    //Visitamos e imprimimos valor
    public void visitar() {
        System.out.print(valor + " ");
                }
}

