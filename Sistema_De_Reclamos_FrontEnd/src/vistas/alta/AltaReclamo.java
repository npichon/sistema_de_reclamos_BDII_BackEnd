package vistas.alta;

import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import modelos.Categoria;
import modelos.Ciudadano;
import modelos.Reclamo;
import vistas.PanelPrincipal;
import controladores.ReclamoController;

import java.awt.SystemColor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class AltaReclamo extends JDialog {

	private PanelPrincipal contentPane;
	private JLabel lblDescripcion;
	private ReclamoController reclamoController = new ReclamoController();
	private static JTextArea descripcion;
	private static JTextField fecha;
	private static JTextField calleReclamo;
	private static JTextField departamentoReclamo;
	private static JTextField pisoReclamo;
	private static JTextField numeroReclamo;
	private static JTextField latitudReclamo;
	private static JTextField longitudReclamo;
	private static JComboBox<String> categoria;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<Categoria> categorias = new ArrayList<Categoria>();
					Ciudadano ciudadano = new Ciudadano();
					AltaReclamo dialog = new AltaReclamo(categorias, ciudadano);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AltaReclamo(List<Categoria> categorias, Ciudadano ciudadano) {
		
        File archivo = new File(this.getClass().getResource("/Archivos/idiomaSeleccionado.properties").getFile().replace("%20", " "));
        Properties propiedades = new Properties();
        try {
        	propiedades.load(new FileInputStream(archivo));
            
            String idiomaSeleccionado = propiedades.getProperty("idioma");
            //System.out.println(idiomaSeleccionado);
            if(idiomaSeleccionado.equals("Español")){
	    	    labels = ResourceBundle
	    				.getBundle("Archivos/labels");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
		
		setModal(true);
		setResizable(false);
		setTitle("Nuevo Reclamo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 695);
		contentPane = new PanelPrincipal();
		//contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		JLabel lblTelefono = new JLabel("");

		JLabel lblNewLabel = new JLabel("* Campos obligatorios");
		lblNewLabel.setForeground(SystemColor.window);
		lblNewLabel.setBackground(SystemColor.window);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));

		JLabel lblDatosDelCiudadano = new JLabel("Datos del ciudadano");
		lblDatosDelCiudadano.setForeground(SystemColor.window);
		lblDatosDelCiudadano.setFont(new Font("Tahoma", Font.BOLD, 9));

		JSeparator separator_4 = new JSeparator();

		lblDescripcion = new JLabel("Descripci\u00F3n *");

		JScrollPane scrollPane = new JScrollPane();

		descripcion = new JTextArea();
		scrollPane.setViewportView(descripcion);

		JLabel lblFecha = new JLabel("Fecha *");

		JLabel lblCategoria = new JLabel("Categor\u00EDa*");

		categoria = new JComboBox<String>();
		for (Categoria cat : categorias) {
			categoria.addItem(cat.getNombre());
		}

		JSeparator separator_1 = new JSeparator();

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JLabel label_1 = new JLabel("");

		JLabel lblNombre_1 = new JLabel("Nombre:");

		JLabel nombre = new JLabel(ciudadano.getNombre());
		nombre.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JLabel lblApellido = new JLabel("Apellido:");

		JLabel apellido = new JLabel(ciudadano.getApellido());
		apellido.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JLabel lblDni = new JLabel("DNI:");

		JLabel dni = new JLabel(String.valueOf(ciudadano.getDni()));
		dni.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JLabel lblEmail = new JLabel("Email:");

		JLabel email = new JLabel(ciudadano.getEmail());
		email.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JLabel lblCalle = new JLabel("Calle:");

		JLabel calle = new JLabel(ciudadano.getDireccion().getCalle());
		calle.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JLabel lblNumero = new JLabel("Numero:");

		JLabel numero = new JLabel(String.valueOf(ciudadano.getDireccion()
				.getNumero()));
		numero.setFont(new Font("SansSerif", Font.ITALIC, 12));

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Categoria catSeleccionada = new Categoria();
				for (Categoria cat : categorias) {
					if(cat.getNombre().equals(categoria.getSelectedItem())){
						catSeleccionada = cat;
					}
				}
				try {
					Reclamo reclamo = reclamoController.altaReclamo(descripcion.getText(), fecha.getText(),
							catSeleccionada, calleReclamo.getText(),
							numeroReclamo.getText(), pisoReclamo.getText(),
							departamentoReclamo.getText(),
							latitudReclamo.getText(), longitudReclamo.getText(),
							ciudadano);
					if (reclamo != null) {
						dispose();
						reclamoController.verReclamo(reclamo.getId());
					}
				} catch (NumberFormatException | SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo guardar el nuevo reclamo.");
					JOptionPane.showMessageDialog(null,
							e.toString());
				}
			}
		});

		JLabel lblDatosDelReclamo = new JLabel("Datos del Reclamo");
		lblDatosDelReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosDelReclamo.setForeground(SystemColor.window);

		JSeparator separator = new JSeparator();

		Calendar fe = Calendar.getInstance();
		fe.getTimeZone();
		Timestamp fechaDada = new Timestamp(fe.getTimeInMillis());

		fecha = new JTextField();
		fecha.setColumns(10);
		fecha.setEnabled(false);
		fecha.setText(fechaDada.toString());

		JLabel lblCalle_1 = new JLabel("Calle *");

		JLabel lblDepartamento = new JLabel("Departamento");

		JLabel lblPiso = new JLabel("Piso");

		calleReclamo = new JTextField();
		calleReclamo.setColumns(10);

		departamentoReclamo = new JTextField();
		departamentoReclamo.setText("");
		departamentoReclamo.setColumns(10);

		pisoReclamo = new JTextField();
		pisoReclamo.setText("");
		pisoReclamo.setColumns(10);

		JLabel lblNumero_1 = new JLabel("N\u00FAmero *");

		JLabel lblLatitud = new JLabel("Latitud");

		JLabel lblLongitud = new JLabel("Longitud");

		numeroReclamo = new JTextField();
		numeroReclamo.setColumns(10);

		latitudReclamo = new JTextField();
		latitudReclamo.setColumns(10);

		longitudReclamo = new JTextField();
		longitudReclamo.setText("");
		longitudReclamo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Direccion del Reclamo");
		lblNewLabel_1.setForeground(SystemColor.window);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 9));

		JSeparator separator_2 = new JSeparator();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(lblDatosDelReclamo)
					.addContainerGap(394, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(156)
							.addComponent(btnAgregar)
							.addGap(44)
							.addComponent(btnCancelar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCalle_1)
										.addComponent(lblDepartamento)
										.addComponent(lblPiso))
									.addGap(32)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(pisoReclamo)
										.addComponent(departamentoReclamo)
										.addComponent(calleReclamo, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLatitud)
										.addComponent(lblLongitud)
										.addComponent(lblNumero_1))
									.addGap(24)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(longitudReclamo, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
										.addComponent(latitudReclamo, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
										.addComponent(numeroReclamo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
									.addGap(33))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, 339, Short.MAX_VALUE)))))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(lblFecha)
						.addComponent(lblCategoria))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(fecha)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
						.addComponent(categoria, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addComponent(lblTelefono)
					.addContainerGap(90, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(lblDatosDelCiudadano, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addGap(77))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCalle)
								.addComponent(lblNombre_1)
								.addComponent(lblDni))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(nombre, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblApellido))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(calle, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addComponent(dni, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmail)
										.addComponent(lblNumero))))))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(numero, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellido, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addGap(108))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(email, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
						.addComponent(separator_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(lblDatosDelReclamo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(142)
							.addComponent(lblTelefono))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescripcion)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFecha)
								.addComponent(fecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCategoria)
								.addComponent(categoria, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCalle_1)
						.addComponent(calleReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(numeroReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumero_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartamento)
						.addComponent(departamentoReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(latitudReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLatitud))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPiso)
						.addComponent(pisoReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(longitudReclamo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongitud))
					.addGap(35)
					.addComponent(lblDatosDelCiudadano)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(label_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre_1)
								.addComponent(nombre, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApellido)
								.addComponent(apellido))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDni)
								.addComponent(dni)
								.addComponent(lblEmail)
								.addComponent(email))
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCalle)
								.addComponent(calle, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNumero)
								.addComponent(numero))))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnCancelar))
					.addGap(20))
		);

		contentPane.setLayout(gl_contentPane);
	}

	public static void clearTextFields() {
		descripcion.setText("");
		
		Calendar fe = Calendar.getInstance();
		fe.getTimeZone();
		Timestamp fechaDada = new Timestamp(fe.getTimeInMillis());
		fecha.setText(fechaDada.toString());
		
		calleReclamo.setText("");
		departamentoReclamo.setText("");
		pisoReclamo.setText("");
		numeroReclamo.setText("");
		latitudReclamo.setText("");
		longitudReclamo.setText("");
		categoria.setSelectedIndex(0);
	}
}
