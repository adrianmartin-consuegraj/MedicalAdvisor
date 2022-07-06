package util;

public class MisUtilidades {
	

	public static  int randomEntero(int desde, int hasta){
		
		return  (int)(Math.random()*((hasta-desde)+1))+desde;
		
	}

	public static  double  randomDouble(int precision, int escala){
		return  redondear(Math.random()*Math.pow(10, precision-escala),escala);
	}
	public static double redondear(double numero,int  decimal){
		
	    double potencia=Math.pow(10, decimal);
		return  Math.round(numero*potencia)/potencia;
		
	}
	public  static boolean esPrimo(int numero){
		
			boolean primo=(numero==0 || numero==1)? false:true;
			boolean seguir=true;// por motivos didácticos pero sería innecesario
			                    // bastaria con poner en la repetitiva (i< numero && primo)
		
			
			if(numero>=0){
				
			/*	
			int i=2;//todos los numeros son divisibles por 1
			while( i< numero && seguir){
				if (numero%i==0){// divisible por i ya no es primo
					primo=false;
				    seguir=false;
				}    
				i++;
			}
		*/	
			// con un for
			
			for(int i=2;i< numero && seguir; i++){
				if (numero%i==0){// divisible por i, ya no es primo
					primo=false;
				    seguir=false;
				}  
				
			}// fin del for
		
			}
			else
				System.out.println("error: el numero "+ numero+ " no es un numero natural");
				
			
			return primo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	//	System.out.println((int)(Math.random()*10+1)    );
		
/* System.out.println(randomEntero(25,75));
 System.out.println(randomEntero(0,2));
 System.out.println(randomEntero(-1,1));
 System.out.println(redondear(125.5,0));
 System.out.println(randomDouble(4,2));
 */
		 System.out.println(randomEntero(0,2));
		/*int i=1;
		while(i<5){
			System.out.println(randomDouble(6,i));	
		i++;	
		}
		*/
   
   

	}

}
