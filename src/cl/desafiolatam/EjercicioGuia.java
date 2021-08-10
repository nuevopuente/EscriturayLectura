package cl.desafiolatam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class EjercicioGuia {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String directorio, fichero, fichero2, texto;

		System.out.println("Ingrese el nombre del directorio a crear: ");
		directorio = scan.nextLine();
		System.out.println("Ingrese el nombre del fichero a crear: ");
		fichero = scan.nextLine();

		if (directorio != "" && fichero != "") {
			crearArchivo(directorio, fichero);
		} else {
			System.out.println("No se ha creado directorio ni archivos ya que no se ingresó un nombre.");
		}

		System.out.println("Ingrese el nombre del fichero a buscar: ");
		fichero2 = "src/" + directorio + "/" + scan.nextLine();
		System.out.println("Ingrese el nombre del texto a buscar: ");
		texto = scan.nextLine();
		buscarTexto(fichero2, texto);

		scan.close();
	}

	public static void crearArchivo(String directorio, String fichero) {
		File carpeta = new File("src/" + directorio + "");
		File archivo = new File("src/" + directorio + "/" + fichero + ".txt");

		if (!(carpeta.exists())) {
			if (carpeta.mkdir()) {
				System.out.println("Se ha creado la carpeta");
			} else {
				System.out.println("Error al crear directorio");
			}
			try {
				archivo.createNewFile();
				System.out.println("se ha creado el fichero");
				escribirArchivo(archivo);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (carpeta.exists()) {
			System.out.println("La carpeta existe");
			if (!(archivo.exists())) {
				try {
					archivo.createNewFile();
					System.out.println("se ha creado el fichero");
					escribirArchivo(archivo);
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				System.out.println("El fichero ya existe");
			}
		}

	}

	public static void buscarTexto(String nombreFichero, String texto) {
		File archivo = new File(nombreFichero + ".txt");
		if (archivo.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
				String contenido;
				int contador = 0;
				while ((contenido = br.readLine()) != null) {
					if (contenido.equalsIgnoreCase(texto)) {
						contador++;
					}
				}
				br.close();
				System.out.println("cantidad de repeticiones del texto -> " + contador);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("El fichero ingresado no existe");
		}

	}

	public static void escribirArchivo(File archivo) {

		try {
			FileWriter fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			ArrayList<String> lista = new ArrayList<String>();
			lista.add("Perro");
			lista.add("Gato");
			lista.add("Juan");
			lista.add("Daniel");
			lista.add("Juan");
			lista.add("Gato");
			lista.add("Perro");
			lista.add("Camila");
			lista.add("Daniel");
			lista.add("Camila");

			Iterator<String> itTexto = lista.iterator();
			while (itTexto.hasNext()) {
				String texto = itTexto.next();
				bw.write(texto + "\n");
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

