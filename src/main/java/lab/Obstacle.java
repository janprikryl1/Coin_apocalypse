package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Obstacle implements DrawableSimulable, Collisionable{
    private Point2D position;
    private int speed;
    private final World world;
    private final double size = 40;
    private Image image;
    private boolean hidden = false;
    Random rnd = new Random();

    public Obstacle(World world, Point2D position, int speed, boolean hidden) {
        super();
        this.world = world;
        this.position = position;
        this.speed = speed;
        image = new Image(getClass().getResourceAsStream("obstacle.png"), size, size, true, true);
        this.hidden = hidden;
    }


    @Override
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY() - size, size, size);
    }

    @Override
    public void hitBy(Collisionable other) {
            if ((other instanceof CoinCollector)) {
                world.EndGame();
            }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!this.hidden) {
            gc.save();
            Point2D canvasPosition = world.getCanvasPoint(position);
            gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
            gc.restore();
        }
    }

    @Override
    public void simulate(double timeDelta) {
        if (!this.hidden) {
            double newY = position.getY() - (speed * timeDelta);
            position = new Point2D(position.getX(), newY);

            if (position.getY() <= 0) {
                int x = rnd.nextInt((int) ((int) world.getWidth() - size));
                position = new Point2D(x, world.getHeight());
                speed = rnd.nextInt(40) + 40;
            }
        }
    }

    public boolean isHidden() {
        return this.hidden;
    }
    public void unHide() {
        this.hidden = false;
    }
}
