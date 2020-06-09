import bagel.util.Point;

import java.util.List;

public class MegaSlicer extends Slicer {
    private static final String IMAGE_FILE = "res/images/megaslicer.png";
    private static final double SPEED = (3.0/4);
    private static double health = 2;
    private static double reward = 10;
    private static double penalty = 2;


    public MegaSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2);
    }
}
