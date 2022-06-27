package ventana.centroMedico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoMedico extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoMedico instancia;

	private static final int MIN_NOMBRE = 1;
	private static final int MAX_NOMBRE = 21;
	private static final String REGEX_CODIGO_PATTERN = "^[1-9][0-9]{0,3}$";
	private static final String REGEX_NOMBRE_PATTERN = "^[A-Za-z0-]+[A-Za-z0-9? ´]*$";
	private static final String[] ESPECIALIDADES = {"Pediatr�a", "Traumatolog�a", "Cardiolog�a"};
	
	private final String NOMBRE_VENTANA = "Ingresar datos del medico";
	private final String INGRESAR_NUEVO = "Se han guardado los datos del Medico correctamente, ¿Desea ingresar otro?";
	
	// AYUDAS
	private final String COD_MEDICO_AYUDA = "El codigo del medico debe ser un numero entero entre 1 y 9999.";
	private final String NOM_MEDICO_AYUDA = "El nombre del medico debe contener un mínimo de 2 caracteres y un máximo de 20, empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios.";
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(NOMBRE_VENTANA);
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel nomMedicoJL = new JLabel("Nombre del medico:");
	private JLabel espMedicoJL = new JLabel("Especialización del medico:");
	private JLabel codMedicoAyuda = new JLabel("?");
	private JLabel nomMedicoAyuda = new JLabel("?");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private JTextField nomMedicoJTF = new JTextField();
	private JComboBox<String> espMedicoJTF = new JComboBox<String>(ESPECIALIDADES);
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngresoMedico(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + NOMBRE_VENTANA);
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
	
	public static boolean verificarDatosMedico(String codigoMedico, String nombreMedico, String especialidadMedico, JLabel label) {
		if (!verificarCodigo(codigoMedico)) {
			mostrarMensaje(label, CENTROMEDICO.ERROR_CODIGO_RANGO_MEDICO);
			return false;
		}
		
		if (!verificarRangoNombre(nombreMedico)) {
			mostrarMensaje(label, CENTROMEDICO.ERROR_NOMBRE_RANGO_MEDICO);
			return false;
		}
		
		if (!verificarFormatoNombre(nombreMedico)) {
			mostrarMensaje(label, CENTROMEDICO.ERROR_NOMBRE_FORMATO_MEDICO);
			return false;
		}
		
		if (!verificarEspecialidad(especialidadMedico)) {
			mostrarMensaje(label, CENTROMEDICO.ERROR_ESPECIALIDAD_VALORES_MEDICO);
			return false;
		}
		
		mostrarMensaje(label, "");
		
		return true;
	}
	
	private static boolean verificarCodigo(String codigoMedico) {
		return Pattern.matches(REGEX_CODIGO_PATTERN, codigoMedico);
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
			
			codMedicoAyuda.setBounds(490, 168, 16, 16);
			codMedicoAyuda.setToolTipText(COD_MEDICO_AYUDA);
			codMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			codMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			codMedicoAyuda.setOpaque(true);
			
			nomMedicoAyuda.setBounds(490, 200, 16, 16);
			nomMedicoAyuda.setToolTipText(NOM_MEDICO_AYUDA);
			nomMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			nomMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			nomMedicoAyuda.setOpaque(true);
			
			mensajeJL.setBounds(160, 255, 320, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						String codigoMedico = codMedicoJTF.getText();
						String nombreMedico = nomMedicoJTF.getText();
						String especialidadMedico = ESPECIALIDADES[espMedicoJTF.getSelectedIndex()];
						
						if (verificarDatosMedico(codigoMedico, nombreMedico, especialidadMedico, mensajeJL)) {
							CENTROMEDICO.ingresarMedico(codigoMedico, nombreMedico, especialidadMedico);
							
							int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, NOMBRE_VENTANA, JOptionPane.YES_NO_OPTION);
						
							if(opcion == JOptionPane.NO_OPTION) {
								cerrarVentana();
							}
							
							resetearVentana();
							mensajeJL.setText("");
						}
					} catch(Exception e) {
						mostrarMensaje(mensajeJL, e.getMessage());
					}
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			
			add(codMedicoJL);
			add(nomMedicoJL);
			add(espMedicoJL);
			add(codMedicoJTF);
			add(nomMedicoJTF);
			add(espMedicoJTF);
			add(codMedicoAyuda);
			add(nomMedicoAyuda);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
	
	private void resetearVentana() {
		codMedicoJTF.setText("");
		nomMedicoJTF.setText("");
	}
	
	private void cerrarVentana() {
		VentanaIngresoMedico.getInstancia().setVisible(false);
		VentanaIngreso.getInstancia().setVisible(true);
	}
}
