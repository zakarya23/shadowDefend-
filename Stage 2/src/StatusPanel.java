import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;

/**
 * The type Status panel.
 */
public class StatusPanel {
    private Image background = new Image("res/images/statuspanel.png");
    private Font text = new Font("res/fonts/DejaVuSans-Bold.ttf", 12);
    private int textY = 760;
    private int waveNum;
    private double timeScale;
    private int lives;
    private DrawOptions textColour = new DrawOptions();
    private Colour colour;
    private Point bgLoc = new Point(1024/2, 758);
    private final int statusX = 450;
    private final int timeScaleX = 150;
    private final int liveX = 934;



    /**
     * Sets time scale.
     *
     * @param timeScale the time scale
     */
    public void setTimeScale(double timeScale) {
        this.timeScale = timeScale;
    }

    /**
     * Sets wave num.
     *
     * @param waveNum the wave num
     */
    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Instantiates a new Status panel.
     */
    public StatusPanel() {
        render();
    }

    /**
     * Render.
     */
    public void render() {
        background.draw(bgLoc.x, bgLoc.y);
        loadWaveNum();
        loadTimeScale();
        loadStatus();
        loadLives();
    }

    /**
     * Load status.
     */
    public void loadStatus() {
        text.drawString("Status: ",statusX - 50, textY);
        if (ShadowDefend.isWaveEnd()){
            text.drawString("Winner!", statusX, textY);
        }
        else if (ShadowDefend.isTowerPlacing()){
            text.drawString("Placing", statusX, textY);
        }
        else if (ShadowDefend.isWaveStarted()) {
            text.drawString("Wave in progress", statusX, textY);
        }
        else {
            text.drawString("Awaiting Start", statusX, textY);
        }
    }

    /**
     * Load wave num.
     */
    public void loadWaveNum() {
        text.drawString("Wave: ",10, textY);
        String waveNo = Integer.toString(waveNum);
        text.drawString(waveNo,50, textY);
    }

    /**
     * Loads time scale.
     */
    public void loadTimeScale(){
        String ts = Double.toString(timeScale);
        if (timeScale > 1) {
            text.drawString("Time Scale: ",timeScaleX, textY, textColour.setBlendColour(colour.GREEN));
            text.drawString(ts, timeScaleX + 70, textY, textColour.setBlendColour(colour.GREEN));
        }
        else{
            text.drawString("Time Scale: ",timeScaleX, textY, textColour.setBlendColour(colour.WHITE));
            text.drawString(ts, timeScaleX + 70 , textY, textColour.setBlendColour(colour.WHITE));
        }
    }

    /**
     * Loads lives.
     */
    public void loadLives() {
        text.drawString("Lives: ", liveX, textY);
        String live = Integer.toString(lives);
        text.drawString(live, liveX + 50, textY);
    }
}
