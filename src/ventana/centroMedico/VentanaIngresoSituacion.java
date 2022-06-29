package ventana.centroMedico;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngresoSituacion extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngresoSituacion instancia;
	
	private final String NOMBRE_VENTANA = "Ingresar situación del paciente";
	private final String INGRESAR_NUEVO = "Se ha guardado el historial correctamente, ¿Desea ingresar otro?";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(NOMBRE_VENTANA);
	private JLabel codPacienteJL = new JLabel("Codigo del paciente:");
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel sitPacienteJL = new JLabel("Diagnostico del paciente:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codPacienteJTF = new JTextField();
	private JTextField codMedicoJTF = new JTextField();
	private JTextArea sitPacienteJTA = new JTextArea(5, 4);
	private JScrollPane sitPacienteJSP = new JScrollPane(sitPacienteJTA);
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngresoSituacion(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + NOMBRE_VENTANA);
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
			
			tituloJL.setBounds(170, 64, 320, 32);
			tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
			nombreVentanaJL.setBounds(180, 96, 360, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			codPacienteJL.setBounds(128, 160, 192, 32);
			codMedicoJL.setBounds(128, 192, 192, 32);
			sitPacienteJL.setBounds(96, 232, 192, 32);
			
			codPacienteJTF.setBounds(288, 165, 192, 24);
			codMedicoJTF.setBounds(288, 197, 192, 24);
			sitPacienteJSP.setBounds(288, 224, 192, 64);
			sitPacienteJTA.setLineWrap(true);
			
			mensajeJL.setBounds(140, 300, 400, 32);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 340, 256, 32);
			volverJB.setBounds(192,  388, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					String codPaciente = codPacienteJTF.getText();
					String codMedico = codMedicoJTF.getText();
					String situacion = sitPacienteJTA.getText();
					
					try {
						CENTROMEDICO.ingresarSituacionPaciente(codPaciente, codMedico, situacion);
					
						int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, NOMBRE_VENTANA, JOptionPane.YES_NO_OPTION);
						
						if(opcion == JOptionPane.NO_OPTION) {
							cerrarVentana();
						}
						
						resetearVentana();
					}catch(Exception e){
						mensajeJL.setText("<html>" + e.getMessage() + "</html>");
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
			add(codMedicoJL);
			add(sitPacienteJL);
			add(codPacienteJTF);
			add(codMedicoJTF);
			add(sitPacienteJSP);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
	
	private void resetearVentana() {
		codPacienteJTF.setText("");
		codMedicoJTF.setText("");
		sitPacienteJTA.setText("");
		mensajeJL.setText("");
	}
	
	private void cerrarVentana() {
		resetearVentana();
		VentanaIngresoSituacion.getInstancia().setVisible(false);
		VentanaIngreso.getInstancia().setVisible(true);
	}
}
