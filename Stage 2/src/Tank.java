import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import java.util.ArrayList;

/**
 * The type Tank.
 */
public class Tank extends Towers {
    private static Image tankImage = new Image("res/images/tank.png");
    private static double damage = 1;
    private static double radius = 100;
    private static double coolDown = 1;
    private static double cost = 300;
    private String projectile = ("res/images/tank_projectile.png");
    private Slicer aimSlicer;
    private boolean inRange;
    private ArrayList<Projectile> projectiles;
    private int frameCount = 0;

    /**
     * Instantiates a new Tank.
     *
     * @param point the point
     */
    public Tank(Point point){
        super(point, tankImage, damage, radius, coolDown, cost);
        this.inRange = false;
        this.projectiles = new ArrayList<>();
        this.inRange = false;
    }

    /**
     * Instantiates a new Tank.
     */
    public Tank(Point point, Image tankImage, String projectile, double damage, double radius, double coolDown, double cost){
        super(point, tankImage, damage, radius, coolDown, cost);
        this.projectile = projectile;
        this.inRange = false;
        this.projectiles = new ArrayList<>();
        this.inRange = false;
    }

    /**
     * Is in range boolean.
     *
     * @return the boolean
     */
    public boolean isInRange() {
        return inRange;
    }

    /**
     * Sets in range.
     *
     * @param inRange the in range
     */
    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }

    /**
     * Gets aim slicer.
     *
     * @return the aim slicer
     */
    public Slicer getAimSlicer() {
        return aimSlicer;
    }

    /**
     * Slicer in range boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean slicerInRange(Point point){
        return (point.distanceTo(getLocation()) <= radius);
    }

    @Override
    public void update(Input input){
        frameCount++;

        // Sets the aim slicer for the tank
        for (int a = ShadowDefend.getSlicers().size() - 1 ; a>=0; a--){
            Slicer s = ShadowDefend.getSlicers().get(a);
            if (slicerInRange(s.getCenter())){
                aimSlicer = s;
                inRange = true;
            }
            // Launches a projectile when the cool down period is over for the tank
            if (inRange && ((ShadowDefend.getTimescale() * frameCount)/ ShadowDefend.FPS) >= coolDown){
                projectiles.add(new Projectile(getCenter(), s, projectile));
                frameCount = 0;
            }

            // Handles the projectiles launched and removed after they hit the slicer
            for (int i = projectiles.size() - 1; i>=0; i--){
                Projectile p = projectiles.get(i);
                // This is when slicer gets hit
                if (p.getRect().intersects(s.getCenter()) && inRange){ // Get the point where it dies and check which slicer died
                    p.setActive(false);
                    inRange = false;
                    s.setHealth(s.getHealth() - 1);
                    frameCount = 0;
                    projectiles.remove(p);
                }
                p.setAngle(getAngle());
                p.update(input);
            }
        }
        super.update(input);
    }
}
