import bagel.Image;
import bagel.Input;
import bagel.util.Point;

import java.util.Random;

/**
 * The type Explosive.
 */
public class Explosive {
    private boolean active;
    private int time;
    private Point explosiveLoc;
    private Image image;
    private int frameCount = 0 ;

    /**
     * Instantiates a new Explosive.
     *
     * @param image        the image
     * @param explosiveLoc the explosive loc
     */
    public Explosive(Image image, Point explosiveLoc) {
        this.image = image;
        this.explosiveLoc = explosiveLoc;
        this.active = true;
        Random rand = new Random();
        int max = 4;
        time = rand.nextInt(max);
    }

    /**
     * Update.
     *
     * @param input the input
     */
    public void update(Input input){
        frameCount++;
        if (!active){
            return;
        }
        image.draw(explosiveLoc.x, explosiveLoc.y);
        if (frameCount >= (time * 10)){  // Need to change maybe
            active = false;
        }
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

}
