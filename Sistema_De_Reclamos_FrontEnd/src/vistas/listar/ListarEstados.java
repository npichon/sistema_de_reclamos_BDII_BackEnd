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
import vistas.alta.AltaEstado;
import vistas.alta.AltaVentaja;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.Estado;
import modelos.Reclamo;
import modelos.Ventaja;
import controladores.EstadoController;
import controladores.VentajaController;

public class ListarEstados extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JScrollPane scrollPane;
	private static JTable table;
	private static EstadoController estadoController = new EstadoController();
	private static ListarEstados lista = null;
	private static ResourceBundle labels;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { ListarEstados dialog = new
	 * ListarEstados(null, null);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	private ListarEstados() {
		
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
		setTitle("Estados Disponibles");
		setBounds(100, 100, 479, 300);
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
						estadoController.verEstado((String) table.getValueAt(
								table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se puede tener acceso a esta sección.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane
							.showMessageDialog(null, "Seleccione un estado.");
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
								"Quiere eliminar el estado?");
						if (JOptionPane.OK_OPTION == confirmado) {
							estadoController.eliminarEstado((String) table
									.getValueAt(table.getSelectedRow(), 0));
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo eliminar el estado.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane
							.showMessageDialog(null, "Seleccione un estado.");
				}
			}
		});

		JButton btnNuevaVentaja = new JButton("Nuevo Estado");
		btnNuevaVentaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AltaEstado dialog = new AltaEstado("lista");
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNuevaVentaja)
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(48)
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBorrar)
					.addContainerGap())
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBorrar)
						.addComponent(btnNuevaVentaja)
						.addComponent(btnEditar)
						.addComponent(btnAceptar)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	public static void actualizarEstados(List<Estado> estados) {
		if (estados != null) {
			int total = 0;
			String[] columns = { "Id", "Nombre", "Descripci\u00F3n" };
			Object[][] valores = new Object[estados.size()][3];
			for (int i = 0; i < estados.size(); i++) {
				valores[i][0] = estados.get(i).getId();
				valores[i][1] = estados.get(i).getNombre();
				valores[i][2] = estados.get(i).getDescripcion();
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
			table.getColumn("Id").setWidth(0);
			table.getColumn("Id").setMinWidth(0);
			table.getColumn("Id").setMaxWidth(0);
		}
	}
	
	public synchronized static ListarEstados getInstance() throws SQLException {
		if(lista == null){
			lista = new ListarEstados();
		}
		return lista;
	}
}
