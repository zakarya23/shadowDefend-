import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * ShadowDefend, a tower defence game.
 */
public class ShadowDefend extends AbstractGame {

    public String waveFile = "res/levels/waves.txt";

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    private static final String MAP_FILE = "res/levels/1.tmx";

    // Change to suit system specifications. This could be
    // dynamically determined but that is out of scope.
    public static final double FPS = 60;
    // The spawn delay (in seconds) to spawn slicers
    private static final int INTIAL_TIMESCALE = 1;
    // Timescale is made static because it is a universal property of the game and the specification
    // says everything in the game is affected by this
    private static int timescale = INTIAL_TIMESCALE;
    private final TiledMap map;
    private double frameCount;
    private StateOfGame gameState;
    private boolean tankWasPlaced = false;
    private ArrayList<Point> tankLoc = new ArrayList<>();
    private boolean state = true;
    private boolean inRange = false;

    // All tower related variables
    private final List<Tank> tanks;
    private final List<SuperTank> superTanks;
    private final List<AirPlane> planes;
    private boolean tank = false;
    private boolean superTank = false;
    private boolean plane = false;

    // All wave related variables
    private static Wave w;
    private SpawnEvent se;
    private boolean delayTime = false;
    private static Player player;
    private boolean waveStarted;

    // All slicer related variables
    private final List<Point> polyline;
    private final List<Slicer> slicers;
    private Point slicerPos;
    private int slicerNum;

    /**
     * Creates a new instance of the ShadowDefend game
     */
    public ShadowDefend() {
        super(WIDTH, HEIGHT, "ShadowDefend");
        this.map = new TiledMap(MAP_FILE);
        this.polyline = map.getAllPolylines().get(0);
        this.slicers = new ArrayList<>();
        this.tanks = new ArrayList<>();
        this.superTanks = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.waveStarted = false;
        this.frameCount = Integer.MAX_VALUE;

        new Slicer(polyline, "res/images/megaslicer.png");
        player = new Player();
        slicerPos = new Point(0, 0);
        slicerNum = 0;
        w = new Wave();
        se = w.loadWave();

        if (se.getWaveType().equals("spawn")){
            w.setWaveNum(se.getWaveNum());
            w.setDelay(se.getDelay() / 1000);
            w.setSlicersSpawned(0);
            w.setSlicerCount(se.getSpawnNum());
            w.setSlicerType(se.getEnemyType());
        }
    }

    public static void main(String[] args) {
        new ShadowDefend().run();
    }

    // Returns details of the wave
    public static Wave getWave() {
        return w;
    }

    // Returns the details from the player class
    public static Player getPlayer() {
        return player;
    }

    public static int getTimescale() {
        return timescale;
    }

    // Increases the timescale
    private void increaseTimescale() {
        timescale++;
    }

    // Decreases the timescale but doesn't go below the base timescale
    private void decreaseTimescale() {
        if (timescale > INTIAL_TIMESCALE) {
            timescale--;
        }
    }

    private void drawTank(Point point) {
        gameState.buyPanel.getTank().getImage().draw(point.x, point.y);
    }

    private void changeWave() {
        w.setLineNum(w.getLineNum() + 1);
        w.setSlicersSpawned(0);
        se = w.loadWave();
        w.setWaveNum(se.getWaveNum());
        w.setDelay(se.getDelay() / 1000);
        w.setSlicerCount(se.getSpawnNum());
        w.setSlicerType(se.getEnemyType());
    }

