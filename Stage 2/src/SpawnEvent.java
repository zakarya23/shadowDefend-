
/**
 * The type Spawn event.
 */
public class SpawnEvent {
    private int waveNum;
    private String waveType;
    private int spawnNum;
    private String enemyType;
    private double delay;

    /**
     * Gets wave num.
     *
     * @return the wave num
     */
    public int getWaveNum() {
        return waveNum;
    }

    /**
     * Gets enemy type.
     *
     * @return the enemy type
     */
    public String getEnemyType() {
        return enemyType;
    }

    /**
     * Gets delay.
     *
     * @return the delay
     */
    public double getDelay() {
        return delay;
    }

    /**
     * Gets wave type.
     *
     * @return the wave type
     */
    public String getWaveType() {
        return waveType;
    }

    /**
     * Gets spawn num.
     *
     * @return the spawn num
     */
    public int getSpawnNum() {
        return spawnNum;
    }

    /**
     * Instantiates a new Spawn event.
     */
    public SpawnEvent(int waveNum, String waveType, int spawnNum, String enemyType, double delay) {
        this.waveNum = waveNum;
        this.waveType = waveType;
        this.spawnNum = spawnNum;
        this.enemyType = enemyType;
        this.delay = delay;
    }

    /**
     * Instantiates a new delay event.
     */
    public SpawnEvent(int waveNum, String waveType, double delay){
        this.waveNum = waveNum;
        this.waveType = waveType;
        this.delay = delay;
    }


}
