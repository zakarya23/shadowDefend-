import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Wave {
    private ArrayList<SpawnEvent> waveDetail = new ArrayList<>();
    private int waveNum = 0;
    private double delay;
    private int slicersSpawned;
    private int slicerCount;
    private String slicerType;
    private int lineNum = 0;

    public String getSlicerType() {
        return slicerType;
    }

    public void setSlicerType(String slicerType) {
        this.slicerType = slicerType;
    }

    public Wave(){
        waveDetails();
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public int getSlicerCount() {
        return slicerCount;
    }

    public void setSlicerCount(int slicerCount) {
        this.slicerCount = slicerCount;
    }

    public int getSlicersSpawned() {
        return slicersSpawned;
    }

    public void setSlicersSpawned(int slicersSpawned) {
        this.slicersSpawned = slicersSpawned;
    }

    public int getWaveNum() {
        return waveNum;
    }

    public void setWaveNum(int waveNum) {
        this.waveNum = waveNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public SpawnEvent loadWave() {
        return waveDetail.get(lineNum);
    }

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
