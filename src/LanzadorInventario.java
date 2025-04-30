package jcolonia.daw2024.inventario;

/**
 * Lanzador de la aplicación de gestión de equipos informáticos en un aula.
 * Este programa arranca el sistema de inventario de los equipos informáticos en el aula
 * y permite al usuario interactuar con él mediante un menú de opciones.
 * 
 * @see ControladorInventarioAula
 * 
 * @version 6.90 (2025426000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class LanzadorInventario {
	
	/**
	 * Método principal que inicia el programa. Crea una instancia del controlador
	 * de inventario y ejecuta el bucle principal de opciones para gestionar el inventario
	 * de los equipos informáticos en el aula.
	 * 
	 * El programa gestionará el inventario permitiendo al usuario realizar varias acciones
	 * como agregar, eliminar o consultar puestos informáticos en el aula. El flujo del
	 * programa depende de las interacciones del usuario con el menú proporcionado.
	 * 
	 * @param argumentos opciones de ejecución - no se utilizan en este caso.
	 * @throws MenúException si ocurre un error durante la interacción con el menú.
	 * @throws InventarioException si ocurre un error relacionado con el inventario
	 *                             (por ejemplo, al agregar o eliminar puestos).
	 */
	public static void main(String[] argumentos) throws MenúException, InventarioException {
		// Crea el controlador de inventario
		ControladorInventarioAula control = new ControladorInventarioAula();
		// Inicia el bucle principal de opciones para gestionar el inventario
		control.buclePrincipal();
	}
}
