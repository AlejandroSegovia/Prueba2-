package jcolonia.daw2024.inventario;

/**
 * Autor: Junior Mendoza Rios
 * Correo: juniorj.menrio@educa.jcyl
 * 
 * Vista para la eliminación de todos los puestos del inventario del aula.
 * 
 * @version 1.0
 */

public class VistaBorrado extends VistaGeneral {

    /**
     * Constructor que recibe el nombre de la vista.
     * 
     * @param nombre el nombre de la vista
     */
    public VistaBorrado(String nombre) {
        super(nombre);
    }

    /**
     * Elimina todos los puestos del aula.
     * Muestra un mensaje de advertencia antes de realizar la eliminación
     * y pide confirmación para proceder con la eliminación total.
     * 
     * @param aula el aula cuyo inventario será borrado
     */
    public void borrarTodo(InventarioAula aula) {
        if (aula.estáVacío()) {
            VistaGeneral.mostrarAviso("El inventario ya está vacío");
            VistaGeneral.preguntaSeguir();
            return;
        }

        VistaGeneral.mostrarTexto("\nAtención: esta acción eliminará TODOS los puestos del aula «" + aula.toString() + "»");

        if (VistaGeneral.pedirConfirmación("¿Está completamente seguro de continuar?", "Esta acción no se puede deshacer")) {
            aula.vaciar(); 
            VistaGeneral.mostrarTexto("Todos los puestos han sido eliminados");
        } else {
            VistaGeneral.mostrarTexto("Operación cancelada");
        }

        VistaGeneral.preguntaSeguir();
    }
}



