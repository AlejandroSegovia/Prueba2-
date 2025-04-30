package jcolonia.daw2024.inventario;

import java.util.Scanner;

/**
 * Vista: Pantalla de altas para equipos informáticos en un aula.
 * Gestiona la interfaz que permite introducir los datos de un nuevo puesto
 * de trabajo en el aula, confirmando los datos antes de completar el alta.
 * 
 * @see ControladorInventarioAula
 * @see InventarioAula
 * 
 * @version 6.90 (2025426000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class VistaDiálogoAltaPuesto extends VistaGeneral {
	/** Control supervisor, de apoyo para completar altas */
	private ControladorInventarioAula control;

	/**
	 * Almacena el nombre o título.
	 * 
	 * @param nombre  el texto deseado
	 * @param control el control de la vista
	 */
	public VistaDiálogoAltaPuesto(String nombre, ControladorInventarioAula control) {
		super(nombre);
		this.control = control;
	}
	
	/**
	 * Recoge de la entrada estándar el código y datos del equipo y realiza el alta.
	 * Pide confirmación al usuario antes de completar el alta y muestra un error si
	 * los datos introducidos no resultan válidos.
	 * 
	 * @see ControladorInventarioAula#procesarAlta(String, PuestoUsuario)
	 * @return si el alta se ha completado o no
	 * @throws InventarioException si ocurre un error al procesar el alta
	 */
	public boolean entradaPuestoUsuario() throws InventarioException {
		Scanner in = getScEntrada();
		boolean completado = false;

		do {
			mostrarTítulo();

			try {
				System.out.print("Código del puesto (ICXX): ");
				String codigo = in.nextLine();

				System.out.print("Identificación del equipo: ");
				String ordenador = in.nextLine();

				System.out.print("Nombre del usuario: ");
				String nombre = in.nextLine();

				System.out.print("Apellidos del usuario: ");
				String apellidos = in.nextLine();

				if (pedirConfirmación("¿Confirmar el alta?")) {
					PuestoUsuario puesto = new PuestoUsuario(ordenador, nombre, apellidos);
					control.procesarAlta(codigo, puesto);
					mostrarTexto("Alta realizada correctamente");
					completado = true;
				} else {
					mostrarAviso("Alta cancelada por el usuario. Repitiendo entrada...");
				}
			} catch (InventarioException e) {
				mostrarError("Error: " + e.getMessage());
				// Se vuelve a repetir
			}
		} while (!completado);

		return true;
	}
}

