
public class Player {
    private int cash;
    private int lives;
    private boolean startWave;

    public Player() {
        this.cash = 10000;
        this.lives = 25;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
