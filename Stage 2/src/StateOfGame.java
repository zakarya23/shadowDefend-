import bagel.Input;
import bagel.util.Point;

public class StateOfGame {
    public BuyPanel buyPanel;
    public StatusPanel statusPanel;

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
    }


    public void tankMoney(){
        if (canBuy(250)){
            buyPanel.setCanBuyTank(true);
        }
        else {
            buyPanel.setCanBuyTank(false);
        }
    }

    public void superTankMoney(){
        if (canBuy(600)){
            buyPanel.setCanBuySuperTank(true);
        }
        else {
            buyPanel.setCanBuySuperTank(false);
        }
    }

    public void airplaneMoney(){
        if (canBuy(500)){
            buyPanel.setCanBuyAirplane(true);
        }
        else {
            buyPanel.setCanBuyAirplane(false);
        }
    }

    public boolean canBuy(double money){
        if (ShadowDefend.getPlayer().getCash() >= money){
            return true;
        }
        return false;
    }


}
