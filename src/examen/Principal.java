package examen;

import java.util.Scanner;

public class Principal {

	// Declaramos el Scanner como estático para poder usarlo en las funciones
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Variable donde se almacenará el nombre del jugador 1
		String jugador1;

		// Variable donde se almacenará el nombre del jugador 2
		String jugador2;

		// Variable donde se almacenará el turno
		boolean turno = true; // True para jugador 1, false para jugador 2

		// Variable donde se almacenará si ha tocado dobles
		boolean dobles = true;

		// Obtenemos el nombre del jugador 1
		jugador1 = pideNombre(sc, 1);

		// Obtenemos el nombre del jugador 2
		do {
			jugador2 = pideNombre(sc, 2);
		} while (jugador2.equalsIgnoreCase(jugador1));

		// Creamos un nuevo objeto Tablero con los nombres de los jugadores
		Tablero tab = new Tablero(jugador1, jugador2);

		// Ejecutamos mientras no haya un ganador o haya conseguido dobles
		do {
			// Le pedimos al jugador que introduzca una letra para continuar
			System.out.println("Pulse una tecla para continuar");
			sc.nextLine();

			// Lanzamos los dados
			Tablero.tiraDados();

			// Mostramos a quien le corresponde el turno
			System.out.println("Turno de " + (turno ? jugador1 : jugador2));

			// Mostramos el valor de la tirada
			System.out.println("La tirada ha sido " + Tablero.dado1 + " y " + Tablero.dado2);

			// Movemos al jugador
			tab.avanzaPosiciones(turno ? 1 : 2);

			// Pintamos el tablero
			tab.pintaTablero();

			// Comprobamos el estado de la carrera
			tab.estadoCarrera();

			// Comprueba si hay un ganador
			tab.esGanador();

			// Comprobamos si no ha sido dobles
			if (Tablero.dado1 != Tablero.dado2) {
				dobles = false;
				turno = !turno;
			}

		} while (tab.esGanador() == "" || dobles);

		// Mostramos el mensaje de victoria
		System.out.println("¡Enhorabuena " + tab.esGanador() + "! Has ganado");

		// Cerramos el Scanner
		sc.close();
	}

	/* Función que pide el nombre al jugador, 
	 * que recibe el Scanner como parámetro para leer el nombre del jugador
	 * y jugador como parámetro para saber que nombre mostrar*/
	static String pideNombre(Scanner sc, int jugador) {
		// Variable donde se almacenará el nombre
		String nombre = "";

		// Le preguntamos el nombre al usuario
		System.out.println((jugador == 1 ? "Jugador 1 " : "Jugador 2 ") + "¿Como te llamas?");

		// Guardamos el nombre introducido
		nombre = sc.nextLine();

		// Devolvemos el nombre
		return nombre;
	}

}
