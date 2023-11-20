package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Coin implements DrawableSimulable, Collisionable{
	
	private Point2D position;
	
	private int speed;
	
	private final World world;
		
	private final double size = 45;
	private Image image;
	Random rnd = new Random();

	public Coin(World world, Point2D position, int speed) {
		super();
		this.world = world;
		this.position = position;
		this.speed = speed;
		image = new Image(getClass().getResourceAsStream("coin.png"), size, size, true, true);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}

	@Override
	public void simulate(double timeDelta) {
		double timeDeltaS = timeDelta;
		//double newX = (position.getX() + speed.getX() * timeDeltaS + world.getWidth()) % world.getWidth();
		double newY = position.getY() - (speed * timeDeltaS);
		position = new Point2D(position.getX(), newY);

		if (position.getY() <= 0) {
			int x = rnd.nextInt((int) ((int) world.getWidth() - size));
			position = new Point2D(x, world.getHeight());
			speed = rnd.nextInt(40) + 45;
		}
	}


	public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY() - size, size, size);
	}


	public void hit() {

	}


	@Override
	public void hitBy(Collisionable other) {
		if (!(other instanceof Coin)) {
			hit();
		}
	}

	
	
	
	
}
