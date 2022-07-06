package daos.interfaces;

public interface ErroresBD {
	 static final String DB_ERR = "Error de la base de datos";

	 static final int ORACLE_DUPLICATE_PK = 1;
	 static final int ORACLE_DELETE_FK = 2292;
	 static final int ORACLE_FALLO_FK = 2291;

}
