package domain;

import util.Validator;


import exceptions.DomainException;

public class Acceso {

	private String renfe;
	private String metro;
	private String coche;

	public Acceso(){}


	public Acceso(String renfe) {this.renfe = renfe;}

	/**
	 * @param renfe
	 * @param metro
	 * @param coche
	 */
	
	public Acceso(String renfe, String metro, String coche) {

		this.renfe = renfe;
		this.metro = metro;
		this.coche = coche;
		
	}
	

	public Acceso(Acceso acceso){

		setRenfe(acceso.renfe);
		setMetro(acceso.metro);
		setCoche(acceso.coche);

	}
	
	/**
	 * @param renfe
	 */

	public String getRenfe() {
		return renfe;
	}

	public void setRenfe(String renfe) {
		if (Validator.length(renfe, 1, 20)) {
			this.renfe = renfe.trim();
		} else {
			throw new DomainException("La longitud de Renfe del Acceso no es válido.");
		}
	}

	/**
	 * @param metro
	 */
	
	public String getMetro() {
		return metro;
	}

	public void setMetro(String metro) {
		if (Validator.length(metro, 1, 20)) {
			this.metro = metro.trim();
		} else {
			throw new DomainException("La longitud de Metro del Acceso no es válido.");
		}
	}

	/**
	 * @param coche
	 */
	
	public String getCoche() {
		return coche;
	}

	public void setCoche(String coche) {
		if (Validator.length(coche, 1, 20)) {
			this.coche = coche.trim();
		} else {
			throw new DomainException("La longitud del Coche del Acceso no es válido.");
		}
	}

	

}
