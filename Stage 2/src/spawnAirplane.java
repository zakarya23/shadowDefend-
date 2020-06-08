import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

public abstract class spawnAirplane {

    private Image image;
    private Point point;
    private final Rectangle rect;
    private double angle;
    private Point start;

    public spawnAirplane(Point point, Image image){
        this.point = point;
        this.image = image;
        this.rect = image.getBoundingBoxAt(point);
        angle = 0;
    }

    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
    }


    public Rectangle getRect() {
        return new Rectangle(rect);
    }

    public Point getCenter() {
        return getRect().centre();
    }

    public void update(Input input) {
        image.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }


}
