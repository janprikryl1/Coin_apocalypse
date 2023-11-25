package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Jan Přikryl
 */
public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	private GameController controller;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml")); //Načtení fxml souboru
			BorderPane pane = loader.load();
			Scene scene = new Scene(pane);
			
			
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Coin apocalypse");
			primaryStage.show();
			controller = loader.getController();
			controller.startGame();
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exitProgram(WindowEvent evt) {
		controller.stopGame();
		System.exit(0);
	}
}