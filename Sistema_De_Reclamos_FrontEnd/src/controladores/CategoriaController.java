package controladores;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.Categoria;
import servicio.ServiceSistemaDeReclamos;
import validaciones.Validar;
import vistas.alta.AltaCategoria;
import vistas.editar.EditarCategoria;
import vistas.listar.ListarCategorias;

public class CategoriaController {
	private ServiceSistemaDeReclamos apiReclamos = new ServiceSistemaDeReclamos();
	private Validar validar = new Validar();

	public CategoriaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String altaCategoria(String nombre, String descripcion, String puntaje,
			String referer) throws SQLException {

		String idCategoria = "";

		Boolean[] ok = new Boolean[1];
		Categoria categoria = validar.validarCategoria(nombre, descripcion,
				puntaje, ok);

		if (ok[0]) {
			idCategoria = apiReclamos.altaCategoria(categoria);
			if (idCategoria != "") {

				if (referer.equals("lista")) {
					actualizarCategorias();
				}
				JOptionPane.showMessageDialog(null,
						"Se ha agregado la nueva categría.");
				AltaCategoria.clearTextFields();
			}
		}
		return idCategoria;
	}

	public List<Categoria> listCategorias() throws SQLException {
		return apiReclamos.listCategorias();
	}

	public void listarCategorias() throws SQLException {
		ListarCategorias lista = ListarCategorias.getInstance();
		actualizarCategorias();
		lista.setLocationRelativeTo(null);
		lista.setVisible(true);
	}

	public void verCategoria(String idCategoria) throws SQLException {
		Categoria categoria = apiReclamos.getCategoria(idCategoria);
		EditarCategoria editar = new EditarCategoria(categoria);
		editar.setLocationRelativeTo(null);
		editar.setVisible(true);
	}

	public void editarCategoria(String idCategoria, String nombre,
			String descripcion, String puntaje) throws SQLException {

		Boolean[] ok = new Boolean[1];
		Categoria categoria = validar.validarCategoria(nombre, descripcion,
				puntaje, ok);
		categoria.setId(idCategoria);

		if (ok[0]) {

			apiReclamos.editarCategoria(categoria);
			actualizarCategorias();
			JOptionPane.showMessageDialog(null, "Se ha editado la categría.");
		}
	}

	public void eliminarCategoria(String idCategoria) throws SQLException {
		apiReclamos.eliminarCategoria(idCategoria);
		actualizarCategorias();
		JOptionPane.showMessageDialog(null, "Se ha eliminado la categría.");
	}

	private void actualizarCategorias() throws SQLException {
		List<Categoria> categorias = apiReclamos.listCategorias();
		ListarCategorias.actualizarCategoria(categorias);
	}

}
