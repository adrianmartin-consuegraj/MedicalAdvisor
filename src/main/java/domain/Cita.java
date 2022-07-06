package domain;

import util.Fecha;
import util.Validator;

import java.sql.Date;

import exceptions.DomainException;

public class Cita {

	private int idCita;
	private String usuario;
	private String centro;
	private String especialidad;
	private Date fecha;

	public Cita(){}


	public Cita(String usuario) {this.usuario = usuario;}

	/**
	 * @param idCita
	 * @param usuario
	 * @param centro
	 * @param especialidad
	 * @param fecha
	 */
	
	public Cita(int idCita, String usuario, String centro, Date fecha) {

		this.idCita = idCita;
		this.usuario = usuario;
		this.centro = centro;
		this.fecha = fecha;
		
	}
	
	public Cita(int idCita, String usuario, String centro, String especialidad, Date fecha) {

		this.idCita = idCita;
		this.usuario = usuario;
		this.centro = centro;
		this.especialidad = especialidad;
		this.fecha = fecha;
		
	}

	public Cita(Cita cita){

		setIdCita(cita.idCita);
		setUsuario(cita.usuario);
		setCentro(cita.centro);
		setFecha(cita.fecha);

	}


	/**
	 * @param nombre
	 */

	//idCita
	public int getIdCita() {
		return idCita;
	}
	public void setIdCita(int idCita) {
		if (idCita>0) {
			this.idCita = idCita;
		} else {
			throw new DomainException("La longitud del idCita no es válida: 1 a 10 caracteres.");
		}
	}

	//Usuario
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		if (Validator.length(usuario, 1, 20)) {
			this.usuario = usuario.trim();
		} else {
			throw new DomainException("La longitud del Usuario no es válida.");
		}
	}


	//Centro
	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		if (Validator.length(centro, 1, 20)) {
			this.centro = centro.trim();
		} else {
			throw new DomainException("La longitud del Centro no es válida.");
		}
	}
	
	//Especialidad
		public String getEspecialidad() {
			return especialidad;
		}

		public void setEspecialidad(String especialidad) {
			if (Validator.length(centro, 1, 20)) {
				this.especialidad = especialidad.trim();
			} else {
				throw new DomainException("La longitud de la Especialidad de la Cita no es válida.");
			}
		}


	//Fecha
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		
		String fechaString = "";
		
		try {
			fechaString = Fecha.convertirAString(fecha, "dd/MM/yyyy");
		}catch (DomainException e) {
			throw new DomainException("El formato de la fecha no es válido.");
		}
		
		if (fechaString != null) {
			this.fecha = fecha;
		} else {
			throw new DomainException("La fecha introducida no es válida.");
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
