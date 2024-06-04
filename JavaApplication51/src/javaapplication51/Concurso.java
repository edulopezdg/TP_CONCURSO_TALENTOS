package javaapplication51;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Concurso {

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        Scanner scanner = new Scanner(System.in);

        // Lista de 20 animales predefinidos
        Animal[] animales = {
            new Animal("Leon", "Grrrr Ruje", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Tigre", "Grrrr Corre rápido", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Elefante", "Prrr Trompetea", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Gato", "Miau Maulla", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Perro", "Guau Ladra", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Lobo", "Auuuu Aúlla", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Zorro", "Ji, ji, ji Escabúllase", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Conejo", "Fui, fui Salta alto", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Oso", "Gruu Pesca", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Jirafa", "Mmmm Alcanza hojas altas", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Rinoceronte", "Buf Embiste", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Hipopotamo", "Bof Bucea", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Canguro", "Boing Boxea", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Koala", "Gruñido Trepa", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Panda", "Grrr Rueda", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Caballo", "Relincho Galopea", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Vaca", "Muuu Muge", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Cerdo", "Oinc Revolcarse en el lodo", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Oveja", "Beee Bala", ThreadLocalRandom.current().nextInt(1, 101)),
            new Animal("Gallina", "Coc, coc, coc Pone huevos", ThreadLocalRandom.current().nextInt(1, 101))
        };

        // Inserción de los 20 animales
        for (Animal animal : animales) {
            arbol.insertarAnimal(animal);
        }

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

        System.out.println("Jugador selecciona 3 animales:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del animal " + (i + 1) + ": ");
            String nombreJugador = scanner.nextLine();
            Animal animalJugador = arbol.buscarAnimal(nombreJugador);
            if (animalJugador != null) {
                System.out.println("Habilidad de " + animalJugador.getNombre() + ": " + animalJugador.getHabilidad());
                puntosJugador += animalJugador.getValor();
            } else {
                System.out.println("Animal no encontrado, por favor ingrese otro animal.");
                i--; // Reintentar la misma iteración
            }
        }

        System.out.println("La máquina selecciona 3 animales:");
        for (int i = 0; i < 3; i++) {
            String nombreMaquina = "AnimalMaquina" + (i + 1);
            int habilidadMaquina = ThreadLocalRandom.current().nextInt(1, 101); // Generar habilidad aleatoria entre 1 y 100
            System.out.println("Habilidad de " + nombreMaquina + ": " + habilidadMaquina);
            puntosMaquina += habilidadMaquina;
        }

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

        System.out.println("Jugador 1 selecciona 3 animales:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del animal " + (i + 1) + ": ");
            String nombreJugador1 = scanner.nextLine();
            Animal animalJugador1 = arbol.buscarAnimal(nombreJugador1);
            if (animalJugador1 != null) {
                System.out.println("Habilidad de " + animalJugador1.getNombre() + ": " + animalJugador1.getHabilidad());
                puntosJugador1 += animalJugador1.getValor();
            } else {
                System.out.println("Animal no encontrado, por favor ingrese otro animal.");
                i--; // Reintentar la misma iteración
            }
        }

        System.out.println("Jugador 2 selecciona 3 animales:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del animal " + (i + 1) + ": ");
            String nombreJugador2 = scanner.nextLine();
            Animal animalJugador2 = arbol.buscarAnimal(nombreJugador2);
            if (animalJugador2 != null) {
                System.out.println("Habilidad de " + animalJugador2.getNombre() + ": " + animalJugador2.getHabilidad());
                puntosJugador2 += animalJugador2.getValor();
            } else {
                System.out.println("Animal no encontrado, por favor ingrese otro animal.");
                i--; // Reintentar la misma iteración
            }
        }

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
}
