package conectads;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//Início da classe de conexão//

public class Mysql  implements Runnable {
	Statement stmt = null;
	public static Properties prop;
	public static String status = "Não conectou...";

//Método Construtor da Classe//

	Mysql() {

		prop = new Properties();

		FileInputStream fi;
		try {
			fi = new FileInputStream("application.prop");
			prop.load(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ResultSet select(String sql) throws SQLException {

		ResultSet rs = stmt.executeQuery(sql);
		// STEP 5: Extract data from result set
		/*
		 * while(rs.next()){ //Retrieve by column name int id = rs.getInt("id"); int age
		 * = rs.getInt("age"); String first = rs.getString("first"); String last =
		 * rs.getString("last");
		 * 
		 * //Display values System.out.print("ID: " + id); System.out.print(", Age: " +
		 * age); System.out.print(", First: " + first); System.out.println(", Last: " +
		 * last); } rs.close();
		 */

		return rs;

	}

//Método de Conexão//

	public static java.sql.Connection conectar() {

		Connection connection = null; // atributo do tipo Connection

		try {

			// Carregando o JDBC Driver padrão

			String driverName = "com.mysql.cj.jdbc.Driver";

			Class.forName(driverName);
 
			String serverName = prop.getProperty("servidorMysql"); // caminho do servidor do BD
			String mydatabase = "db_adpass"; // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = prop.getProperty("usuarioMysql"); // nome de um usuário de seu BD
			String password = prop.getProperty("senhaMysql"); // sua senha de acesso
			connection = DriverManager.getConnection(url, username, password);

			// Testa sua conexão//

			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->Não foi possivel realizar conexão");
			}

			return connection;

		} catch (ClassNotFoundException e) { // Driver não encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

//Não conseguindo se conectar ao banco

			System.out.println("Nao foi possivel conectar ao Banco de Dados." + e.getMessage());

			return null;

		}

	}

	// Método que retorna o status da sua conexão//

	public static String statusConection() {

		return status;

	}

	// Método que fecha sua conexão//

	public static boolean FecharConexao() {

		try {

			Mysql.conectar().close();
			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	// Método que reinicia sua conexão//

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return Mysql.conectar();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.conectar();
	}

}