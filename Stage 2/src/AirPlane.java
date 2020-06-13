import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.ArrayList;

/**
 * The type Air plane.
 */
public class AirPlane extends spawnAirplane{
    private static final Image PLANE_IMAGE = new Image("res/images/airsupport.png");
    private static final Image EXPLOSIVE = new Image("res/images/explosive.png");
    private static int damage = 500;
    private static int radius = 200;
    private static int cost = 500;
    private static final double SPEED = 5;
    private static int x = 0;
    private static int y = 120;
    private Point startH;
    private Point startV;
    private Point endH;
    private Point endV;
    private int planeNum;
    private boolean finished;
    private final int FPS = 60;
    private double frameCount;
    private ArrayList<Explosive> explosives;


    /**
     * Instantiates a new Air plane.
     */
    public AirPlane(Point point, int planeNum, double frameCount){
        super(point, PLANE_IMAGE, new Point(x, point.y), new Point(point.x, y), planeNum);
        startH = new Point(x, point.y);
        startV = new Point(point.x, y);
        endH = new Point(1024, point.y);
        endV = new Point(point.x, 768);
        this.planeNum = planeNum;
        this.frameCount = frameCount;
        this.explosives = new ArrayList<>();
    }

    /**
     * Sets frame count.
     *
     * @param frameCount the frame count
     */
    public void setFrameCount(double frameCount) {
        this.frameCount = frameCount;
    }

    /**
     * Gets frame count.
     *
     * @return the frame count
     */
    public double getFrameCount() {
        return frameCount;
    }

    @Override
    public void update(Input input) {
        if (finished){
            return;
        }

        if (planeNum % 2 != 0) {
            Point current = getCenter();
            Vector2 start = startH.asVector();
            Vector2 target = endH.asVector();
            Vector2 distance = target.sub(start);
            double length = distance.length();

            if ((SPEED * frameCount) / FPS  > 2) {
                frameCount = 0;
                explosives.add(new Explosive(EXPLOSIVE, current));
            }

            if (current.x >= endH.x){
                finished = true;
            }
            super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
            setAngle(1.57);
            super.update(input);
        }
        else {
            Point current = getCenter();
            Vector2 start = startV.asVector();
            Vector2 target = endV.asVector();
            Vector2 distance = target.sub(start);
            double length = distance.length();

            if ((SPEED * frameCount) / FPS  > 2) {
                frameCount = 0;
                explosives.add(new Explosive(EXPLOSIVE, current));
            }

            if (current.y >= endV.y){
                finished = true;
            }
            super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
            setAngle(3.14);
            super.update(input);
        }

        for (int i = explosives.size() - 1; i >= 0; i--){
            Explosive e = explosives.get(i);
            e.update(input);
            if (e.isActive()){
                for (int j = ShadowDefend.getSlicers().size() - 1; j>=0; j--){
                    Slicer s = ShadowDefend.getSlicers().get(j);
                    if (slicerInRange(s.getCenter())){
                        s.setHealth(s.getHealth() - damage);
                    }
                }
            }
        }
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
     * Slicer in range boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean slicerInRange(Point point){
        return (point.distanceTo(getCenter()) <= radius);
    }
}
