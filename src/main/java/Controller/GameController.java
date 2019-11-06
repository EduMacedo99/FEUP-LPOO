package Controller;

import Model.*;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.Random;

public class GameController {

    private SnakeController snakeCont;
    private BonusController bonusCont;
    private FruitController fruitCont;
    private PoisonController poisonCont;
    private ObstacleController obstacleCont;


    public GameController(){
        this.snakeCont = new SnakeController();
        this.bonusCont = new BonusController();
        this.fruitCont = new FruitController();
        this.poisonCont = new PoisonController();
        this.obstacleCont = new ObstacleController();

    }

    public void update(GameModel model){
        if(model.getState().readState().equals("play") && (isToUpdate(model))){
            snakeCont.update(model);
            fruitCont.update(model, this);
            bonusCont.update(model, this);
            obstacleCont.update(model,this);
            poisonCont.update(model, this);
        }

    }


    public void processKey(KeyStroke key, GameModel model) {

        switch (key.getKeyType())
        {
            case ArrowLeft:

                if((!model.getSnake().getDirection().equals("right")) && (!model.getSnake().getLastDir().equals("right"))) model.getSnake().setDirection("left");
                break;

            case ArrowRight:
                if((!model.getSnake().getDirection().equals("left")) && (!model.getSnake().getLastDir().equals("left")))  model.getSnake().setDirection("right");
                break;

            case ArrowUp:
                if((!model.getSnake().getDirection().equals("down")) && (!model.getSnake().getLastDir().equals("down")))  model.getSnake().setDirection("up");
                break;

            case ArrowDown:
                if((!model.getSnake().getDirection().equals("up")) && (!model.getSnake().getLastDir().equals("up"))) model.getSnake().setDirection("down");
                break;

        }

    }

    public boolean isAvailable(GameModel model, Position pos){

        while(true)
        {
            for(int i = 0; i < model.getSnake().getPositions().size(); i++){
                if(pos.equals(model.getSnake().getPositions().get(i)))
                {
                    return false;
                }

            }

            if(model.getObstacle().getObstaclePositions().size()!=0)

                for(int i=0; i< model.getObstacle().getObstaclePositions().size(); i++)
                {
                    if(pos.equals(model.getObstacle().getObstaclePositions().get(i)))
                    {
                        return false;
                    }

                }

            return true;

        }
    }

    public Position getNewPos(GameModel model){

        while(true)
        {
            Random rand = new Random();

            Position pos = new Position(rand.nextInt(model.getWidth()-2)+1, rand.nextInt(model.getHeight()-8)+6);

            if (isAvailable(model, pos))
            {
                return pos;
            }

        }

    }

    public boolean isToUpdate(GameModel model){

        if (System.currentTimeMillis()-model.getSnake().getLastMove()>model.getSnake().getSpeed())
        {
            model.getSnake().setLastMove();
            return true;
        }
        return false;
    }


    public boolean toActivate(GameModel model, int points){
        if (model.getScore() % points == 0 && model.getScore() != 0)
            return true;
        return false;
    }


  public SnakeController getSnakeCont() { return this.snakeCont; }

  public BonusController getBonusCont() { return this.bonusCont; }

  public FruitController getFruitCont() { return this.fruitCont; }

  public ObstacleController getObstacleCont() { return this.obstacleCont; }

  public PoisonController getPoisonCont() { return  this.poisonCont; }
}



