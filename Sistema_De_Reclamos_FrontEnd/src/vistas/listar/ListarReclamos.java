package vistas.listar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vistas.PanelPrincipal;
import vistas.PantallaPrincipal;
import vistas.alta.AltaVentaja;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.Reclamo;
import modelos.Ventaja;
import controladores.ReclamoController;

public class ListarReclamos extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JScrollPane scrollPane;
	private static JTable table;
	private static ReclamoController reclamoController = new ReclamoController();
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { ListarReclamos dialog =
	 * new ListarReclamos(null, null);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public ListarReclamos(List<Reclamo> listReclamos) {
		
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
		setTitle("Lista de Reclamos");
		setBounds(100, 100, 685, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();

		actualizarReclamos(listReclamos);

		JButton btnAceptar = new JButton("Salir");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JButton btnBorrar = new JButton("Borrar");
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
											table.getSelectedRow(), 0), "lista");
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo eliminar el reclamo.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un reclamo.");
				}
			}
		});

		JButton btnNuevoReclamo = new JButton("Nuevo Reclamo");
		btnNuevoReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					dispose();
					JOptionPane.showMessageDialog(null, "Ciudadano asociado.");
					PantallaPrincipal buscarCiudadano = PantallaPrincipal
							.getInstance();
					buscarCiudadano.setLocationRelativeTo(null);
					buscarCiudadano.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							"No se pueden tener acceso a esta sección.");
				}
			}
		});

		JButton btnVerReclamo = new JButton("Ver Reclamo");
		btnVerReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						dispose();
						reclamoController.verReclamo((String) table
								.getValueAt(table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						JOptionPane.showMessageDialog(null,
								"No se pueden tener acceso a esta sección.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione un Reclamo.");
				}
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel
				.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 669,
						Short.MAX_VALUE)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnAceptar)
								.addGap(18)
								.addComponent(btnNuevoReclamo)
								.addGap(103)
								.addComponent(btnVerReclamo)
								.addPreferredGap(ComponentPlacement.RELATED,
										214, Short.MAX_VALUE)
								.addComponent(btnBorrar).addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 214,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										25, Short.MAX_VALUE)
								.addGroup(
										gl_contentPanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnBorrar)
												.addComponent(btnNuevoReclamo)
												.addComponent(btnAceptar)
												.addComponent(btnVerReclamo))));
		contentPanel.setLayout(gl_contentPanel);
	}

	public static void actualizarReclamos(List<Reclamo> reclamos) {
		if (reclamos != null) {
			int total = 0;
			String[] columns = { "N\u00FAmero de Reclamo", "Estado",
					"Descripci\u00F3n", "Fecha", "Categoria", "Calle",
					"N\u00FAmero", "Piso", "Departamento" };
			Object[][] valores = new Object[reclamos.size()][9];
			for (int i = 0; i < reclamos.size(); i++) {
				valores[i][0] = reclamos.get(i).getId();
				valores[i][1] = reclamos.get(i).getEstados()
						.get(reclamos.get(i).getEstados().size() - 1)
						.getEstado().getNombre();
				valores[i][2] = reclamos.get(i).getDescripcion();
				valores[i][3] = reclamos.get(i).getEstados().get(0).getFecha();
				valores[i][4] = reclamos.get(i).getCategoria().getNombre();
				valores[i][5] = reclamos.get(i).getUbicacion().getCalle();
				valores[i][6] = reclamos.get(i).getUbicacion().getNumero();
				valores[i][7] = reclamos.get(i).getUbicacion().getPiso();
				valores[i][8] = reclamos.get(i).getUbicacion()
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
			scrollPane.setViewportView(table);
		}
	}
}
