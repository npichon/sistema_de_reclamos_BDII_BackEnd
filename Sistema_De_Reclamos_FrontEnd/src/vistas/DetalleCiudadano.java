package vistas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import controladores.CategoriaController;
import controladores.ReclamoController;
import controladores.VentajaController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vistas.alta.AltaReclamo;
import vistas.editar.EditarCiudadano;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.LayoutStyle.ComponentPlacement;

import modelos.Ciudadano;
import modelos.Reclamo;

public class DetalleCiudadano extends JFrame {

	private PanelPrincipal contentPane = new PanelPrincipal();
	private static JButton btnVer;
	private static JButton btnBorrar;
	private JButton btnNuevoReclamo;
	private static JScrollPane scrollPane;
	private static JTable table;
	private static JTextField nombre;
	private static JTextField apellido;
	private static JTextField dni;
	private static JTextField email;
	private static JTextField cuentaTwitter;
	private static JTextField puntaje;
	private JLabel lblDireccionDelCiudadano;
	private JLabel lblCalle;
	private JLabel lblNmero;
	private JLabel lblPiso;
	private static JTextField calle;
	private static JTextField numero;
	private static JTextField piso;
	private JLabel lblDepartamento;
	private JLabel lblLatitud;
	private JLabel lblLongitud;
	private static JTextField departamento;
	private static JTextField latitud;
	private static JTextField longitud;
	private JLabel lblReclamo;
	private JLabel img;
	private static ReclamoController reclamoController = new ReclamoController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Ciudadano ciudadano = new
	 * Ciudadano(); DetalleCiudadano frame = new DetalleCiudadano(ciudadano,
	 * null); frame.setLocationRelativeTo(null); frame.setVisible(true); } catch
	 * (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 * 
	 * @param reclamos
	 */
	public DetalleCiudadano(Ciudadano ciudadano, List<Reclamo> reclamos) {

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

		setResizable(false);
		setTitle("Lista de Reclmos");
		// getContentPane().setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBounds(100, 100, 767, 630);
		// contentPane = new PanelPrincipal();

		scrollPane = new JScrollPane();

		JLabel lblNombre = new JLabel(labels.getString("ciudadano.nombre.tabla"));

		JLabel lblApellido = new JLabel(labels.getString("ciudadano.apellido.tabla"));

		JLabel lblDni = new JLabel("Dni");

		nombre = new JTextField();
		nombre.setBackground(SystemColor.control);
		nombre.setForeground(Color.BLACK);
		nombre.setEnabled(false);
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setText(ciudadano.getNombre());

		apellido = new JTextField();
		apellido.setEnabled(false);
		apellido.setEditable(false);
		apellido.setColumns(10);
		apellido.setText(ciudadano.getApellido());

		dni = new JTextField();
		dni.setEnabled(false);
		dni.setEditable(false);
		dni.setText("");
		dni.setColumns(10);
		dni.setText(String.valueOf(ciudadano.getDni()));

		JLabel lbEmail = new JLabel(labels.getString("ciudadano.email"));

		JLabel lblCuentaDeTwitter = new JLabel(labels.getString("ciudadano.cuentaTwitter.tabla"));

		email = new JTextField();
		email.setEnabled(false);
		email.setEditable(false);
		email.setColumns(10);
		email.setText(ciudadano.getEmail());

		cuentaTwitter = new JTextField();
		cuentaTwitter.setEnabled(false);
		cuentaTwitter.setEditable(false);
		cuentaTwitter.setColumns(10);
		cuentaTwitter.setText(ciudadano.getCuentaTwitter());

		JLabel lblDatosPersonales = new JLabel("Datos Personales");
		lblDatosPersonales.setForeground(SystemColor.window);
		lblDatosPersonales.setFont(new Font("Tahoma", Font.BOLD, 9));

		JSeparator separator_1 = new JSeparator();

		JSeparator separator_2 = new JSeparator();

		lblDireccionDelCiudadano = new JLabel("Direcci\u00F3n del Ciudadano");
		lblDireccionDelCiudadano.setForeground(SystemColor.window);
		lblDireccionDelCiudadano.setFont(new Font("Tahoma", Font.BOLD, 9));

		lblCalle = new JLabel(labels.getString("direccion.calle.tabla"));

		lblNmero = new JLabel("N\u00FAmero:");

		lblPiso = new JLabel("Piso:");

		calle = new JTextField();
		calle.setEnabled(false);
		calle.setEditable(false);
		calle.setColumns(10);
		calle.setText(ciudadano.getDireccion().getCalle());

		numero = new JTextField();
		numero.setEnabled(false);
		numero.setEditable(false);
		numero.setColumns(10);
		numero.setText(String.valueOf(ciudadano.getDireccion().getNumero()));

		piso = new JTextField();
		piso.setEnabled(false);
		piso.setEditable(false);
		piso.setColumns(10);
		piso.setText(ciudadano.getDireccion().getPiso());

		lblDepartamento = new JLabel("Departamento:");

		lblLatitud = new JLabel("Latitud:");

		lblLongitud = new JLabel("Longitud:");

		departamento = new JTextField();
		departamento.setEnabled(false);
		departamento.setEditable(false);
		departamento.setColumns(10);
		departamento.setText(ciudadano.getDireccion().getDepartamento());

		latitud = new JTextField();
		latitud.setEnabled(false);
		latitud.setEditable(false);
		latitud.setColumns(10);
		latitud.setText(ciudadano.getDireccion().getLatitud());

		longitud = new JTextField();
		longitud.setEnabled(false);
		longitud.setEditable(false);
		longitud.setColumns(10);
		longitud.setText(ciudadano.getDireccion().getLongitud());

		lblReclamo = new JLabel("Lista de Reclamos");
		lblReclamo.setForeground(SystemColor.window);
		lblReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));

		img = new JLabel("New label");
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EditarCiudadano editarCiudadano = new EditarCiudadano(
						ciudadano, "detalle");
				editarCiudadano.setLocationRelativeTo(null);
				editarCiudadano.setVisible(true);
			}
		});
		img.setIcon(new ImageIcon(DetalleCiudadano.class
				.getResource("/Archivos/edit.png")));

		btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						int confirmado = JOptionPane.showConfirmDialog(null,
								"Quiere eliminar el reclamo?");
						if (JOptionPane.OK_OPTION == confirmado) {

							reclamoController.eliminarReclamo(
									(String) table.getValueAt(
											table.getSelectedRow(), 0),
									"detalle");
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo eliminar el reclamo.");
						JOptionPane.showMessageDialog(null, e.toString());
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un Reclamo.");
				}
			}
		});

		btnVer = new JButton("Ver Reclamo");
		btnVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						reclamoController.verReclamo((String) table
								.getValueAt(table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo tener acceso al relamo.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un Reclamo.");
				}
			}
		});

		btnNuevoReclamo = new JButton("Nuevo Reclamo");

		btnNuevoReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CategoriaController categoriaController = new CategoriaController();
				try {
					AltaReclamo nuevoReclamo = new AltaReclamo(
							categoriaController.listCategorias(), ciudadano);
					nuevoReclamo.setLocationRelativeTo(null);
					nuevoReclamo.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo accder a esta sección.");
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		puntaje = new JTextField();
		puntaje.setEnabled(false);
		puntaje.setEditable(false);
		puntaje.setColumns(10);
		// puntaje.setText(String.valueOf(ciudadano.getPuntos()));

		JSeparator separator = new JSeparator();
		actualizarReclamos(reclamos);

		JLabel lblPuntaje = new JLabel("Puntaje");

		JButton btnVentajas = new JButton("Ventajas");
		btnVentajas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VentajaController ventajaController = new VentajaController();
				try {
					ventajaController.listarVentajasCiudadano(Integer
							.parseInt(puntaje.getText()));
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo listar las ventajas.");
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		JButton btnVerEnMapa = new JButton("Ver en  Mapa");
		btnVerEnMapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String texto1 = (String) table.getValueAt(
						table.getSelectedRow(), 4);
				if (texto1 != null) {
					String calle = texto1.replace(" ", "+");
					String numero = table.getValueAt(table.getSelectedRow(), 5)
							.toString();
					String dir = calle + "+" + numero + ",+" + "Viedma";
					//Mapa.ver(dir);
				} else {
					JOptionPane.showMessageDialog(null,
							"El reclamo no tiene una direccion asociada.");
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(22)
																		.addComponent(
																				lblDatosPersonales))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(
																				separator_1,
																				GroupLayout.PREFERRED_SIZE,
																				729,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(10)
																		.addComponent(
																				separator_2,
																				GroupLayout.PREFERRED_SIZE,
																				729,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(28)
																		.addComponent(
																				lblReclamo))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(89)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblNmero)
																										.addGap(25)
																										.addComponent(
																												numero,
																												GroupLayout.PREFERRED_SIZE,
																												145,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblPiso)
																										.addGap(43)
																										.addComponent(
																												piso,
																												GroupLayout.PREFERRED_SIZE,
																												145,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(64)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblLongitud)
																										.addGap(95)
																										.addComponent(
																												longitud,
																												GroupLayout.PREFERRED_SIZE,
																												157,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblLatitud)
																										.addGap(103)
																										.addComponent(
																												latitud,
																												GroupLayout.PREFERRED_SIZE,
																												157,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(88)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblNombre)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												nombre,
																												GroupLayout.PREFERRED_SIZE,
																												148,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblApellido)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												apellido,
																												GroupLayout.PREFERRED_SIZE,
																												148,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblDni)
																										.addGap(53)
																										.addComponent(
																												dni,
																												GroupLayout.PREFERRED_SIZE,
																												148,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(59)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCuentaDeTwitter)
																						.addComponent(
																								lbEmail)
																						.addComponent(
																								lblPuntaje))
																		.addGap(43)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								email,
																								GroupLayout.DEFAULT_SIZE,
																								161,
																								Short.MAX_VALUE)
																						.addComponent(
																								cuentaTwitter,
																								GroupLayout.DEFAULT_SIZE,
																								161,
																								Short.MAX_VALUE)
																						.addComponent(
																								puntaje)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(89)
																		.addComponent(
																				lblCalle)
																		.addGap(39)
																		.addComponent(
																				calle,
																				GroupLayout.PREFERRED_SIZE,
																				145,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(64)
																		.addComponent(
																				lblDepartamento)
																		.addGap(67)
																		.addComponent(
																				departamento,
																				GroupLayout.PREFERRED_SIZE,
																				157,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(698)
																		.addComponent(
																				img,
																				GroupLayout.PREFERRED_SIZE,
																				24,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				scrollPane,
																				GroupLayout.DEFAULT_SIZE,
																				731,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(10)
																										.addComponent(
																												btnVerEnMapa)
																										.addGap(201)
																										.addComponent(
																												btnNuevoReclamo)
																										.addGap(93)
																										.addComponent(
																												btnVer)
																										.addGap(18)
																										.addComponent(
																												btnBorrar))
																						.addComponent(
																								separator,
																								GroupLayout.DEFAULT_SIZE,
																								731,
																								Short.MAX_VALUE))))
										.addContainerGap())
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(27)
										.addComponent(lblDireccionDelCiudadano)
										.addPreferredGap(
												ComponentPlacement.RELATED,
												419, Short.MAX_VALUE)
										.addComponent(btnVentajas).addGap(111)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(11)
										.addComponent(lblDatosPersonales)
										.addGap(6)
										.addComponent(separator_1,
												GroupLayout.PREFERRED_SIZE, 11,
												GroupLayout.PREFERRED_SIZE)
										.addGap(9)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblNombre)
														.addComponent(
																nombre,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lbEmail)
														.addComponent(
																email,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				apellido,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblApellido))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblCuentaDeTwitter)
																		.addComponent(
																				cuentaTwitter,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(64)
																		.addComponent(
																				lblDireccionDelCiudadano))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(18)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								dni,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblDni)
																						.addComponent(
																								lblPuntaje)
																						.addComponent(
																								puntaje,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnVentajas)))
										.addGap(6)
										.addComponent(separator_2,
												GroupLayout.PREFERRED_SIZE, 3,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblCalle))
														.addComponent(
																calle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblDepartamento))
														.addComponent(
																departamento,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblNmero))
														.addComponent(
																numero,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblLatitud))
														.addComponent(
																latitud,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblPiso))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				piso,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblLongitud))
														.addComponent(
																longitud,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(14)
										.addComponent(img,
												GroupLayout.PREFERRED_SIZE, 26,
												GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(lblReclamo)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												156, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addGap(2)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnVer)
														.addComponent(btnBorrar)
														.addComponent(
																btnNuevoReclamo)
														.addComponent(
																btnVerEnMapa))));
		getContentPane().setLayout(groupLayout);

		MenuPrincipal menuBar = new MenuPrincipal();
		setJMenuBar(menuBar);
	}

	public static void actualizarReclamos(List<Reclamo> reclamos) {
		if (reclamos != null) {
			int total = 0;
			String[] columns = { "N\u00FAmero de Reclamo", "Descripci\u00F3n",
					"Fecha", "Categoria", "Calle", "N\u00FAmero", "Piso",
					"Departamento" };
			Object[][] valores = new Object[reclamos.size()][8];
			for (int i = 0; i < reclamos.size(); i++) {
				valores[i][0] = reclamos.get(i).getId();
				valores[i][1] = reclamos.get(i).getDescripcion();
				valores[i][2] = reclamos.get(i).getEstados().get(0).getFecha();
				valores[i][3] = reclamos.get(i).getCategoria().getNombre();
				valores[i][4] = reclamos.get(i).getUbicacion().getCalle();
				valores[i][5] = reclamos.get(i).getUbicacion().getNumero();
				valores[i][6] = reclamos.get(i).getUbicacion().getPiso();
				valores[i][7] = reclamos.get(i).getUbicacion()
						.getDepartamento();
				total += reclamos.get(i).getCategoria().getPuntaje();
			}
			table = new JTable();
			table = new JTable();
			DefaultTableModel modeloNuevo = new DefaultTableModel(valores,
					columns) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return false;
				}
			};
			table.setModel(modeloNuevo);
			TableModel modelo = table.getModel();
			TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(
					modelo);
			table.setRowSorter(elQueOrdena);
			puntaje.setText(String.valueOf(total));
			scrollPane.setViewportView(table);
		}
	}

	public static void actualizarCampos(Ciudadano ciudadano) {
		nombre.setText(ciudadano.getNombre());
		apellido.setText(ciudadano.getApellido());
		dni.setText(String.valueOf(ciudadano.getDni()));
		email.setText(ciudadano.getEmail());
		cuentaTwitter.setText(ciudadano.getCuentaTwitter());
		calle.setText(ciudadano.getDireccion().getCalle());
		numero.setText(String.valueOf(ciudadano.getDireccion().getNumero()));
		piso.setText(ciudadano.getDireccion().getPiso());
		departamento.setText(ciudadano.getDireccion().getDepartamento());
		latitud.setText(ciudadano.getDireccion().getLatitud());
		longitud.setText(ciudadano.getDireccion().getLongitud());
		puntaje.setText(String.valueOf(ciudadano.getPuntos()));
	}
}
