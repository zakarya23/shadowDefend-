import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class SuperTank extends Towers {
    private static final Image TANK_IMAGE = new Image("res/images/supertank.png");
    private static double damage = 3;
    private static double radius = 150;
    private static double coolDown = 500;
    private static double cost = 300;
    private static final Image projectile = new Image("res/images/supertank_projectile.png");

    public SuperTank(Point point) {
        super(point, TANK_IMAGE, damage, radius, coolDown, cost);
    }

    @Override
    public void update(Input input){
        super.update(input);
    }
}
