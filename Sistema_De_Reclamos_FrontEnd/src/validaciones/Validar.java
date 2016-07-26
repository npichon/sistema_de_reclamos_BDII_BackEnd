package validaciones;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import modelos.Categoria;
import modelos.Ciudadano;
import modelos.Direccion;
import modelos.Estado;
import modelos.Reclamo;
import modelos.Ventaja;

public class Validar {

	public Categoria validarCategoria(String nombre, String descripcion,
			String puntaje, Boolean[] ok) {
		Categoria categoria = new Categoria();
		ok[0] = true;

		if (nombre.length() > 0) {
			if (nombre.length() < 40) {
				if (esPalabraCaracteres(nombre)) {

					categoria.setNombre(nombre);

					if (puntaje.length() > 0) {
						if (puntaje.length() < 6) {
							try {
								categoria.setPuntaje(Integer.parseInt(puntaje));
							} catch (Exception e) {
								ok[0] = false;
								categoria.setPuntaje(-1);
								JOptionPane
										.showMessageDialog(
												null,
												"El campo de Puntaje debe tener sólo números",
												"TIPO DE DATO",
												JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							ok[0] = false;
							JOptionPane
									.showMessageDialog(
											null,
											"El campo de Puntaje debe tener menos de 6 dígitos",
											"CAMPO SOBRECARGADO",
											JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						ok[0] = false;
						categoria.setPuntaje(-1);
						JOptionPane.showMessageDialog(null,
								"El campo de Puntaje esta INCOMPLETO",
								"CAMPO INCOMPLETO",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} else {
					ok[0] = false;
					JOptionPane
							.showMessageDialog(
									null,
									"El campo de Nombre no debe contener numeros ni caracteres especiales.",
									"CAMPO SOBRECARGADO",
									JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				ok[0] = false;
				categoria.setNombre("");
				JOptionPane.showMessageDialog(null,
						"El campo de Nombre debe tener menos de 40 caracteres",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			ok[0] = false;
			JOptionPane.showMessageDialog(null,
					"El campo de Nombre está INCOMPLETO", "CAMPO INCOMPLETO",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (descripcion.length() > 0) {
			if (descripcion.length() < 500) {
				categoria.setDescripcion(descripcion);
			} else {
				ok[0] = false;
				categoria.setDescripcion("");
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de Descripcion debe tener menos de 500 caracteres",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return categoria;
	}

	public Ventaja validarVentaja(String nombre, String descripcion,
			String puntosNecesarios, Boolean[] ok) {
		Ventaja ventaja = new Ventaja();
		ok[0] = true;

		if (nombre.length() > 0) {
			if (nombre.length() < 40) {
				if (esPalabraCaracteres(nombre)) {

					ventaja.setNombre(nombre);

					if (puntosNecesarios.length() > 0) {
						if (puntosNecesarios.length() < 6) {
							try {
								ventaja.setPuntosNecesarios(Integer
										.parseInt(puntosNecesarios));
							} catch (Exception e) {
								ok[0] = false;
								ventaja.setPuntosNecesarios(-1);
								JOptionPane
										.showMessageDialog(
												null,
												"El campo de Puntaje debe tener sólo números",
												"TIPO DE DATO",
												JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							ok[0] = false;
							JOptionPane
									.showMessageDialog(
											null,
											"El campo de Puntaje debe tener menos de 6 dígitos",
											"CAMPO SOBRECARGADO",
											JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						ok[0] = false;
						ventaja.setPuntosNecesarios(-1);
						JOptionPane.showMessageDialog(null,
								"El campo de Puntaje esta INCOMPLETO",
								"CAMPO INCOMPLETO",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} else {
					ok[0] = false;
					JOptionPane
							.showMessageDialog(
									null,
									"El campo de Nombre no debe contener numeros ni caracteres especiales.",
									"CAMPO SOBRECARGADO",
									JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				ok[0] = false;
				ventaja.setNombre("");
				JOptionPane.showMessageDialog(null,
						"El campo de Nombre debe tener menos de 40 caracteres",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			ok[0] = false;
			JOptionPane.showMessageDialog(null,
					"El campo de Nombre está INCOMPLETO", "CAMPO INCOMPLETO",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (descripcion.length() > 0) {
			if (descripcion.length() < 500) {
				ventaja.setDescripcion(descripcion);
			} else {
				ok[0] = false;
				ventaja.setDescripcion("");
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de Descripcion debe tener menos de 500 caracteres",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return ventaja;
	}

	public Estado validarEstado(String nombre, String descripcion, Boolean[] ok) {
		Estado estado = new Estado();
		ok[0] = true;

		if (nombre.length() > 0) {
			if (nombre.length() < 40) {
				if (esPalabraCaracteres(nombre)) {

					estado.setNombre(nombre);

				} else {
					ok[0] = false;
					JOptionPane
							.showMessageDialog(
									null,
									"El campo de Nombre no debe contener numeros ni caracteres especiales.",
									"CAMPO SOBRECARGADO",
									JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				ok[0] = false;
				estado.setNombre("");
				JOptionPane.showMessageDialog(null,
						"El campo de Nombre debe tener menos de 40 caracteres",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			ok[0] = false;
			JOptionPane.showMessageDialog(null,
					"El campo de Nombre está INCOMPLETO", "CAMPO INCOMPLETO",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (descripcion.length() > 0) {
			if (descripcion.length() < 500) {
				estado.setDescripcion(descripcion);
			} else {
				ok[0] = false;
				estado.setDescripcion("");
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de Descripcion debe tener menos de 500 caracteres",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return estado;

	}

	public Reclamo validarReclamo(String descripcion, String fecha,
			String idTwitter, Categoria categoria, Direccion ubicacion,
			Ciudadano ciudadano, Boolean[] ok) {
		Reclamo reclamo = new Reclamo();
		ok[0] = true;

		if (descripcion.length() > 0) {
			if (descripcion.length() < 500) {
				reclamo.setDescripcion(descripcion);
			} else {
				ok[0] = false;
				reclamo.setDescripcion("");
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de Descripcion debe tener menos de 500 caracteres",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			ok[0] = false;
			JOptionPane.showMessageDialog(null,
					"El campo de Descripción está INCOMPLETO",
					"CAMPO INCOMPLETO", JOptionPane.INFORMATION_MESSAGE);
		}

		if (fecha.length() > 0) {
			reclamo.setFecha(Timestamp.valueOf(fecha));
		} else {
			ok[0] = false;
			reclamo.setFecha(null);
			JOptionPane.showMessageDialog(null,
					"El campo de FECHA esta incompleto", "CAMPO INCOMPLETO",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (idTwitter.length() > 0) {
			if (idTwitter.length() < 50) {
				reclamo.setIdTwitter(idTwitter);
			} else {
				ok[0] = false;
				reclamo.setIdTwitter("");
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de ID DE TWITTER debe contener menos de 50 caracteres.",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (categoria != null) {
			reclamo.setCategoria(categoria);
		} else {
			ok[0] = false;
			reclamo.setCategoria(null);
			JOptionPane.showMessageDialog(null,
					"El campo de CATEGORIA esta incompleto",
					"CAMPO INCOMPLETO", JOptionPane.INFORMATION_MESSAGE);
		}

		if (ubicacion != null) {
			reclamo.setUbicacion(ubicacion);
		} else {
			ok[0] = false;
			reclamo.setUbicacion(null);
			JOptionPane.showMessageDialog(null,
					"EL reclamo debe tener una direccion", "CAMPO INCOMPLETO",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (ciudadano != null) {
			reclamo.setCiudadano(ciudadano);
		} else {
			ok[0] = false;
			reclamo.setCiudadano(null);
			JOptionPane.showMessageDialog(null,
					"EL reclamo debe tener un ciudadano asociado",
					"CAMPO INCOMPLETO", JOptionPane.INFORMATION_MESSAGE);
		}

		return reclamo;
	}

	public Ciudadano validarCiudadano(String nombre, String apellido,
			String dni, String email, String cuentaTwitter, String telefono,
			Boolean[] ok) {

		Ciudadano ciudadano = new Ciudadano();
		ok[0] = true;

		if (nombre.length() == 0 || nombre.length() > 60) {
			ok[0] = false;
			ciudadano.setNombre("");
			if (nombre.length() == 0) {
				JOptionPane.showMessageDialog(null,
						"El campo de Nombre está INCOMPLETO",
						"CAMPO INCOMPLETO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				ok[0] = false;
				JOptionPane.showMessageDialog(null,
						"El campo de Nombre debe tener menos de 40 caracteres",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			if (!esPalabraCaracteres(nombre)) {
				ok[0] = false;
				JOptionPane
						.showMessageDialog(
								null,
								"El campo de Nombre no debe tener número ni caracteres especiales.",
								"TIPO DE DATO ",
								JOptionPane.INFORMATION_MESSAGE);
				ciudadano.setNombre("");
			} else {
				ciudadano.setNombre(nombre);
				if (apellido.length() == 0 || apellido.length() > 60) {
					ok[0] = false;
					ciudadano.setApellido("");
					if (apellido.length() == 0) {
						JOptionPane.showMessageDialog(null,
								"El campo de Apellido está INCOMPLETO",
								"CAMPO INCOMPLETO",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						ok[0] = false;
						JOptionPane
								.showMessageDialog(
										null,
										"El campo de Apellido debe tener menos de 40 caracteres",
										"CAMPO SOBRECARGADO",
										JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if (!esPalabraCaracteres(apellido)) {
						ok[0] = false;
						JOptionPane
								.showMessageDialog(
										null,
										"El campo de Apellido no debe tener número ni caracteres especiales.",
										"TIPO DE DATO ",
										JOptionPane.INFORMATION_MESSAGE);
						ciudadano.setApellido("");
					} else {
						ciudadano.setApellido(apellido);
						if (dni.length() == 0 || dni.length() > 8) {
							ok[0] = false;
							ciudadano.setDni(-1);
							if (dni.length() == 0) {
								ok[0] = false;
								JOptionPane.showMessageDialog(null,
										"El campo DNI esta INCOMPLETO",
										"CAMPO INCOMPLETO",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								ok[0] = false;
								JOptionPane
										.showMessageDialog(
												null,
												"El campo DNI debe tener entre 7 y 8 dígitos",
												"CAMPO SOBRECARGADO",
												JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							if (!isNumeric(dni)) {
								ok[0] = false;
								JOptionPane.showMessageDialog(null,
										"El campo DNI debe tener sólo números",
										"TIPO DE DATO",
										JOptionPane.INFORMATION_MESSAGE);
								ciudadano.setDni(-1);

							} else {
								ciudadano.setDni(Integer.parseInt(dni));
								if (telefono.length() == 0
										|| telefono.length() > 30) {
									ok[0] = false;
									ciudadano.setTelefono("");
									if (telefono.length() == 0) {
										ok[0] = false;
										JOptionPane
												.showMessageDialog(
														null,
														"El campo Teléfono esta INCOMPLETO",
														"CAMPO INCOMPLETO",
														JOptionPane.INFORMATION_MESSAGE);
									} else {
										ok[0] = false;
										JOptionPane
												.showMessageDialog(
														null,
														"El campo Teléfono debe tener menos de 30 dígitos",
														"CAMPO SOBRECARGADO",
														JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									ciudadano.setTelefono(telefono);
									if (cuentaTwitter.length() > 0) {
										if (cuentaTwitter.length() > 50) {
											ok[0] = false;
											ciudadano.setCuentaTwitter("");
											JOptionPane
													.showMessageDialog(
															null,
															"El campo Cuenta de Twitter debe tener menos de 50 caracteres.",
															"CAMPO SOBRECARGADO",
															JOptionPane.INFORMATION_MESSAGE);
										} else {
											ciudadano
													.setCuentaTwitter(cuentaTwitter);
										}
									} else {
										ok[0] = false;
										ciudadano.setCuentaTwitter("");
										JOptionPane
												.showMessageDialog(
														null,
														"El campo Cuenta de Twitter no está completo.",
														"CAMPO OBLIGATORIO",
														JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						}
					}
				}
			}
		}

		if (email.length() > 0 || email.length() > 60) {
			if (email.length() > 60) {
				ok[0] = false;
				ciudadano.setEmail("");
				JOptionPane.showMessageDialog(null,
						"El campo Email debe tener menos de 60 caracteres.",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			} else if (email.length() > 0 || email.length() < 60) {
				// Verifica que este bien formado el mail
				Pattern pat = null;
				Matcher mat = null;
				pat = Pattern
						.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
				mat = pat.matcher(email);
				if (mat.find()) {
					ciudadano.setEmail(email);
				} else {
					ok[0] = false;
					ciudadano.setEmail("");
					JOptionPane
							.showMessageDialog(
									null,
									"El campo Email debe tener el siguiente formato: tomail@mail.com",
									"VERIFICAR CAMPO",
									JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else {
			ciudadano.setEmail("");
		}

		return ciudadano;
	}

	public Direccion validarDireccion(String calle, String numero, String piso,
			String departamento, String latitud, String longitud, Boolean[] ok) {

		Direccion direccion = new Direccion();
		ok[0] = true;

		if (calle.length() == 0 || calle.length() > 60) {
			ok[0] = false;
			direccion.setCalle("");
			if (calle.length() == 0) {
				JOptionPane.showMessageDialog(null,
						"El campo Calle esta INCOMPLETO", "CAMPO INCOMPLETO",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"El campo Calle debe tener entre menos de 60 caracteres",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			direccion.setCalle(calle);
			if (numero.length() == 0 || numero.length() > 6) {
				ok[0] = false;
				direccion.setNumero(-1);
				if (numero.length() == 0) {
					JOptionPane
							.showMessageDialog(null,
									"El campo Número esta INCOMPLETO",
									"CAMPO INCOMPLETO",
									JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"El campo Número debe tener menos de 6 dígitos",
							"CAMPO SOBRECARGADO",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				if (!isNumeric(numero)) {
					ok[0] = false;
					JOptionPane.showMessageDialog(null,
							"El campo Número debe tener sólo números",
							"TIPO DE DATO", JOptionPane.INFORMATION_MESSAGE);
					direccion.setNumero(-1);

				} else {
					direccion.setNumero(Integer.parseInt(numero));
				}
			}
		}

		if (piso.length() > 0) {
			if (piso.length() < 50) {
				ok[0] = false;
				direccion.setDepartamento("");
				JOptionPane.showMessageDialog(null,
						"El campo Piso debe tener menos de 50 caracteres.",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			} else {
				direccion.setPiso(piso);
			}
		}

		if (departamento.length() > 0) {
			if (departamento.length() < 50) {
				direccion.setDepartamento(departamento);
			} else {
				ok[0] = false;
				direccion.setDepartamento(null);
				JOptionPane
						.showMessageDialog(
								null,
								"El campo Departamento debe tener menos de 50 caracteres.",
								"CAMPO SOBRECARGADO",
								JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (latitud.length() > 0) {
			if (latitud.length() < 50) {
				direccion.setLatitud(latitud);
			} else {
				ok[0] = false;
				direccion.setDepartamento("");
				JOptionPane.showMessageDialog(null,
						"El campo Latitud debe tener menos de 50 caracteres.",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);

			}
		}

		if (longitud.length() > 0) {
			if (longitud.length() < 50) {
				direccion.setLongitud(longitud);
			} else {
				ok[0] = false;
				direccion.setDepartamento("");
				JOptionPane.showMessageDialog(null,
						"El campo Longitud debe tener menos de 50 caracteres.",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		return direccion;
	}

	public int validarDni(String dni) {
		int result = 1;

		if (dni.length() > 0) {
			if (dni.length() < 9) {
				if (!isNumeric(dni)) {
					result = -1;
					JOptionPane.showMessageDialog(null,
							"El campo DNI debe tener sólo números",
							"TIPO DE DATO", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				result = -1;
				JOptionPane.showMessageDialog(null,
						"El campo DNI debe tener menos de 8 dígitos",
						"CAMPO SOBRECARGADO", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			result = -1;
			JOptionPane.showMessageDialog(null, "Ingrese un DNI valido.",
					"CAMPO VACÍO", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}

	private boolean esPalabraCaracteres(String palabra) {
		boolean respuesta = false;
		if ((palabra)
				.matches("[a-zA-Z\\u00D1\\u00F1\\u00E1\\u00E9\\u00ED\\u00F3\\u00FA \\u00C1\\u00C9\\u00CD\\u00D3\\u00DA ]+")) {
			respuesta = true;
		}
		return respuesta;
	}

	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
