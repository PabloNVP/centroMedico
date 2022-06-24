package ventana.centroMedico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoPaciente extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoPaciente instancia;
	
	private final String nombreVentana = "Ingresar datos del paciente";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel codPacienteJL = new JLabel("Codigo del paciente:");
	private JLabel nomPacienteJL = new JLabel("Nombre del paciente:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codPacienteJTF = new JTextField();
	private JTextField nomPacienteJPF = new JTextField();
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngresoPaciente(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaIngresoPaciente getInstancia() {
		if(instancia == null)
			instancia = new VentanaIngresoPaciente();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(226, 96, 256, 32);
			
			codPacienteJL.setBounds(128, 160, 192, 32);
			nomPacienteJL.setBounds(128, 192, 192, 32);
			
			codPacienteJTF.setBounds(288, 165, 192, 24);
			nomPacienteJPF.setBounds(288, 197, 192, 24);
			
			mensajeJL.setBounds(200, 245, 256, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String codPac = codPacienteJTF.getText();
					String nomPac = nomPacienteJPF.getText();
					
					CENTROMEDICO.ingresarPaciente(codPac,nomPac);
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngresoPaciente.getInstancia().setVisible(false);
					VentanaIngreso.getInstancia().setVisible(true);
				}
			});
			
			add(codPacienteJL);
			add(nomPacienteJL);
			add(codPacienteJTF);
			add(nomPacienteJPF);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
}
