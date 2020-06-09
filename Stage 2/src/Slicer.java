
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;
import java.util.List;

/**
 * A regular slicer.
 */
public class Slicer extends spawnDetails {

    private static final String IMAGE_FILE = "res/images/megaslicer.png";
    private static double speed = 1;
    private final List<Point> polyline;
    private int targetPointIndex;
    private boolean finished;
    private double health;
    private double reward;
    private double penalty;
    private int babySlicers;

    /**
     * Creates a new Slicer
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     */
    public Slicer(List<Point> polyline, String image, double speed, double health, double reward, double penalty, int babySlicers) {
        super(polyline.get(0), image);
        this.polyline = polyline;
        this.targetPointIndex = 1;
        this.finished = false;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.penalty = penalty;
        this.babySlicers = babySlicers;
    }



    /**
     * Updates the current state of the slicer. The slicer moves towards its next target point in
     * the polyline at its specified movement rate.
     */
    @Override
    public void update(Input input) {
        if (finished) {
            return;
        }

        // Obtain where we currently are, and where we want to be
        Point currentPoint = getCenter();
        Point targetPoint = polyline.get(targetPointIndex);
        // Convert them to vectors to perform some very basic vector math
        Vector2 target = targetPoint.asVector();
        Vector2 current = currentPoint.asVector();
        Vector2 distance = target.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < speed * ShadowDefend.getTimescale()) {
            // Check if we have reached the end
            if (targetPointIndex == polyline.size() - 1) {
                finished = true;
                return;
            } else {
                // Make our focus the next point in the polyline
                targetPointIndex += 1;
            }
        }

        // Move towards the target point
        // We do this by getting a unit vector in the direction of our target, and multiplying it
        // by the speed of the slicer (accounting for the timescale)
        super.move(distance.normalised().mul(speed * ShadowDefend.getTimescale()));
        // Update current rotation angle to face target point
        setAngle(Math.atan2(targetPoint.y - currentPoint.y, targetPoint.x - currentPoint.x));
        super.update(input);
    }


    public boolean isFinished() {
        return finished;
    }
}
