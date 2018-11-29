package conectads;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.MqttException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainController implements Initializable {

	@FXML private WebView webview;
	Stage primaryStage;
	Properties prop;
	Properties propVideo;
	@FXML TextField altura;
	@FXML TextField largura;
	@FXML TextField y;
	@FXML TextField x;
	@FXML TextField servidorMqtt;
	@FXML TextField portaMqtt;
	@FXML TextField id;
	@FXML TextField usuarioMqtt;
	@FXML TextField senhaMqtt;
	@FXML TextField servidorMysql;
	@FXML TextField usuarioMysql;
	@FXML TextField senhaMysql;
	@FXML TextField portaMysql;
	@FXML TextField nomePista;
	@FXML TextField urlOrquestrador;
	 
	
	@Override 
	public void initialize(java.net.URL location, ResourceBundle resources) {
		
		//webview.getEngine();
		 prop = new Properties();
		 propVideo = new Properties();
		
		 try {
			lePropriedades();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void btn1(ActionEvent event) {
		// change LoginScene.fxml so it now has fx:controller="LoginController"
        Parent root;
		try {
			
			gravarVideo();
			lePropriedades();
			
			primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/conectads/modal.fxml"));
			
	        Scene scene = new Scene(root, Integer.parseInt(largura.getText()), Integer.parseInt(altura.getText()));
	      
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        scene.setFill(Color.BLACK);
	        primaryStage.setScene(scene);
	        primaryStage.initModality(Modality.WINDOW_MODAL);
	        primaryStage.setX(Integer.parseInt(x.getText()));
	        primaryStage.setY(Integer.parseInt(y.getText()));
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	      
	                
	        for (Screen screen : Screen.getScreens()) {
	           
	        	Rectangle2D bounds = screen.getVisualBounds();
	            primaryStage.setX(bounds.getMinX());
	            
	        }
          
	        primaryStage.setOnCloseRequest((WindowEvent event1) -> {
	        	 System.exit(0);
	        });
	        
	        primaryStage.show();
	      //  buscaMensam();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		
	}
	
 
	
	
	
	public void btnSalvar(ActionEvent event) {
		
	 
		 
		
		//JOptionPane.showMessageDialog(null,"teste", "InfoBox: " + valor, JOptionPane.INFORMATION_MESSAGE);
 
		prop.setProperty("servidorMqtt", servidorMqtt.getText());
		prop.setProperty("portaMqtt", portaMqtt.getText());
		prop.setProperty("usuarioMqtt", usuarioMqtt.getText());
		prop.setProperty("senhaMqtt", senhaMqtt.getText());
		
		
		prop.setProperty("id", id.getText());
		

		prop.setProperty("nomePista", nomePista.getText());
		prop.setProperty("servidorMysql", servidorMysql.getText());
		prop.setProperty("portaMysql", portaMysql.getText());
		prop.setProperty("usuarioMysql", usuarioMysql.getText());
		prop.setProperty("senhaMysql", senhaMysql.getText());
		
		prop.setProperty("urlOrquestrador", urlOrquestrador.getText());
		

		try {
			prop.store(new FileOutputStream("application.prop"),"Properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void gravarVideo() {
				
		//JOptionPane.showMessageDialog(null,"teste", "InfoBox: " + valor, JOptionPane.INFORMATION_MESSAGE);
		prop.setProperty("altura", altura.getText());
		prop.setProperty("largura", largura.getText());
		prop.setProperty("x", x.getText());
		prop.setProperty("y", y.getText());
		 
		try {
			prop.store(new FileOutputStream("application.prop"),"Properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
	
	
	
	public void lePropriedades() throws IOException {
		
		FileInputStream fi=new FileInputStream("application.prop");

		prop.load(fi);
		
		altura.setText(prop.getProperty("altura"));
		largura.setText(prop.getProperty("largura"));
		x.setText(prop.getProperty("x"));
		y.setText(prop.getProperty("y"));
		servidorMqtt.setText(prop.getProperty("servidorMqtt"));
		portaMqtt.setText(prop.getProperty("portaMqtt"));
		id.setText(prop.getProperty("id"));
		
		usuarioMqtt.setText(prop.getProperty("usuarioMqtt"));
		senhaMqtt.setText(prop.getProperty("senhaMqtt"));
		
		servidorMysql.setText(prop.getProperty("servidorMysql"));
		usuarioMysql.setText(prop.getProperty("usuarioMysql"));
		senhaMysql.setText(prop.getProperty("senhaMysql"));
		portaMysql.setText(prop.getProperty("portaMysql"));
 		nomePista.setText(prop.getProperty("nomePista"));
 		urlOrquestrador.setText(prop.getProperty("urlOrquestrador"));
		
		
	}
	 	
	public void buscaMensam() {
		
		
		try {
			Mqtt m = new Mqtt();
			m.posta("teste");			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	
	@FXML
    public void exitApplication(ActionEvent event) {
        System.out.println("stop");
        System.exit(0);
       // Platform.exit();
    }
	
	
}
