/**
 * The type Player.
 */
public class Player {
    private int cash;
    private int lives;
    private final int PLAYER_CASH = 500;
    private final int PLAYER_LIVES = 25;

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this.cash = PLAYER_CASH;
        this.lives = PLAYER_LIVES;
    }

    /**
     * Gets cash.
     *
     * @return the cash
     */
    public int getCash() {
        return cash;
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
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}
