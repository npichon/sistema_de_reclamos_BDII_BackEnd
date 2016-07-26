package vistas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controladores.CiudadanoController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import validaciones.Validar;
import vistas.alta.AltaCiudadano;

public class PantallaPrincipal extends JFrame {

	private JTextField dni;
	private static PantallaPrincipal inicio = null;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { PantallaPrincipal inicio =
	 * PantallaPrincipal.getInstance(); inicio.setLocationRelativeTo(null);
	 * inicio.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	private PantallaPrincipal() {

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 526);

		MenuPrincipal menuBar = new MenuPrincipal();
		setJMenuBar(menuBar);
		PanelPrincipal contentPane = new PanelPrincipal();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblIngreseElDni = new JLabel("Ingrese el DNI del ciudadano");
		lblIngreseElDni.setVerticalAlignment(SwingConstants.TOP);

		dni = new JTextField();
		dni.setColumns(10);

		JButton btnNuevoCiudadano = new JButton("Nuevo Ciudadano");
		btnNuevoCiudadano.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AltaCiudadano nuevoCiudadano = new AltaCiudadano("");
				nuevoCiudadano.setLocationRelativeTo(null);
				nuevoCiudadano.setVisible(true);
			}
		});

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CiudadanoController ciudadanoController = new CiudadanoController();
				try {
					Validar valida = new Validar();
					int ok = valida.validarDni(dni.getText());
					if (ok != -1) {
						int result = ciudadanoController
								.detalleCiudadano(Integer.parseInt(dni
										.getText()));
						if (result != -1) {
							dispose();
						}
					}
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"No se encontro el ciudadano con ese DNI.");
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_contentPane.createSequentialGroup()
								.addContainerGap(502, Short.MAX_VALUE)
								.addComponent(btnNuevoCiudadano)
								.addContainerGap())
				.addGroup(
						gl_contentPane.createSequentialGroup().addGap(296)
								.addComponent(btnBuscar)
								.addContainerGap(308, Short.MAX_VALUE))
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addGap(201)
								.addComponent(dni, GroupLayout.DEFAULT_SIZE,
										225, Short.MAX_VALUE).addGap(203))
				.addGroup(
						gl_contentPane.createSequentialGroup().addGap(255)
								.addComponent(lblIngreseElDni)
								.addContainerGap(276, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addGap(139)
						.addComponent(lblIngreseElDni)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(dni, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(74)
						.addComponent(btnBuscar).addGap(138)
						.addComponent(btnNuevoCiudadano).addContainerGap()));

		contentPane.setLayout(gl_contentPane);

	}

	public synchronized static PantallaPrincipal getInstance()
			throws SQLException {
		if (inicio == null) {
			inicio = new PantallaPrincipal();
		}
		return inicio;
	}
}
