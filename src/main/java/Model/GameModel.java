package Model;


import States.GameState;
import States.MenuState;

public class GameModel {

    private static GameModel instance;
    private GameState state;


    private double score;
    private double multiplier;
    private static int height, width;


    private FruitModel fruit;
    private SnakeModel snake;
    private WallModel wall;
    private ObstacleModel obstacle;
    private BonusModel bonus;
    private PoisonModel poison;



    public GameModel(int width, int height) {

        reset(width, height);
    }


    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel(height,width);

        return instance;
    }

    public SnakeModel getSnake() {
        return snake;
    }

    public WallModel getWall() {
        return wall;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public double getScore() { return score; }

    public void setScore(double score) {
        this.score = score;
    }

    public FruitModel getFruit() {
        return fruit;
    }

    public ObstacleModel getObstacle() {
        return obstacle;
    }

    public BonusModel getBonus() {
        return bonus;
    }

    public PoisonModel getPoison() { return poison; }

    public double getMultiplier() { return multiplier; }

    public void setMultiplier(double multiplier) { this.multiplier = multiplier; }

    public GameState getState() { return state; }

    public void changeState(GameState state) { this.state = state; }


    public void reset(int width, int height) {
        this.score = 0;
        this.multiplier = 1;
        this.state = new MenuState(this);

        this.height = height;
        this.width = width;

        this.snake = new SnakeModel(20,10, 100);
        this.fruit = new FruitModel(15,15);
        this.wall = new WallModel(height,width);
        this.obstacle= new ObstacleModel(height,width);
        this.bonus = new BonusModel(5, 5);
        this.poison = new PoisonModel(10,10);
    }
}
