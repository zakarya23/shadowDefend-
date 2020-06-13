
import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * Represents a game entity
 */
public abstract class spawnDetails {

    private final Image image;
    private Rectangle rect;
    private double angle;

    /**
     * Creates a new spawnDetails (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public spawnDetails(Point point, String imageSrc) {
        this.image = new Image(imageSrc);
        this.rect = image.getBoundingBoxAt(point);
        this.angle = 0;
    }

    /**
     * Sets rect.
     *
     * @param rect the rect
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
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
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Moves the spawnDetails by a specified delta
     *
     * @param dx The move delta vector
     */
    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
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
     * Updates the spawnDetails. Default behaviour is to render the spawnDetails at its current position.
     *
     * @param input the input
     */
    public void update(Input input) {
        image.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }
}
