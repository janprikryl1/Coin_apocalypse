package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class GameOver implements DrawableSimulable {
    private World world;
    private int coins, score;
    private int totalCoins = 0;
    private int bestCoins = 0;

    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);
    private Font btnFont = Font.font("Comic Sans MS", FontWeight.BOLD, 15);

    public GameOver(World world) {
        this.world = world;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, world.getWidth(), world.getHeight());
        gc.setFont(f);
        gc.setFill(Color.WHITE);
        gc.fillText("Game over", world.getWidth() /2 - 80, world.getHeight() / 2 - 80);
        gc.fillText("You collected " + coins + " coins", world.getWidth() / 2 - 140, world.getHeight() / 2 - 40);
        gc.fillText("Total: " + totalCoins + " coins", world.getWidth() / 2 - 100, world.getHeight() / 2 + 10);
        gc.fillText("Best: " + bestCoins + " coins", world.getWidth() / 2 - 90, world.getHeight() / 2 + 40);

        //Use coins
        gc.setFont(btnFont);
        if (totalCoins >= 100) {
            gc.fillText("Press key 'u' to use 100 coins", world.getWidth() / 2 - 120, world.getHeight() - 100);
        }

        //Play again
        gc.fillRect(world.getWidth() - 100, world.getHeight() - 50, 85, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Play again", world.getWidth() - 93, world.getHeight() - 25);

        //Return to Menu
        gc.setFill(Color.WHITE);
        gc.fillRect(20, world.getHeight() - 50, 80, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Scores", 35, world.getHeight() - 25);
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

        updateTotalCoins();
        bestCoins = getBestCoins();
    }

    private void updateTotalCoins() {
        totalCoins = 0;
        try (Scanner scanner = new Scanner(new File("total_coins.txt"))){
            scanner.useDelimiter("[;\\n]");
            totalCoins =  scanner.nextInt();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        totalCoins += coins;

        try(FileWriter fw = new FileWriter("total_coins.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(totalCoins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getBestCoins() {
        int best = 0;
        try (Scanner scanner = new Scanner(new File("score.csv"))) {
            scanner.useDelimiter("[;\\n]");
            while (scanner.hasNext()) {
                scanner.nextInt();
                int coins = scanner.nextInt();
                if (coins > best) {
                    best = coins;
                }
                scanner.next();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return best;
    }

    public void useCoins() {
        if (totalCoins >= 100) {
            int newSpeed = world.getObstacleSpeed();
            newSpeed++;
            world.setObstacleSpeed(newSpeed);

            try (FileWriter fw = new FileWriter("obstacle_speed.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.print(newSpeed);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Substract coins
            totalCoins -= 100;
            try (FileWriter fw = new FileWriter("total_coins.txt", false);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.print(totalCoins);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
