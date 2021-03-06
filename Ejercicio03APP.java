package ejercicio03;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ejercicio03APP {

	public static void main(String[] args) {

		// Aqu? guardamos las bases de datos para tenerlas durante la ejecuci?n del
		// programa.
		Hashtable<String, Double> dato = baseDatos();
		Hashtable<String, Integer> stock = baseStock();

		// Las siguientes variables son las que necesitaremos para ejecutar el men? del
		// programa.
		boolean fin = false;
		String nom = "";
		double val = 0;
		int stck = 0;

		// Este bucle con el switch es nuestro men? principal.
		while (fin == false) {
			switch (pedirNumero(
					"?Qu? acci?n desea realizar?\n1) A?adir productos.\n2) Editar stock.\n3) Consultar la base de datos.\n4) Salir")) {
			// Este caso nos genera un producto y luego nos pregunta que si queremos generar
			// un stock para el producto.
			case 1:
				nom = pedirTexto("Introduzca el nombre del producto.");
				val = pedirDouble("Introduzca el precio del producto.");
				a?adirProducto(dato, nom, val);

				switch (pedirTexto("?Desea introducir un stock al producto?\nSi No")) {
				case "si":
					stck = pedirNumero("Introduzca la cantidad en stock.");
					editarStock(stock, nom, stck);
					break;
				// Aunque el cliente diga que no quiere generar un stock para el producto
				// crearemos uno igualmente a 0 para evitar posibles errores de ejecuci?n.
				default:
					editarStock(stock, nom, 0);
					break;
				}
				break;
			// Esta opci?n nos pedir? los datos para cambiar el stock de un producto ya
			// existente.
			case 2:
				nom = pedirTexto("Introduzca el nombre del producto.");
				stck = pedirNumero("Introduzca la cantidad en stock.");
				editarStock(stock, nom, stck);
				break;
			// Esta opci?n nos llevar? a un men? aparte donde podremos escoger si queremos
			// ver solo un producto de la base de datos o todos.
			case 3:
				mostrarDatos(dato, stock);
				break;
			// Esta opci?n nos dejar? salir del bucle y finalizar el programa.
			case 4:
				fin = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.\nHas de introducir del 1 al 4.");
				break;
			}

		}

	}

	// Este m?todo es nuestra base de datos reci?n ejecutado el programa.
	public static Hashtable<String, Double> baseDatos() {

		Hashtable<String, Double> dato = new Hashtable<String, Double>();

		dato.put("colacao", 5.5);
		dato.put("pan", 1.2);
		dato.put("agua", 1.0);
		dato.put("leche", 2.5);
		dato.put("pa?uelos", 2.35);
		dato.put("kellogs", 3.99);
		dato.put("queso", 2.64);
		dato.put("patatas fritas", 2.3);
		dato.put("longaniza", 4.0);
		dato.put("tomate", 0.5);

		return dato;

	}

	// Este m?todo es nuestra base de datos acerca del stock reci?n ejecutado el
	// programa.
	public static Hashtable<String, Integer> baseStock() {

		Hashtable<String, Integer> dato = new Hashtable<String, Integer>();

		dato.put("colacao", 59);
		dato.put("pan", 17);
		dato.put("agua", 15);
		dato.put("leche", 21);
		dato.put("pa?uelos", 22);
		dato.put("kellogs", 36);
		dato.put("queso", 24);
		dato.put("patatas fritas", 21);
		dato.put("longaniza", 40);
		dato.put("tomate", 50);

		return dato;

	}

	// Este m?todo es un men? que nos permite escoger entre ver un producto de
	// nuestra base de datos o verlos todos.
	public static void mostrarDatos(Hashtable<String, Double> dato, Hashtable<String, Integer> stock) {
		String el1 = "";

		switch (pedirNumero("1) Un solo producto.\n 2) Todos los productos.")) {
		// Este caso es para ver solo un producto.
		case 1:
			el1 = pedirTexto("Que producto quieres comprobar: ");
			System.out.println(el1 + "  " + dato.get(el1) + "?, quedan " + stock.get(el1) + " en stock");

			break;

		// En este caso creamos un bucle para mostrar toda la base de datos.
		case 2:
			Enumeration<String> e = dato.keys();
			while (e.hasMoreElements()) {
				el1 = e.nextElement();
				System.out.println(el1 + "  " + dato.get(el1) + "?, quedan " + stock.get(el1) + " en stock");
			}
			break;

		default:
			JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
			break;
		}

	}

	// Este m?todo a?adir? productos nuevos a la base de datos.
	public static Hashtable<String, Double> a?adirProducto(Hashtable<String, Double> dato, String nombre,
			double precio) {
		dato.put(nombre, precio);
		return dato;

	}

	// Este m?todo a?adira stock a nuestros productos.
	public static Hashtable<String, Integer> editarStock(Hashtable<String, Integer> stock, String nombre,
			int cantidad) {

		// He tenido que poner este if que controle que el stock no sea null porque en
		// caso de ser null se queda pillado y no cambia el valor.
		if (stock.get(nombre) != null) {
			stock.replace(nombre, cantidad);
		} else {
			// Y en caso de que sea null borramos el stock y lo generamos con los datos que
			// nos han pasado.
			stock.remove(nombre);
			stock.put(nombre, cantidad);
		}
		return stock;

	}

	// Este m?todo pedir? un n?mero al usuario y se asegurar? que sea un n?mero
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
						JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
				}
			} else {
				return num;
			}
		}
	}

	// Este m?todo es como el de pedir n?mero, pero le podemos introducir un double.
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
						JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
				}
			} else {
				return num;
			}
		}
	}

	// Este m?todo nos pedir? que introduzcamos un texto y si hemos introducido una
	// cadena vac?a saltara un error conforme hemos introducido un valor no v?lido y
	// nos pedir? de nuevo un valor.
	public static String pedirTexto(String text) {

		while (true) {
			String dato = JOptionPane.showInputDialog(text);

			if (!dato.equals(null)) {
				return dato.toLowerCase();
			} else {
				JOptionPane.showMessageDialog(null, "Valor introducido no v?lido.");
			}
		}
	}

}
