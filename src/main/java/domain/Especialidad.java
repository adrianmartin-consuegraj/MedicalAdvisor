package domain;

import exceptions.DomainException;

public class Especialidad {

	private String especialidad;

	public Especialidad(){}


	public Especialidad(String especialidad) {this.especialidad = especialidad;}

	/**
	 * @param especialidad
	 */
	

	public Especialidad(Especialidad esp){

		setEspecialidad(esp.especialidad);

	}


	
	/**
	 * @param especialidad
	 */

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		if (especialidad!=null) {
			this.especialidad = especialidad;
		} else {
			throw new DomainException("La longitud de la Especialidad de la Especialidad del Centro no es válida.");
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
