import bagel.Image;
import bagel.util.Rectangle;

/**
 * The type Item.
 */
public class item {
    private String name;
    private int cost;
    private Image towerImage;
    private Rectangle area;


    /**
     * Gets area.
     *
     * @return the area
     */
    public Rectangle getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(Rectangle area) {
        this.area = area;
    }

    /**
     * Instantiates a new Item.
     */
    public item(String name, int cost, Image towerImage) {
                this.name = name;
                this.cost = cost;
                this.towerImage = towerImage;
                this.area = towerImage.getBoundingBox();
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return towerImage;
    }

    /**
     * Sets image.
     *
     * @param towerImage the tower image
     */
    public void setImage(Image towerImage) {
        this.towerImage = towerImage;
    }
}
