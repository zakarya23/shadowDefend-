import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * The type Projectile.
 */
public class Projectile extends spawnDetails {
    private boolean active;
    private final double speed = 10;
    private Slicer aim;

    /**
     * Instantiates a new Projectile.
     *
     * @param start the start
     * @param aim   the aim
     * @param image the image
     */
    public Projectile(Point start, Slicer aim, String image){
        super(start, image);
        this.active = true;
        this.aim = aim;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void update(Input input){
        if (!active){
            return;
        }
        Point current = getCenter();
        // Convert them to vectors to perform some very basic vector math
        Vector2 targetPoint = aim.getCenter().asVector();
        Vector2 currentPoint = current.asVector();
        Vector2 distance = targetPoint.sub(currentPoint);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();

        super.move(distance.normalised().mul(speed * ShadowDefend.getTimescale()));
        super.update(input);
    }
}
