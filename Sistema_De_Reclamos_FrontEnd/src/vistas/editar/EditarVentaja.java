package vistas.editar;

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

import modelos.Ventaja;
import vistas.PanelPrincipal;

public class EditarVentaja extends JDialog {
	private static JTextField nombre;
	private static JTextArea descripcion;
	private PanelPrincipal altaVentaja;
	private VentajaController ventajaController = new VentajaController();
	private JTextField puntaje;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarVentaja dialog = new EditarVentaja(null);
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
	public EditarVentaja(Ventaja ventaja) {
		
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
		nombre.setText(ventaja.getNombre());
		
		JLabel lblDescripcion = new JLabel("Descripcion");

		descripcion = new JTextArea();
		descripcion.setText(ventaja.getDescripcion());
		
		JLabel lblPuntaje = new JLabel("Puntos Necesarios *");

		puntaje = new JTextField();
		puntaje.setColumns(10);
		puntaje.setText(String.valueOf(ventaja.getPuntosNecesarios()));

		
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
					ventajaController.editarVentaja(ventaja.getId(), nombre.getText(),
							descripcion.getText(),
							puntaje.getText());
					dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"No se pudo modificar la ventaja.");
					JOptionPane.showMessageDialog(null,
							e.toString());
				}
			}
		});

		JLabel lblCamposObligtorios = new JLabel("* Campos Obligtorios");
		lblCamposObligtorios.setFont(new Font("Tahoma", Font.PLAIN, 9));

		JSeparator separator = new JSeparator();

		JScrollPane scrollPane = new JScrollPane();

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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_altaVentaja.createParallelGroup(Alignment.LEADING)
								.addComponent(puntaje)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_altaVentaja.createSequentialGroup()
									.addGap(1)
									.addComponent(nombre, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))))
						.addGroup(gl_altaVentaja.createSequentialGroup()
							.addGap(146)
							.addComponent(btnCancelar)
							.addGap(38)
							.addComponent(btnGuardar))
						.addGroup(gl_altaVentaja.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCamposObligtorios)))
					.addContainerGap(81, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_altaVentaja.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
					.addGap(11))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_altaVentaja.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);

		scrollPane.setViewportView(descripcion);
		altaVentaja.setLayout(gl_altaVentaja);
	}

	public static void clearTextFields() {
		nombre.setText("");
		descripcion.setText("");
	}
}
