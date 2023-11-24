package lab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Menu implements DrawableSimulable{
    private int width, height;

    private Font f = Font.font("Comic Sans MS", FontWeight.BOLD, 25);
    private Font f_btn = Font.font("Comic Sans MS", FontWeight.BOLD, 15);

    private ArrayList<String> scores = new ArrayList<String>();
    private int page = 0;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //Nadpis Score
        gc.setFont(f);
        gc.setFill(Color.WHITE);
        gc.fillText("Score:", width /2 - 50, 30);

        //Seznam
        gc.setFont(f_btn);
        if (this.scores.isEmpty()) {
            gc.fillText("No scores", width / 2 - 50, 50);
        } else {
            int y = 5;
            for (int i = page*15; i < page * 15 + 15; i++) {
                if (i < scores.size()) {
                    gc.fillText(scores.get(i), width / 2 - 240, 50 + y);
                    y += 20;
                }
            }
        }

        //Previous page
        gc.fillRect(width / 2 - 115, height - 50, 105, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Previous page", width / 2 - 110, height - 25);

        //Next page
        gc.setFill(Color.WHITE);
        gc.fillRect(width / 2, height - 50, 85, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Next page", width / 2 + 5, height - 25);

        //Play again
        gc.setFill(Color.WHITE);
        gc.fillRect(width - 100, height - 50, 80, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Play again", width - 93, height - 25);

        //Flush Score
        gc.setFill(Color.WHITE);
        gc.fillRect(20, height - 50, 80, 35);
        gc.setFill(Color.BLACK);
        gc.fillText("Flush", 35, height - 25);
        gc.restore();

        gc.restore();
    }

    @Override
    public void simulate(double deltaT) {

    }

    public void update_scores() {
        this.scores.clear();
        page = 0;
        try (Scanner scanner = new Scanner(new File("score.csv"))){
            scanner.useDelimiter("[;\\n]");
            while (scanner.hasNext()) {
                int score = scanner.nextInt();
                int coins = scanner.nextInt();
                String datetime = scanner.next();
                this.scores.add("Score: " + score + "\t\tCoins: " + coins + "\t\t" + datetime);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void reset_score() {
        try(FileWriter fw = new FileWriter("score.csv", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scores.clear();
        page = 0;
    }
    
    public void previous_page() {
        if (this.page > 0) {
            this.page--;
        }
    }

    public void next_page() {
        if (this.page < Math.ceil(this.scores.size() / 15)) {
            this.page++;
        }
    }
}
