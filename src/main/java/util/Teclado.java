package util;
import java.io.*;


import exceptions.DomainException;
import exceptions.ServiceException;
public class Teclado{
private static BufferedReader stdin=
                      new BufferedReader(
                      new InputStreamReader(System.in));
 
public double leerNumero() throws ServiceException{
System.out.print("introduzca un numero decimal  ");
String Input;
double numero;
try {
	Input = stdin.readLine();
} catch (IOException e) {
	throw new ServiceException(e.getMessage(),e);
	
}

	numero=Double.parseDouble(Input);

return numero;

	



}// fin metodo
public int leerEntero() throws ServiceException{
System.out.print("introduzca un numero entero  ");
int entero;
String Input;
try {
	Input = stdin.readLine();
} catch (IOException e) {
	throw new ServiceException(e.getMessage(),e);
}

	entero=Integer.parseInt(Input);

return entero;

}


public String  leerCadena() throws ServiceException{
System.out.print("introduzca una cadena ");
String Input;
try {
	Input = stdin.readLine();
} catch (IOException e) {
	throw new ServiceException(e.getMessage(),e);
}

return Input;
}

}