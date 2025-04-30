package jcolonia.daw2024.inventario;

import static java.lang.System.out;

import java.util.List;

/**
 * Vista para gestión de listados en consola de texto.
 * Muestra los elementos de una lista de manera paginada y permite navegar
 * por las páginas a través de confirmaciones por parte del usuario.
 * 
 * @version 1.2 (20250428000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class VistaListado extends VistaGeneral {
	/** Número de líneas a mostrar en cada página. */
	private static final int TAMAÑO_PÁGINA = 10;
	/**
	 * Formato común tipo «printf» para cada línea mostrada. Incluye el número y el
	 * propio texto de la línea.
	 */
	private static final String FORMATO_LISTADO = "  %d: %s%n";

	/**
	 * Almacena el nombre o título.
	 * 
	 * @param nombre el texto deseado
	 */
	public VistaListado(String nombre) {
		super(nombre);
	}

	/**
	 * Envía a la consola una lista numerada de textos distribuidos en lotes de
	 * cierta longitud. Al final de cada lote o página pide confirmación para
	 * mostrar las líneas que conformarán el siguiente lote.
	 * 
	 * @param listaTextos los textos a mostrar
	 * @see VistaGeneral#pedirConfirmación(String)
	 */
	public void mostrar(List<String> listaTextos) {
		mostrarTítulo();
	    
	    if (listaTextos == null || listaTextos.isEmpty()) {
	        mostrarAviso("No hay elementos para mostrar");
	        return;
	    }

	    int inicio = 0;
	    boolean continuar = true;
	    
	    while (inicio < listaTextos.size() && continuar) {
	        int fin = Math.min(inicio + TAMAÑO_PÁGINA, listaTextos.size());
	        
	        // Mostrar página actual
	        for (int i = inicio; i < fin; i++) {
	            out.printf(FORMATO_LISTADO, i + 1, listaTextos.get(i));
	        }
	        
	        // Preguntar si hay más elementos
	        if (fin < listaTextos.size()) {
	            continuar = pedirConfirmación("¿Desea seguir?");
	            dejarEspacio();
	        }
	        
	        inicio = fin;
	    }
	    
	    preguntaSeguir();
	}
	
	
	/**
	 * Genera una pantalla con el listado completo de equipos del aula.
	 */
	public void listado(InventarioAula aula25) {
		mostrar(aula25.generarListadoTexto());
		VistaGeneral.preguntaSeguir();
	}
}
