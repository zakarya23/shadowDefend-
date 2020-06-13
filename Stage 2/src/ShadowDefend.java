import bagel.*;
import bagel.map.TiledMap;
import bagel.util.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * ShadowDefend, a tower defence game.
 */
public class ShadowDefend extends AbstractGame {

    /**
     * The Wave file.
     */
    // Map related
    public String waveFile = "res/levels/waves.txt";        // File to be read for the waves
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private String mapFile = "res/levels/1.tmx";            // Level to start off with
    private TiledMap map;                                   // Variable that stores the map to be loaded

    // All tower related variables
    private List<Tank> tanks;                               // Holds all tanks that are placed and updates them
    private List<AirPlane> planes;                          // Holds all planes placed and updates them
    private boolean tank = false;                           // Checks if tank was selected
    private boolean superTank = false;                      // Checks if super tank was selected
    private boolean plane = false;                          // Checks if plane was selected
    private int planeNum = 1;                               // Stores what number plane is about to fly
    private boolean tankWasPlaced = false;                  // Checks if tank was placed
    private boolean superTankPlaced = false;                // Checks if super tank was placed
    private boolean planePlaced = false;                    // Checks if plane was placed
    private final double NINETY = 1.57;                     // An angle adjustment for tanks

    // All wave related variables
    private static Wave w;                                  // The wave that stores the wave details
    private SpawnEvent se;                                  // Details of what the spawn event is
    private boolean delayTime = false;                      // If its the delay time
    private static Player player;                           // Player class for handling player related things
    private static boolean waveStarted;                     // If the next wave is started
    private static boolean towerPlacing;                    // If player is placing a tower
    private int waveNum;                                    // Stores wave number of current wave
    private static boolean waveEnd;                         // Whether a wave ended or not
    private int levelNum = 1;                               // Which level the player is currently on
    private static final int INITIAL_TIMESCALE = 1;
    private static final int MAX_TIMESCALE = 5;
    private static int timescale = INITIAL_TIMESCALE;
    private final int CONVERSION = 1000;
    private final int START_POINT = 0;
    private boolean nextLevel;
    /**
     * The constant FPS.
     */
    public static final double FPS = 60;
    private static double frameCount;

    // All slicer related variables
    private static List<Point> polyline;                    // The path taken by slicers
    private static List<Slicer> slicers;                    // Stores all the slicers

    // All panels related
    private StateOfGame gameState;
    private final int topPanel = 120;
    private final int bottomPanel = 720;
    private final int TANK_COST = 250;
    private final int SUPER_TANK_COST = 600;
    private final int PLANE_COST = 500;


