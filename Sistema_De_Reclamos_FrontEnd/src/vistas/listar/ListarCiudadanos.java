package vistas.listar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import modelos.Ciudadano;
import controladores.CiudadanoController;
import vistas.PanelPrincipal;
import vistas.alta.AltaCiudadano;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarCiudadanos extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private JButton salir;
	private JButton eliminar;
	private static JTable table;
	private static JScrollPane scrollPane;
	private JButton btnNuevoCiudadano;
	private CiudadanoController ciudadanoController = new CiudadanoController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			ListarCiudadanos dialog = new ListarCiudadanos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListarCiudadanos() {
		
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
		
		setBounds(100, 100, 730, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();

		JSeparator separator = new JSeparator();
		{
			salir = new JButton("Salir");
			salir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}
			});
			// salir.setActionCommand("Cancel");
		}

		btnNuevoCiudadano = new JButton("Nuevo Ciudadano");
		btnNuevoCiudadano.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AltaCiudadano nuevoCiudadano = new AltaCiudadano("lista");
				nuevoCiudadano.setLocationRelativeTo(null);
				nuevoCiudadano.setVisible(true);
			}
		});

		JButton btnVerReclamos = new JButton("Lista de Reclamos");
		btnVerReclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						ciudadanoController.detalleCiudadano((Integer) table
								.getValueAt(table.getSelectedRow(), 3));
						dispose();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se puden ver los reclamos del ciudadano.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un ciudadano.");
				}
			}
		});

		JButton editar = new JButton("Editar");
		editar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						ciudadanoController.verCiudadano((String) table
								.getValueAt(table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudede tener acceso a esta seccion.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un ciudadano.");
				}
			}
		});
		{
			eliminar = new JButton("Eliminar");
			eliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						try {
							int confirmado = JOptionPane.showConfirmDialog(
									null, "Quiere eliminar el ciudadano?");
							if (JOptionPane.OK_OPTION == confirmado) {

								ciudadanoController
										.eliminarCiudadano((String) table
												.getValueAt(
														table.getSelectedRow(),
														0));
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null,
									"No se pudo eliminar el ciudadano.");
							JOptionPane.showMessageDialog(null, e.toString());
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Seleccione un ciudadano.");
					}
				}
			});
			eliminar.setActionCommand("OK");
			getRootPane().setDefaultButton(eliminar);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel
				.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 714,
						Short.MAX_VALUE)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(salir)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNuevoCiudadano)
								.addGap(88)
								.addComponent(btnVerReclamos)
								.addPreferredGap(ComponentPlacement.RELATED,
										153, Short.MAX_VALUE)
								.addComponent(editar).addGap(18)
								.addComponent(eliminar).addGap(22))
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(separator,
										GroupLayout.DEFAULT_SIZE, 694,
										Short.MAX_VALUE).addContainerGap()));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												306, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 17,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(salir)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				btnNuevoCiudadano)
																		.addComponent(
																				btnVerReclamos))
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				eliminar)
																		.addComponent(
																				editar)))
										.addContainerGap()));

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
	}

	public static void actualizarCiudadanos(List<Ciudadano> ciudadanos) {
		if (ciudadanos != null) {
			String[] columns = { "Id", "Nombre", "Apellido", "DNI", "Email",
					"Puntos", "Cuenta de twitter", "Telefono", "Calle",
					"Número" };
			Object[][] valores = new Object[ciudadanos.size()][10];
			for (int i = 0; i < ciudadanos.size(); i++) {
				valores[i][0] = ciudadanos.get(i).getId();
				valores[i][1] = ciudadanos.get(i).getNombre();
				valores[i][2] = ciudadanos.get(i).getApellido();
				valores[i][3] = ciudadanos.get(i).getDni();
				valores[i][4] = ciudadanos.get(i).getEmail();
				valores[i][5] = ciudadanos.get(i).getPuntos();
				valores[i][6] = ciudadanos.get(i).getCuentaTwitter();
				valores[i][7] = ciudadanos.get(i).getTelefono();
				valores[i][8] = ciudadanos.get(i).getDireccion().getCalle();
				valores[i][9] = ciudadanos.get(i).getDireccion().getNumero();
			}
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
			scrollPane.setViewportView(table);
			table.getColumn("Id").setWidth(0);
			table.getColumn("Id").setMinWidth(0);
			table.getColumn("Id").setMaxWidth(0);
		}
	}
}
