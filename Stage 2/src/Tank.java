import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class Tank extends Towers {
    private static final Image TANK_IMAGE = new Image("res/images/tank.png");
    private static double damage = 1;
    private static double radius = 100;
    private static double coolDown = 1000;
    private static double cost = 300;
    private static final Image projectile = new Image("res/images/tank_projectile.png");
    private Point aim;
    private Slicer aimSlicer;
    private int slicerNum;
    private boolean inRange;

    public Tank(Point point){
        super(point, TANK_IMAGE, damage, radius, coolDown, cost);
        this.aim = new Point(0, 0);
        this.slicerNum = 0;
        this.inRange = false;
    }

    public boolean isInRange() {
        return inRange;
    }

    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }

    public int getSlicerNum() {
        return slicerNum;
    }

    public void setSlicerNum(int slicerNum) {
        this.slicerNum = slicerNum;
    }

    public Slicer getAimSlicer() {
        return aimSlicer;
    }

    public void setAimSlicer(Slicer aimSlicer) {
        this.aimSlicer = aimSlicer;
    }

    public boolean slicerInRange(Point point){
        if (point.distanceTo(getLocation()) <= radius){
            return true;
        }
        return false;
    }

    @Override
    public void update(Input input){
        setAngle(getAngle());
        super.update(input);
    }

    public void setTarget(Slicer s){
        aim = s.getCenter();
    }

    public Point getAim() {
        return aim;
    }

    public void setAim(Point aim) {
        this.aim = aim;
    }
}
