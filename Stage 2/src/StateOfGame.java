import bagel.Input;
import bagel.util.Point;

/**
 * The type State of game.
 */
public class StateOfGame {
    /**
     * The Buy panel.
     */
    public BuyPanel buyPanel;
    /**
     * The Status panel.
     */
    public StatusPanel statusPanel;
    private Point slicerPosition;
    private int slicerNum = 0;

    /**
     * Instantiates a new State of game.
     */
    public StateOfGame(){
        buyPanel = new BuyPanel();
        statusPanel = new StatusPanel();
        tankMoney();
        superTankMoney();
        airplaneMoney();
        buyPanel.setCash(ShadowDefend.getPlayer().getCash());
        statusPanel.setWaveNum(ShadowDefend.getWave().getWaveNum());
        statusPanel.setLives(ShadowDefend.getPlayer().getLives());
        statusPanel.setTimeScale(ShadowDefend.getTimescale());
        buyPanel.render();
        statusPanel.render();
        this.slicerPosition = new Point(0,0);
    }


    /**
     * Tank money.
     */
    public void tankMoney(){
        if (canBuy(250)){
            buyPanel.setCanBuyTank(true);
        }
        else {
            buyPanel.setCanBuyTank(false);
        }
    }

    /**
     * Super tank money.
     */
    public void superTankMoney(){
        if (canBuy(600)){
            buyPanel.setCanBuySuperTank(true);
        }
        else {
            buyPanel.setCanBuySuperTank(false);
        }
    }

    /**
     * Airplane money.
     */
    public void airplaneMoney(){
        if (canBuy(500)){
            buyPanel.setCanBuyAirplane(true);
        }
        else {
            buyPanel.setCanBuyAirplane(false);
        }
    }

    /**
     * Can buy boolean.
     *
     * @param money the money
     * @return the boolean
     */
    public boolean canBuy(double money){
        if (ShadowDefend.getPlayer().getCash() >= money){
            return true;
        }
        return false;
    }


}
