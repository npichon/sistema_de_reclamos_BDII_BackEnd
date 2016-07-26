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
import vistas.alta.AltaVentaja;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.Reclamo;
import modelos.Ventaja;
import controladores.VentajaController;

public class ListarVentajas extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JScrollPane scrollPane;
	private static JTable table;
	private static VentajaController ventajaController = new VentajaController();
	private static ListarVentajas lista = null;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			ListarVentajas dialog = new ListarVentajas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	private ListarVentajas(String referer) {
		
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
		setTitle("Lista de Ventajas Disponible");
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		table = new JTable();

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						ventajaController.verVentaja((String) table
								.getValueAt(table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						JOptionPane.showMessageDialog(null,
								"No se pueden tener acceso a esta sección.");
					}
					// dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione una ventaja.");
				}
			}
		});

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					try {
						int confirmado = JOptionPane.showConfirmDialog(null,
								"Quiere eliminar la ventaja?");
						if (JOptionPane.OK_OPTION == confirmado) {
							ventajaController.eliminarVentaja((String) table
									.getValueAt(table.getSelectedRow(), 0));
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo eliminar la ventaja.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Seleccione una ventaja.");
				}
			}
		});

		JButton btnNuevaVentaja = new JButton("Nueva Ventaja");
		btnNuevaVentaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AltaVentaja dialog = new AltaVentaja("lista");
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		if (referer.equals("menu")) {
			// btnAceptar.setVisible(false);
			btnEditar.setVisible(true);
			btnBorrar.setVisible(true);
			btnNuevaVentaja.setVisible(true);
		} else {
			btnNuevaVentaja.setVisible(false);
			btnEditar.setVisible(false);
			btnBorrar.setVisible(false);
			// btnAceptar.setVisible(true);
		}

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel
				.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584,
						Short.MAX_VALUE)
				.addGroup(
						gl_contentPanel
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNuevaVentaja)
								.addPreferredGap(ComponentPlacement.RELATED,
										163, Short.MAX_VALUE)
								.addComponent(btnAceptar).addGap(97)
								.addComponent(btnEditar)
								.addPreferredGap(ComponentPlacement.RELATED)
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
												.addComponent(btnNuevaVentaja)
												.addComponent(btnEditar)
												.addComponent(btnAceptar))));
		contentPanel.setLayout(gl_contentPanel);
	}

	public static void actualizarVentajas(List<Ventaja> ventajas) {
		if (ventajas != null) {
			int total = 0;
			String[] columns = { "N\u00FAmero de Ventaja", "Nombre",
					"Descripci\u00F3n", "Puntaje Necesario" };
			Object[][] valores = new Object[ventajas.size()][4];
			for (int i = 0; i < ventajas.size(); i++) {
				valores[i][0] = ventajas.get(i).getId();
				valores[i][1] = ventajas.get(i).getNombre();
				valores[i][2] = ventajas.get(i).getDescripcion();
				valores[i][3] = ventajas.get(i).getPuntosNecesarios();
			}
			
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
	
	public synchronized static ListarVentajas getInstance(String referer) throws SQLException {
		if(lista == null){
			lista = new ListarVentajas(referer);
		}
		return lista;
	}
}
