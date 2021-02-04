package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Bars extends Obstacle {
    private ArrayList<Line> lines=new ArrayList<>();
    private double x[];
    private double y[];

    public Bars(double height,double size)
    {
        setHeight(height);
        setStar(new Star(height));
        setSwitcher(new Switcher(height-200));
        double m=Math.sqrt(3);
        x=new double[]{100,200,300,400,500,600,700,800};
        y=new double[]{height+size,height-size};
        for(int i=0;i<8;i++)
        {
            lines.add(create(x[i],y[0],x[i],y[1],Coloring.getColor(i%4)));
        }
    }

    public Line create(double x1,double y1,double x2,double y2, Color color)
    {

        Line line=new Line(x1,y1,x2,y2);
        line.setStroke(color);
        line.setStrokeWidth(20);
        line.setFill(Color.TRANSPARENT);
        return line;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    @Override
    public void move()
    {
        for(int i=0;i<lines.size();i++)
        {
            Line line=lines.get(i);
            line.setTranslateX(line.getTranslateX()+2);
            if(line.getTranslateX()>1000)
                line.setTranslateX(line.getTranslateX()%1000);

        }
    }


    @Override
    public boolean isCollision(Ball ball)
    {
        boolean b=false;
        for(int i=0;i<lines.size();i++)
        {
            if (Shape.intersect(ball, lines.get(i)).getBoundsInLocal().getWidth() != -1 && !ball.getFill().equals(Coloring.getColor(i%4)))
            {
                b=true;
                break;
            }

        }
        return b;

    }

}
