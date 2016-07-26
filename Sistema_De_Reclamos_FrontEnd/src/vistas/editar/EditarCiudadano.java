package vistas.editar;

import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;

import modelos.Ciudadano;
import vistas.DetalleCiudadano;
import vistas.PanelPrincipal;
import vistas.listar.ListarCiudadanos;
import controladores.CiudadanoController;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class EditarCiudadano extends JDialog {

	private JPanel AltaCiudadano;
	private static JTextField calle;
	private static JTextField numero;
	private static JTextField piso;
	private static JTextField departamento;
	private static JTextField latitud;
	private static JTextField longitud;
	private static JTextField nombre;
	private static JTextField apellido;
	private static JTextField dni;
	private static JTextField email;
	private static JTextField cuentaTwitter;
	private CiudadanoController ciudadanoController = new CiudadanoController();
	private JTextField telefono;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Ciudadano ciudadano = new
	 * Ciudadano(); EditarCiudadano dialog = new EditarCiudadano(ciudadano, "");
	 * dialog.setLocationRelativeTo(null); dialog.setVisible(true); } catch
	 * (Exception e) { e.printStackTrace(); } } }); }
	 */

	public static void clearTextFields() {
		calle.setText("");
		numero.setText("");
		piso.setText("");
		departamento.setText("");
		latitud.setText("");
		longitud.setText("");
		nombre.setText("");
		apellido.setText("");
		dni.setText("");
		email.setText("");
		cuentaTwitter.setText("");
	}

	/**
	 * Create the frame.
	 */
	public EditarCiudadano(Ciudadano ciudadano, String referer) {

		File archivo = new File(this.getClass()
				.getResource("/Archivos/idiomaSeleccionado.properties")
				.getFile().replace("%20", " "));
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(archivo));

			String idiomaSeleccionado = propiedades.getProperty("idioma");
			// System.out.println(idiomaSeleccionado);
			if (idiomaSeleccionado.equals("Español")) {
				labels = ResourceBundle.getBundle("Archivos/labels");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Nuevo Ciudadano");
		setBounds(100, 100, 575, 742);
		AltaCiudadano = new PanelPrincipal();
		// AltaCiudadano.setBackground(SystemColor.activeCaption);
		AltaCiudadano.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(AltaCiudadano);

		JLabel lblCalle = new JLabel("Calle *");

		JLabel lblNumero = new JLabel("N\u00FAmero *");

		JLabel lblPiso = new JLabel("Piso");

		JLabel lblDepartamento = new JLabel("Departamento");

		JLabel lblLatitud = new JLabel("Latitud");

		JLabel lblLongitud = new JLabel("Longitud");

		calle = new JTextField();
		calle.setColumns(10);
		calle.setText(ciudadano.getDireccion().getCalle());

		numero = new JTextField();
		numero.setColumns(10);
		numero.setText(String.valueOf(ciudadano.getDireccion().getNumero()));

		piso = new JTextField();
		piso.setColumns(10);
		piso.setText(ciudadano.getDireccion().getPiso());

		departamento = new JTextField();
		departamento.setColumns(10);
		departamento.setText(ciudadano.getDireccion().getDepartamento());

		latitud = new JTextField();
		latitud.setColumns(10);
		latitud.setText(ciudadano.getDireccion().getLatitud());

		longitud = new JTextField();
		longitud.setColumns(10);
		longitud.setText(ciudadano.getDireccion().getLongitud());

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Ciudadano ciudadanoEditado = ciudadanoController
							.editarCiudadadano(ciudadano.getId(), nombre
									.getText(), apellido.getText(), dni
									.getText(), email.getText(), cuentaTwitter
									.getText(), telefono.getText(), ciudadano
									.getDireccion().getId(), calle.getText(),
									numero.getText(), piso.getText(),
									departamento.getText(), latitud.getText(),
									longitud.getText(), referer);
					if (referer.equals("detalle")) {
						DetalleCiudadano.actualizarCampos(ciudadanoEditado);
					} else {
						List<Ciudadano> ciudadanos = ciudadanoController
								.ciudadanoGetList();
						ListarCiudadanos.actualizarCiudadanos(ciudadanos);
					}
					dispose();
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"No se pudo modificar el ciudadano.");
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		JLabel lblDatosPersonales = new JLabel("Datos Personales");
		lblDatosPersonales.setBackground(SystemColor.controlHighlight);
		lblDatosPersonales.setForeground(SystemColor.textInactiveText);
		lblDatosPersonales.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNombre = new JLabel("Nombre *");

		JLabel lblApellido = new JLabel("Apellido *");

		JLabel lblDni = new JLabel("Dni *");

		JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");

		JLabel lblTeitter = new JLabel("Twitter");

		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setText(ciudadano.getNombre());

		apellido = new JTextField();
		apellido.setColumns(10);
		apellido.setText(ciudadano.getApellido());

		dni = new JTextField();
		dni.setColumns(10);
		dni.setText(String.valueOf(ciudadano.getDni()));

		email = new JTextField();
		email.setColumns(10);
		email.setText(ciudadano.getEmail());

		cuentaTwitter = new JTextField();
		cuentaTwitter.setColumns(10);
		cuentaTwitter.setText(ciudadano.getCuentaTwitter());

		JLabel lblDatosObligatorios = new JLabel("* Datos Obligatorios");
		lblDatosObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 9));

		JSeparator separator_1 = new JSeparator();

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setForeground(SystemColor.textInactiveText);
		lblDireccin.setBackground(SystemColor.menu);
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JSeparator separator = new JSeparator();

		JSeparator separator_2 = new JSeparator();

		JLabel lblTelfono = new JLabel("Tel\u00E9fono *");

		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setText(ciudadano.getTelefono());

		GroupLayout gl_AltaCiudadano = new GroupLayout(AltaCiudadano);
		gl_AltaCiudadano
				.setHorizontalGroup(gl_AltaCiudadano
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_AltaCiudadano.createSequentialGroup()
										.addContainerGap(180, Short.MAX_VALUE)
										.addComponent(btnGuardar).addGap(51)
										.addComponent(btnCancelar).addGap(190))
						.addGroup(
								gl_AltaCiudadano
										.createSequentialGroup()
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(84)
																		.addGroup(
																				gl_AltaCiudadano
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDni)
																						.addComponent(
																								lblNombre)
																						.addComponent(
																								lblApellido)
																						.addComponent(
																								lblTeitter)
																						.addComponent(
																								lblCorreoElectrnico)
																						.addComponent(
																								lblTelfono))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_AltaCiudadano
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								cuentaTwitter)
																						.addGroup(
																								gl_AltaCiudadano
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												email)
																										.addComponent(
																												telefono)
																										.addComponent(
																												dni)
																										.addComponent(
																												apellido)
																										.addComponent(
																												nombre,
																												GroupLayout.DEFAULT_SIZE,
																												282,
																												Short.MAX_VALUE))))
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(27)
																		.addComponent(
																				separator_1,
																				GroupLayout.PREFERRED_SIZE,
																				329,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(103,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_AltaCiudadano.createSequentialGroup()
										.addGap(38)
										.addComponent(lblDatosPersonales)
										.addContainerGap(431, Short.MAX_VALUE))
						.addGroup(
								gl_AltaCiudadano
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																separator_2,
																GroupLayout.PREFERRED_SIZE,
																547,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(78)
																		.addGroup(
																				gl_AltaCiudadano
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCalle)
																						.addComponent(
																								lblNumero)
																						.addComponent(
																								lblDepartamento)
																						.addComponent(
																								lblLatitud)
																						.addComponent(
																								lblLongitud)
																						.addComponent(
																								lblPiso))
																		.addGap(25)
																		.addGroup(
																				gl_AltaCiudadano
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								latitud,
																								GroupLayout.PREFERRED_SIZE,
																								278,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								numero,
																								GroupLayout.PREFERRED_SIZE,
																								276,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								calle,
																								GroupLayout.PREFERRED_SIZE,
																								278,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_AltaCiudadano
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addComponent(
																												departamento,
																												Alignment.LEADING)
																										.addComponent(
																												piso,
																												Alignment.LEADING,
																												GroupLayout.PREFERRED_SIZE,
																												277,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								longitud,
																								GroupLayout.PREFERRED_SIZE,
																								278,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(30)
																		.addComponent(
																				lblDireccin))
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(11)
																		.addComponent(
																				lblDatosObligatorios)))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGroup(
								gl_AltaCiudadano
										.createSequentialGroup()
										.addGap(35)
										.addComponent(separator,
												GroupLayout.DEFAULT_SIZE, 522,
												Short.MAX_VALUE)
										.addContainerGap()));
		gl_AltaCiudadano
				.setVerticalGroup(gl_AltaCiudadano
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_AltaCiudadano
										.createSequentialGroup()
										.addGap(26)
										.addComponent(lblDatosPersonales)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(31)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblNombre)
														.addComponent(
																nombre,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblApellido)
														.addComponent(
																apellido,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE))
										.addGap(16)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblDni)
														.addComponent(
																dni,
																GroupLayout.PREFERRED_SIZE,
																22,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblTelfono)
														.addComponent(
																telefono,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED, 25,
												Short.MAX_VALUE)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCorreoElectrnico)
														.addComponent(
																email,
																GroupLayout.PREFERRED_SIZE,
																21,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblTeitter)
														.addComponent(
																cuentaTwitter,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(26)
										.addComponent(lblDireccin)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 7,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_AltaCiudadano
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				lblCalle))
														.addComponent(
																calle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(15)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(lblNumero)
														.addComponent(
																numero,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(15)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																piso,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPiso))
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblDepartamento)
														.addComponent(
																departamento,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblLatitud)
														.addComponent(
																latitud,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(21)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblLongitud)
														.addComponent(
																longitud,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(31)
										.addComponent(lblDatosObligatorios)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_2,
												GroupLayout.PREFERRED_SIZE, 7,
												GroupLayout.PREFERRED_SIZE)
										.addGap(30)
										.addGroup(
												gl_AltaCiudadano
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnGuardar)
														.addComponent(
																btnCancelar))
										.addContainerGap()));
		AltaCiudadano.setLayout(gl_AltaCiudadano);
	}
}
