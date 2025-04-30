package jcolonia.daw2024.inventario;

import java.util.List;

/**
 * Vista para la exportación de equipos a un archivo.
 * 
 * @version 1.3 (20250428000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class VistaExportación extends VistaGeneral {
	
	public VistaExportación(String nombre) {
		super(nombre);
	}
	
	/**
	 * Realiza el volcado de todos los equipos almacenados a un archivo de texto.
	 * Emplea un formato propio –de estilo CSV con separador «#» u otro– que puede
	 * ser recuperado posteriormente (ver {@link #importación(String)}). El formato
	 * concreto de cada línea así como la lógica de importación/exportación de los
	 * puestos depende del inventario. En caso de producirse algún error de acceso
	 * se envía el mensaje a la salida de error estándar y el programa continúa.
	 * 
	 * @see InventarioAula#generarListadoCSV()
	 * @param rutaArchivo el nombre o ruta al archivo
	 */
	public void exportación(String rutaArchivo, InventarioAula aula25) {
	    try {
	        AccesoArchivo archivo = new AccesoArchivo(rutaArchivo);
	        List<String> contenido = aula25.generarListadoCSV();
	        archivo.escribir(contenido);
	        
	        String mensaje = String.format("%d equipos exportados a %s", 
	            aula25.getNúmElementos(), rutaArchivo);
	        VistaGeneral.mostrarTexto(mensaje);
	    } catch (Exception e) {
	        VistaGeneral.mostrarError("Error al exportar: " + e.getMessage());
	    }
	}
}
