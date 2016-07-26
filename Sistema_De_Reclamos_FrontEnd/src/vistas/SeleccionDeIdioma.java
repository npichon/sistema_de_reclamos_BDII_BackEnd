package vistas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class SeleccionDeIdioma extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JComboBox<String> idioma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionDeIdioma dialog = new SeleccionDeIdioma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionDeIdioma() {
		setTitle("Selecci\u00F3n de Idioma");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		idioma = new JComboBox(); 
		idioma.setModel(new DefaultComboBoxModel(new String[] { "Español" }));

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent arg0) {

				File archivo = new File(this.getClass()
						.getResource("/Archivos/idiomaSeleccionado.properties")
						.getFile().replace("%20", " "));

				Properties properties = new Properties();
				try {
					properties.load(new FileInputStream(archivo));
					properties.setProperty("idioma", idioma.getSelectedItem()
							.toString());
					FileOutputStream fos = new FileOutputStream(archivo
							.toString().replace("\\", "/"));

					properties.store(fos, null);

					PantallaPrincipal inicio;
					try {
						inicio = PantallaPrincipal.getInstance();
						inicio.setLocationRelativeTo(null);
						inicio.setVisible(true);
						dispose();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Seleccione un Idioma");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap(111, Short.MAX_VALUE)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				idioma,
																				GroupLayout.PREFERRED_SIZE,
																				220,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(93))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				btnAceptar)
																		.addGap(163))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblNewLabel)
																		.addGap(154)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addGap(46)
						.addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(idioma, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(32)
						.addComponent(btnAceptar)
						.addContainerGap(56, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
	}
}
