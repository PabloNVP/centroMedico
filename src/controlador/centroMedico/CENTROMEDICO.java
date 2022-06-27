package controlador.centroMedico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import ventana.centroMedico.VentanaConectar;

public class CENTROMEDICO {
	// Dimensiones de la interfaz
	public final static int ALTO = 640;
	public final static int ANCHO = 480;

	// Titulo de la interfaz
	public final static String TITULO = "CENTRO MEDICO UNLAM";

	// Mensajes de Errores
	public final static String ERROR_CODIGO_RANGO_MEDICO = "El código del medico debe ser un número entre 1 y 9999.";
	public final static String ERROR_NOMBRE_RANGO_MEDICO = "El nombre del medico debe contener un mínimo de 2 caracteres y un máximo de 20.";
	public final static String ERROR_NOMBRE_FORMATO_MEDICO = "El nombre del medico debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios.";
	public final static String ERROR_ESPECIALIDAD_VALORES_MEDICO = "La especialidad solo puede ser Pediatría, Traumatología o Cardiología.";
	
	public final static String ERROR_CODIGO_RANGO_PACIENTE = "El código del paciente debe ser un número entre 1 y 9999.";
	public final static String ERROR_FORMATO_SITUACION = "El diagnostico del paciente debe contener un minimo de 10 y un maximo de 200 caracteres.";
	
	public final static String ERROR_ARCHIVO_PACIENTE = "No se pudo guardar los datos del paciente en el archivo.";
	public final static String ERROR_ARCHIVO_MEDICO = "No se pudo guardar los datos del medico en el archivo.";
	public final static String ERROR_ARCHIVO_SITUACION = "No se pudo guardar el historial en el archivo.";
	
	// Path de archivos
	private static final String DATO_MED_PATH = "./datomed.txt";
	private static final String DATO_PAC_PATH = "./datopac.txt";
	private static final String SITU_PAC_PATH = "./situpac.txt";

	// Clave de Encriptado
	private static final String CLAVE="AnalisiSdeSoftwarE2022";

