package databaseTest;

public class mainDatabase {

	public static void main(String[] args) {
		databaseTesting test = new databaseTesting("My database");
		
		test.crearMoneda("Bitcoin");
		test.cargarMoneda("Bitcoin", "ETH", 5.5f, 3.0f);
		String monedaCargada = test.getData("Bitcoin");
		
		System.out.println(monedaCargada);
		
		test.closeConnection();
	}

}
