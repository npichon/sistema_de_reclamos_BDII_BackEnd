package vistas;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import modelos.Estado;
import modelos.EstadoReclamo;
import modelos.Reclamo;
import controladores.ReclamoController;

public class DetalleReclamo extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JTextField categoria;
	private static JTextField fechaReclamo;
	private static JTextField calle;
	private static JTextField numero;
	private static JTextField piso;
	private static JTextField departamento;
	private static JTextField latitud;
	private static JTextField longitud;
	private static JTextField fechaEstado;
	private static JTextField estado;
	private static JButton btnGuardar;
	private static JButton btnCambiarEstado;
	private static JButton btnCancelar;
	private static JScrollPane scrollPane;
	private static JScrollPane scrollPane_1;
	private static JScrollPane scrollPaneTable;
	private static JTextArea detalle;
	private static JTable table;
	private static JComboBox<String> estadosCombo;
	private static ReclamoController reclamoController = new ReclamoController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { DetalleReclamo dialog =
	 * new DetalleReclamo(null, null);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public DetalleReclamo(Reclamo reclamo, List<Estado> listEstados) {

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
		setTitle("Detalle del Reclamo");
		setModal(true);

		List<EstadoReclamo> estados = reclamo.getEstados();
		int ultimo = estados.size() - 1;

		setResizable(false);
		setBounds(100, 100, 686, 725);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblDatosReclamo = new JLabel("Datos del Reclamo");
		lblDatosReclamo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosReclamo.setForeground(SystemColor.window);

		JSeparator separator = new JSeparator();

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");

		scrollPane = new JScrollPane();

		JLabel lblDireccinDelReclamo = new JLabel("Direcci\u00F3n del Reclamo");
		lblDireccinDelReclamo.setForeground(SystemColor.window);
		lblDireccinDelReclamo.setFont(new Font("Tahoma", Font.BOLD
				| Font.ITALIC, 9));

		JSeparator separator_1 = new JSeparator();

		JLabel lblCalle = new JLabel("Calle: ");

		JLabel lblNmero = new JLabel("N\u00FAmero:");

		JLabel lblEstado = new JLabel("Estado Actual");
		lblEstado.setForeground(SystemColor.window);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 9));

		JLabel lblDetalle = new JLabel("Detalle");

		scrollPane_1 = new JScrollPane();

		fechaEstado = new JTextField();
		fechaEstado.setEditable(false);
		fechaEstado.setColumns(10);
		fechaEstado.setText(estados.get(ultimo).getFecha().toString());

		JSeparator separator_2 = new JSeparator();

		btnGuardar = new JButton("Guardar");
		btnGuardar.setVisible(false);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCambiarEstado = new JButton("Cambiar Estado");

		btnCambiarEstado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnCambiarEstado.setVisible(false);
				estado.setVisible(false);
				btnGuardar.setVisible(true);
				btnCancelar.setVisible(true);
				detalle.setEditable(true);

				Calendar fe = Calendar.getInstance();
				fe.getTimeZone();
				Timestamp fechaDada = new Timestamp(fe.getTimeInMillis());
				fechaEstado.setText(fechaDada.toString());

				estadosCombo.setVisible(true);
			}
		});

		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Estado estadoSeleccionado = new Estado();
				for (Estado e : listEstados) {
					if (e.getNombre().equals(
							estadosCombo.getSelectedItem().toString())) {
						estadoSeleccionado = e;
					}
				}
				try {
					reclamoController.altaEstadoReclamo(detalle.getText(),
							fechaEstado.getText(), estadoSeleccionado,
							reclamo.getId());
					btnCambiarEstado.setVisible(true);
					btnGuardar.setVisible(false);
					btnCancelar.setVisible(false);
					estadosCombo.setVisible(false);
					estado.setText(estadosCombo.getSelectedItem().toString());
					estado.setVisible(true);
					detalle.setEditable(false);

					/*
					 * Object[] valores = new Object[4]; valores[0] =
					 * estados.get(ultimo).getId(); valores[1] =
					 * estados.get(ultimo).getEstado().getNombre(); valores[2] =
					 * estados.get(ultimo).getDetalle(); valores[3] =
					 * estados.get(ultimo).getFecha();
					 * 
					 * ((DefaultTableModel)table.getModel()).addRow(valores);
					 */
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnCambiarEstado.setVisible(true);
				btnGuardar.setVisible(false);
				btnCancelar.setVisible(false);
				estadosCombo.setVisible(false);
				estado.setVisible(true);
				detalle.setEditable(false);
			}
		});

		JLabel lblHistorialDeEstados = new JLabel("Historial de Estados");
		lblHistorialDeEstados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHistorialDeEstados.setForeground(SystemColor.window);

		JSeparator separator_3 = new JSeparator();

		JTextArea descripcion = new JTextArea();
		descripcion.setEnabled(false);
		descripcion.setEditable(false);
		scrollPane.setViewportView(descripcion);
		descripcion.setText(reclamo.getDescripcion());

		detalle = new JTextArea();
		detalle.setEditable(false);
		scrollPane_1.setViewportView(detalle);

		scrollPaneTable = new JScrollPane();
		detalle.setText(estados.get(ultimo).getDetalle());

		JLabel lblCategora = new JLabel("Categor\u00EDa");

		categoria = new JTextField();
		categoria.setEditable(false);
		categoria.setColumns(10);
		categoria.setText(reclamo.getCategoria().getNombre());

		JLabel lblFecha = new JLabel("Fecha de Alta");

		fechaReclamo = new JTextField();
		fechaReclamo.setEditable(false);
		fechaReclamo.setColumns(10);
		fechaReclamo.setText(estados.get(ultimo).getFecha().toString());

		calle = new JTextField();
		calle.setEditable(false);
		calle.setColumns(10);
		calle.setText(reclamo.getUbicacion().getCalle());

		JLabel lblDepartamento = new JLabel("Departamento: ");

		departamento = new JTextField();
		departamento.setEditable(false);
		departamento.setColumns(10);
		departamento.setText(reclamo.getUbicacion().getDepartamento());

		JLabel lblLatitud = new JLabel("Latitud: ");

		latitud = new JTextField();
		latitud.setEditable(false);
		latitud.setColumns(10);
		latitud.setText(reclamo.getUbicacion().getLatitud());

		numero = new JTextField();
		numero.setEditable(false);
		numero.setColumns(10);
		numero.setText(String.valueOf(reclamo.getUbicacion().getNumero()));

		JLabel lblPiso = new JLabel("Piso: ");

		piso = new JTextField();
		piso.setEditable(false);
		piso.setColumns(10);
		piso.setText(reclamo.getUbicacion().getPiso());

		JLabel lblLongitud = new JLabel("Longitud: ");

		longitud = new JTextField();
		longitud.setEditable(false);
		longitud.setColumns(10);
		longitud.setText(reclamo.getUbicacion().getLongitud());

		JLabel lblNewLabel = new JLabel("Estado");

		estadosCombo = new JComboBox<String>();
		estadosCombo.setVisible(false);
		for (Estado e : listEstados) {
			estadosCombo.addItem(e.getNombre());
		}

		estado = new JTextField();
		estado.setEditable(false);
		estado.setColumns(10);
		estado.setText(estados.get(ultimo).getEstado().getNombre());

		/* Completo la tabla */
		actualizarEstados(estados);

		JLabel lblFecha_1 = new JLabel("Fecha");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(contentPanel,
				GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(contentPanel,
				GroupLayout.PREFERRED_SIZE, 718, Short.MAX_VALUE));

		JButton btnNewButton = new JButton("Ver en Mapa");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (reclamo.getUbicacion().getCalle() != null) {
					String texto1 = reclamo.getUbicacion().getCalle();
					String calle = texto1.replace(" ", "+");
					String numero = String.valueOf(reclamo.getUbicacion()
							.getNumero());
					String dir = calle + "+" + numero + ",+" + "Viedma";
					//Mapa.ver(dir);
				} else {
					JOptionPane.showMessageDialog(null,
							"El reclamo no tiene una direccion asociada.");
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_contentPanel
										.createSequentialGroup()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblDatosReclamo)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				459,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnNewButton))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblDireccinDelReclamo))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblEstado))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblDetalle)
																		.addGap(429)
																		.addComponent(
																				lblNewLabel))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(21)
																										.addComponent(
																												lblCalle)
																										.addGap(15)
																										.addComponent(
																												calle,
																												GroupLayout.PREFERRED_SIZE,
																												142,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(6)
																										.addComponent(
																												lblDepartamento)
																										.addGap(6)
																										.addComponent(
																												departamento,
																												GroupLayout.PREFERRED_SIZE,
																												134,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(17)
																										.addComponent(
																												lblNmero)
																										.addGap(8)
																										.addComponent(
																												numero,
																												GroupLayout.PREFERRED_SIZE,
																												142,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(6)
																										.addComponent(
																												lblPiso)
																										.addGap(56)
																										.addComponent(
																												piso)))
																		.addGap(10)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												lblLongitud)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												longitud,
																												GroupLayout.PREFERRED_SIZE,
																												127,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												lblLatitud)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												latitud))))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblHistorialDeEstados))
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(17)
																										.addGroup(
																												gl_contentPanel
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addComponent(
																																scrollPane_1,
																																GroupLayout.PREFERRED_SIZE,
																																320,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																btnCambiarEstado)))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(25)
																										.addGroup(
																												gl_contentPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																scrollPane,
																																GroupLayout.PREFERRED_SIZE,
																																312,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblDescripcin))))
																		.addGap(42)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblCategora)
																						.addComponent(
																								lblFecha)
																						.addComponent(
																								fechaReclamo,
																								220,
																								220,
																								220)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(99)
																										.addComponent(
																												lblFecha_1))
																						.addComponent(
																								estadosCombo,
																								220,
																								220,
																								220)
																						.addComponent(
																								estado,
																								220,
																								220,
																								220)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(21)
																										.addComponent(
																												btnGuardar,
																												GroupLayout.PREFERRED_SIZE,
																												82,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(42)
																										.addComponent(
																												btnCancelar))
																						.addComponent(
																								categoria,
																								220,
																								220,
																								220)
																						.addComponent(
																								fechaEstado,
																								220,
																								220,
																								220))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				71,
																				Short.MAX_VALUE))
														.addComponent(
																separator,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																670,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.LEADING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								separator_2,
																								Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								642,
																								Short.MAX_VALUE)
																						.addComponent(
																								separator_1,
																								GroupLayout.DEFAULT_SIZE,
																								642,
																								Short.MAX_VALUE)
																						.addComponent(
																								scrollPaneTable,
																								GroupLayout.DEFAULT_SIZE,
																								642,
																								Short.MAX_VALUE))
																		.addGap(18)
																		.addComponent(
																				separator_3,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGap(14)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDatosReclamo)
														.addComponent(
																btnNewButton))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDescripcin)
														.addComponent(
																lblCategora))
										.addGap(6)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																102,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				categoria,
																				20,
																				20,
																				20)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				38,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblFecha)
																		.addGap(10)
																		.addComponent(
																				fechaReclamo,
																				20,
																				20,
																				20)))
										.addGap(14)
										.addComponent(lblDireccinDelReclamo)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_1,
												GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
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
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblDepartamento))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(3)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblLatitud)
																						.addComponent(
																								latitud,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																departamento,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(31)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(4)
																		.addComponent(
																				lblNmero))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				numero,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(4)
																		.addComponent(
																				lblPiso))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(3)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								piso,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblLongitud)
																						.addComponent(
																								longitud,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addGap(11)
										.addComponent(lblEstado)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_2,
												GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDetalle)
														.addComponent(
																lblNewLabel))
										.addGap(6)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_1,
																GroupLayout.PREFERRED_SIZE,
																108,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				estadosCombo,
																				20,
																				20,
																				20)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				estado,
																				20,
																				20,
																				20)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				categoria,
																				20,
																				20,
																				20)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				32,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblFecha_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				fechaEstado,
																				20,
																				20,
																				20)))
										.addGap(10)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(52)
																		.addComponent(
																				lblHistorialDeEstados))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(23)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.BASELINE)
																										.addComponent(
																												btnGuardar)
																										.addComponent(
																												btnCambiarEstado))
																						.addComponent(
																								btnCancelar))))
										.addGap(6)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																separator_3,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																scrollPaneTable,
																GroupLayout.PREFERRED_SIZE,
																100,
																GroupLayout.PREFERRED_SIZE))
										.addGap(30)));
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
	}

	public static void actualizarEstados(List<EstadoReclamo> estados) {
		if (estados != null) {
			String[] columns = { "Id", "Estado", "Detalle", "Fecha" };
			Object[][] valores = new Object[estados.size() - 1][4];
			for (int i = 0; i < estados.size() - 1; i++) {
				valores[i][0] = estados.get(i).getId();
				valores[i][1] = estados.get(i).getEstado().getNombre();
				valores[i][2] = estados.get(i).getDetalle();
				valores[i][3] = estados.get(i).getFecha();
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
			scrollPaneTable.setViewportView(table);

			table.getColumn("Id").setWidth(0);
			table.getColumn("Id").setMinWidth(0);
			table.getColumn("Id").setMaxWidth(0);
		}
	}
}
