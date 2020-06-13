import bagel.Image;
import bagel.util.Point;

/**
 * The type Super tank.
 */
public class SuperTank extends Tank {
    private static final Image TANK_IMAGE = new Image("res/images/supertank.png");
    private static double damage = 3;
    private static double radius = 150;
    private static double coolDown = 0.5;
    private static double cost = 300;
    private static final String projectile = ("res/images/supertank_projectile.png");

    /**
     * Instantiates a new Super tank.
     *
     * @param point the point
     */
    public SuperTank(Point point){
        super(point, TANK_IMAGE, projectile, damage, radius, coolDown, cost);
    }
}
