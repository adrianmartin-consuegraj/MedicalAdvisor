package recursos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import exceptions.DAOException;

public class Recursos {

	public static void closeResultSet(ResultSet rs) throws DAOException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}

	public static void closePreparedStatement(PreparedStatement st) throws DAOException {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}
	public static void closeCallableStatement(CallableStatement st) throws DAOException {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			throw new DAOException("Error de la base de datos", e);
		}
	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String randomString(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	static final String AB1 = "0123456789";

	public static int randomEntero(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB1.charAt(rnd.nextInt(AB1.length())));
		return Integer.valueOf(sb.toString());
	}
	static final String AB2 = "0123456789";

	public static String randomStringNumero(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB2.charAt(rnd.nextInt(AB2.length())));
		return sb.toString();
	}
	
	public static double  redondearAngel(double numero,int decimal) {
		
	//int	 entero =(int) ((numero + 5*Math.pow(10, -1*(decimal+1)))*(Math.pow(10,decimal)));
		int	 entero;
		
		if (decimal >=0){		
			if (numero>=0)
				entero =(int) ((numero + 5/Math.pow(10, (decimal+1)))*(Math.pow(10,decimal)));
			else
				entero =(int) ((numero - 5/Math.pow(10, (decimal+1)))*(Math.pow(10,decimal)));


			return entero/(Math.pow(10,decimal));
		} else
			return -1;// pendiente de programar se podría levantar una eception
		
		
			
		
	}
	
	public static double  redondearJava(double numero,int decimal) {
		
		//int	 entero =(int) ((numero + 5*Math.pow(10, -1*(decimal+1)))*(Math.pow(10,decimal)));
			int	 entero;
			
			if (decimal >=0){		
				entero =(int) Math.round(numero * Math.pow(10, decimal));
				return entero/(Math.pow(10,decimal));
			} else
				return -1;// pendiente de programar se podría levantar una eception
			
			
				
			
		}

	// en programa principal hay que tratar NumbreFormatException
	public static Double numeroRealNulo(String numero){
		Double num=null;
		if(numero!=null){
			if (numero.trim().length()!=0){
				num= new Double (Double.parseDouble(numero.trim()));
			}	
		}
		return num;
	}
	// en programa principal hay que tratar NumbreFormatException
		public static Integer numeroEnteroNulo(String numero){
			Integer num=null;
			if(numero!=null){
				if (numero.trim().length()!=0){
					num= new Integer (Integer.parseInt(numero.trim()));
				}	
			}
			return num;
		}
	
	public static void main(String[] args) {
		
    // mio mejor que el de java
	// mio 5 para arriba 	java para abajo.
	System.out.println(redondearJava(2.6870,3));	
	System.out.println(redondearJava(-2.6874,3));
	System.out.println(redondearJava(-2.6875,3));
	System.out.println(redondearJava(2.6875,3));
	System.out.println("hhhhhhhhhhhhhhhh");
	System.out.println(Math.round(45.5));
	System.out.println(Math.round(-45.5));
	System.out.println("hhhhhhhhhhhhhhhh");
	System.out.println(redondearAngel(2.6870,3));	
	System.out.println(redondearAngel(-2.6874,3));
	System.out.println(redondearAngel(-2.6875,3));
	System.out.println(redondearAngel(2.6876,3));
	System.out.println(numeroRealNulo(""));
	System.out.println(numeroRealNulo(" "));
	System.out.println(numeroRealNulo(null));
	System.out.println(numeroEnteroNulo(""));
	System.out.println(numeroEnteroNulo(" "));
	System.out.println(numeroEnteroNulo(null));
	System.out.println(numeroEnteroNulo("  f "));
	
	
	}
}
