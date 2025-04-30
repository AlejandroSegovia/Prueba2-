package jcolonia.daw2024.inventario;

/**
 * Datos de un ordenador o equipo informático de un aula o despacho.
 * Representa un equipo informático asignado a un puesto con el nombre y apellidos
 * del usuario que lo utiliza. Cada puesto está identificado por un código de ordenador
 * y está asociado a un alumno o usuario.
 * 
 * @see InventarioAula
 *
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 * @version 6.90 (2025426000)
 * 
 * @param ordenador el código de identificación del equipo para el CAU
 * @param nombre    el nombre del alumno/usuario
 * @param apellidos los apellidos del alumno/usuario
 */
public record PuestoUsuario(String ordenador, String nombre, String apellidos) {

	/** Formato de línea empleado para listados de texto. */
	private static final String FORMATO_LISTADO_TEXTO = "%s (%s %s)";

	/**
	 * Genera una descripción del equipo incluyendo el identificador y el
	 * usuario.
	 * <div>Ejemplo:
	 * 
	 * <pre>
	 *   ED09012096P382 (José García Pérez)
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @return el texto correspondiente a la descripción del puesto de usuario
	 */
	@Override
	public final String toString() {
		String descripción;
		descripción = String.format(FORMATO_LISTADO_TEXTO, ordenador, nombre, apellidos());
		return descripción;
	}
}

