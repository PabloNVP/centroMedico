package ventana.centroMedico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoSituacion extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoSituacion instancia;
	
	private final String titulo = "CENTRO MEDICO UNLAM";
	private final String nombreVentana = "Ingresar situación del paciente";
	
	private JLabel tituloJL = new JLabel(titulo);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel codPacienteJL = new JLabel("Codigo del paciente:");
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel sitPacienteJL = new JLabel("Diagnostico del paciente:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codPacienteJTF = new JTextField();
	private JTextField codMedicoJTF = new JTextField();
	private JTextArea sitPacienteJTA = new JTextArea();
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngresoSituacion(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(titulo + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaIngresoSituacion getInstancia() {
		if(instancia == null)
			instancia = new VentanaIngresoSituacion();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(208, 96, 256, 32);
			
			codPacienteJL.setBounds(128, 160, 192, 32);
			codMedicoJL.setBounds(128, 192, 192, 32);
			sitPacienteJL.setBounds(96, 232, 192, 32);
			
			codPacienteJTF.setBounds(288, 165, 192, 24);
			codMedicoJTF.setBounds(288, 197, 192, 24);
			sitPacienteJTA.setBounds(288, 224, 192, 64);
			
			mensajeJL.setBounds(200, 245, 256, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// CENTROMEDICO.ingresarSituacionPaciente();
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngresoSituacion.getInstancia().setVisible(false);
					VentanaIngreso.getInstancia().setVisible(true);
				}
			});
			
			add(codPacienteJL);
			add(codMedicoJL);
			add(sitPacienteJL);
			add(codPacienteJTF);
			add(codMedicoJTF);
			add(sitPacienteJTA);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
}
