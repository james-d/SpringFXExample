package springfx.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TableTest extends Application {

    private AnnotationConfigApplicationContext applicationContext;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/springfx/table/TableView.fxml"));
		loader.setControllerFactory(applicationContext::getBean);
		Scene scene = new Scene(loader.load(), 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
	    if (applicationContext != null) {
	        applicationContext.close();
	    }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
