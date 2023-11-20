package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background implements DrawableSimulable{
    private Image image;
    private World world;
    public Background(World world) {
        this.world = world;
        image = new Image(getClass().getResourceAsStream("background.png"), world.getWidth(), world.getHeight(), true, true);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.drawImage(image, 0, 0);
        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {

    }
}
