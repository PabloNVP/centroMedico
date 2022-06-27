package ventana.centroMedico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoPaciente extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoPaciente instancia;
	
	private final String NOMBRE_VENTANA = "Ingresar datos del paciente";
	private final String INGRESAR_NUEVO = "Se han guardado los datos del paciente correctamente, ¿Desea ingresar otro?";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(NOMBRE_VENTANA);
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
		setTitle(CENTROMEDICO.TITULO + " - " + NOMBRE_VENTANA);
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
				public void actionPerformed(ActionEvent event) {
					String codPac = codPacienteJTF.getText();
					String nomPac = nomPacienteJPF.getText();
					
					try {
						CENTROMEDICO.ingresarPaciente(codPac,nomPac);
						
						int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, NOMBRE_VENTANA, JOptionPane.YES_NO_OPTION);
						
						if(opcion == JOptionPane.NO_OPTION) {
							cerrarVentana();
						}
						
						resetearVentana();
						mensajeJL.setText("");
					} catch (Exception e) {
						mensajeJL.setText(e.getMessage());
					}
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
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
	
	private void resetearVentana() {
		codPacienteJTF.setText("");
		nomPacienteJPF.setText("");
	}
	
	private void cerrarVentana() {
		VentanaIngresoPaciente.getInstancia().setVisible(false);
		VentanaIngreso.getInstancia().setVisible(true);
	}
}
