package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Game extends Group {
    private Obstacle lowerObstacle;
    private Obstacle upperObstacle;
    private Ball ball;
    private int score;
    private Random random;
    private Button button;
    private Label label;
    public Game()
    {
        score=0;
        ball=new Ball();
        random=new Random();
        getChildren().add(ball);
        addObstacle();
        addButton();
        addLabel();



    }

    public int getScore() {
        return score;
    }

    public void addLabel()
    {
        label=new Label("Score: "+ score);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Times New Roman",40));
        getChildren().add(label);
    }


    public void addObstacle()
    {
        lowerObstacle=new Ring(600,100);
        for(Arc arc:((Ring)lowerObstacle).getArcs())
            getChildren().add(arc);
        getChildren().addAll(lowerObstacle.getStar(),lowerObstacle.getSwitcher());
        upperObstacle=new Square(200,80);
        for(Line line:((Square)upperObstacle).getLines())
            getChildren().add(line);
        getChildren().addAll(upperObstacle.getStar(),upperObstacle.getSwitcher());
//        upperObstacle=new Cross(200,100);
//        for(Line line:((Cross)upperObstacle).getLines())
//            getChildren().add(line);
//        getChildren().addAll(upperObstacle.getStar(),upperObstacle.getSwitcher());
//        upperObstacle=new Bars(200,100);
//        for(Line line:((Bars)upperObstacle).getLines())
//            getChildren().add(line);
//        getChildren().addAll(upperObstacle.getStar(),upperObstacle.getSwitcher());

    }


    public void update(double t)
    {
        ball.moveDown(t);
        lowerObstacle.move();
        upperObstacle.move();

    }

    public Ball getBall() {
        return ball;
    }
    public boolean hitObstacle()
    {
        return lowerObstacle.isCollision(ball) || upperObstacle.isCollision(ball);
    }
    public void hit(Obstacle obstacle)
    {
        if(obstacle.getStar()!=null && obstacle.getStar().isCollision(ball))
        {
            score++;
            label.setText("Score: "+score);
            System.out.println("Score :"+score);
            getChildren().remove(obstacle.getStar());
            obstacle.setStar(null);
        }
        if(obstacle.getSwitcher()!=null && obstacle.getSwitcher().isCollision(ball))
        {
            System.out.println("switch");
            int n;
            while(Coloring.getColor(n=random.nextInt(4))==ball.getFill())
            {
            }
            ball.setFill(Coloring.getColor(n));
            ball.setColor(Coloring.getColor(n));
            getChildren().remove(obstacle.getSwitcher());
            obstacle.setSwitcher(null);
        }



    }

    public void hitItem()
    {
        hit(upperObstacle);
        hit(lowerObstacle);
    }
    public void addButton()
    {
        button = new Button();
        button.setTranslateX(700);
        button.setTranslateY(20);
        button.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Image image;
        try {
            image = new Image(new FileInputStream("C:\\Users\\Aaloke Mozumdar\\IdeaProjects\\ColorSwitch\\src\\sample\\button.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return;
        }
        ImageView view = new ImageView(image);
        button.setGraphic(view);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("pause.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root,600,800,Color.BLACK));
                window.show();
            }
        });

        getChildren().add(button);

    }
}
