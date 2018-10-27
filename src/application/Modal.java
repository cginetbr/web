package application;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Modal implements Initializable {

	@FXML private WebView webview;
	private WebEngine engine;
	
 

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		engine = webview.getEngine();
		engine.load("http://www.conectcar.com");
		 
		
	}
	
	public void btn1(ActionEvent event) {
		
		
		engine.load("http://www.conectcar.com");
		 
	}
	
	
}
