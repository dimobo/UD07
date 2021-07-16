package Ejercicio02;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ejercicio02APP {

	public static void main(String[] args) {

	}

	public static void ventaCompleta() {

	}

	// Este método es el carro de la compra, se ocupará de ir guardando las
	// cantidades compradas.
	public static ArrayList<Integer> carritoCompra() {

		ArrayList<Integer> carrito = new ArrayList<>();

		while (true) {

			switch (pedirNumero("Desea añadir un producto al carro?\n   1) Si 2) No")) {
			case 1:
				carrito.add(pedirNumero("¿Que cantidad?"));
				break;

			case 2:
				return carrito;

			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido, has de introducir 1 o 2.");
				break;
			}

		}

	}

	// Este método pedirá un número al usuario y se asegurará que sea un número
	// entero positivo.
	public static int pedirNumero(String text) {

		int num = 0;

		while (true) {
			String dato = JOptionPane.showInputDialog(text);

			if (!dato.equals(null)) {
				try {
					num = Integer.parseInt(dato);
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

}
