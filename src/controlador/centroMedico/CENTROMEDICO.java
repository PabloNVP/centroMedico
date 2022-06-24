package controlador.centroMedico;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import ventana.centroMedico.VentanaConectar;

public class CENTROMEDICO {
	// Dimensiones de la interfaz
	public final static int ALTO = 640;
	public final static int ANCHO = 480;

	// Titulo de la interfaz
	public final static String TITULO = "CENTRO MEDICO UNLAM";

	// Mensajes de Errores
	public final static String ERROR_CODIGO_RANGO_MEDICO = "El código debe ser un número entre 0 y 10000.";
	public final static String ERROR_NOMBRE_RANGO_MEDICO = "El nombre debe contener un mínimo de 2 caracteres y un máximo de 20.";
	public final static String ERROR_NOMBRE_FORMATO_MEDICO = "El nombre debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios.";
	public final static String ERROR_ESPECIALIDAD_VALORES_MEDICO = "La especialidad solo puede ser Pediatría, Traumatología o Cardiología.";
	public final static String ERROR_ARCHIVO_MEDICO = "";

	private static final String DATO_MED_PATH = "./datomed.txt";
	private static final String DATO_PAC_PATH = "./datopac.txt";
	private static final String SITU_PAC_PATH = "./situpac.txt";

	// Obsoleto
	public static void ps(String x) {
		System.out.print(x);
	}

	public static int LeerEntero() {
		int entero = 0;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			entero = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (entero);
	}

	public static String LeerCadena() {
		String linea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			linea = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (linea);
	}

	public static void menuIngresoDeDatos() {
//		int opcion = 0;
//
//		do {
//			ps("   ..............................................." + "\n");
//			ps("   :-: -I N G R E S O  D E  P A C I E N T E S- :-:" + "\n");
//			ps("   :-:.........................................:-:" + "\n");
//			ps("   :-: 1.  Datos del paciente                  :-:" + "\n");
//			ps("   :-: 2.  Situacion del paciente              :-:" + "\n");
//			ps("   :-: 3.  Datos del medico                    :-:" + "\n");
//			ps("   :-: 4.  VOLVER                              :-:" + "\n");
//			ps("   ..............................................." + "\n");
//			ps("   ....Elija la opcion deseada: ");
//
//			opcion = LeerEntero();
//
//			if (opcion == 1)
////				ingresarPaciente();
//			else if (opcion == 2)
//				ingresarSituacionPaciente();
//			else if (opcion == 3)
//				ps("HOLA");
//			// ingresarMedico();
//			else if (opcion != 4)
//				ps("Debe digitar una opcion del menu" + "\n");
//
//		} while (opcion != 4);
	}

	public static void menuInformes() {
		int opcion;

		do {
			ps("   ..............................................." + "\n");
			ps("   :-:  C O N T R O L  D E  P A C I E N T E S  :-:" + "\n");
			ps("   ..............................................." + "\n");
			ps("   :-:           - I N F O R M E S -           :-:" + "\n");
			ps("   :-:.........................................:-:" + "\n");
			ps("   :-: 1. Listado de pacientes por medico      :-:" + "\n");
			ps("   :-: 2. Enfermedades que atiende cada medico :-:" + "\n");
			ps("   :-: 3. Anterior                             :-:" + "\n");
			ps("   ..............................................." + "\n");
			ps("   ....Elija la opcion deseada: ");

			opcion = LeerEntero();

			if (opcion == 1)
				listarPacientesPorMedico();
			/*
			 * else if(opcion == 2) listarEnfermedadesPorMedico();
			 */
			else if (opcion != 3)
				ps("Seleccione una de las opciones del menu" + "\n");

		} while (opcion != 3);
	}

