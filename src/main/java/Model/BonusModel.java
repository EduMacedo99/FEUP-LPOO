package Model;

public class BonusModel extends EntityModel {

    private int points;
    private int secondsToDis;
    private boolean active;
    private double timeCheck;

    BonusModel(int x, int y) {

        super(x, y);
        this.points = 50;
        this.secondsToDis = 8;
        this.active = false;
        this.timeCheck = System.currentTimeMillis();

    }

    public int getSecondsToDis() {
        return secondsToDis;
    }

    public void setSecondsToDis(int sec) {
        this.secondsToDis=sec;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPoints() {
        return points;
    }

    public double getTimeCheck() {
        return timeCheck;
    }

    public void updateTimeCheck() {
        this.timeCheck = System.currentTimeMillis();
    }
}
