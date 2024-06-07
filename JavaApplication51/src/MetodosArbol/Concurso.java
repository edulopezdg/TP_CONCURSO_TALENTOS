package MetodosArbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import MetodosArbol.ArbolBinario;

public class Concurso {

    static Animal profundo = null;

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        Scanner scanner = new Scanner(System.in);
        cargarAnimales(arbol);

        System.out.println("===========================================");
        System.out.println("==CONCURSO DE TALENTOS DEL BOSQUE BINARIO==");
        System.out.println("===========================================");
        System.out.println("LISTA DE CONCURSANTES, ELEJI AL MEJOR..!!!!");

        for (Animal a : arbol.obtenerAnimales()) {
            System.out.println("-" + a.getNombre());
        }

        while (true) {
            System.out.println("--Elija entre las siguientes opciones---");
            System.out.println("1. Jugar contra la máquina");
            System.out.println("2. Jugar contra otro jugador");
            System.out.println("3. Ver arbol de concursantes");
            System.out.println("4. Ver concursantes y profundidad");
            System.out.println("5. Adivina la profundidad");
            System.out.println("6. Adivina la altura");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    jugarContraMaquina(scanner, arbol);
                    System.out.println("El animal mas profundo fue " + profundo.getNombre());
                    break;
                case 2:
                    jugarContraUsuario(scanner, arbol);
                    System.out.println("El animal mas profundo fue " + profundo.getNombre());
                    break;

                case 3:
                    System.out.println("---------CONCURSANTES------------");
                    arbol.imprimirArbolGrafico();
                    System.out.println("------------------------------------");
                    break;
                case 4:
                    System.out.println("------Concursantes y su profundidad--------");
                    verNombreYProfundidad(arbol);
                    System.out.println("------------------------------------");
                    break;
                case 5:
                    System.out.println("------Adivina la Profundidad--------");
                    arbol.imprimirArbolGrafico();
                    adivinaProfundidad(arbol, scanner);
                    System.out.println("------------------------------------");
                    break;
                case 6:
                    System.out.println("------Adivina la Altura--------");
                    arbol.imprimirArbolGrafico();
                    adivinaAltura(arbol, scanner);
                    System.out.println("------------------------------------");
                    break;
                case 9:
                    scanner.close();
                    System.out.println("------------------------------------");
                    System.out.println("Gracias por participar en el concurso de talentos del Bosque Binario.");
                    return;
                default:
                    System.out.println("------------------------------------");
                    System.out.println("Opción no válida. Intente de nuevo.");
                    System.out.println("------------------------------------");
                    break;
            }

        }

    }

    private static void jugarContraMaquina(Scanner scanner, ArbolBinario arbol) {
        int puntosJugador = 0;
        int puntosMaquina = 0;
        profundo = null;

        puntosJugador = juegaHumano(puntosJugador, scanner, arbol);

        System.out.println("La máquina selecciona 3 animales:");
        puntosMaquina = juegaMaquina(puntosMaquina, arbol);
        System.out.println("--------------------------");
        System.out.println("Puntos del Jugador: " + puntosJugador);
        System.out.println("Puntos de la Máquina: " + puntosMaquina);
        System.out.println("--------------------------");

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
        profundo = null;

        System.out.println("Turno Jugador 1");
        puntosJugador1 = juegaHumano(puntosJugador1, scanner, arbol);
        System.out.println("Turno Jugador 2");
        puntosJugador2 = juegaHumano(puntosJugador2, scanner, arbol);

        System.out.println("------Puntajes------");
        System.out.println("Puntos del Jugador 1: " + puntosJugador1);
        System.out.println("Puntos del Jugador 2: " + puntosJugador2);

        System.out.println("--------Resultado-------");
        if (puntosJugador1 > puntosJugador2) {
            System.out.println("Ganador: Jugador 1");
        } else if (puntosJugador2 > puntosJugador1) {
            System.out.println("Ganador: Jugador 2");
        } else {
            System.out.println("Empate");
        }
        System.out.println("-------------------------------");
    }

    public static int juegaHumano(int puntosJugador, Scanner scanner, ArbolBinario arbol) {
        System.out.println("-------------------------------");
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
            if (profundo == null) {
                profundo = animalJugador;
            }
            if (arbol.profundidadAnimal(profundo) < arbol.profundidadAnimal(animalJugador)) {
                profundo = animalJugador;
            }
            System.out.println("-------------------------------");
        }
        return puntosJugador;
    }

    public static int juegaMaquina(int puntosMaquina, ArbolBinario arbol) {
        Random random = new Random();
        List<Integer> valores = arbol.obtenerValores();
        List<Animal> animalesSeleccionados = new ArrayList<>();

        System.out.println("-------------------------------");
        System.out.println("Turno de maquina");
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

            if (arbol.profundidadAnimal(profundo) < arbol.profundidadAnimal(animalSeleccionado)) {
                profundo = animalSeleccionado;
            }
            System.out.println("-------------------------------");
        }
        return puntosMaquina;
    }

    public static void adivinaProfundidad(ArbolBinario arbol, Scanner scanner) {
        //Animal random
        Random random = new Random();
        List<Animal> animalesSeleccionados = new ArrayList<>();
        animalesSeleccionados = arbol.obtenerAnimales();
        Animal a = animalesSeleccionados.get(random.nextInt(animalesSeleccionados.size()));

        //juego
        System.out.println("=============================");
        System.out.println("El animal aleatorio es: " + a.getNombre());
        System.out.println("Indique la profundidad: ");
        int adivina = scanner.nextInt();
        if (adivina == arbol.profundidadAnimal(a)) {
            System.out.println("===============");
            System.out.println("Felicitaciones, Ud esta promocionado!!");
        } else {
            System.out.println("=========================");
            System.out.println("Siga repasando arboles binarios!!");
        }

    }

    public static void adivinaAltura(ArbolBinario arbol, Scanner scanner) {
        //Animal random
        Random random = new Random();
        List<Animal> animalesSeleccionados = new ArrayList<>();
        animalesSeleccionados = arbol.obtenerAnimales();
        Animal a = animalesSeleccionados.get(random.nextInt(animalesSeleccionados.size()));

        //juego
        System.out.println("=============================");
        System.out.println("El animal aleatorio es: " + a.getNombre());
        System.out.println("Indique la altura: ");
        System.out.println("Altura: " + arbol.alturaAnimal(a));
        int adivina = scanner.nextInt();
        if (adivina == arbol.alturaAnimal(a)) {
            System.out.println("===============");
            System.out.println("Felicitaciones, Ud esta promocionado!!");
        } else {
            System.out.println("=========================");
            System.out.println("Siga repasando arboles binarios!!");
        }

    }

    public static void verNombreYProfundidad(ArbolBinario arbol) {
        for (Animal a : arbol.obtenerAnimales()) {
            System.out.println(a.getNombre() + " Profundidad en arbol: " + arbol.profundidadAnimal(a) + " ,Valor: " + a.getValor());

        }
    }

    public static Animal[] cargarAnimales(ArbolBinario arbol) {

        Random random = new Random();

        // Lista de 20 animales predefinidos
        Animal[] animales = {
            new Animal("leon", "Soy el león y vengo a rugir", random.nextInt(100)),
            new Animal("tigre", "Soy el tigre y corro rápido", random.nextInt(100)),
            new Animal("elefante", "Soy el elefante y trompeteo fuerte", random.nextInt(100)),
            new Animal("gato", "Soy el gato y maullo suavemente", random.nextInt(100)),
            new Animal("perro", "Soy el perro y ladro felizmente", random.nextInt(100)),
            new Animal("lobo", "Soy el lobo y aúllo a la luna", random.nextInt(100)),
            new Animal("zorro", "Soy el zorro y me escabullo sigilosamente", random.nextInt(100)),
            new Animal("conejo", "Soy el conejo y salto muy alto", random.nextInt(100)),
            new Animal("oso", "Soy el oso y pesco en el río", random.nextInt(100)),
            new Animal("jirafa", "Soy la jirafa y alcanzo las hojas altas", random.nextInt(100)),
            new Animal("rinoceronte", "Soy el rinoceronte y embisto con fuerza", random.nextInt(100)),
            new Animal("hipopotamo", "Soy el hipopótamo y buceo en el agua", random.nextInt(100)),
            new Animal("canguro", "Soy el canguro y boxeo como un profesional", random.nextInt(100)),
            new Animal("koala", "Soy el koala y trepo a los árboles", random.nextInt(100)),
            new Animal("panda", "Soy el panda y ruedo por el suelo", random.nextInt(100)),
            new Animal("caballo", "Soy el caballo y galopo velozmente", random.nextInt(100)),
            new Animal("vaca", "Soy la vaca y mujo tranquilamente", random.nextInt(100)),
            new Animal("cerdo", "Soy el cerdo y me revuelco en el lodo", random.nextInt(100)),
            new Animal("oveja", "Soy la oveja y balo dulcemente", random.nextInt(100)),
            new Animal("gallina", "Soy la gallina y pongo huevos", random.nextInt(100))
        };

        // Inserción de los 20 animales
        for (Animal animal : animales) {
            arbol.insertarAnimal(animal);
        }

        return animales;
    }

    // Método para pausar la ejecución del programa por unos milisegundos
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
