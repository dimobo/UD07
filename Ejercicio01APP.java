package Ejercicio01;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

public class Ejercicio01APP {
	public static void main(String[] args) {

		menu();

	}

	// Este método es el menú que dependiendo de que acción queremos realizar
	// ejecutara unos métodos u otros.
	public static void menu() {

		// Aquí estamos creando la hash table de java donde guardaremos las notas de los
		// alumnos que crearemos.
		Hashtable<String, Double> alumno = new Hashtable<String, Double>();
		boolean bucMenu = false;
		int alum = 0;

		// Aquí empieza el menú propiamente dicho.
		while (bucMenu == false) {
			int menu = Integer.parseInt(JOptionPane
					.showInputDialog("\n\n¿Qué operación desea hacer?\r\n" + "   1) Añadir notas de un alumno.\r\n"
							+ "   2) Ver las notas de los alumnos.\r\n" + "   3) Salir del programa."));

			switch (menu) {

			// Este caso es el que generara el alumno con su media de notas.
			case 1:
				alumno.put(String.valueOf(alum), calcMedia());
				alum++;
				break;

			// Este caso nos mostrará por pantalla (he evitado usar jpanel en este punto
			// para evitar spam) los alumnos introducidos.
			case 2:
				alum = 1;

				Enumeration<Double> e = alumno.elements();
				while (e.hasMoreElements()) {
					System.out.println("Alumno " + alum + ": " + e.nextElement());
					alum++;
				}

				break;

			// Este caso nos va a permitir salir del programa.
			case 3:
				bucMenu = true;
				break;

			// El default lo usamos como mensaje de error en caso de que alguien haya
			// escrito mal la opción.
			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido.\nLas opciones son 1, 2 y 3");
			}

		}

	}

	// Este método pedirá doubles al usuario que en este caso serán las notas de los
	// alumnos, he decidido crear una variación del pedirNumero que ya tenía porque
	// las notas puedes ser decimales.
	public static double pedirDouble(String text) {

		double num = 0;

		while (true) {
			String dato = JOptionPane.showInputDialog(text);

			if (!dato.equals(null)) {
				try {
					num = Double.parseDouble(dato);
					if (num > 0) {
						return num;
					} else {
						JOptionPane.showMessageDialog(null, "Valor introducido no válido.");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Valor introducido no válido.");
				}
			} else {
				return num;
			}
		}
	}

	// Este método nos generará un array con las notas de los alumnos que nosotros
	// vayamos introduciendo.
	public static double[] notasAlumno() {

		double ar1[] = new double[(int) pedirDouble("Introduzca la cantidad de notas a añadir: ")];

		for (int i = 0; i < ar1.length; i++) {

			ar1[i] = pedirDouble((i + 1) + " Introduzca la nota del alumno: ");

		}

		return ar1;

	}

	// Este método recorre la array con las notas del alumno y genera la media de
	// sus notas.
	public static double calcMedia() {
		double media = 0;
		double notas[] = notasAlumno();

		for (int i = 0; i < notas.length; i++) {
			media = media + notas[i];
		}

		media = media / notas.length;
		return media;
	}

}
