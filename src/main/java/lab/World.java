package lab;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class World {
	private boolean game = true;
	private boolean menu = false;
	private double width;
	private double height;
	private DrawableSimulable []entities;
	private CoinCollector coinCollector;
	private ScoreLabel scoreLabel;
	private CoinsCount coinsCount;
	private Obstacle obstacle;
	private GameOver gameOver;
	private Menu menuScore;
	Random rnd = new Random();

	public World(double width, double height) {
		super();
		this.width = width;
		this.height = height;
		coinCollector = new CoinCollector(this, new Point2D(70, 65));
		scoreLabel = new ScoreLabel((int) (this.width/2), 30, this);
		coinsCount = new CoinsCount((int) (this.width/2), 30);
		entities = new DrawableSimulable[1 + 3 + 10 + 1 + 3];
		entities[0] = new Background(this);
		entities[1] = scoreLabel;
		entities[2] = coinsCount;

		entities[3] = coinCollector;


		for (int i = 4; i < 14; i++) {
			Coin coin = new Coin(this, new Point2D(rnd.nextInt((int) width - 45), height), rnd.nextInt(40) + 45);
			entities[i] = coin;
		}

		obstacle = new Obstacle(this, new Point2D(rnd.nextInt((int) width - 40), height), rnd.nextInt(40) + 40, false);
		entities[14] = obstacle;

		for (int i = 15; i < 18; i++) {
			Obstacle o = new Obstacle(this, new Point2D(rnd.nextInt((int) width - 40), height), rnd.nextInt(40) + 40, true);
			entities[i] = o;
		}

		gameOver = new GameOver((int) width, (int) height);
		menuScore = new Menu((int)width, (int) height);
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}

	public void draw(GraphicsContext gc) {
		if (game) {
			gc.clearRect(0, 0, width, height);
			for (DrawableSimulable entity : entities) {
				entity.draw(gc);
			}
		} else {
			if (!menu) {
				gameOver.draw(gc);
			} else {
				menuScore.draw(gc);
			}
		}
	}

	public void simulate(double timeDelta) {
		if (game) {
			for (DrawableSimulable entity : entities) {
				entity.simulate(timeDelta);
				if (entity instanceof Collisionable) {
					Collisionable thisCollinsable = (Collisionable) entity;
					for (DrawableSimulable entity2 : entities) {
						if (entity != entity2 && entity2 instanceof Collisionable) {
							Collisionable thatCollinsable = (Collisionable) entity2;
							if (thisCollinsable.intersects(thatCollinsable)) {
								thisCollinsable.hitBy(thatCollinsable);
								thatCollinsable.hitBy(thisCollinsable);
							}
						}
					}
				}
			}
		}
	}

	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public void setCoinCollectorMovingLeft() {
		coinCollector.moveLeft();
	}
	public void setCoinCollectorMovingRight() {
		coinCollector.moveRight();
	}
	public void CoinCollectorJump() {
		coinCollector.jump();
	}

	public void increaseCoins() {
		this.coinsCount.increaseCoins();
	}
	public void EndGame() {
		this.game = false;
		gameOver.setCoins(coinsCount.getCoins());
		gameOver.setScore(scoreLabel.getScore());
		gameOver.saveToScore();
		menu = false;
	}

	public void playAgainClicked() {
		if (!game) {
			scoreLabel.restartScore();
			coinsCount.restartCoin();
			this.game = true;
			coinCollector.setPoint(new Point2D(70, 65));
			coinCollector.restartJump();

			for (int i = 4; i < 14; i++) {
				Coin coin = new Coin(this, new Point2D(rnd.nextInt((int) width - 45), height), rnd.nextInt(40) + 45);
				entities[i] = coin;
			}

			obstacle = new Obstacle(this, new Point2D(rnd.nextInt((int) width - 40), height), rnd.nextInt(40) + 40, false);
			entities[14] = obstacle;
			for (int i = 15; i < 18; i++) {
				Obstacle o = new Obstacle(this, new Point2D(rnd.nextInt((int) width - 40), height), rnd.nextInt(40) + 40, true);
				entities[i] = o;
			}
		}
	}
	public void MenuClicked() {
		if (!game) {
			if (!menu) {
				menu = true;
				menuScore.updateScores();
			} else  {
				menuScore.resetScore();
			}
		}
	}
	public void menuPreviousPageClicked() {
		menuScore.previousPage();
	}
	public void menuNextPageClicked() {
		menuScore.nextPage();
	}
	public void unhideNextObstacle() {
		for (int i = 15; i < 18; i++) {
			Obstacle o = (Obstacle) entities[i];
			if (o.isHidden()) {
				o.unHide();
				break;
			}
		}
	}
}
