package Model;

public class FruitModel extends EntityModel {
    private int points;

    public FruitModel(int x, int y) {
        super(x, y);
        this.points = 10;
    }

    public int getPoints() {
        return points;
    }

}