package ejercicio02;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

public class Ejercicio02APP {

	public static void main(String[] args) {

		ventaCompleta();

	}

	// Este método es el que compone el proyecto, es el que ira ejecutando los otros
	// métodos y mandándoles la información necesaria.
	public static void ventaCompleta() {

		// Este boolean nos dejará salir del bucle while que usamos para indicar el IVA.
		boolean finalizar = false;

		// Estas dos hashtables nos guardarán la información de la base de datos y del
		// carro de la compra.
		Hashtable<String, Integer> carrito = carritoCompra();
		Hashtable<String, Double> dato = baseDatos();

		// Estas dos variables double nos guardarán el precio bruto y el precio con el
		// IVA una vez sepamos que % aplicar.
		double precioBruto = calcPrecio(carrito, dato), precioNeto = 0.0;

		// Aunque normalmente el IVA se guardaría como una constante esta vez no lo he
		// hecho así porque se ha estipulado que podrá ser del 21% o del 4%.
		String ivaAplicado = "";

		// Este bucle while nos pedirá que % de IVA se tiene que aplicar y calculará el
		// precio neto..
		while (finalizar == false) {
			switch (pedirNumero("IVA a apicar\n  1) 21%  2) 4%")) {
			case 1:
				precioNeto = precioBruto + (precioBruto * 0.21);
				ivaAplicado = "21%";
				finalizar = true;
				break;
			case 2:
				precioNeto = precioBruto + (precioBruto * 0.04);
				ivaAplicado = "4%";
				finalizar = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido, has de introducir 1 o 2.");
				break;
			}

			// A partir de aquí estamos imprimiendo los valores que hemos ido recopilando.
			System.out.println("Se ha aplicado " + ivaAplicado + " IVA");
			System.out.printf("Precio total bruto: %.2f\n", precioBruto);
			System.out.printf("Precio mas IVA: %.2f\n", precioNeto);
			System.out.println("Productos comprados.");
			Enumeration<String> e = carrito.keys();
			while (e.hasMoreElements()) {
				String el1 = e.nextElement();
				if (!el1.equals("")) {
					System.out.println(el1 + " -------- " + carrito.get(el1));
				}
			}

			// Estas líneas nos piden que indiquemos quanto ha pagado el cliente, he
			// esperado hasta este punto para mostrar por pantalla el total con IVA para
			// poder indicárselo al cliente.
			double dineroCliente = pedirDouble("¿Cuánto ha pagado el cliente?"),
					dineroCambio = dineroCliente - precioNeto;

			// Y estas líneas imprimen el resto que sé pedía para el ejercicio.
			System.out.printf("Cantidad pagada: %.2f\n", dineroCliente);
			System.out.printf("Cambio a devolver: %.2f\n", dineroCambio);

		}

	}

	// Este método es el carro de la compra, se ocupará de ir guardando las
	// cantidades compradas.
	public static Hashtable<String, Integer> carritoCompra() {

		// He usado un hashtable para el carro de la compra porque nos permite guardar
		// el nombre del producto y la cantidad comprada para luego imprimirlo por
		// pantalla.
		Hashtable<String, Integer> carrito = new Hashtable<String, Integer>();

		while (true) {

			switch (pedirNumero("Desea añadir un producto al carro?\n   1) Si 2) No")) {
			case 1:
				carrito.put(pedirTexto("Introduzca el nombre del producto."), pedirNumero("¿Que cantidad?"));
				break;

			case 2:
				return carrito;

			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido, has de introducir 1 o 2.");
				break;
			}
		}
	}

	// Este método contiene una pequeña base de datos para hacer 4 pruebas.
	public static Hashtable<String, Double> baseDatos() {

		Hashtable<String, Double> dato = new Hashtable<String, Double>();

		dato.put("colacao", 5.5);
		dato.put("pan", 1.2);
		dato.put("agua", 1.0);
		dato.put("leche", 2.5);
		dato.put("pañuelos", 2.35);
		dato.put("kellogs", 3.99);

		return dato;

	}

	// Este método nos devolverá el precio final de lo que tengamos guardado en el
	// carrito.
	public static double calcPrecio(Hashtable<String, Integer> carrito, Hashtable<String, Double> dato) {

		double val = 0;

		Enumeration<String> e1 = carrito.keys();
		while (e1.hasMoreElements()) {
			String el1 = e1.nextElement();

			// El enumeration de la segunda hashtable se genera dentro del bucle porque si
			// lo genero fuera me quedo sin elementos.
			Enumeration<String> e2 = dato.keys();
			while (e2.hasMoreElements()) {
				String el2 = e2.nextElement();

				if (el1.equals(el2)) {
					val = val + (dato.get(el2) * carrito.get(el1));
				}
			}
		}
		return val;
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
