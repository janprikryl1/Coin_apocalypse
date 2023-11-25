package lab;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GameController {
	private World world;
	@FXML
	private Canvas canvas;
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
		canvas.setOnMouseClicked(this::mouseClicked);
		canvas.requestFocus();  // Pro zajištění, že canvas bude mít focus a bude moci zachytávat klávesové události

	}

	private void mouseClicked(MouseEvent mouseEvent) {
		if (mouseEvent.getX() >= 499 && mouseEvent.getX() <= 584 && mouseEvent.getY() >= 348 && mouseEvent.getY() <= 384) { //Play again X od 499, Y od 348 do X: 584, Y: 384
			world.playAgainClicked();
		} else if (mouseEvent.getX() >= 20 && mouseEvent.getX() <= 99 && mouseEvent.getY() >= 348 && mouseEvent.getY() <= 384) { //Menu od X: 20, Y: 348 do X: 99, Y: 348
			world.MenuClicked();
		} else if (mouseEvent.getX() >= 185 && mouseEvent.getX() <= 290 && mouseEvent.getY() >= 348 && mouseEvent.getY() <= 384) {
			world.menuPreviousPageClicked();
		} else if (mouseEvent.getX() >= 300 && mouseEvent.getX() <= 385 && mouseEvent.getY() >= 348 && mouseEvent.getY() <= 384) {
			world.menuNextPageClicked();
		}
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
