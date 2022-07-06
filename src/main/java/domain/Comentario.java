package domain;

import util.Validator;

import java.sql.Date;

import exceptions.DomainException;

public class Comentario {

	private int idCentro;
	private String usuario;
	private Date fecha;
	private String mensaje;

	public Comentario(){}


	public Comentario(int idCentro) {this.idCentro = idCentro;}

	/**
	 * @param idCentro
	 * @param usario
	 * @param fecha
	 * @param mensaje
	 */
	
	public Comentario(int idCentro, String usuario, Date fecha, String mensaje) {

		this.idCentro = idCentro;
		this.usuario = usuario;
		this.fecha = fecha;
		this.mensaje = mensaje;
		
	}
	
	
	public Comentario(String usuario, Date fecha, String mensaje) {

		this.usuario = usuario;
		this.fecha = fecha;
		this.mensaje = mensaje;
		
	}

	public Comentario(Comentario comentario){

		setIdCentro(comentario.idCentro);
		setUsuario(comentario.usuario);
		setFecha(comentario.fecha);
		setMensaje(comentario.mensaje);

	}


	/**
	 * @param idCentro
	 */

	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		if (idCentro>0) {
			this.idCentro = idCentro;
		} else {
			throw new DomainException("La longitud del idCentro del Acceso no es válido: 1 a 10 caracteres.");
		}
	}

	
	/**
	 * @param usario
	 */

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		if (Validator.length(usuario, 1, 20)) {
			this.usuario = usuario.trim();
		} else {
			throw new DomainException("La longitud de Usuario de Comentario no es válido.");
		}
	}

	/**
	 * @param fecha
	 */
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
//		if (Validator.length(fecha, 1, 20)) {
			this.fecha = fecha;
//		} else {
//			throw new DomainException("La longitud de Fecha de Comentario no es válido.");
//		}
	}

	/**
	 * @param mensaje
	 */
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		if (Validator.length(mensaje, 1, 20)) {
			this.mensaje = mensaje.trim();
		} else {
			throw new DomainException("La longitud del Mensaje de Comentario no es válido.");
		}
	}

	
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//	@Override
	//	public String toString() {
	//		return "Cliente [codcli=" + codcli + ", razonsocial=" + razonsocial
	//				+ ", telf=" + telf + ", direccion=" + direccion + ", oferta="
	//				+ oferta + ", albfact=" + albfact + ", iva=" + iva
	//				+ ", tarifa=" + tarifa + ", formapago=" + formapago + "]";
	//	}
	//	public String toStringFashion() {
	//		return "Cliente [codcli=" + codcli + ", razonsocial=" + razonsocial
	//				+ ", telf=" + telf + ", direccion=" + direccion + ", oferta="
	//				+ oferta + ", albfact=" + albfact + ", iva=" + iva.gettipoIva()
	//				+ ", tarifa=" + tarifa.getDescripcion() + ", formapago=(" + formapago.getNumeroVtos()+" vencinientos a  "+formapago.getDias()+" Días)" + "]";
	//	}
	//	

}
