package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.eclipse.paho.client.mqttv3.MqttException;

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

public class MainController implements Initializable {

	@FXML private WebView webview;
	Stage primaryStage;
	Properties prop;
	@FXML TextField altura;
	@FXML TextField largura;
	@FXML TextField y;
	@FXML TextField x;
	@FXML TextField servidor;
	@FXML TextField porta;
	@FXML TextField id;
	 
	String larg ;
	String px ;
	String py ;
	String serv ;
	String port ;
	String pid ;
	String alt ;
	String server;
	
	@Override 
	public void initialize(java.net.URL location, ResourceBundle resources) {
		
		//webview.getEngine();
		 prop = new Properties();
		
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
			lePropriedades();
		
			primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/application/modal.fxml"));
			
	        Scene scene = new Scene(root, Integer.parseInt(larg), Integer.parseInt(alt));
	      //  Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        scene.setFill(Color.BLACK);
	        primaryStage.setScene(scene);
	        primaryStage.initModality(Modality.WINDOW_MODAL);
	        primaryStage.setX(Integer.parseInt(px));
	        primaryStage.setY(Integer.parseInt(py));
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        primaryStage.show();
	                
	        for (Screen screen : Screen.getScreens()) {
	           
	        	Rectangle2D bounds = screen.getVisualBounds();
	            primaryStage.setX(bounds.getMinX());
	            
	        }
             
	        buscaMensam();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
		
	}
	
	public void btnSalvar(ActionEvent event) {
		
		alt = altura.getText();
		larg = largura.getText();
		px = x.getText();
		py = y.getText();
		server = servidor.getText();
		port = porta.getText();
		pid = id.getText();
		
		
		//JOptionPane.showMessageDialog(null,"teste", "InfoBox: " + valor, JOptionPane.INFORMATION_MESSAGE);
		prop.setProperty("altura", alt);
		prop.setProperty("largura", larg);
		prop.setProperty("x", px);
		prop.setProperty("y", py);
		prop.setProperty("servidor", server);
		prop.setProperty("porta", port);
		prop.setProperty("id", pid);

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
		
		
		InputStream in = getClass().getResourceAsStream("application.prop");
		FileInputStream fi=new FileInputStream("application.prop");

		prop.load(fi);
		alt = prop.getProperty("altura");
		
		
		larg = prop.getProperty("largura");
		px = prop.getProperty("x");
		py = prop.getProperty("y");
		serv = prop.getProperty("servidor");
		port = prop.getProperty("porta");
		pid = prop.getProperty("id");
				
		altura.setText(alt);
		largura.setText(larg);
		x.setText(px);
		y.setText(py);
		servidor.setText(serv);
		porta.setText(port);
		id.setText(pid);
		
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
	
	
}
