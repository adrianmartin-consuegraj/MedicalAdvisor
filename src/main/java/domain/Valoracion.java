package domain;

import util.Validator;

import java.sql.Date;

import exceptions.DomainException;

public class Valoracion {

	private int idValoracion;
	private String usuario;
	private int idCentro;
	private int puntuacion;

	public Valoracion(){}


	public Valoracion(int idCentro) {this.idCentro = idCentro;}

	/**
	 * @param idValoracion
	 * @param usario
	 * @param idCentro
	 * @param puntuacion
	 */
	
	public Valoracion(String usuario, int idCentro, int puntuacion) {

		this.usuario = usuario;
		this.idCentro = idCentro;
		this.puntuacion = puntuacion;
		
	}


	/**
	 * @param idCentro
	 */

	
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
			throw new DomainException("La longitud del usuario de la Valoración no es válido.");
		}
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
			throw new DomainException("La longitud del idCentro de la Valoración no es válido: 1 a 10 caracteres.");
		}
	}

	/**
	 * @param puntuacion
	 */
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		if (puntuacion > 0) {
			this.puntuacion = puntuacion;
		} else {
			throw new DomainException("La longitud de la puntuación de la Valoración no es válido.");
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
