package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameOver implements DrawableSimulable {
    private int width, height, coins, score;
    private World world;
    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);
    private Font f_btn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);

    public GameOver(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.setFont(f);
        gc.setFill(Color.WHITE);
        gc.fillText("Game over", width /2 - 80, height / 2 - 30);
        gc.fillText("You collected " + coins + " coins", width / 2 - 140, height / 2 + 30);
        //Play again
        gc.fillRect(width - 100, height - 50, 85, 35);
        gc.setFont(f_btn);
        gc.setFill(Color.BLACK);
        gc.fillText("Play again", width - 93, height - 25);

        //Return to Menu
        gc.setFill(Color.WHITE);
        gc.fillRect(20, height - 50, 80, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Scores", 35, height - 25);
        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {

    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void saveToScore() {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        try(FileWriter fw = new FileWriter("score.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(score + ";" + coins + ";" + timeStamp +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
