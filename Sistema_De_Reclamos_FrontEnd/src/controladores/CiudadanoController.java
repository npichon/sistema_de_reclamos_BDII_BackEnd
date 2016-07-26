package controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.Ciudadano;
import modelos.Direccion;
import modelos.Reclamo;
import modelos.Ventaja;
import servicio.ServiceSistemaDeReclamos;
import validaciones.Validar;
import vistas.DetalleCiudadano;
import vistas.editar.EditarCiudadano;
import vistas.listar.ListarCiudadanos;

public class CiudadanoController {
	private ServiceSistemaDeReclamos apiReclamos = new ServiceSistemaDeReclamos();
	private Validar validar = new Validar();

	public CiudadanoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String altaCiudadadano(String nombre, String apellido, String dni,
			String email, String cuentaTwitter, String telefono, String calle,
			String numero, String piso, String departamento, String latitud,
			String longitud) throws SQLException {
		
		String idCiudadano = "";
		
		Boolean[] ok = new Boolean[1];
		Ciudadano ciudadano = validar.validarCiudadano(nombre, apellido, dni,
				email, cuentaTwitter, telefono, ok);

		if (ok[0]) {

			if(apiReclamos.ciudadanoByCondicion(ciudadano.getDni()).size() == 0){
				Direccion direccion = validar.validarDireccion(calle, numero, piso,
						departamento, latitud, longitud, ok);
	 
				if (ok[0]) {
					ciudadano.setDireccion(direccion); 
					idCiudadano = apiReclamos.altaCiudadadano(ciudadano);
					if (idCiudadano != "") {
						JOptionPane
								.showMessageDialog(null,
										"Se ha guardado la información del nuevo ciudadano.");
						//AltaCiudadano.clearTextFields();
					}
				}
			}else{
				JOptionPane
				.showMessageDialog(null,
						"Este ciudadano ya esta cargado en el sistema");
			}
		}
		return idCiudadano;
	}

	public int detalleCiudadano(int dni) throws SQLException {
		int result = 1;
		List<Ciudadano> ciudadanos = apiReclamos.ciudadanoByCondicion(dni);

		if(ciudadanos.size() > 0){
			Ciudadano ciudadano = ciudadanos.get(0);
			if(ciudadano.getReclamos() == null){
				ciudadano.setReclamos(new ArrayList<Reclamo>());
			}
			if(ciudadano.getVentajas() == null){
				ciudadano.setVentajas(new ArrayList<Ventaja>());
			}
			DetalleCiudadano detalleUser = new DetalleCiudadano(ciudadano, ciudadano.getReclamos());
			detalleUser.setLocationRelativeTo(null);
			detalleUser.setVisible(true);
		}else{
			result = -1;
			JOptionPane.showMessageDialog(null,
					"No se encontro el ciudadano con ese DNI");
		}
		return result;
	}

	public void listarCiudadanos() throws SQLException {
		List<Ciudadano> ciudadanos = apiReclamos.ciudadanoGetList();
		ListarCiudadanos lista = new ListarCiudadanos();
		ListarCiudadanos.actualizarCiudadanos(ciudadanos);
		lista.setLocationRelativeTo(null);
		lista.setVisible(true);
	}

	public void verCiudadano(String id) throws SQLException {
		Ciudadano ciudadano = apiReclamos.getCiudadano(id);
		EditarCiudadano editarCiudadano = new EditarCiudadano(ciudadano,
				"lista");
		editarCiudadano.setLocationRelativeTo(null);
		editarCiudadano.setVisible(true);
	}

	public Ciudadano editarCiudadadano(String idCiudadano, String nombre,
			String apellido, String dni, String email, String cuentaTwitter,
			String telefono, String idDireccion, String calle, String numero,
			String piso, String departamento, String latitud, String longitud,
			String referer) throws SQLException {

		Boolean[] ok = new Boolean[1];
		Ciudadano ciudadano = validar.validarCiudadano(nombre, apellido, dni,
				email, cuentaTwitter, telefono, ok);
		if (ok[0]) {

			Direccion direccion = validar.validarDireccion(calle, numero, piso,
					departamento, latitud, longitud, ok);

			if (ok[0]) {
				apiReclamos.editarCiudadadano(ciudadano, direccion);
				ciudadano.setDireccion(direccion);
				JOptionPane.showMessageDialog(null,
						"Se ha guardado la nueva informacion del ciudadano.");
			}
		}
		return ciudadano;
	}

	public void eliminarCiudadano(String id) throws SQLException {
		Ciudadano ciudadano = apiReclamos.getCiudadano(id);
		apiReclamos.eliminarCiudadano(ciudadano);
		List<Ciudadano> ciudadanos = apiReclamos.ciudadanoGetList();
		ListarCiudadanos.actualizarCiudadanos(ciudadanos);
		JOptionPane.showMessageDialog(null, "Se ha eliminado el ciudadano.");
	}

	public List<Ciudadano> ciudadanoGetList() throws SQLException {
		return apiReclamos.ciudadanoGetList();
	}
}
