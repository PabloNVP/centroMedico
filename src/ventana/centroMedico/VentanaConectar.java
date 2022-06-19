package ventana.centroMedico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaConectar extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static final String USUARIO = "centroMedico";
	private static final Integer PASSWORD = -643275276; //productoFinal2022
	private static final String ERROR_LOGIN = "Usuario y/o contraseña incorrecta.";
	
	private static int ALTO = 640;
	private static int ANCHO = 480;
	
	private final String titulo = "CENTRO MEDICO UNLAM";
	private final String nombreVentana = "Conectar";
	
	private JLabel tituloJL = new JLabel(titulo);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel usuarioJL = new JLabel("Usuario:");
	private JLabel contrasenaJL = new JLabel("Contraseña:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField usuarioJTF = new JTextField();
	private JPasswordField contrasenaJPF = new JPasswordField();
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton cerrarJB = new JButton("Cerrar");
	
	public VentanaConectar(){
		JPanel pantalla = new Pantalla();
		
		setSize(ALTO, ANCHO);
		setTitle(titulo + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static boolean puedeIngresarUsuario(String usuario, String contrasena) {
		// Verificación de usuario y contraseña correctos.
		return (USUARIO.equals(usuario) && PASSWORD.equals(contrasena.hashCode()));
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(240, 64, 256, 32);
			nombreVentanaJL.setBounds(290, 96, 256, 32);
			
			usuarioJL.setBounds(208, 160, 128, 32);
			contrasenaJL.setBounds(192, 192, 96, 32);
			
			usuarioJTF.setBounds(288, 165, 144, 24);
			contrasenaJPF.setBounds(288, 197, 144, 24);
			contrasenaJPF.setEchoChar('*');
			
			mensajeJL.setBounds(200, 245, 256, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			cerrarJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!puedeIngresarUsuario(usuarioJTF.getText(), String.valueOf(contrasenaJPF.getPassword()))) {
						mensajeJL.setText(ERROR_LOGIN);	
					}else {
						VentanaInicio.getInstancia().setVisible(true);
						dispose();
					}
				}
			});
			
			cerrarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			add(usuarioJL);
			add(contrasenaJL);
			add(usuarioJTF);
			add(contrasenaJPF);
			add(mensajeJL);
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(cerrarJB);
		}
	}
}
