import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;

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


    public void setTimeScale(double timeScale) {
        this.timeScale = timeScale;
    }

    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public StatusPanel() {
        render();
    }

    public void render() {
        background.draw(bgLoc.x, bgLoc.y);
        loadWaveNum();
        loadTimeScale();
        loadStatus();
        loadLives();
    }

    public void loadStatus() {
        text.drawString("Status: ",400, textY);
        text.drawString("Wave in progress",450, textY);
    }

    public void loadWaveNum() {
        text.drawString("Wave: ",10, textY);
        String waveNo = Integer.toString(waveNum);
        text.drawString(waveNo,50, textY);
    }

    public void loadTimeScale(){
        String ts = Double.toString(timeScale);
        if (timeScale > 1) {
            text.drawString("Time Scale: ",150, textY, textColour.setBlendColour(colour.GREEN));
            text.drawString(ts, 220, textY, textColour.setBlendColour(colour.GREEN));
        }
        else{
            text.drawString("Time Scale: ",150, textY, textColour.setBlendColour(colour.WHITE));
            text.drawString(ts, 220, textY, textColour.setBlendColour(colour.WHITE));
        }
    }

    public void loadLives() {
        text.drawString("Lives: ",1024 - 90, textY);
        String live = Integer.toString(lives);
        text.drawString(live, 1024 - 40, textY);
    }
}
