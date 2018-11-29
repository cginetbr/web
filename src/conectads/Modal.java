package conectads;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ResourceBundle;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;

public class Modal implements Initializable {

	@FXML private WebView webview;
	private WebEngine engine;
	private log log;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			log = new log();
		} catch (SecurityException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		engine = webview.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.setOnError(event -> log.log(event.getException()));
		engine.load("http://192.168.8.115/post/campanha.php");
		
		
		
		
		engine.getLoadWorker().stateProperty().addListener(
		            new ChangeListener<State>() {
		                public void changed(ObservableValue ov, State oldState, State newState) {
		                    if (newState == Worker.State.SUCCEEDED) {
		                        Document doc = engine.getDocument();
		                        
		                        try {
		                            
		                        	String a =  (String) engine.executeScript("document.getElementById().outerHTML");
;
		                        	String ab = "aaa";
		                        } catch (Exception ex) {
		                            ex.printStackTrace();
		                        }
		                    }
		                }
		            });
		
		
		
		
		
		
		
		/* engine.loadContent("<html><body border=0 ><style>* {\n" + 
		 		"  margin: 0;\n" + 
		 		"  padding: 0;\n" + 
		 		"}</style><iframe src=\"http://cdn.bannersnack.com/banners/bh3ez0s3x/embed/index.html?userId=38298231&t=1540778269\" width=\"300\" height=\"600\" scrolling=\"no\" frameborder=\"0\" allowtransparency=\"true\" allow=\"autoplay\" allowfullscreen=\"true\"></iframe></body></html>" );
		 */
		
		try {

			Barramento barramento = new Barramento();
			barramento.setModal(this);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.log(e);
			e.printStackTrace();
		}
		 
		
	}
		
	
	public void setSite(String url) {
				
		Platform.runLater(()->{
			try {   
			engine.load(url);	
			}catch (Exception e) {
				log.log(e);
			}
					
		});
				
	}
 
	
}
