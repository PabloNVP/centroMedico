package ventana.centroMedico;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaInformes extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaInformes instancia;
	
	private final String titulo = "CENTRO MEDICO UNLAM";
	private final String nombreVentana = "Informes";
	
	private JLabel tituloJL = new JLabel(titulo);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton listarPacientesJB = new JButton("Listado de pacientes por medico");
	private JButton enfermedadesMedicoJB = new JButton("Listado de enfermedades por medico");
	private JButton volverJB = new JButton("Volver");
	
	private VentanaInformes(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(titulo + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaInformes getInstancia() {
		if(instancia == null)
			instancia = new VentanaInformes();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(292, 96, 256, 32);
			
			listarPacientesJB.setBounds(162, 192, 304, 32);
			enfermedadesMedicoJB.setBounds(162,  256, 304, 32);
			volverJB.setBounds(162, 320, 304, 32);
			
			listarPacientesJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaInformes.getInstancia().setVisible(false);
					VentanaInformesPacientesXMedico.getInstancia().setVisible(true);
				}
			});
			
			enfermedadesMedicoJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaInformes.getInstancia().setVisible(false);
					VentanaInformesEnfermedadesXMedico.getInstancia().setVisible(true);
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaInformes.getInstancia().setVisible(false);
					VentanaInicio.getInstancia().setVisible(true);
				}
			});
			
			add(tituloJL);
			add(nombreVentanaJL);
			add(listarPacientesJB);
			add(enfermedadesMedicoJB);
			add(volverJB);
		}
	}
}
