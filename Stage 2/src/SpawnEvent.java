import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SpawnEvent {
    private int waveNum;
    private String waveType;
    private int spawnNum;
    private String enemyType;
    private double delay;

    public int getWaveNum() {
        return waveNum;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public double getDelay() {
        return delay;
    }

    public String getWaveType() {
        return waveType;
    }

    public int getSpawnNum() {
        return spawnNum;
    }

    public SpawnEvent(int waveNum, String waveType, int spawnNum, String enemyType, double delay) {
        this.waveNum = waveNum;
        this.waveType = waveType;
        this.spawnNum = spawnNum;
        this.enemyType = enemyType;
        this.delay = delay;
    }
    public SpawnEvent(int waveNum, String waveType, double delay){
        this.waveNum = waveNum;
        this.waveType = waveType;
        this.delay = delay;
    }


}
