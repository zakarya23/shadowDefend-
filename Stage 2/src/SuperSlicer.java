import bagel.util.Point;

import java.util.List;

public class SuperSlicer extends Slicer{
    private static final String IMAGE_FILE = "res/images/superslicer.png";
    private static final double SPEED = (3.0/4);
    private static double health = 1;
    private static double reward = 15;
    private static double penalty = 2;


    public SuperSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE, SPEED, health, reward, penalty, 2);
    }


}
