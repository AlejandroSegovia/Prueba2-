package jcolonia.daw2024.inventario;

import java.util.List;

/**
 * Vista para la importación de equipos desde un archivo.
 * 
 * @version 1.3 (20250428000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class vistaImportación extends VistaGeneral {

    public vistaImportación(String nombre) {
        super(nombre);
    }

    /**
     * Importa equipos almacenados en un archivo de texto reemplazando el contenido
     * actual del programa. Emplea un formato propio –de estilo CSV con separador
     * «#» u otro– producido por una exportación previa (ver
     * {@link #exportación(String)}). El formato concreto de cada línea así como la
     * lógica de importación/exportación de los puestos depende del inventario. En
     * caso de producirse algún error de acceso o por el propio formato del archivo,
     * se envía el mensaje a la salida de error estándar y el programa continúa sin
     * perder el contenido anterior.
     * 
     * @see InventarioAula#of(List)
     * 
     * @param rutaArchivo el nombre o ruta al archivo
     * @return el nuevo inventario actualizado
     */
    public InventarioAula importación(String rutaArchivo, InventarioAula aula25, VistaListado listado) {
        AccesoArchivo archivo;
        InventarioAula nuevaLista = aula25;
        List<String> contenido;
        int númElementos;
        String mensaje;

        try {
            archivo = new AccesoArchivo(rutaArchivo);
            contenido = archivo.leer();
            númElementos = contenido.size() - 1; // 1 línea de cabecera, el prefijo

            if (númElementos < 1) {
                VistaGeneral.mostrarAviso("No hay ningún elemento que importar");
            } else {
                nuevaLista = InventarioAula.of(contenido);
                númElementos = nuevaLista.getNúmElementos();

                if (númElementos > 0) {
                    aula25 = nuevaLista;
                    mensaje = String.format("%d equipos importados", númElementos);
                    VistaGeneral.mostrarTexto(mensaje);
                    listado.listado(aula25);
                } else {
                    mensaje = String.format("%d equipos importados, se conservará la relación previa", númElementos);
                    VistaGeneral.mostrarAviso(mensaje);
                }
            }
        } catch (InventarioException ex) {
            mensaje = String.format("Error de importación: %s", ex.getLocalizedMessage());
            VistaGeneral.mostrarAviso(mensaje);
        }
        return nuevaLista;
    }
}
