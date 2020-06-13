import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * The type Spawn airplane.
 */
public abstract class spawnAirplane {

    private Image image;
    private Point point;
    private final Rectangle rect;
    private double angle;
    private Point start;

    /**
     * Instantiates a new Spawn airplane.
     *
     * @param point     the point
     * @param image     the image
     * @param startingH the starting h
     * @param startingV the starting v
     * @param planeNum  the plane num
     */
    public spawnAirplane(Point point, Image image, Point startingH, Point startingV, int planeNum){
        this.point = point;
        this.image = image;
        if (planeNum % 2 != 0) {
            this.rect = image.getBoundingBoxAt(startingH);
        }
        else {
            this.rect = image.getBoundingBoxAt(startingV);
        }
        angle = 0;
    }

    /**
     * Move.
     *
     * @param dx the dx
     */
    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
    }


    /**
     * Gets rect.
     *
     * @return the rect
     */
    public Rectangle getRect() {
        return new Rectangle(rect);
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return getRect().centre();
    }

    /**
     * Sets angle.
     *
     * @param angle the angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Update.
     *
     * @param input the input
     */
    public void update(Input input) {
        image.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }


}
