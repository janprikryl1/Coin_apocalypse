package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class Coin_collector implements DrawableSimulable, Collisionable {

	private boolean jump = false;
	private Point2D position;
	private int size = 60;
	private World world;
	
	private double default_y;

	private Image image_left, image_right;
	private boolean left = true;
	 

	public Coin_collector(World world, Point2D position) {
		super();
		this.world = world;
		this.position = position;
		default_y = position.getY();
        image_left = new Image(getClass().getResourceAsStream("coin_collector_left.png"), size, size, true, true);
		image_right = new Image(getClass().getResourceAsStream("coin_collector_right.png"), size, size, true, true);
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
		if (left) {
			gc.drawImage(image_left, canvasPosition.getX(), canvasPosition.getY());
		} else {
			gc.drawImage(image_right, canvasPosition.getX(), canvasPosition.getY());
		}
		gc.restore();
	}

	public void move_left() {
		if (this.position.getX() > 0) {
			this.position = new Point2D(this.position.getX() - 20, this.position.getY());
			left = true;
		}
	}
	public void move_right() {
		if (this.position.getX() < world.getWidth()-this.size) {
			this.position = new Point2D(this.position.getX() + 20, this.position.getY());
			left = false;
		}
	}
	public void jump() {
		if (!jump) {
			jump = true;
		}
	}

	@Override
	public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY() - size, size, size);
	}

	@Override
	public void hitBy(Collisionable other) {

	}

    public void setPoint(Point2D point2D) {
		this.position = point2D;
    }
	public void restart_jump() {
		this.jump = false;
	}
}
