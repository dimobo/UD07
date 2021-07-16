package Ejercicio01;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Ejercicio01APP {
	public static void main(String[] args) {

		menu();

	}

	public static void menu() {
		ArrayList<Double> alumno = new ArrayList<>();
		boolean bucMenu = false;

		while (bucMenu == false) {
			int menu = Integer.parseInt(JOptionPane
					.showInputDialog("\n\n�Qu� operaci�n desea hacer?\r\n" + "1) A�adir notas de un alumno.\r\n"
							+ "2) Ver las notas de los alumnos.\r\n" + "3) Salir del programa."));

			switch (menu) {
			case 1:
				alumno.add(calcMedia());
				break;

			case 2:
//				for (int i = 0; i < alumno.size(); i++) {
//					
//				}
				JOptionPane.showMessageDialog(null, alumno.toString());
				break;

			case 3:
				bucMenu = true;
				break;

			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no v�lido.");
			}

		}

	}

	// Este m�todo pedir� doubles al usuario que en este caso ser�n las notas de los
	// alumnos, he decidido crear una variaci�n del pedirNumero que ya ten�a porque
	// las notas puedes ser decimales.
	public static double pedirDouble(String text) {

		double num = 0;

		while (true) {
			String dato = JOptionPane.showInputDialog(text);
			if (!dato.equals(null)) {
				num = Double.parseDouble(dato);
				if (num > 0) {
					return num;
				} else {
					JOptionPane.showMessageDialog(null, "Valor introducido no v�lido.");
				}
			} else {
				return num;
			}
		}
	}

	// Este m�todo nos generar� un array con las notas de los alumnos que nosotros
	// vayamos introduciendo.
	public static double[] notasAlumno() {

		double ar1[] = new double[(int) pedirDouble("Introduzca la cantidad de notas a a�adir: ")];

		for (int i = 0; i < ar1.length; i++) {

			ar1[i] = pedirDouble((i + 1) + " Introduzca la nota del alumno: ");

		}

		return ar1;

	}

	// Este m�todo recorre la array con las notas del alumno y genera la media de
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
