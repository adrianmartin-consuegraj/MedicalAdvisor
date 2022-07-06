package domain;

import util.Validator;

import java.util.List;

import exceptions.DomainException;

public class Usuario {

	private String nombre;
	private String password;
	private String tipo;
	private Distrito ubicacion;
	private Favorito centrosFav;
	private List<Cita> citas;

	public Usuario(){}


	public Usuario(String nombre) {this.nombre = nombre;}

	/**
	 * @param nombre
	 * @param password
	 * @param tipo
	 * @param ubicacion
	 * @param favoritos
	 * @param citas
	 */

	public Usuario(String nombre, String password, String tipo, Distrito ubicacion) {

		this.nombre = nombre;
		this.password = password;
		this.tipo = tipo;
		this.ubicacion = ubicacion;

	}

	public Usuario(String nombre, String password, String tipo, Distrito ubicacion, Favorito centrosFav) {

		this.nombre = nombre;
		this.password = password;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.centrosFav = centrosFav;

	}

	public Usuario(String nombre, String password, String tipo, Distrito ubicacion, Favorito centrosFav, List<Cita> citas) {

		this.nombre = nombre;
		this.password = password;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
		this.centrosFav = centrosFav;
		this.citas = citas;

	}

	public Usuario(Usuario usuario){

		setNombre(usuario.nombre);
		setPassword(usuario.password);
		setTipo(usuario.tipo);
		setUbicacion(usuario.ubicacion);
		setFavoritos(usuario.centrosFav);

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
			throw new DomainException("La longitud del nombre no es válida: 1 a 20 caracteres.");
		}
	}


	/**
	 * @param password
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (Validator.length(password, 1, 20)) {
			this.password = password.trim();
		} else {
			throw new DomainException("La longitud de la Password no es válida.");
		}
	}


	/**
	 * @param tipo
	 */

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if (Validator.length(tipo, 1, 20)) {
			this.tipo = tipo.trim();
		} else {
			throw new DomainException("La longitud del Tipo no es válida.");
		}
	}


	/**
	 * @param ubicacion
	 */

	public Distrito getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Distrito ubicacion) {
		if (ubicacion.getNombre()!=null || ubicacion.getNumero()!=0) {
			this.ubicacion = ubicacion;
		} else {
			throw new DomainException("La longitud de la ubicación del Usuario no es válida.");
		}
	}


	/**
	 * @param favoritos
	 */

	public Favorito getFavoritos() {
		return centrosFav;
	}

	public void setFavoritos(Favorito centrosFav) {
		this.centrosFav = centrosFav;

	}


	/**
	 * @param recetas
	 */

	public List<Cita> getCitas() {
		return citas;
	}
	public void setCitas(List<Cita> citas) {
		//		if (recetas.size()>0) {
		this.citas = citas;
		//		} else {
		//			throw new DomainException("La longitud de las Citas del Usuario no es válida: 1 a 20 caracteres.");
		//		}
	}

}
