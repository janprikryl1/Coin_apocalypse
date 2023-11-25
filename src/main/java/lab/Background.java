package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background implements DrawableSimulable{
    private Image image;
    public Background(World world) {
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
