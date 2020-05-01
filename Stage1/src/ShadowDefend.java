import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;
import bagel.util.Vector2;
import java.util.ArrayList;
import java.util.Collections;


public class ShadowDefend extends AbstractGame {
    private final TiledMap map;
    private double x = 30;
    private double y = 300;
    private final Image slicer;
    boolean sWasPressed = false;
    ArrayList<Point> route = new ArrayList<>();
    boolean needsRotation = false;
    private static final String BLOCKED_PROPERTY = "blocked";
    private Point lastPoint;
    boolean pointsCollected = true;
    private int playerIndex = 0;
    private ArrayList<Point> turningPoints = new ArrayList<>();
    private DrawOptions turn;
    private int turnIndex = 0;
    boolean firstTurn = false;
    private double speed = 7;
    boolean increase = false;
    boolean decrease = false;

    public static void main(String[] args) {
        // Create new instance of game and run it
        new ShadowDefend().run();
    }


    public ShadowDefend(){
        // Constructor
        turn = new DrawOptions();
        slicer = new Image("res/images/slicer.png");
        map = new TiledMap("res/levels/1.tmx");
    }


    @Override
    protected void update(Input input) {

        ArrayList<Point> points = new ArrayList<>();
        boolean toDraw = true;

        map.draw(0, 0, 0, 0, Window.getWidth(), Window.getHeight());

        if (pointsCollected) {
            for (Point point : map.getAllPolylines().get(0)) {
                // Access each Point of the polyline here
                Collections.addAll(points, point);
            }

            route.add(points.get(0));
            for (int i = 0; i < points.size() - 1; i++) {
                Point startPoint = points.get(i);
                Point destination = points.get(i+1);
                lastPoint = points.get(points.size() - 1);

                if (destination.x == startPoint.x || destination.y == startPoint.y){
                    Collections.addAll(turningPoints, startPoint);
                }

                Vector2 point1 = new Vector2(startPoint.x, startPoint.y);
                Vector2 point2 = new Vector2(destination.x, destination.y);
                double distanceToGoal = point2.sub(point1).length();
                double disCovered = 0;

                while (disCovered <= distanceToGoal && !lastPoint.equals(destination)) {
                    if (x < destination.x && startPoint.x < destination.x) {
                        x += speed;
                    }
                    else if (x >= destination.x && startPoint.x >= destination.x) {
                        x -= speed;
                    }

                    if (y >= destination.y && startPoint.y >= destination.y) {
                        y -= speed;
                    }
                    else if (y < destination.y && startPoint.y < destination.y) {
                        y += speed;
                    }



                    Vector2 dis = new Vector2(x, y);
                    disCovered = dis.sub(point1).length();
                    Point movement = new Point(x, y);

                    route.add(movement);
                }

                needsRotation = true;
            }
            pointsCollected = false;
        }

        if (sWasPressed) {

            if (increase){
                speed+=1;
            }
            else if (decrease){
                speed-=1;

            }
                if (!route.isEmpty()) {
                    Point slicerFuture = new Point();
                    Point slicerPosition = new Point(route.get(playerIndex).x, route.get(playerIndex).y);

                    if (slicerPosition.x > turningPoints.get(turnIndex).x && slicerPosition.x > turningPoints.get(turnIndex).y){
                        firstTurn = true;
                    }
                   if (playerIndex < route.size() - 1 ) {
                       Point future = new Point(route.get(playerIndex + 1).x, route.get(playerIndex + 1).y);
                       slicerFuture = future;
                   }


                   /*Point curve = new Point(turningPoints.get(turnIndex).x, turningPoints.get(turnIndex).y);*//*
                   System.out.println("x" + slicerPosition.x);*/
            if (firstTurn) {
                if (slicerPosition.x == slicerFuture.x && slicerPosition.y > slicerFuture.y) {
                    slicer.draw(slicerPosition.x, slicerPosition.y, turn.setRotation((3 * 3.14) / 2));
                    System.out.println("a");
                } else if (slicerPosition.x == slicerFuture.x && slicerPosition.y < slicerFuture.y) {
                    slicer.draw(slicerPosition.x, slicerPosition.y, turn.setRotation((3.14) / 2));
                    System.out.println("b");
                } else if (slicerPosition.x > slicerFuture.x && slicerPosition.y == slicerFuture.y) {
                    slicer.draw(slicerPosition.x, slicerPosition.y, turn.setRotation(3.14));
                    System.out.println("c");
                } else if (slicerPosition.x < slicerFuture.x && slicerPosition.y == slicerFuture.y) {
                    slicer.draw(slicerPosition.x, slicerPosition.y, turn.setRotation((2* 3.14)));
                    System.out.println("d");
                } else {
                    slicer.draw(slicerPosition.x, slicerPosition.y);
                }

                playerIndex++;
                if (playerIndex == route.size()) {
                    playerIndex = 0;
                }
            }
            else {
                slicer.draw(slicerPosition.x, slicerPosition.y);
                playerIndex++;
            }
                }
        }


        if (input.wasPressed(Keys.S)){
            sWasPressed = true;
        }
        if (input.wasPressed(Keys.L)){
            increase = true;
            decrease = false;
        }
        if (input.wasPressed(Keys.K)){
            decrease = true;
            increase = false;
        }


        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
    }
}
