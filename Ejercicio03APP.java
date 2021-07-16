package Ejercicio03;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ejercicio03APP {

	public static void main(String[] args) {

		Hashtable<String, Double> dato = baseDatos();
		Hashtable<String, Integer> stock = baseStock();

		boolean fin = false;
		String nom = "";
		double val = 0;
		int stck = 0;

		while (fin == false) {

			switch (pedirNumero(
					"¿Qué acción desea realizar?\n1) Añadir productos.\n2) Editar stock.\n3) Consultar la base de datos.\n4) Salir")) {
			case 1:
				nom = pedirTexto("Introduzca el nombre del producto.");
				val = pedirDouble("Introduzca el precio del producto.");
				añadirProducto(dato, nom, val);

				switch (pedirTexto("¿Desea introducir un stock al producto?\nSi No")) {
				case "si":
					stck = pedirNumero("Introduzca la cantidad en stock.");
					editarStock(stock, nom, stck);
					break;

				default:
					editarStock(stock, nom, 0);
					break;
				}

				break;
			case 2:
				nom = pedirTexto("Introduzca el nombre del producto.");
				stck = pedirNumero("Introduzca la cantidad en stock.");
				editarStock(stock, nom, stck);
				break;
			case 3:
				mostrarDatos(dato, stock);
				break;
			case 4:
				fin = true;
				break;
			default:
				break;
			}

		}

	}

	// Este método es nuestra base de datos recién ejecutado el programa.
	public static Hashtable<String, Double> baseDatos() {

		Hashtable<String, Double> dato = new Hashtable<String, Double>();

		dato.put("colacao", 5.5);
		dato.put("pan", 1.2);
		dato.put("agua", 1.0);
		dato.put("leche", 2.5);
		dato.put("pañuelos", 2.35);
		dato.put("kellogs", 3.99);
		dato.put("queso", 2.64);
		dato.put("patatas fritas", 2.3);
		dato.put("longaniza", 4.0);
		dato.put("tomate", 0.5);

		return dato;

	}

	// Este método es nuestra base de datos acerca del stock recién ejecutado el
	// programa.
	public static Hashtable<String, Integer> baseStock() {

		Hashtable<String, Integer> dato = new Hashtable<String, Integer>();

		dato.put("colacao", 59);
		dato.put("pan", 17);
		dato.put("agua", 15);
		dato.put("leche", 21);
		dato.put("pañuelos", 22);
		dato.put("kellogs", 36);
		dato.put("queso", 24);
		dato.put("patatas fritas", 21);
		dato.put("longaniza", 40);
		dato.put("tomate", 50);

		return dato;

	}

	// Este método es un menú que nos permite escoger entre ver un producto de
	// nuestra base de datos o verlos todos.
	public static void mostrarDatos(Hashtable<String, Double> dato, Hashtable<String, Integer> stock) {
		String el1 = "";

		switch (pedirNumero("1) Un solo producto.\n 2) Todos los productos.")) {
		case 1:
			el1 = pedirTexto("Que producto quieres comprobar: ");
			System.out.println(el1 + "  " + dato.get(el1) + "€, quedan " + stock.get(el1) + " en stock");

			break;

		case 2:
			Enumeration<String> e = dato.keys();
			while (e.hasMoreElements()) {
				el1 = e.nextElement();
				System.out.println(el1 + "  " + dato.get(el1) + "€, quedan " + stock.get(el1) + " en stock");
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, "Valor introducido no válido.");
			break;
		}

	}

	// Este método añadirá productos nuevos a la base de datos.
	public static Hashtable<String, Double> añadirProducto(Hashtable<String, Double> dato, String nombre,
			double precio) {
		dato.put(nombre, precio);
		return dato;

	}

	// Este método añadira stock a nuestros productos.
	public static Hashtable<String, Integer> editarStock(Hashtable<String, Integer> stock, String nombre,
			int cantidad) {
		if (stock.get(nombre) != null) {
			stock.replace(nombre, cantidad);
		} else {
			stock.remove(nombre);
			stock.put(nombre, cantidad);
		}
		return stock;

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

	// Este método es como el de pedir número, pero le podemos introducir un double.
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

	// Este método nos pedirá que introduzcamos un texto y si hemos introducido una
	// cadena vacía saltara un error conforme hemos introducido un valor no válido y
	// nos pedirá de nuevo un valor.
	public static String pedirTexto(String text) {

		while (true) {
			String dato = JOptionPane.showInputDialog(text);

			if (!dato.equals(null)) {
				return dato.toLowerCase();
			} else {
				JOptionPane.showMessageDialog(null, "Valor introducido no válido.");
			}
		}
	}

}
