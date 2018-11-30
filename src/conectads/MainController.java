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
	Parametros parm;
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
		
		 
		 parm = new Parametros();
		
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
 
		parm.SetParametro("servidorMqtt", servidorMqtt.getText());
		parm.SetParametro("portaMqtt", portaMqtt.getText());
		parm.SetParametro("usuarioMqtt", usuarioMqtt.getText());
		parm.SetParametro("senhaMqtt", senhaMqtt.getText());
		
		
		parm.SetParametro("id", id.getText());
		

		parm.SetParametro("nomePista", nomePista.getText());
		parm.SetParametro("servidorMysql", servidorMysql.getText());
		parm.SetParametro("portaMysql", portaMysql.getText());
		parm.SetParametro("usuarioMysql", usuarioMysql.getText());
		parm.SetParametro("senhaMysql", senhaMysql.getText());
		
		parm.SetParametro("urlOrquestrador", urlOrquestrador.getText());
		
		parm.salvar();

		 
		
	}
	
	
	
	public void gravarVideo() {
				
		//JOptionPane.showMessageDialog(null,"teste", "InfoBox: " + valor, JOptionPane.INFORMATION_MESSAGE);
		parm.SetParametro("altura", altura.getText());
		parm.SetParametro("largura", largura.getText());
		parm.SetParametro("x", x.getText());
		parm.SetParametro("y", y.getText());
		parm.salvar();
		 
		
	}
	
	
	
	public void lePropriedades() throws IOException {
		
		 
		altura.setText(parm.getParametro("altura"));
		largura.setText(parm.getParametro("largura"));
		x.setText(parm.getParametro("x"));
		y.setText(parm.getParametro("y"));
		servidorMqtt.setText(parm.getParametro("servidorMqtt"));
		portaMqtt.setText(parm.getParametro("portaMqtt"));
		id.setText(parm.getParametro("id"));
		
		usuarioMqtt.setText(parm.getParametro("usuarioMqtt"));
		senhaMqtt.setText(parm.getParametro("senhaMqtt"));
		
		servidorMysql.setText(parm.getParametro("servidorMysql"));
		usuarioMysql.setText(parm.getParametro("usuarioMysql"));
		senhaMysql.setText(parm.getParametro("senhaMysql"));
		portaMysql.setText(parm.getParametro("portaMysql"));
 		nomePista.setText(parm.getParametro("nomePista"));
 		urlOrquestrador.setText(parm.getParametro("urlOrquestrador"));
		
		
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
