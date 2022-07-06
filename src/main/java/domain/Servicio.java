package domain;

import util.Validator;


import exceptions.DomainException;

public class Servicio {

	private String aparcamiento;
	private String television;
	private String restaurantes;

	public Servicio(){}


	public Servicio(String aparcamiento) {this.aparcamiento = aparcamiento;}

	/**
	 * @param aparcamiento
	 * @param television
	 * @param restaurantes
	 */
	
	public Servicio(String aparcamiento, String television, String restaurantes) {

		this.aparcamiento = aparcamiento;
		this.television = television;
		this.restaurantes = restaurantes;
		
	}

	public Servicio(Servicio servicios){

		setAparcamiento(servicios.aparcamiento);
		setTelevision(servicios.television);
		setRestaurantes(servicios.restaurantes);

	}

	
	/**
	 * @param aparcamiento
	 */

	public String getAparcamiento() {
		return aparcamiento;
	}

	public void setAparcamiento(String aparcamiento) {
		if (Validator.length(aparcamiento, 1, 20)) {
			this.aparcamiento = aparcamiento.trim();
		} else {
			throw new DomainException("La longitud del Aparcamiento de los Servicios no es válido.");
		}
	}

	/**
	 * @param television
	 */
	
	public String getTelevision() {
		return television;
	}

	public void setTelevision(String television) {
		if (Validator.length(television, 1, 20)) {
			this.television = television.trim();
		} else {
			throw new DomainException("La longitud de la Televisión de los Servicios no es válido.");
		}
	}

	/**
	 * @param restaurantes
	 */
	
	public String getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(String restaurantes) {
		if (Validator.length(restaurantes, 1, 20)) {
			this.restaurantes = restaurantes.trim();
		} else {
			throw new DomainException("La longitud de los Restaurantes de los Servicios no es válido.");
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
