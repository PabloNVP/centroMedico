package ventana.centroMedico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoMedico extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoMedico instancia;

	private static final int MIN_CODIGO = 0;
	private static final int MIN_NOMBRE = 1;
	private static final int MAX_CODIGO = 10000;
	private static final int MAX_NOMBRE = 21;
	private static final String REGEX_NOMBRE_PATTERN = "^[A-Za-z0-]+[A-Za-z0-9? ´]*$";
	private static final String[] ESPECIALIDADES = {"Pediatría", "Traumatología", "Cardiología"};
	private final static String ERROR_CODIGO_RANGO = "El código debe ser un número entre 0 y 10000.";
	private final static String ERROR_NOMBRE_RANGO = "El nombre debe contener un mínimo de 2 caracteres y un máximo de 20.";
	private final static String ERROR_NOMBRE_FORMATO = "El nombre debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios.";
	private final static String ERROR_ESPECIALIDAD_VALORES = "La especialidad solo puede ser Pediatría, Traumatología o Cardiología.";
	
	private final String nombreVentana = "Ingresar datos del medico";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel nomMedicoJL = new JLabel("Nombre del medico:");
	private JLabel espMedicoJL = new JLabel("Especialización del medico:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private JTextField nomMedicoJTF = new JTextField();
	private JComboBox<String> espMedicoJTF = new JComboBox<String>(ESPECIALIDADES);
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngresoMedico(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaIngresoMedico getInstancia() {
		if(instancia == null)
			instancia = new VentanaIngresoMedico();
		
		return instancia;
	}
	
	public static boolean verificarDatosMedico(int codigoMedico, String nombreMedico, String especialidadMedico, JLabel label) {
		if (!verificarCodigo(codigoMedico)) {
			mostrarMensaje(label, ERROR_CODIGO_RANGO);
			return false;
		}
		
		if (!verificarRangoNombre(nombreMedico)) {
			mostrarMensaje(label, ERROR_NOMBRE_RANGO);
			return false;
		}
		
		if (!verificarFormatoNombre(nombreMedico)) {
			mostrarMensaje(label, ERROR_NOMBRE_FORMATO);
			return false;
		}
		
		if (!verificarEspecialidad(especialidadMedico)) {
			mostrarMensaje(label, ERROR_ESPECIALIDAD_VALORES);
			return false;
		}
		
		mostrarMensaje(label, "");
		
		return true;
	}
	
	private static boolean verificarCodigo(int codigoMedico) {
		return codigoMedico > MIN_CODIGO && codigoMedico < MAX_CODIGO;
	}
	
	private static boolean verificarRangoNombre(String nombreMedico) {
		int longitud = nombreMedico.length();
		
		return longitud > MIN_NOMBRE && longitud < MAX_NOMBRE;
	}
	
	private static boolean verificarFormatoNombre(String nombreMedico) {
		return Pattern.matches(REGEX_NOMBRE_PATTERN, nombreMedico);
	}
	
	private static boolean verificarEspecialidad(String especialidadMedico) {
		return Arrays.stream(ESPECIALIDADES).anyMatch(especialidad -> especialidad.equals(especialidadMedico));
	}
	
	private static void mostrarMensaje(JLabel label, String mensaje) {
		label.setText(mensaje);
	}
	
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(226, 96, 256, 32);
			
			codMedicoJL.setBounds(128, 160, 192, 32);
			nomMedicoJL.setBounds(128, 192, 192, 32);
			espMedicoJL.setBounds(92, 224, 224, 32);
			
			codMedicoJTF.setBounds(288, 165, 192, 24);
			nomMedicoJTF.setBounds(288, 197, 192, 24);

			espMedicoJTF.setBounds(288, 229, 192, 24);
			
			mensajeJL.setBounds(160, 255, 320, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						int codigoMedico = Integer.valueOf(codMedicoJTF.getText());
						String nombreMedico = nomMedicoJTF.getText();
						String especialidadMedico = ESPECIALIDADES[espMedicoJTF.getSelectedIndex()];
						
						if (verificarDatosMedico(codigoMedico, nombreMedico, especialidadMedico, mensajeJL)) {
							CENTROMEDICO.ingresarMedico(String.valueOf(codigoMedico), nombreMedico, especialidadMedico);
						}
					} catch(IOException ioe) {
						mostrarMensaje(mensajeJL, ioe.getMessage());
					}
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngresoMedico.getInstancia().setVisible(false);
					VentanaIngreso.getInstancia().setVisible(true);
				}
			});
			
			add(codMedicoJL);
			add(nomMedicoJL);
			add(espMedicoJL);
			add(codMedicoJTF);
			add(nomMedicoJTF);
			add(espMedicoJTF);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
}
