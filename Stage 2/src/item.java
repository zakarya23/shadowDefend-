import bagel.Image;
import bagel.util.Rectangle;

import java.awt.*;
import java.util.concurrent.RecursiveAction;

public class item {
    private String name;
    private int cost;
    private Image towerImage;
    private Rectangle area;


    public Rectangle getArea() {
        return area;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public item(String name, int cost, Image towerImage) {
                this.name = name;
                this.cost = cost;
                this.towerImage = towerImage;
                this.area = towerImage.getBoundingBox(); // Needs to be at the point
    }

    public Image getImage() {
        return towerImage;
    }

    public void setImage(Image towerImage) {
        this.towerImage = towerImage;
    }
}
