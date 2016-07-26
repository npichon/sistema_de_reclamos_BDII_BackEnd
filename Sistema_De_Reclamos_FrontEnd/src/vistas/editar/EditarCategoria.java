package vistas.editar;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelos.Categoria;
import vistas.PanelPrincipal;
import controladores.CategoriaController;

@SuppressWarnings("serial")
public class EditarCategoria extends JDialog {
	private static JTextField nombre;
	private static JTextField puntaje;
	private static JTextArea descripcion;
	private PanelPrincipal altaCategoria;
	private CategoriaController categoriaController = new CategoriaController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { AltaCategoria frame = new
	 * AltaCategoria(null); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); } *
	 */

	/**
	 * Create the frame.
	 * 
	 * @param string
	 */
	public EditarCategoria(Categoria categoria) {
		setModal(true);
		setTitle("Nueva Categor\u00EDa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 363);
		altaCategoria = new PanelPrincipal();
		altaCategoria.setBackground(SystemColor.activeCaption);
		altaCategoria.setToolTipText("Nueva Categoria");
		altaCategoria.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(altaCategoria);

		JLabel lblNombre = new JLabel("Nombre*");

		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setText(categoria.getNombre());

		JLabel lblDescripcion = new JLabel("Descripcion");

		JScrollPane scrollDescripcion = new JScrollPane();
		descripcion = new JTextArea();
		scrollDescripcion.setViewportView(descripcion);
		descripcion.setText(categoria.getDescripcion());

		puntaje = new JTextField();
		puntaje.setColumns(10);
		puntaje.setText(String.valueOf(categoria.getPuntaje()));

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
			public void mouseClicked(MouseEvent arg0) {
				try {
					categoriaController.editarCategoria(categoria.getId(),
							nombre.getText(), descripcion.getText(),
							puntaje.getText());
					dispose();
				} catch (NumberFormatException | SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo modificar la categoría.");
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		});

		JLabel lblPuntaje = new JLabel("Puntaje*");

		GroupLayout gl_altaCategoria = new GroupLayout(altaCategoria);
		gl_altaCategoria
				.setHorizontalGroup(gl_altaCategoria
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_altaCategoria
										.createSequentialGroup()
										.addGroup(
												gl_altaCategoria
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_altaCategoria
																		.createSequentialGroup()
																		.addGap(83)
																		.addGroup(
																				gl_altaCategoria
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDescripcion)
																						.addComponent(
																								lblPuntaje,
																								GroupLayout.PREFERRED_SIZE,
																								54,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblNombre))
																		.addGap(28)
																		.addGroup(
																				gl_altaCategoria
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								nombre)
																						.addGroup(
																								gl_altaCategoria
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												puntaje)
																										.addComponent(
																												scrollDescripcion,
																												GroupLayout.DEFAULT_SIZE,
																												215,
																												Short.MAX_VALUE))))
														.addGroup(
																gl_altaCategoria
																		.createSequentialGroup()
																		.addGap(159)
																		.addComponent(
																				btnCancelar)
																		.addGap(18)
																		.addComponent(
																				btnGuardar)))
										.addContainerGap(89, Short.MAX_VALUE)));
		gl_altaCategoria
				.setVerticalGroup(gl_altaCategoria
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_altaCategoria
										.createSequentialGroup()
										.addGap(42)
										.addGroup(
												gl_altaCategoria
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(lblNombre)
														.addComponent(
																nombre,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_altaCategoria
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblDescripcion)
														.addComponent(
																scrollDescripcion,
																GroupLayout.PREFERRED_SIZE,
																111,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_altaCategoria
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPuntaje)
														.addComponent(
																puntaje,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(44)
										.addGroup(
												gl_altaCategoria
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnCancelar)
														.addComponent(
																btnGuardar))
										.addGap(25)));

		altaCategoria.setLayout(gl_altaCategoria);
	}

	public static void clearTextFields() {
		nombre.setText("");
		descripcion.setText("");
		puntaje.setText("");
	}
}