    /**
     * Creates a new instance of the ShadowDefend game
     */
    public ShadowDefend() {
        super(WIDTH, HEIGHT, "ShadowDefend");
        this.map = new TiledMap(mapFile);
        polyline = map.getAllPolylines().get(0);
        slicers = new ArrayList<>();
        this.tanks = new ArrayList<>();
        this.planes = new ArrayList<>();
        waveStarted = false;
        frameCount = Integer.MAX_VALUE;
        player = new Player();
        w = new Wave();
        se = w.loadWave();
        waveNum = se.getWaveNum();
        waveEnd = false;
        nextLevel = false;

        // Loads the first wave
        if (se.getWaveType().equals("spawn")){
            spawnWave();
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new ShadowDefend().run();
    }

    /**
     * Update the state of the game, potentially reading from input
     *
     * @param input The current mouse/keyboard state
     */
    @Override
    protected void update(Input input) {

        // If the wave event is a delay, then this waits for the delay time shown.
        if (delayTime){
            if (frameCount/ FPS >= w.getDelay()){
                frameCount = 0;
                waveStarted = true;
                delayTime = false;
                changeWave();
            }
        }

        // Increase the frame counter by the current timescale
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

        // Want to deselect the item that was selected
        if (input.wasPressed(MouseButtons.RIGHT)){
            deselect();
        }

        // Mouse was let go or wasn't clicked yet
        if (input.isUp(MouseButtons.LEFT)){
            Point mousePoint = new Point(input.getMouseX(), input.getMouseY());     // Current location of mouse

            boolean notOnPanel = (mousePoint.y > topPanel) && (mousePoint.y < bottomPanel);

            // If tank was selected in panel
            if (tank && validLocation(mousePoint)){
                // Draws a tank at the Mouse current location
                gameState.buyPanel.getTank().getImage().draw(mousePoint.x, mousePoint.y);
                tankWasPlaced = true;
            }

            // If super tank was selected in panel
            if (superTank && validLocation(mousePoint)){
                // Draws a super tank at the Mouse current location
                gameState.buyPanel.getSuperTank().getImage().draw(mousePoint.x, mousePoint.y);
                superTankPlaced = true;
            }

            // If plane was selected in panel
            if (plane && notOnPanel){
                // Draws a plane at the Mouse current location
                gameState.buyPanel.getAirplane().getImage().draw(mousePoint.x, mousePoint.y);
                planePlaced = true;
            }
        }

        if (input.wasPressed(MouseButtons.LEFT)){
            Point mousePoint = new Point(input.getMouseX(), input.getMouseY());
            boolean notOnPanel = (mousePoint.y > topPanel) && (mousePoint.y < bottomPanel);

            // If mouse is hovering over the tank on buy panel
            if (gameState.buyPanel.getTank().getArea().intersects(mousePoint)){
                tank = true;
                towerPlacing = true;
            }

            // If mouse is hovering over the super tank on buy panel
            if (gameState.buyPanel.getSuperTank().getArea().intersects(mousePoint)){
                superTank = true;
                towerPlacing = true;
            }

            // If mouse is hovering over the airplane on buy panel
            if (gameState.buyPanel.getAirplane().getArea().intersects(mousePoint)){
                plane = true;
                towerPlacing = true;
            }

            // These are for the 2nd left click when the player wants to place the tower
            if (tankWasPlaced && player.getCash() >= TANK_COST && validLocation(mousePoint)){
                player.setCash(player.getCash() - TANK_COST);
                tanks.add(new Tank(mousePoint));
                tank = false;
                tankWasPlaced = false;
                towerPlacing = false;
            }

            if (superTankPlaced && player.getCash() >= SUPER_TANK_COST && validLocation(mousePoint)) {
                player.setCash(player.getCash() - SUPER_TANK_COST);
                tanks.add(new SuperTank(mousePoint));
                superTank = false;
                superTankPlaced = false;
                towerPlacing = false;
            }

            if (planePlaced && player.getCash() >= PLANE_COST && notOnPanel) {
                player.setCash(player.getCash() - PLANE_COST);
                planes.add(new AirPlane(mousePoint, planeNum, frameCount));
                planeNum++;
                planePlaced = false;
                plane = false;
                towerPlacing = false;
            }
        }

        // Check if it is time to spawn a new slicer
        if (waveStarted && (frameCount / FPS >= w.getDelay()) && (w.getSlicersSpawned() != w.getSlicerCount())) {
            String enemy = se.getEnemyType();
            if (enemy.equals("slicer")) {
                slicers.add(new Slicer(polyline));
            }
            if (enemy.equals("superslicer")) {
                slicers.add(new SuperSlicer(polyline, START_POINT));
            }
            if (enemy.equals("megaslicer")) {
                slicers.add(new MegaSlicer(polyline, START_POINT));
            }
            if (enemy.equals("apexslicer")) {
                slicers.add(new ApexSlicer(polyline, START_POINT));
            }
            w.setSlicersSpawned(w.getSlicersSpawned() + 1);

            // Reset frame counter
            frameCount = 0;
        }

       // Changes the wave number after ended and if all waves ended then loads next level
        if (w.getSlicersSpawned() == w.getSlicerCount() && slicers.isEmpty()) {
            // One level ended so loads next level
            if (w.getWaveDetail().size() - 1 == w.getLineNum()){
                levelNum++;
                loadNextLevel(levelNum);
                nextLevel = true;
            }

            // If one level ended this loads the next level
            if (nextLevel){
                waveEnd = false;
                nextLevel = false;
                w = new Wave();
                w.setLineNum(-1);
            }

            // While waves are going this part keeps updating
            if (!waveEnd) {
                w.setLineNum(w.getLineNum() + 1);
                se = w.loadWave();
                // Wave ended so player is awarded cash
                if (se.getWaveNum() > waveNum) {
                    waveStarted = false;
                    player.setCash(player.getCash() + 150 + (waveNum * 100));
                    waveNum++;
                }

                if (se.getWaveType().equals("spawn")) {
                    spawnWave();
                }
                else {
                    // Handles the delay events
                    w.setDelay(se.getDelay() / CONVERSION);
                    delayTime = true;
                    waveStarted = false;
                }
            }
        }

        // Spawns all the planes that were placed
        for (int a = planes.size() - 1; a >= 0; a--){
            AirPlane ap = planes.get(a);
            ap.setFrameCount(ap.getFrameCount() + 1);
            ap.update(input);
            if (ap.isFinished()){
                planes.remove(a);
            }
        }

        // Update all sprites, and remove them if they've finished
        for (int i = slicers.size() - 1; i >= 0; i--) {
            Slicer s = slicers.get(i);
            s.update(input);
            if (s.isGotEnd()){
                player.setLives((int) (player.getLives() - s.getPenalty()));
                // Player loses the game
                if (player.getLives() <= 0){
                    Window.close();
                }
            }
            if (s.isFinished()) {
                slicers.remove(i);
            }
        }

        // Updates all tanks that were placed
        for (int j= tanks.size() - 1; j >= 0; j--){
            Tank t = tanks.get(j);
            // Sets the aim slicer and the direction that the tank is pointing
            if (waveStarted && t.isInRange()){
                double angle = Math.atan2((t.getCenter().y - t.getAimSlicer().getCenter().y), (t.getCenter().x - t.getAimSlicer().getCenter().x));
                t.setAngle(angle - NINETY);
                t.setInRange(false);
            }
            t.update(input);
        }
    }


    /**
     * Gets wave.
     *
     * @return the wave
     */
    // Returns details of the wave
    public static Wave getWave() {
        return w;
    }

    /**
     *  Returns the details from the player class
      */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Gets timescale.
     *
     * @return the timescale
     */
    public static int getTimescale() {
        return timescale;
    }

    // Increases the timescale
    private void increaseTimescale() {
            if (timescale < MAX_TIMESCALE) {
            timescale++;
        }
    }

    // Decreases the timescale but doesn't go below the base timescale
    private void decreaseTimescale() {
        if (timescale > INITIAL_TIMESCALE) {
            timescale--;
        }
    }

    /**
     * Loads the next wave once one finishes.
     */
    private void changeWave() {
        w.setLineNum(w.getLineNum() + 1);
        w.setSlicersSpawned(0);
        se = w.loadWave();
        w.setWaveNum(se.getWaveNum());
        w.setDelay(se.getDelay() / CONVERSION);
        w.setSlicerCount(se.getSpawnNum());
        w.setSlicerType(se.getEnemyType());
    }

    /**
     * Gets slicers.
     *
     * @return the slicers
     */
    public static List<Slicer> getSlicers() {
        return slicers;
    }

    /**
     * Gets polyline.
     *
     * @return the polyline
     */
    public static List<Point> getPolyline() {
        return polyline;
    }

    /**
     * Is wave started boolean.
     *
     * @return the boolean
     */
    public static boolean isWaveStarted() {
        return waveStarted;
    }

    /**
     * Is tower placing boolean.
     *
     * @return the boolean
     */
    public static boolean isTowerPlacing() {
        return towerPlacing;
    }

    /**
     * Is wave end boolean.
     *
     * @return the boolean
     */
    public static boolean isWaveEnd() {
        return waveEnd;
    }

    /**
     * Load next level.
     *
     * @param levelNum the level num
     */
    // If one level ends then this loads the next one
    public void loadNextLevel(int levelNum) {
        waveEnd = true;
        mapFile = String.format("res/levels/%s.tmx", levelNum);
        this.map = new TiledMap(mapFile);
        polyline = map.getAllPolylines().get(0);
        slicers = new ArrayList<>();
        this.tanks = new ArrayList<>();
        this.planes = new ArrayList<>();
        waveStarted = false;
        frameCount = Integer.MAX_VALUE;
        player = new Player();
        w = new Wave();
        se = w.loadWave();
        waveNum = se.getWaveNum();
        w.setLineNum(1);
        if (se.getWaveType().equals("spawn")){
            spawnWave();
        }
    }

    /**
     * Some tower was placed so we need to make sure everything else is set to false
     */
    private void deselect() {
        tank = false;
        superTank = false;
        plane = false;
        tankWasPlaced = false;
        superTankPlaced = false;
        planePlaced = false;
    }

    /**
     * Checks whether the place to put the tower is valid
     * @param mousePoint
     * @return
     */
    private boolean validLocation(Point mousePoint){
        boolean canBeDrawn = map.getPropertyBoolean((int) mousePoint.x,
                (int) mousePoint.y, "blocked", false);
        boolean notOnPanel = (mousePoint.y > topPanel) && (mousePoint.y < bottomPanel);
        boolean finalOut = false;

        if (!canBeDrawn && notOnPanel){
            finalOut = true;
        }

        // Iterates over each tank to see if it doesn't collide with any other tanks
        for (Tank tank : tanks){
            if (tank.getRect().intersects(mousePoint)){
                finalOut = false;
            }
        }
        return finalOut;
    }

    /**
     * Spawns a wave
     */
    private void spawnWave() {
        w.setWaveNum(se.getWaveNum());
        w.setDelay(se.getDelay() / CONVERSION);
        w.setSlicersSpawned(0);
        w.setSlicerCount(se.getSpawnNum());
        w.setSlicerType(se.getEnemyType());
    }
}
