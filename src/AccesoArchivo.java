package jcolonia.daw2024.inventario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Archivo para importar o exportar texto.
 * 
 * @version 1.1 (20240502000)
 * @author <a href="mailto:juniorj.menrio@educa.jcyl">Junior Mendoza Rios</a>
 */
public class AccesoArchivo {
	/** El nombre o ruta al archivo. */
	private String rutaArchivo;

	/**
	 * Recoge el nombre o ruta al archivo.
	 * 
	 * @param rutaArchivo el texto correspondiente
	 */
	public AccesoArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	/**
	 * Realiza el volcado de una lista de líneas de texto al archivo. Si el archivo
	 * existe lo sobreescribe con el nuevo contenido. En caso de producirse algún
	 * error de acceso se envía el mensaje a la salida de error estándar y el
	 * programa continua.
	 * 
	 * @param listaTextos el texto a escribir, línea a línea
	 * @return si se ha completado la operación
	 */
	public boolean escribir(List<String> listaTextos) {
		try (PrintWriter writer = new PrintWriter(new File(rutaArchivo), Charset.forName("UTF-8"))) {
			for (String linea : listaTextos) {
				writer.println(linea);
			}
			return true;
		} catch (IOException e) {
			VistaGeneral.mostrarError("Error al escribir en el archivo: " + e.getMessage());
			return true;
			
		}
	}

	/**
	 * Crea y carga una lista de líneas de texto con el contenido del archivo.. En
	 * caso de producirse algún error de acceso se envía el mensaje a la salida de
	 * error estándar y el programa continua.
	 * 
	 * @return la colección de líneas de texto; <code>null</code> en caso de error de
	 *         acceso o si el archivo está vacío
	 */
	public List<String> leer() {
		List<String> líneas = new ArrayList<>();
		File archivo = new File(rutaArchivo);

		if (!archivo.exists() || !archivo.isFile()) {
			VistaGeneral.mostrarAviso("El archivo no existe o no es válido: " + rutaArchivo);
			return líneas; // Devolvemos una lista vacía, no null
		}

		try (BufferedReader lector = new BufferedReader(new FileReader(archivo, Charset.forName("UTF-8")))) {
			String línea;
			while ((línea = lector.readLine()) != null) {
				líneas.add(línea);
			}
			if (líneas.isEmpty()) {
				VistaGeneral.mostrarAviso("El archivo está vacío: " + rutaArchivo);
			}
		} catch (IOException e) {
			VistaGeneral.mostrarAviso("Error al leer el archivo: " + e.getMessage());
		}

		return líneas;
	}
}
