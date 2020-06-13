import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;
import java.util.List;

/**
 * A regular slicer.
 */
public class Slicer extends spawnDetails {

    private static double speed = 1;
    private final List<Point> polyline;
    private int targetPointIndex;
    private boolean finished;
    private double health;
    private int reward;
    private double penalty;
    private int babySlicers;
    private boolean hit;
    private String babyType;
    private boolean gotEnd;
    private final int HEALTH = 1;
    private final int SPEED = 1;
    private final int REWARD = 2;
    private final int PENALTY = 1;
    private static final String SLICER_IMAGE = "res/images/slicer.png";

    /**
     * Creates a new Slicer if for apex slicer
     */
    public Slicer(List<Point> polyline, String image, double speed, double health, int reward, double penalty, int babySlicers, String babyType, int targetPointIndex) {
        super(polyline.get(targetPointIndex), image);
        this.polyline = polyline;
        this.finished = false;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.penalty = penalty;
        this.babySlicers = babySlicers;
        this.hit = false;
        this.targetPointIndex = targetPointIndex;
        this.babyType = babyType;
    }

    /**
     * Instantiates a new Slicer if from a higher slicer other then apex
     */
    public Slicer(List<Point> polyline, String image, double speed, double health, int reward, double penalty, int babySlicers, String babyType, int targetPointIndex, Point start) {
        super(start, image);
        this.polyline = polyline;
        this.finished = false;
        this.speed = speed;
        this.health = health;
        this.reward = reward;
        this.penalty = penalty;
        this.babySlicers = babySlicers;
        this.hit = false;
        this.targetPointIndex = targetPointIndex;
        this.babyType = babyType;
    }

    /**
     * Creating a slicer from scratch
     * @param polyline
     */
    public Slicer(List<Point> polyline){
        super(polyline.get(0), SLICER_IMAGE);
        this.polyline = polyline;
        this.finished = false;
        this.speed = SPEED;
        this.health = HEALTH;
        this.reward = REWARD;
        this.penalty = PENALTY;
        this.babySlicers = 0;
        this.hit = false;
        this.babyType = "none";
    }

    /**
     * Updates the current state of the slicer. The slicer moves towards its next target point in
     * the polyline at its specified movement rate.
     */
    @Override
    public void update(Input input) {
        if (finished) {
            setRect(null);
            return;
        }

        if (health <= 0){
            ShadowDefend.getPlayer().setCash(ShadowDefend.getPlayer().getCash() + reward);
            if (getBabyType().equals("slicer")){
                spawnSlicers();
            }
            if (getBabyType().equals("superslicer")){
                spawnSuperSlicers();
            }
            if (getBabyType().equals("megaslicer")){
                spawnMegaSlicers();
            }
            finished = true;
        }

        // Obtain where we currently are, and where we want to be
        Point currentPoint = getCenter(); // This needs to be changed when spawning baby
        Vector2 target;
        Point targetPoint = polyline.get(polyline.size() - 1 );
        if (targetPointIndex < polyline.size() - 1 ) {
            targetPoint = polyline.get(targetPointIndex + 1);
            target = targetPoint.asVector();
        }
        // Convert them to vectors to perform some very basic vector math
        target = targetPoint.asVector();
        Vector2 current = currentPoint.asVector();
        Vector2 distance = target.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < speed * ShadowDefend.getTimescale()) {
            // Check if we have reached the end
            if (targetPointIndex == polyline.size() - 5) {
                finished = true;
                gotEnd = true;
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

    /**
     * Is got end boolean.
     *
     * @return the boolean
     */
    public boolean isGotEnd() {
        return gotEnd;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Gets baby type.
     *
     * @return the baby type
     */
    public String getBabyType() {
        return babyType;
    }

    /**
     * Gets penalty.
     *
     * @return the penalty
     */
    public double getPenalty() {
        return penalty;
    }

    /**
     * Spawn slicers.
     */
    public void spawnSlicers() {
        ShadowDefend.getSlicers().add(new Slicer(ShadowDefend.getPolyline(), "res/images/slicer.png", 1, 1, 2,
                1, 0,"none",  targetPointIndex, getCenter()));
        ShadowDefend.getSlicers().add(new Slicer(ShadowDefend.getPolyline(), "res/images/slicer.png", 1, 1, 2,
                1, 0,"none",  targetPointIndex, getCenter()));
    }

    /**
     * Spawn super slicers.
     */
    public void spawnSuperSlicers() {
        ShadowDefend.getSlicers().add(new SuperSlicer(ShadowDefend.getPolyline(), targetPointIndex, getCenter()));
        ShadowDefend.getSlicers().add(new SuperSlicer(ShadowDefend.getPolyline(), targetPointIndex, getCenter()));
    }

    /**
     * Spawn mega slicers.
     */
    public void spawnMegaSlicers() {
        for (int i =0; i<4;i++) {
            ShadowDefend.getSlicers().add(new MegaSlicer(ShadowDefend.getPolyline(), targetPointIndex, getCenter()));
        }
    }

}
