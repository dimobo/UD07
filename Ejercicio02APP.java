package Ejercicio02;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

public class Ejercicio02APP {

	public static void main(String[] args) {

		ventaCompleta();

	}

	public static void ventaCompleta() {

		boolean finalizar = false;

		Hashtable<String, Integer> carrito = carritoCompra();
		Hashtable<String, Double> dato = baseDatos();
		double precioBruto = calcPrecio(carrito, dato), precioNeto = 0.0;
		String ivaAplicado = "";

		while (finalizar == false) {

			switch (pedirNumero("IVA a apicar\n  1) 21%  2) 4%")) {
			case 1:

				precioNeto = precioBruto + (precioBruto * 0.21);
				ivaAplicado = "21%";
				finalizar = true;
				break;

			case 2:

				precioNeto = precioBruto + (precioBruto * 0.4);
				ivaAplicado = "4%";
				finalizar = true;
				break;

			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido, has de introducir 1 o 2.");
				break;
			}

			

			System.out.println("Se ha aplicado " + ivaAplicado + " IVA");
			System.out.printf("Precio total bruto: %.2f\n", precioBruto);
			System.out.printf("Precio mas IVA: %.2f\n", precioNeto);
			System.out.println("Productos comprados.");
			Enumeration<String> e = carrito.keys();
			while (e.hasMoreElements()) {
				String el1 = e.nextElement();
				System.out.println(el1 + " -------- " + carrito.get(el1));

			}
			
			double dineroCliente = pedirDouble("¿Cuánto ha pagado el cliente?"),
					dineroCambio = dineroCliente - precioNeto;
			
			System.out.printf("Cantidad pagada: %.2f\n", dineroCliente);
			System.out.printf("Cambio a devolver: %.2f\n", dineroCambio);

		}

	}

	// Este método es el carro de la compra, se ocupará de ir guardando las
	// cantidades compradas.
	public static Hashtable<String, Integer> carritoCompra() {

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

	//
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

	//
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
