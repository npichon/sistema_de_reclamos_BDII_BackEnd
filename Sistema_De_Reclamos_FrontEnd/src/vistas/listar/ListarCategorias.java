package vistas.listar;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vistas.PanelPrincipal;
import vistas.alta.AltaCategoria;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.Categoria;
import controladores.CategoriaController;

public class ListarCategorias extends JDialog {

	private final PanelPrincipal contentPanel = new PanelPrincipal();
	private static JScrollPane scrollPane;
	private static JTable table;
	private static CategoriaController categoriaController = new CategoriaController();
	private static ListarCategorias lista = null;
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
	private ListarCategorias() {
		
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
		setTitle("Categor\u00EDas Disponibles");
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
						categoriaController.verCategoria((String) table
								.getValueAt(table.getSelectedRow(), 0));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se puede tener acceso a esta seccion.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane
							.showMessageDialog(null, "Seleccione una categoría.");
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
								"Quiere eliminar la categoría?");
						if (JOptionPane.OK_OPTION == confirmado) {
							categoriaController
									.eliminarCategoria((String) table
											.getValueAt(table.getSelectedRow(),
													0));
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,
								"No se pudo eliminar la categoría.");
						JOptionPane.showMessageDialog(null, e.toString());
					}
					// dispose();
				} else {
					JOptionPane
							.showMessageDialog(null, "Seleccione una categoría.");
				}
			}
		});

		JButton btnNuevaVentaja = new JButton("Nueva Categor\u00EDa");
		btnNuevaVentaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AltaCategoria dialog = new AltaCategoria("lista");
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
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(41)
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

	public static void actualizarCategoria(List<Categoria> categorias) {
		if (categorias != null) {
			int total = 0;
			String[] columns = { "Id", "Nombre", "Descripci\u00F3n", "Puntaje" };
			Object[][] valores = new Object[categorias.size()][4];
			for (int i = 0; i < categorias.size(); i++) {
				valores[i][0] = categorias.get(i).getId();
				valores[i][1] = categorias.get(i).getNombre();
				valores[i][2] = categorias.get(i).getDescripcion();
				valores[i][3] = categorias.get(i).getPuntaje();
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
	
	public synchronized static ListarCategorias getInstance() throws SQLException {
		if(lista == null){
			lista = new ListarCategorias();
		}
		return lista;
	}
}
