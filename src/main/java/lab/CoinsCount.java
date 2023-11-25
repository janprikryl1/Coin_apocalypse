package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CoinsCount implements DrawableSimulable{
    private int width, height;
    private int coins = 0;
    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);

    CoinsCount(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        Point2D worldPosition = new Point2D(width, 0);
        gc.setFill(Color.YELLOW);
        gc.fillRect(worldPosition.getX(), worldPosition.getY(), width*2, height);
        gc.setFont(f);
        gc.setFill(Color.BLACK);
        gc.fillText("Coins: " + this.coins, width + 10, height - 5);
        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {

    }
    public int getCoins () {
        return this.coins;
    }
    public void increaseCoins() {
        this.coins++;
    }
    public void restartCoin() {
        this.coins = 0;
    }
}
