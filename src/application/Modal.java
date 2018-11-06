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
		//engine.load("http://www.conectcar.com");

		 engine.loadContent("<html><body border=0 ><style>* {\n" + 
		 		"  margin: 0;\n" + 
		 		"  padding: 0;\n" + 
		 		"}</style><iframe src=\"http://cdn.bannersnack.com/banners/bh3ez0s3x/embed/index.html?userId=38298231&t=1540778269\" width=\"300\" height=\"600\" scrolling=\"no\" frameborder=\"0\" allowtransparency=\"true\" allow=\"autoplay\" allowfullscreen=\"true\"></iframe></body></html>" );
		
		
		try {
			Barramento barramento = new Barramento();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void btn1(ActionEvent event) {
				
		engine.load("http://www.conectcar.com");
		 
	}
	
	
	public WebEngine getWebview() {
		
		return this.engine;
		
	}
	
	
	
	
	
	
}
