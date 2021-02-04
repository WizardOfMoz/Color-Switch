package sample;

public abstract class Obstacle {
    private double height;
    private Star star;
    private Switcher switcher;
    public abstract void move();
    public abstract boolean isCollision(Ball ball);

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Star getStar() {
        return star;
    }

    public Switcher getSwitcher() {
        return switcher;
    }

    public void setSwitcher(Switcher switcher) {
        this.switcher = switcher;
    }
}
