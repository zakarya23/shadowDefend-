import bagel.util.Point;

import java.util.List;

public class ApexSlicer extends Slicer {

    private static final String IMAGE_FILE = "res/images/apexslicer.png";
    private static final double SPEED = (1.0/2);
    private static double health = 25;
    private static double reward = 150;
    private static double penalty = 2;

    public ApexSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2);
    }
}
