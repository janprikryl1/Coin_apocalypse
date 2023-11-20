package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Coin_collector implements DrawableSimulable {

	private boolean jump = false;
	private Point2D position;
	private int size = 60;
	private World world;
	
	private double default_y;

	private Image image;
	 

	public Coin_collector(World world, Point2D position) {
		super();
		this.world = world;
		this.position = position;
		default_y = position.getY();
        image = new Image(getClass().getResourceAsStream("coin_collector.png"), size, size, true, true);
    }

	public void simulate(double timeStep) {
		if (jump && this.position.getY() <= 120) {
				this.position = new Point2D(this.position.getX(), this.position.getY() + 5);
		} else {
			if (this.position.getY() > default_y) {
				this.jump = false;
				this.position = new Point2D(this.position.getX(), this.position.getY() - 5);
			}
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}

	public void move_left() {
		if (this.position.getX() > 0) {
			this.position = new Point2D(this.position.getX() - 10, this.position.getY());
		}
	}
	public void move_right() {
		if (this.position.getX() < world.getWidth()-this.size) {
			this.position = new Point2D(this.position.getX() + 10, this.position.getY());
		}
	}
	public void jump() {
		if (!jump) {
			jump = true;
		}
	}
}
