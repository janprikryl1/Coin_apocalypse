package lab;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class World {
	private double width;
	private double height;
	private DrawableSimulable []entities;

	private Coin_collector coinCollector;
	private Score_label scoreLabel;
	private Coins_count coinsCount;
	private Obstacle obstacle;

	public World(double width, double height) {
		super();
		this.width = width;
		this.height = height;
		coinCollector = new Coin_collector(this, new Point2D(70, 65));
		scoreLabel = new Score_label((int) (this.width/2), 30);
		coinsCount = new Coins_count((int) (this.width/2), 30);
		entities = new DrawableSimulable[1 + 3 + 10 + 1];
		entities[0] = new Background(this);
		entities[1] = scoreLabel;
		entities[2] = coinsCount;

		entities[3] = coinCollector;

		Random rnd = new Random();

		for (int i = 4; i < 14; i++) {
			Coin coin = new Coin(this, new Point2D(rnd.nextInt((int) width - 45), height), rnd.nextInt(40) + 45);
			entities[i] = coin;
		}

		obstacle = new Obstacle(this, new Point2D(rnd.nextInt((int) width - 40), height), rnd.nextInt(40) + 40);
		entities[14] = obstacle;
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}

	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, width, height);
		for(DrawableSimulable entity: entities) {
			entity.draw(gc);
		}
	}

	public void simulate(double timeDelta) {
		for(DrawableSimulable entity: entities) {
			entity.simulate(timeDelta);
		/*	if (entity instanceof Collisionable) {
				Collisionable thisCollinsable = (Collisionable) entity;
				for(DrawableSimulable entity2 : entities) {
					if (entity != entity2 && entity2 instanceof Collisionable) {
						Collisionable thatCollinsable = (Collisionable) entity2;
						if (thisCollinsable.intersects(thatCollinsable)) {
							thisCollinsable.hitBy(thatCollinsable);
							thatCollinsable.hitBy(thisCollinsable);
						}
					}
				}
			}*/
		}
		
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setCoinCollectorMovingLeft() {
		coinCollector.move_left();
	}
	public void setCoinCollectorMovingRight() {
		coinCollector.move_right();
	}
	public void CoinCollectorJump() {
		coinCollector.jump();
	}
}
