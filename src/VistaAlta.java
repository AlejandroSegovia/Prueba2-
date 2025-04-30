package jcolonia.daw2024.inventario;

/**
 * Autor: Junior Mendoza Rios
 * Correo: juniorj.menrio@educa.jcyl
 * 
 * Vista para gestionar el alta de equipos en el inventario.
 * Permite al usuario ingresar los datos del equipo y procesar la alta
 * en el sistema.
 * 
 * @version 1.0
 */
public class VistaAlta extends VistaGeneral {

    /**
     * Constructor de la vista de alta.
     * 
     * @param nombre el nombre de la vista
     */
    public VistaAlta(String nombre ) {
        super(nombre);
        // TODO Auto-generated constructor stub
    }

    /**
     * Agrega al inventario un nuevo equipo con los datos facilitados por el
     * usuario. La vista de altas debe pedir al usuario los datos correspondientes y
     * se apoyará en el método {@link #procesarAlta(String, PuestoUsuario)} para
     * validar las entradas y completar el alta.
     * 
     * @param dlg el diálogo de entrada de datos del puesto de usuario
     * @throws InventarioException si ocurre algún error al procesar la alta
     */
    public void alta(VistaDiálogoAltaPuesto dlg) throws InventarioException {
        dlg.mostrarTítulo();
        dlg.entradaPuestoUsuario();
    }
}

