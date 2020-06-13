import bagel.util.Point;
import java.util.List;

/**
 * The type Super slicer.
 */
public class SuperSlicer extends Slicer{
    private static final String IMAGE_FILE = "res/images/superslicer.png";
    private static final double SPEED = (3.0/4);
    private static double health = 1;
    private static int reward = 15;
    private static double penalty = 2;
    private static String baby = "slicer";

    /**
     * Instantiates a new Super slicer for first time.
     */
    public SuperSlicer(List<Point> polyline, int targetPointIndex) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2, "slicer", targetPointIndex);
    }

    /**
     * Instantiates a new Super slicer if from a higher slicers death.
     */
    public SuperSlicer(List<Point> polyline, int targetPointIndex, Point start) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2, "slicer", targetPointIndex, start);
    }

}
