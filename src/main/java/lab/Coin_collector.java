package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Coin_collector implements DrawableSimulable {

	private boolean jump = false;
	private Point2D position;
	private Point2D size =  new Point2D(60, 20);
	private World world;
	
	private double default_y;
	 

	public Coin_collector(World world, Point2D position) {
		super();
		this.world = world;
		this.position = position;
		default_y = position.getY();
	}

	public void simulate(double timeStep) {
		if (jump && this.position.getY() <= 120) {
				this.position = new Point2D(this.position.getX(), this.position.getY() + 1);
		} else {
			if (this.position.getY() > default_y) {
				this.jump = false;
				this.position = new Point2D(this.position.getX(), this.position.getY() - 1);
			}
		}
	}
	
	public void draw(GraphicsContext gc) {
		gc.save();
		Point2D worldPosition = world.getCanvasPoint(position);
		gc.setFill(Color.BLACK);
		gc.fillRect(worldPosition.getX(), worldPosition.getY(), size.getX(), size.getY());
		gc.restore();
	}

	public void move_left() {
		if (this.position.getX() > 0) {
			this.position = new Point2D(this.position.getX() - 5, this.position.getY());
		}
	}
	public void move_right() {
		if (this.position.getX() < world.getWidth()-this.size.getX()) {
			this.position = new Point2D(this.position.getX() + 5, this.position.getY());
		}
	}
	public void jump() {
		if (!jump) {
			jump = true;
		}
	}
}