	public static void ingresarPaciente(String codPac, String nomPac) {

		try {
			DataOutputStream datomed = new DataOutputStream(new FileOutputStream(DATO_MED_PATH, true));

			datomed.writeUTF(codPac);
			datomed.writeUTF(nomPac);

			datomed.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			String codpac, nompac, op;
//			DataOutputStream datopac = new DataOutputStream(new FileOutputStream(DATO_PAC_PATH));
//
//			do {
//				ps("   ..............................................." + "\n");
//				ps("   :-:  - D A T O S  D E L  P A C I E N T E -  :-:" + "\n");
//				ps("   :-:.........................................:-:" + "\n");
//
//				ps("Digite el codigo del paciente: ");
//				codpac = LeerCadena();
//				datopac.writeUTF(codpac);
//
//				ps("Digite el nombre del paciente: ");
//				nompac = LeerCadena();
//				datopac.writeUTF(nompac);
//
//				ps("Desea ingresar otro paciente? S/N" + "\n");
//				op = LeerCadena();
//
//			} while (op.equals("S") || op.equals("s"));
//			datopac.close();
//
//		} catch (IOException ioe) {
//			// mensaje de error
//		}
	}

	public static void ingresarSituacionPaciente() {
		try {
			String codp, codm, enfpac, op;
			DataOutputStream situpac = new DataOutputStream(new FileOutputStream(SITU_PAC_PATH));

			do {
				ps("   ....................................................." + "\n");
				ps("   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:" + "\n");
				ps("   :-:...............................................:-:" + "\n");

				ps("Digite el codigo del paciente: ");
				codp = LeerCadena();
				situpac.writeUTF(codp);

				ps("Digite el codigo del medico: ");
				codm = LeerCadena();
				situpac.writeUTF(codm);

				ps("Digite el diagnostico del medico: ");
				enfpac = LeerCadena();
				situpac.writeUTF(enfpac);

				ps("Desea ingresar otro registro al historial? S/N");
				ps("\n");
				op = LeerCadena();

			} while (op.equals("S") || op.equals("s"));

			situpac.close();
		} catch (IOException ioe) {
			// Mensaje Error
			System.err.println("Error paciente");
		}
	}

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static IvParameterSpec generateIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public static void ingresarMedico(String codmed, String nommed, String espmed) throws IOException {
		// Abre el archivo haciendo referencia al final del mismo.
		SecretKey key = null;
		IvParameterSpec ivParameterSpec = generateIv();
		IvParameterSpec ivParameterSpec2 = null;
		DataOutputStream datomed = new DataOutputStream(new FileOutputStream(DATO_MED_PATH, true));

		try {
			key = getKeyFromPassword("asd123", "asd123");
			for (int i = 0; i < ivParameterSpec.getIV().length; i++) {
				datomed.writeByte(ivParameterSpec.getIV()[i]);
			}
			datomed.writeUTF(encrypt("AES/CBC/PKCS5Padding", codmed, key, ivParameterSpec));
			datomed.writeUTF(encrypt("AES/CBC/PKCS5Padding", nommed, key, ivParameterSpec));
			datomed.writeUTF(encrypt("AES/CBC/PKCS5Padding", espmed, key, ivParameterSpec));
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException
				| InvalidKeySpecException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
//		datomed.writeUTF(codmed);
//		datomed.writeUTF(nommed);
//		datomed.writeUTF(espmed);

		datomed.close();

		DataInputStream datomedIn = new DataInputStream(new FileInputStream(DATO_MED_PATH));
		byte[] iv = new byte[16];
		for (int i = 0; i < 16; i++) {
			byte asd = datomedIn.readByte();
			iv[i] = asd;
		}
		ivParameterSpec2 = new IvParameterSpec(iv);
		try {
			String dato = datomedIn.readUTF();
			String desenc = decrypt("AES/CBC/PKCS5Padding", dato, getKeyFromPassword("asd123", "asd123"), ivParameterSpec2);
			System.out.println(desenc);
			dato = datomedIn.readUTF();
			desenc = decrypt("AES/CBC/PKCS5Padding", dato, getKeyFromPassword("asd123", "asd123"), ivParameterSpec2);
			System.out.println(desenc);
			dato = datomedIn.readUTF();
			desenc = decrypt("AES/CBC/PKCS5Padding", dato, getKeyFromPassword("asd123", "asd123"), ivParameterSpec2);
			System.out.println(desenc);
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException
				| InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datomedIn.close();
		/*
		 * try { String op; DataOutputStream datomed = new DataOutputStream(new
		 * FileOutputStream("C:\\datomed.txt"));
		 * 
		 * datomed.writeUTF(codmed); datomed.writeUTF(nommed); datomed.writeUTF(espmed);
		 * 
		 * datomed.close();
		 * 
		 * do { ps("   ....................................................." + "\n");
		 * ps("   :-:      - D A T O S   D E L   M E D I C O -      :-:" + "\n");
		 * ps("   :-:...............................................:-:" + "\n");
		 * 
		 * ps("Digite el codigo del medico: "); codmed = LeerCadena();
		 * datomed.writeUTF(codmed);
		 * 
		 * ps("Digite el nombre del medico: "); nommed = LeerCadena();
		 * datomed.writeUTF(nommed);
		 * 
		 * ps("Digite la especializacion del medico: "); espmed = LeerCadena();
		 * datomed.writeUTF(espmed);
		 * 
		 * ps("Desea ingresar otro medico? S/N"); ps("\n");
		 * 
		 * op = LeerCadena();
		 * 
		 * } while (op.equals("S") || op.equals("s"));
		 * 
		 * datomed.close(); } catch (IOException ioe) { //Mensaje de Error }
		 */
	}

	@SuppressWarnings("resource")
	public static void listarPacientesPorMedico() {
		int sw = 0, sw1 = 0;
		String codtem, codm = "", nomm = "", espm, codp, codme, enfp, codpa, nompa;

		ps("Digite el codigo del medico que desea consultar: ");
		codtem = LeerCadena();

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
						ps("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");
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
										ps("::: Paciente: " + nompa + "\n");
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

	public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

	public static void main(String args[]) throws Exception {

		VentanaConectar ventanaConectar = new VentanaConectar();
		ventanaConectar.setVisible(true);

		/*
		 * int opcion;
		 * 
		 * do { ps("   .............................................." + "\n");
		 * ps("   :-:           CENTRO MEDICO                :-:" + "\n");
		 * ps("   :-:   >>>>     U N L A M   <<<<            :-:" + "\n");
		 * ps("   :-:    M E S A   D E    A D M I S I O N    :-:" + "\n");
		 * ps("   :-:........................................:-:" + "\n");
		 * ps("   :-: 1.  Ingreso de datos                   :-:" + "\n");
		 * ps("   :-: 2.  Informes                           :-:" + "\n");
		 * ps("   :-: 3.  Salir                              :-:" + "\n");
		 * ps("   .............................................." + "\n");
		 * ps("   ....Elija la opcion deseada: ");
		 * 
		 * opcion = LeerEntero();
		 * 
		 * //Seleccion menu ingreso de pacientes if (opcion == 1) menuIngresoDeDatos();
		 * else if(opcion == 2) // Seleccion menu informes menuInformes(); else
		 * if(opcion != 3) ps("Debe digitar una opcion del menu" + "\n");
		 * 
		 * } while (opcion != 3);
		 */
	}
}