    /**
     * Update the state of the game, potentially reading from input
     *
     * @param input The current mouse/keyboard state
     */
    @Override
    protected void update(Input input) {
        // Need to draw tank in here cos update removes it

        // Increase the frame counter by the current timescale
        if (delayTime){ ;
            if (frameCount/ FPS >= w.getDelay()){
                frameCount = 0;
                waveStarted = true;
                delayTime = false;
                changeWave();
            }
        }

        frameCount += getTimescale();

        // Draw map from the top left of the window
        map.draw(0, 0, 0, 0, WIDTH, HEIGHT);

        // Draws the panels and handles the updates to player and towers
        gameState = new StateOfGame();

        // Handle key presses
        if (input.wasPressed(Keys.S)) {
            waveStarted = true;
        }

        if (input.wasPressed(Keys.L)) {
            increaseTimescale();
        }

        if (input.wasPressed(Keys.K)) {
            decreaseTimescale();
        }

        if (input.isUp(MouseButtons.LEFT)){
            double xPos = input.getMouseX();
            double yPos = input.getMouseY();
            Point mousePoint = new Point(xPos, yPos);
            boolean canBeDrawn = map.getPropertyBoolean((int) xPos, (int) yPos, "blocked", false);

            if (tank){
                if (player.getCash() >= 250 && !canBeDrawn) {
                    player.setCash(player.getCash() - 250);
                    tanks.add(new Tank(mousePoint));
                }
            }

            if (superTank){
                if (player.getCash() >= 600 && !canBeDrawn) {
                    player.setCash(player.getCash() - 600);
                    superTanks.add(new SuperTank(mousePoint));
                }
            }

            if (plane){
                if (player.getCash() >= 500 && canBeDrawn) {
                    player.setCash(player.getCash() - 500);
                    planes.add(new AirPlane(mousePoint));
                }
            }
            tank = false;
            superTank = false;
            plane = false;
        }

        if (input.isDown(MouseButtons.LEFT)){
            double xPos = input.getMouseX();
            double yPos = input.getMouseY();
            Point mousePoint = new Point(xPos, yPos);

            // If mouse is hovering over the airplane on buy panel
            if (gameState.buyPanel.getAirplane().getArea().intersects(mousePoint)){
                plane = true;
            }

            if (plane){
                gameState.buyPanel.getAirplane().getImage().draw(xPos, yPos);
            }

            // If mouse is hovering over the tank on buy panel
            if (gameState.buyPanel.getTank().getArea().intersects(mousePoint)){
                tank = true;
                tankWasPlaced = true;
            }

            if (tank){
                gameState.buyPanel.getTank().getImage().draw(xPos, yPos);
            }

            // If mouse is hovering over the super tank on buy panel
            if (gameState.buyPanel.getSuperTank().getArea().intersects(mousePoint)){
                superTank = true;
            }

            if (superTank){
                gameState.buyPanel.getSuperTank().getImage().draw(xPos, yPos);
            }
        }


        // Check if it is time to spawn a new slicer (and we have some left to spawn)
        if (waveStarted && (frameCount / FPS >= w.getDelay()) && (w.getSlicersSpawned() != w.getSlicerCount())) {
            if (se.getEnemyType().equals("slicer")) {
                slicers.add(new Slicer(polyline, "res/images/slicer.png"));
            }
            if (se.getEnemyType().equals("superslicer")) {
                slicers.add(new Slicer(polyline, "res/images/superslicer.png"));
            }
            if (se.getEnemyType().equals("megaslicer")) {
                slicers.add(new Slicer(polyline, "res/images/megaslicer.png"));
            }
            if (se.getEnemyType().equals("apexslicer")) {
                slicers.add(new Slicer(polyline, "res/images/apexslicer.png"));
            }
            w.setSlicersSpawned(w.getSlicersSpawned() + 1);
            // Reset frame counter
            frameCount = 0;
        }

        // Close game if all slicers have finished traversing the polyline
        if (w.getSlicersSpawned() == w.getSlicerCount() && slicers.isEmpty()) {

            w.setLineNum(w.getLineNum() + 1);
            w.setSlicersSpawned(0);
            se = w.loadWave();
            if (se.getWaveType().equals("spawn")) {
                w.setWaveNum(se.getWaveNum());
                w.setDelay(se.getDelay() / 1000);
                w.setSlicerCount(se.getSpawnNum());
                w.setSlicerType(se.getEnemyType());
            }
            else {
                w.setDelay(se.getDelay() / 1000);
                delayTime = true;
                waveStarted = false;
                /*Window.close();*/
            }
        }

        for (int k = superTanks.size() - 1; k >= 0; k--){
            SuperTank st = superTanks.get(k);
            st.update(input);
        }

        for (int a = planes.size() - 1; a >= 0; a--){
            AirPlane ap = planes.get(a);
            ap.update(input);
        }

        // Update all sprites, and remove them if they've finished
        for (int i = slicers.size() - 1; i >= 0; i--) {
            Slicer s = slicers.get(i);
            slicerPos = new Point(s.getCenter().x, s.getCenter().y);
            /*gameState.setSlicerPosition(slicers.get(i).getCenter());*/
            if (inRange) {
                /*gameState.setSlicerNum(i);*/
                slicerNum = i;
                gameState.setSlicerPosition(slicers.get(i).getCenter());
                inRange = false;
            }
            s.update(input);
            if (s.isFinished()) {
                slicers.remove(i);
            }
        }

        // Doesn't update
        for (int j= tanks.size() - 1; j >= 0; j--){
            Tank t = tanks.get(j);
            Point p1 = new Point(t.getCenter().x, t.getCenter().y);
            Point p2 = new Point(gameState.getSlicerPosition().x, gameState.getSlicerPosition().y);
            Point p;
            Point p22;
            if (waveStarted) {
                Slicer ass = slicers.get(slicerNum/*gameState.getSlicerNum()*/);
                p = new Point(ass.getCenter().x, ass.getCenter().y);
            }
            if (tanks.get(j).slicerInRange(slicerPos)){
                Slicer a = slicers.get(slicerNum/*gameState.getSlicerNum()*/);
                inRange = true;
                p22 = new Point(a.getCenter().x, a.getCenter().y);
                t.setAngle(Math.atan2(t.getCenter().y - gameState.getSlicerPosition().y, t.getCenter().x - gameState.getSlicerPosition().x));
            }
            t.update(input);
        }
    }
}
