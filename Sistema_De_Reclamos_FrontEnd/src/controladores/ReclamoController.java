package controladores;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import modelos.Categoria;
import modelos.Ciudadano;
import modelos.Direccion;
import modelos.Estado;
import modelos.EstadoReclamo;
import modelos.Reclamo;
import modelos.Twitt;
import servicio.ServiceSistemaDeReclamos;
import validaciones.Validar;
import vistas.DetalleCiudadano;
import vistas.DetalleReclamo;
import vistas.alta.AltaReclamo;
import vistas.listar.ListarReclamos;

public class ReclamoController {
	private ServiceSistemaDeReclamos apiReclamos = new ServiceSistemaDeReclamos();
	private Validar validar = new Validar();

	public ReclamoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reclamo altaReclamo(String descripcion, String fecha,
			Categoria categoria, String calle, String numero, String piso,
			String departamento, String latitud, String longitud,
			Ciudadano ciudadano) throws SQLException {

		int result = -1;

		Boolean[] ok = new Boolean[1];
		Direccion ubicacion = validar.validarDireccion(calle, numero, piso,
				departamento, latitud, longitud, ok);
		Reclamo reclamo = null;
		if (ok[0]) {

			reclamo = validar.validarReclamo(descripcion, fecha, "",
					categoria, ubicacion, ciudadano, ok);
			if (ok[0]) {

				result = apiReclamos.altaReclamo(reclamo);
				//result = apiReclamos.altaReclamo(ciudadano, reclamo);
				if (result != -1) {
					List<Reclamo> reclamos = apiReclamos
							.reclamoByCondicion(ciudadano.getDni());
					DetalleCiudadano.actualizarReclamos(reclamos);
					JOptionPane.showMessageDialog(null,
							"Se ha realizado un nuevo reclamo.");
					AltaReclamo.clearTextFields();
				}
			}
		}
		return reclamo;
	}

	public void verReclamo(String idReclamo) throws SQLException {
		Reclamo reclamo = apiReclamos.getReclamo(idReclamo);
		List<Estado> estados = apiReclamos.getListEstados();
		DetalleReclamo detalleReclamo = new DetalleReclamo(reclamo, estados);
		detalleReclamo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		detalleReclamo.setLocationRelativeTo(null);
		detalleReclamo.setVisible(true);
	}
         
	public void altaEstadoReclamo(String detalle, String fecha, Estado estado,
		 	String idReclamo) throws SQLException {
		
		String result = "";
		result = apiReclamos.altaEstadoReclamo(detalle, fecha, estado,
		 		idReclamo);
		 
		if (result != "") {
		 	List<EstadoReclamo> estados = apiReclamos
		 			.listEstadosByReclamo(idReclamo);
		 	DetalleReclamo.actualizarEstados(estados);
		 	JOptionPane.showMessageDialog(null,
		 			"Se ha registrado un nuevo estado del reclamo.");
		}
		  
	}	 
		 
	public void eliminarReclamo(String idReclamo, String referer)
			throws SQLException {
		Reclamo reclamo = apiReclamos.getReclamo(idReclamo);
		apiReclamos.eliminarReclamo(reclamo);
		List<Reclamo> reclamos;
		if (referer.equals("lista")) {
			reclamos = apiReclamos.listReclamos();
			ListarReclamos.actualizarReclamos(reclamos);
		} else {
			reclamos = apiReclamos.reclamoByCondicion(Integer.parseInt(reclamo.getCiudadano()
					.getId()));
			DetalleCiudadano.actualizarReclamos(reclamos);
		}
		JOptionPane.showMessageDialog(null, "Se ha eliminado el reclamo.");
	}

	public void listarReclamos() throws SQLException {
		List<Reclamo> reclamos = apiReclamos.listReclamos();
		ListarReclamos lista = new ListarReclamos(reclamos);
		lista.setLocationRelativeTo(null);
		lista.setVisible(true);
	}

	/*public void verTwitter(String idTwitter, String cuentaTwitter,
			String hashtag, String contenido) throws SQLException {
		Ciudadano ciudadano = apiReclamos.getCiudadanobyTwitter(cuentaTwitter);
		Twitt twitt = new Twitt(idTwitter, cuentaTwitter, hashtag, contenido);
		DetalleTwitter detalle = new DetalleTwitter(ciudadano, twitt);
		detalle.setLocationRelativeTo(null);
		detalle.setVisible(true);
	}*/

	public int altaReclamoDesdeTwitter(Ciudadano ciudadano,
			String nombreCategoria, String descripcion, String fecha,
			String idTwitter) throws SQLException {
		int result;
		Categoria categoria = apiReclamos.getCategoriaByNombre(nombreCategoria);
		Reclamo reclamo = new Reclamo(descripcion, Timestamp.valueOf(fecha),
				null, null, categoria, ciudadano, idTwitter);
		result = apiReclamos.altaReclamoByTwitter(reclamo);
		if (result != -1) {
			JOptionPane.showMessageDialog(null,
					"Se ha registrado un nuevo estado del reclamo.");
		}else{
			result = -1;
		}
		return result;
	}

}
