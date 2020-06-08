import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

public class AirPlane extends spawnAirplane{
    private static final Image PLANE_IMAGE = new Image("res/images/airsupport.png");
    private static final Image EXPLOSIVE = new Image("res/images/explosive.png");
    private static int damage = 500;
    private static int radius = 200;
    private static int cost = 500;
    private static final double SPEED = 5;
    private int x = 0;
    private Point startH;
    private Point startV;
    private Point endH;
    private Point endV;

    public AirPlane(Point point){
        super(point, PLANE_IMAGE);
        startH = new Point(x, point.y);
        startV = new Point(point.x, 0);
        endH = new Point(1024, point.y);
        endV = new Point(point.x, 768);
    }


    @Override
    public void update(Input input) {
        Vector2 start = startH.asVector();
        Vector2 target = endH.asVector();
        Vector2 distance = target.sub(start);
        double length = distance.length();

        if (length < SPEED){
            x++;
        }

        super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
        super.update(input);
    }


}
