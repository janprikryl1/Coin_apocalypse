package lab;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameController {

	private World world;
	@FXML
	private Canvas canvas; //Sem se dá ta instance z FXML podle id=canvas

	private AnimationTimer animationTimer;
	
	public GameController() {
	}
	
	public void startGame() {
		this.world = new World(canvas.getWidth(), canvas.getHeight());

		//Draw scene on a separate thread to avoid blocking UI.
		animationTimer = new DrawingThread(canvas, world);
		animationTimer.start();
		canvas.setOnKeyPressed(this::handleKeyPressed);
		//canvas.setOnKeyReleased(this::handleKeyReleased);
		canvas.requestFocus();  // Pro zajištění, že canvas bude mít focus a bude moci zachytávat klávesové události

	}
	private void handleKeyPressed(KeyEvent event) {
		// Reakce na stisknutou klávesu
		if (event.getCode().getCode() == 37) { //Left
			world.setCoinCollectorMovingLeft();
		} else if (event.getCode().getCode() == 39) { //Right
			world.setCoinCollectorMovingRight();
		} else if(event.getCode().getCode() == 38) { //Jump
			world.CoinCollectorJump();
		}
	}

	public void stopGame() {
		animationTimer.stop();
	}
}
