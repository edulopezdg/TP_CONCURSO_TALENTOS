package javaapplication51;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Concurso {

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        Scanner scanner = new Scanner(System.in);
        cargarAnimales(arbol);

        while (true) {
            System.out.println("\nConcurso de talentos del Bosque Binario");
            System.out.println("1. Jugar contra la máquina");
            System.out.println("2. Jugar contra otro usuario");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    jugarContraMaquina(scanner, arbol);
                    break;
                case 2:
                    jugarContraUsuario(scanner, arbol);
                    break;
                case 3:
                    scanner.close();
                    System.out.println("Gracias por participar en el concurso de talentos del Bosque Binario.");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private static void jugarContraMaquina(Scanner scanner, ArbolBinario arbol) {
        int puntosJugador = 0;
        int puntosMaquina = 0;

        puntosJugador = juegaHumano(puntosJugador, scanner, arbol);

        System.out.println("La máquina selecciona 3 animales:");
        puntosMaquina = juegaMaquina(puntosMaquina, arbol);

        System.out.println("Puntos del Jugador: " + puntosJugador);
        System.out.println("Puntos de la Máquina: " + puntosMaquina);

        if (puntosJugador > puntosMaquina) {
            System.out.println("Ganador: Jugador");
        } else if (puntosMaquina > puntosJugador) {
            System.out.println("Ganador: Máquina");
        } else {
            System.out.println("Empate");
        }
    }

    private static void jugarContraUsuario(Scanner scanner, ArbolBinario arbol) {
        int puntosJugador1 = 0;
        int puntosJugador2 = 0;

        puntosJugador1 = juegaHumano(puntosJugador1, scanner, arbol);
        puntosJugador2 = juegaHumano(puntosJugador2, scanner, arbol);

        System.out.println("Puntos del Jugador 1: " + puntosJugador1);
        System.out.println("Puntos del Jugador 2: " + puntosJugador2);

        if (puntosJugador1 > puntosJugador2) {
            System.out.println("Ganador: Jugador 1");
        } else if (puntosJugador2 > puntosJugador1) {
            System.out.println("Ganador: Jugador 2");
        } else {
            System.out.println("Empate");
        }
    }

    public static Animal[] cargarAnimales(ArbolBinario arbol) {

        Random random = new Random(100);

        // Lista de 20 animales predefinidos
        Animal[] animales = {
    new Animal("leon", "Grrrr Ruje", random.nextInt(100)),
    new Animal("tigre", "Grrrr Corre rápido", random.nextInt(100)),
    new Animal("elefante", "Prrr Trompetea", random.nextInt(100)),
    new Animal("gato", "Miau Maulla", random.nextInt(100)),
    new Animal("perro", "Guau Ladra", random.nextInt(100)),
    new Animal("lobo", "Auuuu Aúlla", random.nextInt(100)),
    new Animal("zorro", "Ji, ji, ji Escabúllase", random.nextInt(100)),
    new Animal("conejo", "Fui, fui Salta alto", random.nextInt(100)),
    new Animal("oso", "Gruu Pesca", random.nextInt(100)),
    new Animal("jirafa", "Mmmm Alcanza hojas altas", random.nextInt(100)),
    new Animal("rinoceronte", "Buf Embiste", random.nextInt(100)),
    new Animal("hipopotamo", "Bof Bucea", random.nextInt(100)),
    new Animal("canguro", "Boing Boxea", random.nextInt(100)),
    new Animal("koala", "Gruñido Trepa", random.nextInt(100)),
    new Animal("panda", "Grrr Rueda", random.nextInt(100)),
    new Animal("caballo", "Relincho Galopea", random.nextInt(100)),
    new Animal("vaca", "Muuu Muge", random.nextInt(100)),
    new Animal("cerdo", "Oinc Revolcarse en el lodo", random.nextInt(100)),
    new Animal("oveja", "Beee Bala", random.nextInt(100)),
    new Animal("gallina", "Coc, coc, coc Pone huevos", random.nextInt(100))
};


        // Inserción de los 20 animales
        for (Animal animal : animales) {
            arbol.insertarAnimal(animal);
        }

        return animales;
    }

    public static int juegaHumano(int puntosJugador, Scanner scanner, ArbolBinario arbol) {

        System.out.println("Jugador selecciona 3 animales:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del animal " + (i + 1) + ": ");
            String nombreJugador = scanner.nextLine().toLowerCase();
            Animal animalJugador = arbol.buscarAnimalPorNombre(nombreJugador);
            if (animalJugador != null) {
                System.out.println("Habilidad de " + animalJugador.getNombre() + ": " + animalJugador.getHabilidad());
                puntosJugador += animalJugador.getValor();
            } else {
                System.out.println("Animal no encontrado, por favor ingrese otro animal.");
                i--; // Reintentar la misma iteración
            }
        }
        return puntosJugador;
    }

  public static int juegaMaquina(int puntosMaquina, ArbolBinario arbol) {
    Random random = new Random();
    List<Integer> valores = arbol.obtenerValores();
    List<Animal> animalesSeleccionados = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
        Animal animalSeleccionado;
        do {
            int indice = random.nextInt(valores.size());
            int valorSeleccionado = valores.get(indice);
            animalSeleccionado = arbol.buscarAnimalPorValor(valorSeleccionado);
        } while (animalesSeleccionados.contains(animalSeleccionado));
        
        animalesSeleccionados.add(animalSeleccionado);
        System.out.println("Habilidad de " + animalSeleccionado.getNombre() + ": " + animalSeleccionado.getHabilidad());
        puntosMaquina += animalSeleccionado.getValor();
    }
    return puntosMaquina;
}
    
    
}
