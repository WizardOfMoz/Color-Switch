package sample;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;


public class Ball extends Circle {
    private Color color;
    private double velocity;
    private double height;
    public Ball() {
        super(500,800,10);
        height=800;
        color=Coloring.getColor(0);
        setFill(color);
        velocity=0;
    }
    public void moveUp()
    {
        velocity=-5;
        height=getCenterY()+velocity;
        setCenterY(height);
        Main.then= System.nanoTime();
    }
    public void moveDown(double t)
    {
        if(getCenterY()<800) {
            velocity += 1.8 * t;
            height=getCenterY() + velocity;
            setCenterY(height);
        }
        else
            setCenterY(800);
    }
    public void changeColor()
    {
        Random random =new Random();
        color=Coloring.getColor(random.nextInt(4));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {


    }
}
