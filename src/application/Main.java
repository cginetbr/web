package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
	  
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			//Scene scene = new Scene(root,1024,800);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Console");
			primaryStage.show();
			
		//	this.stage.setScene(scene);
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void resizeScene(double width, double height) {
	    this.stage.setWidth(1800);
	    this.stage.setHeight(1200);
	}
	
	
}
