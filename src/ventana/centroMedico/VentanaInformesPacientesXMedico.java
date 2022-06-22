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

public class VentanaInformesPacientesXMedico extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaInformesPacientesXMedico instancia;
	
	private final String nombreVentana = "Informes de pacientes por medico";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private JTextArea resultadoJTA = new JTextArea();
	private JButton buscarJB = new JButton("Buscar");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaInformesPacientesXMedico(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaInformesPacientesXMedico getInstancia() {
		if(instancia == null)
			instancia = new VentanaInformesPacientesXMedico();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(176, 96, 320, 32);
			
			codMedicoJL.setBounds(128, 126, 192, 32);
		
			codMedicoJTF.setBounds(284, 130, 128, 24);
			resultadoJTA.setBounds(128, 164, 406, 192);
			
			mensajeJL.setBounds(200, 245, 256, 24);
			mensajeJL.setForeground(Color.RED);
			
			buscarJB.setBounds(420, 123, 128, 32);
			volverJB.setBounds(192,  388, 256, 32);

			buscarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// CENTROMEDICO.listarPacientesPorMedico();
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaInformesPacientesXMedico.getInstancia().setVisible(false);
					VentanaInformes.getInstancia().setVisible(true);
				}
			});
			
			add(codMedicoJL);
			add(codMedicoJTF);
			add(resultadoJTA);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(buscarJB);
			add(volverJB);
		}
	}
}
