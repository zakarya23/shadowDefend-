import bagel.Image;


public class Slicer {
    private double x, y;
    private Image slicer;
    private double start_x = 30;
    private double start_y = 300;
    private String slicer_file = "res/images/slicer.png";

    public void slicer() {
        this.x = start_x;
        this.y = start_y;
        this.slicer = new Image(slicer_file);
    }
    public void slicer(double x, double y, Image slicer){
        this.x = x;
        this.y = y;
        this.slicer = slicer;
    }

    public Image getImage(){
        return slicer;
    }

    public void render(double x, double y, Image slicer){
        slicer.draw(x, y);
    }







}
