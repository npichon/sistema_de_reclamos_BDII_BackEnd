package vistas;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import vistas.alta.AltaCategoria;
import vistas.alta.AltaCiudadano;
import vistas.alta.AltaEstado;
import vistas.alta.AltaVentaja;
//import vistas.listar.ListarTwitters;
import controladores.CategoriaController;
import controladores.CiudadanoController;
import controladores.EstadoController;
import controladores.ReclamoController;
import controladores.VentajaController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MenuPrincipal extends JMenuBar {
	private CiudadanoController ciudadanoController = new CiudadanoController();
	private static ResourceBundle labels;

	public MenuPrincipal() {
		
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
				
        
		JMenu mnReclamo = new JMenu(labels.getString("menu.principal.reclamo"));
		this.add(mnReclamo);

		JMenuItem mntmAlta_5 = new JMenuItem(
				labels.getString("menu.principal.subMenu.agregar"));
		mntmAlta_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PantallaPrincipal buscarCiudadano = PantallaPrincipal
							.getInstance();
					buscarCiudadano.setLocationRelativeTo(null);
					buscarCiudadano.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("reclamo.acceso.excepcion"));
				}
			}
		});
		mnReclamo.add(mntmAlta_5);

		JMenuItem mntmListar_5 = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReclamoController reclamoController = new ReclamoController();
				try {
					reclamoController.listarReclamos();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("reclamo.listar.excepcion"));
				}
			}
		});
		mnReclamo.add(mntmListar_5);



		JMenu mnCiudadano = new JMenu(
				labels.getString("menu.principal.ciudadano"));
		this.add(mnCiudadano);

		JMenuItem mntmAlta_4 = new JMenuItem(
				labels.getString("menu.principal.subMenu.agregar"));
		mntmAlta_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaCiudadano dialog = new AltaCiudadano("");
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
		mnCiudadano.add(mntmAlta_4);

		JMenuItem mntmListar_4 = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ciudadanoController.listarCiudadanos();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("ciudadano.listar.excepcion"));
				}
			}
		});
		mnCiudadano.add(mntmListar_4);

		JMenuItem mntmBuscar_4 = new JMenuItem(
				labels.getString("menu.principal.subMenu.buscar"));
		mntmBuscar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					PantallaPrincipal buscarCiudadano = PantallaPrincipal
							.getInstance();
					buscarCiudadano.setLocationRelativeTo(null);
					buscarCiudadano.setVisible(true);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("ciudadano.buscar.excepcion"));
				}
			}
		});
		mnCiudadano.add(mntmBuscar_4);

		JMenu mnVentaja = new JMenu(labels.getString("menu.principal.ventaja"));
		this.add(mnVentaja);

		JMenuItem mntmAlta_3 = new JMenuItem(
				labels.getString("menu.principal.subMenu.agregar"));
		mntmAlta_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaVentaja dialog = new AltaVentaja("menu");
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		});
		mnVentaja.add(mntmAlta_3);

		JMenuItem mntmListar_3 = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentajaController ventajaController = new VentajaController();
				try {
					ventajaController.listarVentajas();
				} catch (SQLException e1) {
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("ventaja.listar.excepcion"));
				}

			}
		});
		mnVentaja.add(mntmListar_3);


		JMenu mnEstado = new JMenu(labels.getString("menu.principal.estado"));
		this.add(mnEstado);

		JMenuItem mntmAlta_2 = new JMenuItem(
				labels.getString("menu.principal.subMenu.agregar"));
		mntmAlta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEstado altaEstado = new AltaEstado("menu");
				altaEstado.setLocationRelativeTo(null);
				altaEstado.setVisible(true);
			}
		});
		mnEstado.add(mntmAlta_2);

		JMenuItem mntmListar_2 = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoController estadoController = new EstadoController();
				try {
					estadoController.listarEstados();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("estado.listar.excepcion"));
				}
			}
		});
		mnEstado.add(mntmListar_2);

		JMenu mnCategoria = new JMenu(
				labels.getString("menu.principal.categoria"));
		this.add(mnCategoria);

		JMenuItem mntmAlta = new JMenuItem(
				labels.getString("menu.principal.subMenu.agregar"));
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaCategoria nuevaCategoria = new AltaCategoria("menu");
				nuevaCategoria.setLocationRelativeTo(null);
				nuevaCategoria.setVisible(true);
			}
		});
		mnCategoria.add(mntmAlta);

		JMenuItem mntmListar = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoriaController categoriaController = new CategoriaController();
				try {
					categoriaController.listarCategorias();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					JOptionPane.showMessageDialog(null,
							labels.getString("categoria.listar.excepcion"));
				}
			}
		});
		mnCategoria.add(mntmListar);

		JMenu mnTwitter = new JMenu(
				labels.getString("menu.principal.twitter"));
		this.add(mnTwitter);
		
		JMenuItem mntmListar_7 = new JMenuItem(
				labels.getString("menu.principal.subMenu.listar"));
		mntmListar_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	ListarTwitters listaTwitter = new ListarTwitters();
				listaTwitter.setLocationRelativeTo(null);
				listaTwitter.setVisible(true);*/
			}
		});
		mnTwitter.add(mntmListar_7);
		
	}
}
