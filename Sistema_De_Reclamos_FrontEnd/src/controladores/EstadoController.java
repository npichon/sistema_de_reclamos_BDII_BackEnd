package controladores;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.Estado;
import modelos.Ventaja;
import servicio.ServiceSistemaDeReclamos;
import validaciones.Validar;
import vistas.alta.AltaEstado;
import vistas.editar.EditarEstado;
import vistas.listar.ListarEstados;

public class EstadoController {
	private ServiceSistemaDeReclamos apiReclamos = new ServiceSistemaDeReclamos();
	private Validar validar = new Validar();

	public EstadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String altaEstado(String nombre, String descripcion, String referer)
			throws SQLException {
		String idEstado = "";

		Boolean[] ok = new Boolean[1];
		Estado estado = validar.validarEstado(nombre, descripcion, ok);
		
		if (ok[0]) {
			idEstado = apiReclamos.altaEstado(estado);
			if (idEstado != "") {

				if (referer.equals("lista")) {
					actulizarEstados();
				}

				JOptionPane.showMessageDialog(null,
						"Se ha agregado la nueva categría.");
				AltaEstado.clearTextFields();
			}
		}
		return idEstado;
	}

	public void verEstado(String idEstado) throws SQLException {
		Estado estado = apiReclamos.getEstado(idEstado);
		EditarEstado editar = new EditarEstado(estado);
		editar.setLocationRelativeTo(null);
		editar.setVisible(true);
	}

	public void eliminarEstado(String idEstado) throws SQLException {
		apiReclamos.eliminarEstado(idEstado);
		actulizarEstados();
		JOptionPane.showMessageDialog(null, "Se ha eliminado el estado.");
	}

	public void editarEstado(String idEstado, String nombre, String descripcion)
			throws SQLException {

		Boolean[] ok = new Boolean[1];
		Estado estado = validar.validarEstado(nombre, descripcion, ok);
		estado.setId(idEstado);
		
		if (ok[0]) {
			apiReclamos.editarEstado(estado);
			actulizarEstados();
			JOptionPane.showMessageDialog(null, "Se ha modificado el estado.");
		}
	}

	public void listarEstados() throws SQLException {
		ListarEstados listaEstados = ListarEstados.getInstance();
		actulizarEstados();
		listaEstados.setLocationRelativeTo(null);
		listaEstados.setVisible(true);
	}

	private void actulizarEstados() throws SQLException {
		List<Estado> estados = apiReclamos.getListEstados();
		ListarEstados.actualizarEstados(estados);
	}

}
