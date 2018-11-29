package conectads;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	private Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
	  
			Parent root = FXMLLoader.load(getClass().getResource("/conectads/Main.fxml"));
			//Scene scene = new Scene(root,1024,800);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Console");
			primaryStage.show();
					 
			primaryStage.setOnCloseRequest(event -> {
			    System.out.println("Stage is closing");
			    System.out.println("stop");
		        System.exit(0);
			});
			
			
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
	
	
	@FXML
    public void exitApplication(ActionEvent event) {
        System.out.println("stop");
    
        Platform.exit();
    }
	
 
	
	
}
