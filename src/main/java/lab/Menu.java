package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Menu implements DrawableSimulable{
    private int width, height;

    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);
    private Font f_btn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);
    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        /*gc.setFont(f);
        gc.setFill(Color.WHITE);
        gc.fillText("Game over", width /2 - 80, height / 2 - 30);
        gc.fillText("You collected " + coins + " coins", width / 2 - 140, height / 2 + 30);
        */

        //Play again
        gc.fillRect(width - 100, height - 50, 85, 35);
        gc.setFont(f_btn);
        gc.setFill(Color.BLACK);
        gc.fillText("Play again", width - 93, height - 25);

        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {

    }
}
