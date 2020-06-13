import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The type Wave.
 */
public class Wave {
    private ArrayList<SpawnEvent> waveDetail = new ArrayList<>();
    private int waveNum = 0;
    private double delay;
    private int slicersSpawned;
    private int slicerCount;
    private String slicerType;
    private int lineNum = 0;

    /**
     * Sets slicer type.
     *
     * @param slicerType the slicer type
     */
    public void setSlicerType(String slicerType) {
        this.slicerType = slicerType;
    }

    public Wave(){
        waveDetails();
        this.waveNum = 0;
        this.slicersSpawned = 0;
        this.slicerCount = 0;
        this.lineNum = 0;
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
     * Sets delay.
     *
     * @param delay the delay
     */
    public void setDelay(double delay) {
        this.delay = delay;
    }

    /**
     * Gets slicer count.
     *
     * @return the slicer count
     */
    public int getSlicerCount() {
        return slicerCount;
    }

    /**
     * Sets slicer count.
     *
     * @param slicerCount the slicer count
     */
    public void setSlicerCount(int slicerCount) {
        this.slicerCount = slicerCount;
    }

    /**
     * Gets slicers spawned.
     *
     * @return the slicers spawned
     */
    public int getSlicersSpawned() {
        return slicersSpawned;
    }

    /**
     * Sets slicers spawned.
     *
     * @param slicersSpawned the slicers spawned
     */
    public void setSlicersSpawned(int slicersSpawned) {
        this.slicersSpawned = slicersSpawned;
    }

    /**
     * Gets wave num.
     *
     * @return the wave num
     */
    public int getWaveNum() {
        return waveNum;
    }

    /**
     * Sets wave num.
     *
     * @param waveNum the wave num
     */
    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    /**
     * Gets line num.
     *
     * @return the line num
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * Sets line num.
     *
     * @param lineNum the line num
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * Load wave spawn event.
     *
     * @return the spawn event
     */
    public SpawnEvent loadWave() {
        return waveDetail.get(lineNum);
    }

    /**
     * Gets wave detail.
     *
     * @return the wave detail
     */
    public ArrayList<SpawnEvent> getWaveDetail() {
        return waveDetail;
    }

    /**
     * Scans the file and stores the details
     */
    private void waveDetails() {
        String file = "res/levels/waves.txt";
        BufferedReader br;
        String line = "";
        ArrayList<String[]> wave = new ArrayList<>();
        SpawnEvent waveInfo;

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                wave.add(line.split(","));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (int i =0; i< wave.size(); i++){
            String[] lines = wave.get(i);
            if (lines[1].equals("spawn")){
                waveInfo = new SpawnEvent(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]),
                        lines[3], Integer.parseInt(lines[4]));
                waveDetail.add(waveInfo);
            }
            else {
                waveInfo = new SpawnEvent(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]));
                waveDetail.add(waveInfo);
            }
        }
    }

}
