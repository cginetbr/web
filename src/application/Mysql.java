package application;

import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
  
 
//In�cio da classe de conex�o//
 
public class Mysql {
	Statement stmt = null;
	 
             public static String status = "N�o conectou...";
 
//M�todo Construtor da Classe//
 
             Mysql() {
 
    }
 
  
   public ResultSet select(String sql) throws SQLException {
	   	   
	      ResultSet rs = stmt.executeQuery(sql);
	      //STEP 5: Extract data from result set
	      /*while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         int age = rs.getInt("age");
	         String first = rs.getString("first");
	         String last = rs.getString("last");

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Age: " + age);
	         System.out.print(", First: " + first);
	         System.out.println(", Last: " + last);
	      }
	      rs.close();*/
	      
	      
	      return rs;
	   
   }
             
             
 
//M�todo de Conex�o//
 
public static java.sql.Connection getConexaoMySQL() {
 
        Connection connection = null;          //atributo do tipo Connection
 
  
 
try {
 
// Carregando o JDBC Driver padr�o
 
String driverName = "com.mysql.jdbc.Driver";                        
 
Class.forName(driverName);
 
  
 
// Configurando a nossa conex�o com um banco de dados//
 
            String serverName = "10.143.16.89";    //caminho do servidor do BD
 
            String mydatabase ="adpass";        //nome do seu banco de dados
 
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
 
            String username = "root";        //nome de um usu�rio de seu BD      
 
            String password = "55Mudaca";      //sua senha de acesso
 
            connection = DriverManager.getConnection(url, username, password);
 
  
 
            //Testa sua conex�o//  
 
            if (connection != null) {
 
                status = ("STATUS--->Conectado com sucesso!");
 
            } else {
 
                status = ("STATUS--->N�o foi possivel realizar conex�o");
 
            }
 
  
 
            return connection;
 
  
 
        } catch (ClassNotFoundException e) {  //Driver n�o encontrado
 
  
 
            System.out.println("O driver expecificado nao foi encontrado.");
 
            return null;
 
        } catch (SQLException e) {
 
//N�o conseguindo se conectar ao banco
 
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
 
            return null;
 
        }
 
  
 
    }
 
  
 
    //M�todo que retorna o status da sua conex�o//
 
    public static String statusConection() {
 
        return status;
 
    }
 
   
 
   //M�todo que fecha sua conex�o//
 
    public static boolean FecharConexao() {
 
        try {
 
            Mysql.getConexaoMySQL().close();
 
            return true;
 
        } catch (SQLException e) {
 
            return false;
 
        }
 
  
 
    }
 
   
 
   //M�todo que reinicia sua conex�o//
 
    public static java.sql.Connection ReiniciarConexao() {
 
        FecharConexao();
  
        return Mysql.getConexaoMySQL();
 
    }
 
}