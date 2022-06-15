package ventana.centroMedico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaIngreso extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaIngreso instancia;
	
	private final String titulo = "CENTRO MEDICO UNLAM";
	private final String nombreVentana = "Ingreso de Pacientes";
	
	private JLabel tituloJL = new JLabel(titulo);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton datosJB = new JButton("Ingresar datos del Paciente");
	private JButton situacionJB = new JButton("Ingresar situación del Paciente");
	private JButton medicoJB = new JButton("Ingresar datos del Medico");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaIngreso(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(titulo + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaIngreso getInstancia() {
		if(instancia == null)
			instancia = new VentanaIngreso();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(255, 96, 256, 32);
			
			datosJB.setBounds(192, 192, 256, 32);
			situacionJB.setBounds(192,  256, 256, 32);
			medicoJB.setBounds(192, 320, 256, 32);
			volverJB.setBounds(192, 384, 256, 32);
			
			datosJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().setVisible(false);
					VentanaIngresoPaciente.getInstancia().setVisible(true);
				}
			});
			
			situacionJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().setVisible(false);
					VentanaIngresoSituacion.getInstancia().setVisible(true);
				}
			});
			
			medicoJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().setVisible(false);
					VentanaIngresoMedico.getInstancia().setVisible(true);
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().setVisible(false);
					VentanaInicio.getInstancia().setVisible(true);
				}
			});
			
			add(tituloJL);
			add(nombreVentanaJL);
			add(datosJB);
			add(situacionJB);
			add(medicoJB);
			add(volverJB);
		}
	}
}
