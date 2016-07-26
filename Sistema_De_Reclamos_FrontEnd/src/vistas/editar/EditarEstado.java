package vistas.editar;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import controladores.EstadoController;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelos.Estado;
import vistas.PanelPrincipal;

@SuppressWarnings("serial")
public class EditarEstado extends JDialog {

	private PanelPrincipal altaEstado;
	private static JTextField nombre;
	private static JTextArea descripcion;
	private EstadoController estadoController = new EstadoController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarEstado dialog = new EditarEstado(null);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	} */

	/**
	 * Create the frame.
	 */
	public EditarEstado(Estado estado) {
		
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
		
		setResizable(false);
		setModal(true);
		setTitle("Editar Estado");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 316);
		altaEstado = new PanelPrincipal();
		altaEstado.setBackground(SystemColor.activeCaption);
		altaEstado.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(altaEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblNombre = new JLabel("Nombre*");
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setText(estado.getNombre());
		
		descripcion = new JTextArea();
		scrollPane.setViewportView(descripcion);
		descripcion.setText(estado.getDescripcion());
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		
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
					estadoController.editarEstado(estado.getId(), nombre.getText(), descripcion.getText());
					dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo modificar el estado.");
					JOptionPane.showMessageDialog(null,
							e.toString());
				}
			}
		});
		
		JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
		lblCamposObligatorios.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		JSeparator separator = new JSeparator();
		
		GroupLayout gl_altaEstado = new GroupLayout(altaEstado);
		gl_altaEstado.setHorizontalGroup(
			gl_altaEstado.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_altaEstado.createSequentialGroup()
					.addGroup(gl_altaEstado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_altaEstado.createSequentialGroup()
							.addGap(89)
							.addGroup(gl_altaEstado.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_altaEstado.createSequentialGroup()
									.addComponent(lblNombre)
									.addGap(29))
								.addGroup(gl_altaEstado.createSequentialGroup()
									.addComponent(lblDescripcion)
									.addGap(18)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_altaEstado.createParallelGroup(Alignment.LEADING, false)
								.addComponent(nombre)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
						.addGroup(gl_altaEstado.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_altaEstado.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_altaEstado.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_altaEstado.createSequentialGroup()
										.addGap(13)
										.addComponent(lblCamposObligatorios))
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_altaEstado.createSequentialGroup()
									.addComponent(btnCancelar)
									.addGap(33)
									.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(118)))))
					.addContainerGap())
		);
		gl_altaEstado.setVerticalGroup(
			gl_altaEstado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_altaEstado.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_altaEstado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_altaEstado.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_altaEstado.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
							.addComponent(lblCamposObligatorios))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_altaEstado.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		
		altaEstado.setLayout(gl_altaEstado);
	}

	public static void clearTextFields() {
		nombre.setText("");
		descripcion.setText("");
	}
}
