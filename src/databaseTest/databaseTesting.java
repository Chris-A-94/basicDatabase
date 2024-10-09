package databaseTest;
import java.sql.*;

public class databaseTesting {
	
	private String databaseURL;
	private Connection connected;
	
	public databaseTesting(String database)
	{
		// settea el driver
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//se conecta o crea la database si no existe
		conectarDatabase(database);
	}
	
	private void conectarDatabase(String database)
	{
		this.databaseURL = "jdbc:sqlite:"+database+".db";
		try {
			this.connected = DriverManager.getConnection(this.databaseURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearMoneda(String coin)
	{
		//crea la tabla pasada por String nombre
		String tablaCreada = "CREATE TABLE IF NOT EXISTS "+coin+" (" +
				"id TEXT PRIMARY KEY, " +
                "stock REAL NOT NULL, " +
                "precio REAL NOT NULL)";		
		try {
			Statement word = this.connected.createStatement();
			word.execute(tablaCreada);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarMoneda(String coin, String id, float precio, float stock)
	{
		String aCargar = "INSERT INTO "+coin+" (id, stock, precio) VALUES (?, ?, ?)";
		try {
			PreparedStatement cargarWord = this.connected.prepareStatement(aCargar);
			cargarWord.setString(1, id);
			cargarWord.setFloat(2, stock);
			cargarWord.setFloat(3, precio);
			cargarWord.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public String getData(String coin)
	{
		String retorno = coin+":";
		try {
			Statement palabra = this.connected.createStatement();
			ResultSet obtenerData = palabra.executeQuery("SELECT * FROM "+coin);
			while(obtenerData.next())
			{
				String id = obtenerData.getString("id");
				float precio = obtenerData.getFloat("precio");
				float stock = obtenerData.getFloat("stock");
				retorno = retorno+"\n ID: "+id+" Precio: "+precio+" Stock: "+stock;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void closeConnection()
	{
		if(this.connected != null)
		{
			try {
				this.connected.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
