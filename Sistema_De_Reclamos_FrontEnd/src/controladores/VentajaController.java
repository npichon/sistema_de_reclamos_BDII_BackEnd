package controladores;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.Ventaja;
import servicio.ServiceSistemaDeReclamos;
import validaciones.Validar;
import vistas.alta.AltaVentaja;
import vistas.editar.EditarVentaja;
import vistas.listar.ListarVentajas;

public class VentajaController {
	private ServiceSistemaDeReclamos apiReclamos = new ServiceSistemaDeReclamos();
	private Validar validar = new Validar();

	public VentajaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String altaVentaja(String nombre, String descripcion,
			String puntajeNecesario, String referer) throws SQLException {
		
		String idVentaja = "";

		Boolean[] ok = new Boolean[1];
		Ventaja ventaja = validar.validarVentaja(nombre, descripcion,
				puntajeNecesario, ok);

		if (ok[0]) {
			idVentaja = apiReclamos.altaVentaja(ventaja);
			if (idVentaja != "") {

				if (referer.equals("lista")) {
					actualizarVentajas();
				}
				JOptionPane.showMessageDialog(null,
						"Se ha agregado la nueva ventaja.");
				AltaVentaja.clearTextFields();
			}
		}
		return idVentaja;
	}

	public void listarVentajasCiudadano(int puntaje) throws SQLException {
		ListarVentajas listaVentajas = ListarVentajas.getInstance("ciudadano");
		List<Ventaja> ventajas = apiReclamos.listVentajasCiudadano(puntaje);
		ListarVentajas.actualizarVentajas(ventajas);
		listaVentajas.setLocationRelativeTo(null);
		listaVentajas.setVisible(true);
	}

	public void listarVentajas() throws SQLException {
		ListarVentajas dialog = ListarVentajas.getInstance("menu");
		actualizarVentajas();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	public void verVentaja(String idVentaja) throws SQLException {
		Ventaja ventaja = apiReclamos.getVentaja(idVentaja);
		EditarVentaja editar = new EditarVentaja(ventaja);
		editar.setLocationRelativeTo(null);
		editar.setVisible(true);
	}

	public void editarVentaja(String idVentaja, String nombre, String descripcion,
			String puntosNecesarios) throws SQLException {

		Boolean[] ok = new Boolean[1];
		Ventaja ventaja = validar.validarVentaja(nombre, descripcion,
				puntosNecesarios, ok);
		ventaja.setId(idVentaja);

		if (ok[0]) {
			apiReclamos.editarVentaja(ventaja);
			actualizarVentajas();
			JOptionPane.showMessageDialog(null, "Se ha modificado la ventaja.");
		}
	}

	public void eliminarVentaja(String idVentaja) throws SQLException {
		apiReclamos.eliminarVentaja(idVentaja);
		actualizarVentajas();
		JOptionPane.showMessageDialog(null, "Se ha eliminado la ventaja.");
	}

	private void actualizarVentajas() throws SQLException {
		List<Ventaja> ventajas = apiReclamos.listVentajas();
		ListarVentajas.actualizarVentajas(ventajas);
	}

}
