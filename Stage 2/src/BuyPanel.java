import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class BuyPanel {
    private Image background = new Image("res/images/buypanel.png");

    private item tank = new item("Tank", 250, new Image("res/images/tank.png"));
    private item superTank = new item("Super Tank", 600, new Image("res/images/supertank.png"));
    private item airplane = new item("Airplane", 500, new Image("res/images/airsupport.png"));
    private Font name = new Font("res/fonts/DejaVuSans-Bold.ttf", 20);
    private Font keys = new Font("res/fonts/DejaVuSans-Bold.ttf", 10);
    private Font playerCash = new Font("res/fonts/DejaVuSans-Bold.ttf", 50);


    private int imageX = 68;
    private int imageY = 45;

    private int distance = 120;
    private int textX = 40;
    private int textY = 90;
    private boolean canBuyTank;
    private boolean canBuySuperTank;
    private boolean canBuyAirplane;
    private DrawOptions textColour = new DrawOptions();
    private Colour colour;
    private int cash;

    public BuyPanel() {
        renderImages();

    }
    public item getTank() {
        return tank;
    }

    public item getSuperTank() {
        return superTank;
    }

    public item getAirplane() {
        return airplane;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setCanBuyAirplane(boolean canBuyAirplane) {
        this.canBuyAirplane = canBuyAirplane;
    }

    public void setCanBuySuperTank(boolean canBuySuperTank) {
        this.canBuySuperTank = canBuySuperTank;
    }

    public void setCanBuyTank(boolean canBuyTank) {
        this.canBuyTank = canBuyTank;
    }

    public void renderImages(){
        background.drawFromTopLeft(0,0);
        tank.getImage().draw(imageX, imageY);
        tank.setArea(tank.getImage().getBoundingBoxAt(new Point(imageX, imageY)));

        superTank.getImage().draw(imageX + distance, imageY);
        superTank.setArea(superTank.getImage().getBoundingBoxAt(new Point(imageX + distance, imageY)));

        airplane.getImage().draw(imageX + 2 * distance, imageY);
        airplane.setArea(airplane.getImage().getBoundingBoxAt(new Point(imageX + 2 * distance, imageY)));
    }

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
        playerCash.drawString("$" + money , 1024 - 200, 65);
    }

    public void renderKeys() {
        keys.drawString("Key binds: ", (1024/2) - 20, 20);
        keys.drawString("S - Start Wave ", (1024/2) - 20, 20 + 30);
        keys.drawString("L - Increase Timescale", (1024/2) - 20, 20 + 40);
        keys.drawString("K - Decrease Timescale", (1024/2) - 20, 20 + 50);
    }


}
