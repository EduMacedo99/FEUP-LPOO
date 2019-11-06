package Model;

public class PoisonModel extends EntityModel {

    private int secondsToDis;
    private boolean active;
    private double timeCheck;

    public PoisonModel(int x, int y) {
        super(x, y);
        this.secondsToDis = 10;
        this.active = false;
        this.timeCheck = System.currentTimeMillis();
    }

    public int getSecondsToDis() { return secondsToDis; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public double getTimeCheck() { return timeCheck; }

    public void updateTimeCheck() { this.timeCheck = System.currentTimeMillis(); }
}
