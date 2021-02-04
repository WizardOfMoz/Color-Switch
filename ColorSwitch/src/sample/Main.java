package sample;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;


public class Main extends Application{

    public static long then=System.nanoTime();
    public static Scene gameScene ;
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO

        Game game=new Game();

        gameScene = new Scene(game,1000,1000, Color.BLACK);


        gameScene.setOnKeyPressed(e ->{
            if(e.getCode().equals(KeyCode.UP))
            {
                game.getBall().moveUp();
            }

        });



        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now-then>1000000)
                {
                    game.update((now-then)/800000000.0);
                    if(game.hitObstacle())
                    {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("over.fxml"));
                            Label label = (Label)root.lookup("#score");
                            label.setText("Score: "+game.getScore());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        primaryStage.setScene(new Scene(root,600,800,Color.BLACK));

                    }
                    game.hitItem();

                }

            }
        };

        timer.start();



        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setScene(new Scene(root,600,800,Color.BLACK));
        primaryStage.setTitle("Color Switch");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static Button addButton()
    {
        Button button = new Button();
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
            return new Button();
        }
        ImageView view = new ImageView(image);
        button.setGraphic(view);
        return button;
    }
}
