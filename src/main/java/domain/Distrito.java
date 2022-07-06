package domain;

import util.Validator;
import exceptions.DomainException;

public class Distrito {

	private int numero;
	private String nombre;
	private int incidencia;
	private String confinamiento;
	private String restriccionMovilidad;
	private String mascarilla;

	public Distrito(){}


	public Distrito(int numero) {this.numero = numero;}

	/**
	 * @param numero
	 * @param nombre
	 * @param incidencia
	 * @param confinamiento
	 * @param restriccionMovilidad
	 * @param mascarilla
	 */

	public Distrito(int numero, String nombre) {

		this.numero = numero;
		this.nombre = nombre;

	}
	
	public Distrito(int numero, String nombre, int incidencia, String confinamiento, String restriccionMovilidad, String mascarilla) {

		this.numero = numero;
		this.nombre = nombre;
		this.incidencia = incidencia;
		this.confinamiento = confinamiento;
		this.restriccionMovilidad = restriccionMovilidad;
		this.mascarilla = mascarilla;

	}

	public Distrito(Distrito distrito){

		setNumero(distrito.numero);
		setNombre(distrito.nombre);
		setIncidencia(distrito.incidencia);
		setConfinamiento(distrito.confinamiento);
		setRestriccionMovilidad(distrito.restriccionMovilidad);
		setMascarilla(distrito.mascarilla);
	}


	/**
	 * @param numero
	 */

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		if (numero!=0) {
			this.numero = numero;
		} else {
			throw new DomainException("La longitud del número del Distrito no es válida: 1 a 20 caracteres.");
		}
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
			throw new DomainException("La longitud del nombre de Distrito no es válida: 1 a 20 caracteres.");
		}
	}


	/**
	 * @param incidencia
	 */

	public int getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(int incidencia) {
		if (incidencia!=0) {
			this.incidencia = incidencia;
		} else {
			throw new DomainException("La longitud de la Incidencia del Distrito no es válida: 1 a 20 caracteres.");
		}
	}

	
	/**
	 * @param confinamiento
	 */

	public String getConfinamiento() {
		return confinamiento;
	}
	public void setConfinamiento(String confinamiento) {
		if (Validator.length(confinamiento, 1, 20)) {
			this.confinamiento = confinamiento.trim();
		} else {
			throw new DomainException("La longitud del Confinamiento de Distrito no es válida: 1 a 20 caracteres.");
		}
	}

	
	/**
	 * @param restriccionMovilidad
	 */

	public String getRestriccionMovilidad() {
		return restriccionMovilidad;
	}
	public void setRestriccionMovilidad(String restriccionMovilidad) {
		if (Validator.length(restriccionMovilidad, 1, 20)) {
			this.restriccionMovilidad = restriccionMovilidad.trim();
		} else {
			throw new DomainException("La longitud de la Restricción de Movilidad de Distrito no es válida: 1 a 20 caracteres.");
		}
	}


	/**
	 * @param mascarilla
	 */

	public String getMascarilla() {
		return mascarilla;
	}
	public void setMascarilla(String mascarilla) {
		if (Validator.length(mascarilla, 1, 20)) {
			this.mascarilla = mascarilla.trim();
		} else {
			throw new DomainException("La longitud de la Mascarilla de Distrito no es válida: 1 a 20 caracteres.");
		}
	}


}
