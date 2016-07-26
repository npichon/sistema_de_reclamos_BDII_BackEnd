package vistas.alta;

import java.awt.BorderLayout;
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

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

import controladores.VentajaController;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import vistas.PanelPrincipal;

public class AltaVentaja extends JDialog {
	private static JTextField nombre;
	private static JTextArea descripcion;
	private PanelPrincipal altaVentaja;
	private VentajaController ventajaController = new VentajaController();
	private static JTextField puntaje;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaVentaja dialog = new AltaVentaja("");
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
	public AltaVentaja(String referer) {
		
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
		setTitle("Nueva Ventaja");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 350);
		altaVentaja = new PanelPrincipal();
		altaVentaja.setBackground(SystemColor.activeCaption);
		altaVentaja.setToolTipText("Nueva Categoria");
		altaVentaja.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(altaVentaja);

		JLabel lblNombre = new JLabel("Nombre *");

		nombre = new JTextField();
		nombre.setColumns(10);

		descripcion = new JTextArea();

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
					String result = ventajaController.altaVentaja(nombre.getText(),
							descripcion.getText(),
							puntaje.getText(), referer);
					if(result != ""){
						dispose();
						ventajaController.listarVentajas();
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo guardar la nueva ventaja.");
					JOptionPane.showMessageDialog(null,
							e.toString());
				}
			}
		});

		JLabel lblCamposObligtorios = new JLabel("* Campos Obligtorios");
		lblCamposObligtorios.setFont(new Font("Tahoma", Font.PLAIN, 9));

		JSeparator separator = new JSeparator();

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblPuntaje = new JLabel("Puntaje Necesario *");

		puntaje = new JTextField();
		puntaje.setColumns(10);
		
		
		GroupLayout gl_altaVentaja = new GroupLayout(altaVentaja);
		gl_altaVentaja.setHorizontalGroup(
			gl_altaVentaja.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_altaVentaja.createSequentialGroup()
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_altaVentaja.createSequentialGroup()
							.addGap(70)
							.addGroup(gl_altaVentaja.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblDescripcion)
								.addComponent(lblPuntaje))
							.addGap(10)
							.addGroup(gl_altaVentaja.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addGroup(gl_altaVentaja.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(puntaje, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
								.addComponent(nombre)))
						.addGroup(gl_altaVentaja.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCamposObligtorios)))
					.addContainerGap(114, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_altaVentaja.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(30)
					.addComponent(btnGuardar)
					.addGap(118))
				.addGroup(gl_altaVentaja.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
		);
		gl_altaVentaja.setVerticalGroup(
			gl_altaVentaja.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_altaVentaja.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPuntaje)
						.addComponent(puntaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(lblCamposObligtorios)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnGuardar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		scrollPane.setViewportView(descripcion);
		altaVentaja.setLayout(gl_altaVentaja);
	}

	public static void clearTextFields() {
		nombre.setText("");
		descripcion.setText("");
		puntaje.setText("");
	}
}
