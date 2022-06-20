package ventana.centroMedico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.centroMedico.CENTROMEDICO;

public class VentanaInicio extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static VentanaInicio instancia;
	
	private final String nombreVentana = "Mesa de admisión";
	
	private JLabel tituloJL = new JLabel(CENTROMEDICO.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton ingresarJB = new JButton("Ingreso de datos");
	private JButton informesJB = new JButton("Informes");
	private JButton salirJB = new JButton("Salir");
	
	private VentanaInicio(){
		JPanel pantalla = new Pantalla();
		
		setSize(CENTROMEDICO.ALTO, CENTROMEDICO.ANCHO);
		setTitle(CENTROMEDICO.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static VentanaInicio getInstancia() {
		if(instancia == null)
			instancia = new VentanaInicio();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(255, 96, 256, 32);
			
			ingresarJB.setBounds(192, 192, 256, 32);
			informesJB.setBounds(192,  256, 256, 32);
			salirJB.setBounds(192, 320, 256, 32);
			
			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().setVisible(true);
					VentanaInicio.getInstancia().setVisible(false);
				}
			});
			
			informesJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaInformes.getInstancia().setVisible(true);
					VentanaInicio.getInstancia().setVisible(false);
				}
			});
			
			salirJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaIngreso.getInstancia().dispose();
					VentanaIngresoPaciente.getInstancia().dispose();
					VentanaIngresoSituacion.getInstancia().dispose();
					VentanaIngresoMedico.getInstancia().dispose();
					VentanaInformes.getInstancia().dispose();
					VentanaInformesPacientesXMedico.getInstancia().dispose();
					VentanaInformesEnfermedadesXMedico.getInstancia().dispose();
					VentanaInicio.getInstancia().dispose();
				}
			});
			
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(informesJB);
			add(salirJB);
		}
	}
}
