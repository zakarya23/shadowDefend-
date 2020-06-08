import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

public abstract class Towers {
    private Image towerImage;
    private Rectangle rect;
    private double damage;
    private double radius;
    private double coolDown;
    private double cost;
    private double angle;
    private Point location;
    private Image explosive;

    public Towers(Point point, Image image, double damage, double radius, double coolDown, double cost) {
        this.rect = image.getBoundingBoxAt(point);
        this.towerImage = image;
        this.damage = damage;
        this.radius = radius;
        this.coolDown = coolDown;
        this.cost = cost;
        this.location = point;
        this.angle = 0;
    }

    public Towers(Point point, Image image, Image explosive, int damage, int radius, int cost){
        this.rect = image.getBoundingBoxAt(point);
        this.towerImage = image;
        this.damage = damage;
        this.radius = radius;
        this.cost = cost;
        this.location = point;
        this.angle = 0;
        this.explosive = explosive;
    }

    public Point getLocation() {
        return location;
    }

    public void slicerTarget(){

    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Point getCenter() {
        return getRect().centre();
    }

    public void update(Input input){
        towerImage.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }

}
