package jcolonia.daw2024.inventario;

/**
 * Controlador: Aplicación de gestión de equipos informáticos en un aula.
 * Gestiona las distintas funciones del menú principal. Manipula de manera única
 * el aula 25, cuyos equipos están identificados como ICXX.
 * 
 * @see InventarioAula
 * 
 * @version 6.90 (2025426000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class ControladorInventarioAula {
	/** Nombre del archivo de datos para impotación/exportación. */
	private static final String NOMBRE_ARCHIVO = "Inventario ICXX.txt";
	/** Opciones del menú principal. */
	private static final String[] TXT_MENÚ_PRINCIPAL = { "Alta", "Baja", "Listado", "Exportación", "Importación",
			"Borrado" };
	/** Colección principal de resultados. */
	private InventarioAula aula25;
	
	VistaDiálogoAltaPuesto dlg = new VistaDiálogoAltaPuesto("Alta ICXX", this);
	
	private vistaImportación vistaImportación = new vistaImportación("Importar");
	
	private VistaListado vistaListado = new VistaListado("Lista"); 
	
	private VistaExportación vistaExportar = new VistaExportación("Exportar");
	
	private VistaBaja vistaBaja = new VistaBaja ("Baja");
	
	private VistaAlta vistaAlta = new VistaAlta ("Alta");
	
	private VistaBorrado vistaBorrado = new VistaBorrado ("Borrado");
	
	/**
	 * Inicializa la lista/colección donde se irán guardando los equipos.
	 */
	public ControladorInventarioAula() {
		try {
			aula25 = InventarioAula.of("IC");
		} catch (InventarioException ex) {
			String mensaje;
			mensaje = String.format("Error fatal: %s", ex.getLocalizedMessage());
			VistaGeneral.mostrarAviso(mensaje);
			System.exit(1);
		}
	}

	/**
	 * Bucle principal ligado al menú de entrada.
	 * @throws MenúException 
	 * @throws InventarioException 
	 */
	public void buclePrincipal() throws MenúException, InventarioException {
		VistaMenú menú;
		int opción;
		int n = 0;
		boolean salir = false;

		menú = new VistaMenú("Inventario aula 25", TXT_MENÚ_PRINCIPAL);

		do {
			menú.mostrarTítuloPrincipal();
			menú.mostrarMenú();
			opción = menú.pedirOpción();
			n++;

			switch (opción) {
			case 1: // Alta
				vistaAlta.alta(dlg);
				break;
			case 2: // Baja
				vistaBaja.baja(aula25);
				break;
			case 3: // Listado
				vistaListado.listado(aula25);
				break;
			case 4: // Exportación
				vistaExportar.exportación(NOMBRE_ARCHIVO, aula25);
				break;
			case 5: // Importación
				aula25 = vistaImportación.importación(NOMBRE_ARCHIVO, aula25, vistaListado);
				break;
			case 6: // Borrado
				vistaBorrado.borrarTodo(aula25);
				break;
			case 0: // Finalizar
				finalizar(); // Finalizar programa
				salir = true;
				break;
			default:
				stub(opción, n);
				break;
			}
		} while (!salir);
	}
	
	/**
	 * Incorpora un nuevo equipo al inventario del aula. Actúa de intermediario
	 * forzoso entre la vista y el almacén; evitando así compartir el almacen con la
	 * vista impidiendo potenciales manipulaciones descontroladas.
	 * 
	 * @see InventarioAula#añadir(String, PuestoUsuario)
	 * 
	 * @param código el identificador del puesto
	 * @param nuevo  los datos del equipo
	 * @throws InventarioException si alguna incidencia impide realizar el alta
	 */
	public void procesarAlta(String código, PuestoUsuario nuevo) throws InventarioException {
		aula25.añadir(código, nuevo);
	}
	
	/**
	 * Muestra un mensaje temporal, de relleno, para opciones pendientes de
	 * implementar.
	 * 
	 * @param entrada la opción elegida
	 * @param n       el número de secuencia en el historial de opciones cursadas
	 */
	private void stub(int entrada, int n) {
		String mensaje;
		mensaje = String.format("(%02d) → %d [Opción sin desarrollar]", n, entrada);
		VistaGeneral.mostrarAviso(mensaje);
	}

	/**
	 * Finaliza el programa. Muestra un mensaje final y cierra la conexión con la
	 * entrada estándar.
	 */
	private void finalizar() {
		VistaGeneral.mostrarTexto("*** FIN ***");
		VistaGeneral.close();
	}
}