	public static void ingresarPaciente(String codPac, String nomPac) throws Exception{
		try {
			// Abre el archivo haciendo referencia al final del mismo.
			DataOutputStream archivo = new DataOutputStream(new FileOutputStream(DATO_PAC_PATH, true));

			// Escribe en el archivo los datos del paciente encriptados.
			archivo.writeUTF(encriptar(codPac));
			archivo.writeUTF(encriptar(nomPac));

			archivo.close();
		} catch (IOException e) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(ERROR_ARCHIVO_PACIENTE);
		}
	}

	public static void ingresarSituacionPaciente(String codPac, String codMed, String sit) throws Exception {
		try {
			if(!codPac.matches("^([0-9]?[0-9]?[0-9]?[1-9])$")){
				throw new Exception(ERROR_CODIGO_RANGO_PACIENTE);
			}
			
			if(!codMed.matches("^([0-9]?[0-9]?[0-9]?[1-9])$")){
				throw new Exception(ERROR_CODIGO_RANGO_MEDICO);
			}
			
			if(!sit.matches("^[a-zA-Z]{1}([\\w\\s,.]{9,199})$")) {
				throw new Exception(ERROR_FORMATO_SITUACION);
			}
			
			// Abre el archivo haciendo referencia al final del mismo.
			DataOutputStream archivo = new DataOutputStream(new FileOutputStream(SITU_PAC_PATH, true));

			// Escribe en el archivo el historial encriptado.
			archivo.writeUTF(encriptar(codPac));
			archivo.writeUTF(encriptar(codMed));
			archivo.writeUTF(encriptar(sit));
			
			archivo.close();
		} catch (IOException ioe) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(ERROR_ARCHIVO_SITUACION);
		}
	}

	public static void ingresarMedico(String codmed, String nommed, String espmed) throws Exception{
		try {
			// Abre el archivo haciendo referencia al final del mismo.
			DataOutputStream archivo = new DataOutputStream(new FileOutputStream(DATO_MED_PATH, true));
		
			// Escribe en el archivo los datos del medico encriptados.
			archivo.writeUTF(encriptar(codmed));
			archivo.writeUTF(encriptar(nommed));
			archivo.writeUTF(encriptar(espmed));
			
			archivo.close();
		} catch (IOException e) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(ERROR_ARCHIVO_MEDICO);
		} 
	}

	@SuppressWarnings("resource")
	public static void listarPacientesPorMedico() {
		int sw = 0, sw1 = 0;
		String codtem = "", codm = "", nomm = "", espm, codp, codme, enfp, codpa, nompa;
/*
		ps("Digite el codigo del medico que desea consultar: ");
		codtem = LeerCadena();*/

		sw = 1;
		while (sw != 0) {
			try {
				DataInputStream datomed = new DataInputStream(new FileInputStream(DATO_MED_PATH));

				codm = datomed.readUTF();
				nomm = datomed.readUTF();
				espm = datomed.readUTF();
			} catch (IOException e) {
				sw = 0;
			}

			// compara el codigo extraido de la tabla "datomed" con el codigo digitado
			if (codm.equals(codtem)) {
				sw = 1;
				while (sw != 0) {
					try {
						//ps("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");
						DataInputStream situpac = new DataInputStream(new FileInputStream(SITU_PAC_PATH));

						codp = situpac.readUTF();
						codme = situpac.readUTF();
						enfp = situpac.readUTF();

						// compara el codigo medico de la tabla "datomed" con el de la tabla "situpac"
						if (codme.equals(codtem)) {
							DataInputStream datopac = new DataInputStream(new FileInputStream(DATO_PAC_PATH));

							sw1 = 1;
							while (sw1 != 0) {
								try {
									codpa = datopac.readUTF();
									nompa = datopac.readUTF();

									// compara el codigo del paciente de la tabla "situpac" con el codigo del
									// paciente de la tabla "datopac"
									if (codpa.equals(codp)) {
										//ps("::: Paciente: " + nompa + "\n");
									}
								} catch (EOFException e) {
									sw1 = 0;
								}
							}

							datopac.close();
						}
						situpac.close();
					} catch (IOException e) {
						sw = 0;
					}
				}

			}
		}
	}

	public static ArrayList<String> listarEnfermedadesPorMedico(String codigo) throws Exception {
		// String codtem;
		// Variables de corte de control
		int sw = 0, sw1 = 0;

		// Variables usadas en la lectura de datos.
		String codm = "", codme = "", enfp = "", nomm = "", espm = "", codp;

		// ps("Digite el codigo del medico que desea consultar: ");
		// codtem = LeerCadena();

		// Arreglo de información
		ArrayList<String> pacientes = new ArrayList<String>();

		try {
			DataInputStream datomed = new DataInputStream(new FileInputStream(DATO_MED_PATH));
			sw1 = 1;

			while (sw1 != 0) {
				try {
					codm = datomed.readUTF();
					nomm = datomed.readUTF();
					espm = datomed.readUTF();

					// compara el codigo digitado
					// con el codigo del medico de la
					// tabla "datomed"
					if (codm.equals(codigo)) {
						// ps("El medico " + nomm + " trata las siguientes enfermedades:" + "\n");
						pacientes.add("Nombre del Medico: " + nomm);

						DataInputStream situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));

						pacientes.add("Nombre de las enfermedades: ");

						sw = 1;

						while (sw != 0) {
							try {
								codp = situpac.readUTF();
								codme = situpac.readUTF();
								enfp = situpac.readUTF();

								// compara el codigo del medico
								// de la tabla "datomed"
								// con el codigo del medico en la
								// tabla "situpac"
								if (codme.equals(codigo)) {
									// ps(">>>> " + enfp + "\n");
									pacientes.add("   " + enfp);
								}
							} catch (EOFException e) {
								sw = 0;
							}

							situpac.close();
						}
					}
				} catch (EOFException e) {
					sw1 = 0;
				}
			}
			datomed.close();
		} catch (FileNotFoundException e) {
			throw new Exception("louc");
		}

		return pacientes;
	}

	private static String encriptar(String mensaje) throws Exception {	
		Key aesKey = new SecretKeySpec(CLAVE.getBytes(), 0, 16, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);

		byte[] encrypted = cipher.doFinal(mensaje.getBytes());
			
		return Base64.getEncoder().encodeToString(encrypted);
	}

	private static String desencriptar(String mensaje) throws Exception {
		Key aesKey = new SecretKeySpec(CLAVE.getBytes(), 0, 16, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);

		String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(mensaje)));
	        
		return decrypted;
	}

	public static void main(String args[]) throws Exception {
		VentanaConectar ventanaConectar = new VentanaConectar();
		ventanaConectar.setVisible(true);
	}
}
