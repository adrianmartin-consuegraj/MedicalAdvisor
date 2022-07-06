package domain;

import util.Fecha;
import util.Validator;

import java.sql.Date;

import exceptions.DomainException;

public class Vacuna {

	//private String usuario;
	private String nombre;
	private Date fecha;
	private String centro;

	public Vacuna(){}


	public Vacuna(String nombre) {this.nombre = nombre;}

	/**
	 * @param idCita
	 * @param usuario
	 * @param centro
	 * @param fecha
	 */
	
	public Vacuna(String nombre, Date fecha, String centro) {

		this.nombre = nombre;
		this.fecha = fecha;
		this.centro = centro;
		
	}

	public Vacuna(Vacuna vacuna){

		setNombre(vacuna.nombre);
		setFecha(vacuna.fecha);
		setCentro(vacuna.centro);

	}


	/**
	 * @param nombre
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (Validator.length(nombre, 1, 20)) {
			this.nombre = nombre.trim();
		} else {
			throw new DomainException("La longitud del Nombre de la Vacuna no es válida.");
		}
	}

	
	/**
	 * @param fecha
	 */
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		if (fecha!=null) {
			this.fecha = fecha;
		} else {
			throw new DomainException("La longitud de la fecha de la Vacuna no es válida.");
		}
	}
	
	
	/**
	 * @param centro
	 */
	
	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		if (Validator.length(centro, 1, 20)) {
			this.centro = centro.trim();
		} else {
			throw new DomainException("La longitud del Centro de la Vacuna no es válida.");
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
