package ejercicio04;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JOptionPane;

public class Ejercicio04APP {
	public static void main(String[] args) {

		// Aquí guardamos las bases de datos para tenerlas durante la ejecución del
		// programa.
		Hashtable<String, Double> dato = baseDatos();
		Hashtable<String, Integer> stock = baseStock();

		// Las siguientes variables son las que necesitaremos para ejecutar el menú del
		// programa.
		boolean fin = false;
		String nom = "";
		double val = 0;
		int stck = 0;

		// Este bucle con el switch es nuestro menú principal.
		while (fin == false) {
			switch (pedirNumero(
					"¿Qué acción desea realizar?\n1) Añadir productos.\n2) Editar stock.\n3) Consultar la base de datos.\n4) Realizar una venta.\n5) Salir")) {
			// Este caso nos genera un producto y luego nos pregunta que si queremos generar
			// un stock para el producto.
			case 1:
				nom = pedirTexto("Introduzca el nombre del producto.");
				val = pedirDouble("Introduzca el precio del producto.");
				añadirProducto(dato, nom, val);

				switch (pedirNumero("¿Desea introducir un stock al producto?\n1) Si 2) No")) {
				case 1:
					stck = pedirNumero("Introduzca la cantidad en stock.");
					editarStock(stock, nom, stck);
					break;
				// Aunque el cliente diga que no quiere generar un stock para el producto
				// crearemos uno igualmente a 0 para evitar posibles errores de ejecución.
				default:
					editarStock(stock, nom, 0);
					break;
				}
				break;
			// Esta opción nos pedirá los datos para cambiar el stock de un producto ya
			// existente.
			case 2:
				nom = pedirTexto("Introduzca el nombre del producto.");
				stck = pedirNumero("Introduzca la cantidad en stock.");
				editarStock(stock, nom, stck);
				break;
			// Esta opción nos llevará a un menú aparte donde podremos escoger si queremos
			// ver solo un producto de la base de datos o todos.
			case 3:
				mostrarDatos(dato, stock);
				break;
			// Esta opción nos permite realizar una venda a un cliente.
			case 4:
				realizarVenta(dato, stock);
				break;
			// Esta opción nos dejará salir del bucle y finalizar el programa.
			case 5:
				fin = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Valor introducido no válido.\nHas de introducir del 1 al 4.");
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
		// Este caso es para ver solo un producto.
		case 1:
			el1 = pedirTexto("Que producto quieres comprobar: ");
			System.out.println(el1 + "  " + dato.get(el1) + "€, quedan " + stock.get(el1) + " en stock");

			break;

		// En este caso creamos un bucle para mostrar toda la base de datos.
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

	// Este método se ejecutara solo cuando realicemos una venta y le restara a
	// nuestro stock actual la cantidad que estamos vendiendo.
	public static Hashtable<String, Integer> editarStockVenta(Hashtable<String, Integer> stock, String nombre,
			int cantidad) {

		stock.replace(nombre, (stock.get(nombre) - cantidad));

		// Aunque no tendría que pasar nunca que nos quedemos sin stock he añadido una
		// alerta por si nos quedáramos sin.
		if (stock.get(nombre) <= 0) {
			JOptionPane.showMessageDialog(null, "Nos hemos quedado sin stock de " + nombre);
		}

		return stock;

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

	// Este método es el que compone el proyecto, es el que ira ejecutando los otros
	// métodos y mandándoles la información necesaria.
	public static void realizarVenta(Hashtable<String, Double> dato, Hashtable<String, Integer> stock) {

		// Este boolean nos dejará salir del bucle while que usamos para indicar el IVA.
		boolean finalizar = false;

		// Estas dos hashtables nos guardarán la información de la base de datos y del
		// carro de la compra.
		Hashtable<String, Integer> carrito = carritoCompra();

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
					editarStockVenta(stock, el1, carrito.get(el1));
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
