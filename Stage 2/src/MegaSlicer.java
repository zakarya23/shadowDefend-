import bagel.util.Point;

import java.util.List;

/**
 * The type Mega slicer.
 */
public class MegaSlicer extends Slicer {
    private static final String IMAGE_FILE = "res/images/megaslicer.png";
    private static final double SPEED = (3.0/4);
    private static double health = 2;
    private static int reward = 10;
    private static double penalty = 2;


    /**
     * Instantiates a new Mega slicer if for first time.
     *
     * @param polyline         the polyline
     * @param targetPointIndex the target point index
     */
    public MegaSlicer(List<Point> polyline, int targetPointIndex) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2, "superslicer", targetPointIndex);
    }

    /**
     * Instantiates a new Mega slicer if from death of a higher slicer.
     *
     * @param polyline         the polyline
     * @param targetPointIndex the target point index
     * @param start            the start
     */
    public MegaSlicer(List<Point> polyline, int targetPointIndex, Point start) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2, "superslicer", targetPointIndex, start);
    }
}
