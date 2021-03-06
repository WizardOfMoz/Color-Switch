package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public class Star extends ImageView implements Collectible{
    private double height;
    public Star(double height)
    {
        Image image;
        try {
            image = new Image(new FileInputStream("C:\\Users\\Aaloke Mozumdar\\IdeaProjects\\ColorSwitch\\src\\sample\\star.jpg"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return;
        }
        this.height=height;
        setImage(image);
        setX(480);
        setY(height-20);
        setFitHeight(40);
        setFitWidth(40);
    }

    @Override
    public boolean isCollision(Ball ball)
    {
        return ball.getCenterY()<=height+20;
    }
}
