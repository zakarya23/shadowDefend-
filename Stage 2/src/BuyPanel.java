import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;
/**
 * The type Buy panel.
 */
public class BuyPanel {
    private Image background = new Image("res/images/buypanel.png");
    private final String FONT = "res/fonts/DejaVuSans-Bold.ttf";
    private item tank = new item("Tank", 250, new Image("res/images/tank.png"));
    private item superTank = new item("Super Tank", 600, new Image("res/images/supertank.png"));
    private item airplane = new item("Airplane", 500, new Image("res/images/airsupport.png"));
    private Font name = new Font(FONT, 20);
    private Font keys = new Font(FONT, 15);
    private Font playerCash = new Font(FONT, 50);
    private final int imageX = 68;
    private final int imageY = 45;
    private int distance = 120;
    private final int textX = 40;
    private final int textY = 90;
    private final double keyX = 492;
    private final double keyY = 20;
    private final double cashX = 824;
    private final double cashY = 65;
    private boolean canBuyTank;
    private boolean canBuySuperTank;
    private boolean canBuyAirplane;
    private DrawOptions textColour = new DrawOptions();
    private Colour colour;
    private int cash;

    /**
     * Render images.
     */
    public void renderImages(){
        background.drawFromTopLeft(0,0);
        tank.getImage().draw(imageX, imageY);
        tank.setArea(tank.getImage().getBoundingBoxAt(new Point(imageX, imageY)));

        superTank.getImage().draw(imageX + distance, imageY);
        superTank.setArea(superTank.getImage().getBoundingBoxAt(new Point(imageX + distance, imageY)));

        airplane.getImage().draw(imageX + 2 * distance, imageY);
        airplane.setArea(airplane.getImage().getBoundingBoxAt(new Point(imageX + 2 * distance, imageY)));
    }

    /**
     * Renders the details
     */
    public void render(){
        renderImages();
        if (canBuyTank) {
            name.drawString("$250", textX, textY, textColour.setBlendColour(colour.GREEN));
        }
        else {
            name.drawString("$250", textX, textY, textColour.setBlendColour(colour.RED));
        }
        if (canBuySuperTank) {
            name.drawString("$600", textX + distance, textY, textColour.setBlendColour(colour.GREEN));
        }
        else {
            name.drawString("$600", textX + distance, textY, textColour.setBlendColour(colour.RED));
        }
        if (canBuyAirplane) {
            name.drawString("$500", textX + 2 * distance, textY, textColour.setBlendColour(colour.GREEN));
        }
        else {
            name.drawString("$500", textX + 2 * distance, textY, textColour.setBlendColour(colour.RED));
        }
        renderKeys();
        String money = Integer.toString(cash);
        playerCash.drawString("$" + money , cashX, cashY);
    }

    /**
     * Render keys.
     */
    public void renderKeys() {
        keys.drawString("Key binds: ", keyX, keyY);
        keys.drawString("S - Start Wave ", keyX, keyY + 30);
        keys.drawString("L - Increase Timescale", keyX, keyY + 45);
        keys.drawString("K - Decrease Timescale", keyX, keyY + 60);
    }


    /**
     * Instantiates a new Buy panel.
     */
    public BuyPanel() {
        renderImages();
    }

    /**
     * Gets tank.
     *
     * @return the tank
     */
    public item getTank() {
        return tank;
    }

    /**
     * Gets super tank.
     *
     * @return the super tank
     */
    public item getSuperTank() {
        return superTank;
    }

    /**
     * Gets airplane.
     *
     * @return the airplane
     */
    public item getAirplane() {
        return airplane;
    }

    /**
     * Sets cash.
     *
     * @param cash the cash
     */
    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * Sets can buy airplane.
     *
     * @param canBuyAirplane the can buy airplane
     */
    public void setCanBuyAirplane(boolean canBuyAirplane) {
        this.canBuyAirplane = canBuyAirplane;
    }

    /**
     * Sets can buy super tank.
     *
     * @param canBuySuperTank the can buy super tank
     */
    public void setCanBuySuperTank(boolean canBuySuperTank) {
        this.canBuySuperTank = canBuySuperTank;
    }

    /**
     * Sets can buy tank.
     *
     * @param canBuyTank the can buy tank
     */
    public void setCanBuyTank(boolean canBuyTank) {
        this.canBuyTank = canBuyTank;
    }


}
