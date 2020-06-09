import bagel.Image;
import bagel.Input;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tank extends Towers {
    private static final Image TANK_IMAGE = new Image("res/images/tank.png");
    private static double damage = 1;
    private static double radius = 100;
    private static double coolDown = 1000;
    private static double cost = 300;
    private static final Image PROJECTILE = new Image("res/images/tank_projectile.png");
    private Point aim;
    private Slicer aimSlicer;
    private int slicerNum;
    private boolean inRange;
    private double projectileSpeed;
    private Point slicerLoc;
/*    private ArrayList<Projectile> projectiles;*/
    private int frameCount;

    public Tank(Point point, int frameCount){
        super(point, TANK_IMAGE, damage, radius, coolDown, cost);
        this.aim = new Point(0, 0);
        this.slicerNum = 0;
        this.inRange = false;
        this.projectileSpeed = 10;
 /*       this.projectiles = new ArrayList<>();*/
        this.frameCount = frameCount;
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
        return point.distanceTo(getLocation()) <= radius;
    }

    public Point getSlicerLoc() {
        return slicerLoc;
    }

    public void setSlicerLoc(Point slicerLoc) {
        this.slicerLoc = slicerLoc;
    }

    @Override
    public void update(Input input){
        setAngle(getAngle());

       /* if (inRange){
            projectiles.add(new Projectile(PROJECTILE, getLocation(), slicerLoc));
        }

        for (int i = projectiles.size() - 1; i>=0; i--){
            Projectile p = projectiles.get(i);
            p.setAngle(getAngle());
            System.out.println(p.getAngle());
            p.update(input);
        }*/

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
