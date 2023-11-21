package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Score_label implements DrawableSimulable{
    private int width, height;
    private int score = 0;

    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);

    Score_label(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        Point2D worldPosition = new Point2D(0, 0);
        gc.setFill(Color.GREEN);
        gc.fillRect(worldPosition.getX(), worldPosition.getY(), width, height);
        gc.setFont(f);
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + this.score, 10, height - 5);
        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {
        increaseScore();
    }

    public void increaseScore() {
        this.score++;
    }

    public int getScore() {
        return this.score;
    }
}
