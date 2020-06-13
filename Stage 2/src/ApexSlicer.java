import bagel.util.Point;

import java.util.List;

/**
 * The type Apex slicer.
 */
public class ApexSlicer extends Slicer {

    private static final String IMAGE_FILE = "res/images/apexslicer.png";
    private static final double SPEED = (1.0/2);
    private static double health = 25;
    private static int reward = 150;
    private static double penalty = 4;

    /**
     * Instantiates a new Apex slicer.
     */
    public ApexSlicer(List<Point> polyline, int  targetPointIndex) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2, "megaslicer", targetPointIndex);
    }
}
