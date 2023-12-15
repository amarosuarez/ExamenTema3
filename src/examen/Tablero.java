package examen;

import java.util.Random;

public class Tablero {

	// Atributo constante que marca el número de casillas que tiene el tablero
	static final int TAM_TABLERO = 20;

	// Atributo que marca la posición del jugador 1 en el tablero
	int fichaJ1 = 0;

	// Atributo que marca la posición del jugador 2 en el tablero
	int fichaJ2 = 0;

	// Atributo que marca el valor del dado 1
	static int dado1;

	// Atributo que marca el valor del dado 2
	static int dado2;

	// Atributo que almacena el nombre del jugador 1
	String nomJ1;

	// Atributo que almacena el nombre del jugador 2
	String nomJ2;

	// Constructor vacío
	public Tablero() {}

	// Constructor con los parámetros nomJ1 y nomJ2
	public Tablero(String nomJ1, String nomJ2) {
		this.nomJ1 = nomJ1;
		this.nomJ2 = nomJ2;
	}

	// Función que asigna valores aleatorios a los dados (dado1 y dado2)
	static void tiraDados() {
		// Creamos un nuevo objeto Random
		Random rand = new Random();

		// Asignamos un valor aleatorio entre 1 y 6 al primer dado
		dado1 = rand.nextInt(1, 6);

		// Asignamos un valor aleatorio entre 1 y 6 al segundo dado
		dado2 = rand.nextInt(1, 6);
	}

	// Función que pinta el tablero
	void pintaTablero() {
		// Bucle para generar las columnas
		for (int i = 0; i <= TAM_TABLERO; i++) {
			// Comprobamos la posición de la i para determinar que es lo que hay que pintar
			if (i == 0) {
				// En la posición inicial pintamos I en vez del número
				System.out.print("\tI");
			} else if (i == TAM_TABLERO) {
				// En la posición inicial pintamos F en vez del número
				System.out.print("\tF");
			} else {
				// En el resto de posiciones pintamos los números
				System.out.print("\t" + i);
			}
		}

		// Salto de línea para las filas
		System.out.println();
		
		// Bucle para generar dos filas
		for (int j = 1; j <= 2; j++) {
			
			// Cuando j vale 1 hace referencia al jugador 1, cuando j vale 2 hace referencia a jugador 2
			for (int i = -1; i <= TAM_TABLERO; i++) {
				// Comprobamos la posición de i para determinar que pintar
				if (i == -1) {
					// Con un ternario, comprobamos el valor de j para saber que jugador está pintando y pintar su nombre
					System.out.print(j == 1 ? nomJ1 : nomJ2);
				} else {
					// Con un ternario, comprobamos el valor de j para saber que jugador está pintando y pintar su ficha
					if ((j == 1 ? fichaJ1 : fichaJ2) == i) {
						// Pintamos la ficha del jugador
						System.out.print("\tO");
					} else if (i == 0) {
						// Colocamos la barrera inicial
						System.out.print("\tI");
					} else {
						// Damos espacio tabulador para mantener el orden
						System.out.print("\t");
					}
				}
			}
			System.out.println();
		}
		
		// Salto de línea para dejar de escribir a la derecha del jugador
		System.out.println();
		
		// Salto de línea para generar un espacio abajo del tablero
		System.out.println();
	}

	/* Función que avanza las posiciones del jugador, 
	 * recibiendo como parámetro el número que indica el jugador al que le toca*/
	void avanzaPosiciones(int jugador) {
		// Variable donde se almacena la suma de los dados
		int movimientos = dado1 + dado2;

		// Auxiliar donde se almacena la última posición del jugador
		int ultimaPosicion;
		
		// Variable donde se almacenará cuantos movimientos debe retroceder
		int cantidadRetroceso;
		
		/*
		 * Auxiliar que almacenará el número de posiciones desde la última posición del
		 * jugador hasta el final del tablero
		 */
		int auxiliarResta;

		// Comprueba que jugador debe moverse
		if (jugador == 1) {
			// Guardamos la última posición del jugador 1
			ultimaPosicion = fichaJ1;

			// Actualizamos la posición del jugador 1
			fichaJ1 += movimientos;

			// Comprobamos si la nueva posición se sale del tablero
			if (fichaJ1 > TAM_TABLERO) {
				/* Calculamos el número de posiciones desde la última posición
				 * del jugador hasta el final del tablero */
				auxiliarResta = TAM_TABLERO - ultimaPosicion;

				// Calculamos cuantas fichas debe retroceder
				cantidadRetroceso = TAM_TABLERO - movimientos + auxiliarResta;

				// Actualizamos la posición del jugador 1
				fichaJ1 = cantidadRetroceso;
			}
		} else {
			// Guardamos la última posición del jugador 2
			ultimaPosicion = fichaJ2;

			// Actualizamos la posición del jugador 2
			fichaJ2 += movimientos;

			// Comprobamos si la nueva posición se sale del tablero
			if (fichaJ2 > TAM_TABLERO) {
				/* Calculamos el número de posiciones desde la última posición
				 * del jugador hasta el final del tablero */
				auxiliarResta = TAM_TABLERO - ultimaPosicion;

				// Calculamos cuantas fichas debe retroceder
				cantidadRetroceso = TAM_TABLERO - movimientos + auxiliarResta;

				// Actualizamos la posición del jugador 2
				fichaJ2 = cantidadRetroceso;
			}
		}

	}

	// Función que determina el estado de la carrera
	void estadoCarrera() {
		// Variable donde se almacenará el mensaje a mostrar
		String mensaje;
		
		// Según las posiciones generamos el mensaje
		if (fichaJ1 == fichaJ2) {
			// En este caso, van empatados
			mensaje = nomJ1 + " y " + nomJ2 + " van empatados";
		} else if (fichaJ1 > fichaJ2) {
			// En este caso, jugador 1 va ganando
			mensaje = nomJ1 + " va ganando";
		} else {
			// En este caso, jugador 2 va ganando
			mensaje = nomJ2 + " va ganando";
		}
		
		// Mostramos el mensaje
		System.out.println(mensaje);
		
		// Salto de línea para generar un espacio abajo del estado de la carrera
		System.out.println();
	}

	// Función que muestra el ganador
	String esGanador() {
		// Variable donde se almacenará el nombre del jugador ganador
		String nombre;
		
		// Condición para determinar quién ha ganado
		if (fichaJ1 == TAM_TABLERO) {
			// Jugador 1 ha ganado
			nombre = nomJ1;
		} else if (fichaJ2 == TAM_TABLERO){
			// Jugador 2 ha ganado
			nombre = nomJ2;
		} else {
			nombre = "";
		}
		
		// Devolvemos el nombre
		return nombre;
	}
}
