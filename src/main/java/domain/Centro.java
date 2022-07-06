package domain;

import util.Validator;

import java.util.List;

import exceptions.DomainException;

public class Centro {

	private int idCentro;
	private String nombre;
	private String categoria;
	private String direccion;
	private String telefono;
	private double puntuacion;
	private String descripcion;
	private Acceso acceso;
	private List<Especialidad> especialidades;
	private Servicio servicio;
	
	private List<Comentario> comentario;

	public Centro(){}


	public Centro(int idCentro) {this.idCentro = idCentro;}

	/**
	 * @param idCita
	 * @param nombre
	 * @param categoria
	 * @param direccion
	 * @param telefono
	 * @param puntuacion
	 * @param descripcion
	 * @param acceso
	 * @param especialidades
	 * @param servicio
	 * @param comentario
	 */
	
	public Centro(int idCentro, String nombre, String categoria, String direccion, String telefono, double puntuacion, String descripcion) {

		this.idCentro = idCentro;
		this.nombre = nombre;
		this.categoria = categoria;
		this.direccion = direccion;
		this.telefono = telefono;
		this.puntuacion = puntuacion;
		this.descripcion = descripcion;
		
	}
	
	
	public Centro(int idCentro, String nombre, String categoria, String direccion, String telefono, double puntuacion, String descripcion, Acceso acceso, List<Especialidad> especialidades, Servicio servicio, List<Comentario> comentario) {

		this.idCentro = idCentro;
		this.nombre = nombre;
		this.categoria = categoria;
		this.direccion = direccion;
		this.telefono = telefono;
		this.puntuacion = puntuacion;
		this.descripcion = descripcion;
		this.acceso = acceso;
		this.especialidades = especialidades;
		this.servicio = servicio;
		this.comentario = comentario;
		
	}
	
	
	public Centro(Acceso acceso, List<Especialidad> especialidades, Servicio servicio, List<Comentario> comentario) {

		this.acceso = acceso;
		this.especialidades = especialidades;
		this.servicio = servicio;
		this.comentario = comentario;
		
	}

	public Centro(Centro centro){

		setIdCentro(centro.idCentro);
		setNombre(centro.nombre);
		setCategoria(centro.categoria);
		setDireccion(centro.direccion);
		setTelefono(centro.telefono);
		setPuntuacion(centro.puntuacion);
		setDescripcion(centro.descripcion);
		setAcceso(centro.acceso);
		setEspecialidades(centro.especialidades);
		setServicio(centro.servicio);
		setComentario(centro.comentario);

	}


	/**
	 * @param idCentro
	 */

	public int getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(int idCentro) {
		if (idCentro>=0) {
			this.idCentro = idCentro;
		} else {
			throw new DomainException("La longitud del idCentro de Centro no es válido: 1 a 10 caracteres.");
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
			throw new DomainException("La longitud del Nombre del Centro no es válido.");
		}
	}

	
	/**
	 * @param categoria
	 */

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if (Validator.length(categoria, 1, 20)) {
			this.categoria = categoria.trim();
		} else {
			throw new DomainException("La longitud de la categoria del Centro no es válida.");
		}
	}

	
	/**
	 * @param direccion
	 */

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (Validator.length(direccion, 1, 20)) {
			this.direccion = direccion.trim();
		} else {
			throw new DomainException("La longitud de la dirección del Centro no es válida.");
		}
	}
	
	
	/**
	 * @param telefono
	 */

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (Validator.length(telefono, 1, 20)) {
			this.telefono = telefono.trim();
		} else {
			throw new DomainException("La longitud del teléfono del Centro no es válida.");
		}
	}

	
	/**
	 * @param puntuacion
	 */

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		if (Validator.lengthDecimal(puntuacion, 1, 20)) {
			this.puntuacion = puntuacion;
		} else {
			throw new DomainException("La longitud de la puntuación del Centro no es válida.");
		}
	}

	
	/**
	 * @param descripcion
	 */

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (Validator.length(descripcion, 1, 20)) {
			this.descripcion = descripcion.trim();
		} else {
			throw new DomainException("La longitud de la descripcion del Centro no es válida.");
		}
	}
	
	
	/**
	 * @param acceso
	 */
	
	public Acceso getAcceso() {
		return acceso;
	}

	public void setAcceso(Acceso acceso) {
		if (acceso!=null) {
			this.acceso = acceso;
		} else {
			throw new DomainException("La longitud del Acceso del Centro no es válido.");
		}
	}
	
	
	/**
	 * @param especialidades
	 */
	
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		if (especialidades!=null) {
			this.especialidades = especialidades;
		} else {
			throw new DomainException("La longitud de las Especialidades del Centro no es válido.");
		}
	}
	
	
	/**
	 * @param servicio
	 */
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		if (servicio!=null) {
			this.servicio = servicio;
		} else {
			throw new DomainException("La longitud del Servicio del Centro no es válido.");
		}
	}
	
	
	/**
	 * @param comentario
	 */
	
	public List<Comentario> getComentario() {
		return comentario;
	}

	public void setComentario(List<Comentario> comentario) {
		if (comentario!=null) {
			this.comentario = comentario;
		} else {
			throw new DomainException("La longitud del Comentario del Centro no es válido.");
		}
	}
	
	
}
