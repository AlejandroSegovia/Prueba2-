package jcolonia.daw2024.inventario;

import java.util.List;
import java.util.Scanner;

/**
 * Vista simplificada para eliminar equipos del inventario.
 * Permite al usuario seleccionar y eliminar equipos del aula.
 * 
 * @version 1.0 (20250428000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class VistaBaja extends VistaGeneral {

	public VistaBaja(String nombre) {
		super(nombre);
	}

	/**
	 * Permite al usuario eliminar un equipo del inventario del aula.
	 * Muestra el listado de equipos y permite seleccionar uno para eliminar.
	 * Si el inventario está vacío, muestra un mensaje de aviso.
	 * 
	 * @param aula25 el aula desde la cual se eliminará el equipo
	 */
	public void baja(InventarioAula aula25) {
	    if (aula25.estáVacío()) {
	        VistaGeneral.mostrarAviso("El inventario está vacío - No hay equipos para eliminar");
	        VistaGeneral.preguntaSeguir();
	        return;
	    }

	    // Mostrar listado actual
	    VistaListado vistaListado = new VistaListado("Seleccione equipo a eliminar");
	    List<String> listado = aula25.generarListadoTexto();
	    vistaListado.mostrar(listado);

	    try {
	        Scanner in = VistaGeneral.getScEntrada();
	        System.out.print("\nIntroduzca el número del equipo a eliminar (0 para cancelar): ");
	        int selección = Integer.parseInt(in.nextLine());

	        if (selección == 0) {
	            VistaGeneral.mostrarTexto("Operación cancelada");
	            return;
	        }

	        if (selección < 1 || selección > listado.size()) {
	            VistaGeneral.mostrarError("Número inválido. Debe ser entre 1 y " + listado.size());
	            return;
	        }

	        // Extraer código del equipo seleccionado (formato: "IC## – ...")
	        String líneaSeleccionada = listado.get(selección - 1);
	        String código = líneaSeleccionada.split(" – ")[0].trim();

	        VistaGeneral.mostrarTexto("\nEquipo seleccionado:");
	        VistaGeneral.mostrarTexto(líneaSeleccionada);

	        // Confirmación final
	        if (VistaGeneral.pedirConfirmación("¿Está seguro de eliminar este equipo?")) {
	            aula25.eliminarPuesto(código);
	            VistaGeneral.mostrarTexto("Equipo " + código + " eliminado correctamente");
	        } else {
	            VistaGeneral.mostrarTexto("Operación cancelada");
	        }

	    } catch (NumberFormatException e) {
	        VistaGeneral.mostrarError("Debe introducir un número válido");
	    } catch (InventarioException e) {
	        VistaGeneral.mostrarError("Error al eliminar: " + e.getMessage());
	    } finally {
	        VistaGeneral.preguntaSeguir();
	    }
	}
}
