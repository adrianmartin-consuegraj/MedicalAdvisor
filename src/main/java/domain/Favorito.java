package domain;

import java.util.List;
import exceptions.DomainException;

public class Favorito {

	private List<Centro> centro;

	public Favorito(){}


	//public Favorito(Centro centro) {this.centro = centro;}

	/**
	 * @param usuario
	 * @param centro
	 */
	
//	public Favorito(Centro centro) {
//
//		this.centro = (List<Centro>) centro;
//		
//	}

	public Favorito(Favorito favorito){

		setCentro(favorito.centro);

	}



	
	/**
	 * @param centro
	 */

	public List<Centro> getCentro() {
		return centro;
	}

	public void setCentro(List<Centro> centro) {
//		if (centro.size()>0) {
			this.centro = centro;
//		} else {
//			throw new DomainException("La longitud del Centro de Favoritos no es válida.");
//		}
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
