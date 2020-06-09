import bagel.Image;
import bagel.Input;
import bagel.util.Point;

public class Explosive {
    private double damage;
    private int radius;
    private boolean active;
    private double time;
    private Point explosiveLoc;
    private Image image;
    private int frameCount;

    public Explosive(Image image, Point explosiveLoc) {
        this.image = image;
        this.explosiveLoc = explosiveLoc;
        this.active = true;
    }

    public void update(Input input){
        frameCount++;
        if (!active){
            return;
        }
        image.draw(explosiveLoc.x, explosiveLoc.y);
        if (frameCount == 20){
            active = false;
        }
    }

}
