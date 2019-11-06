package Controller;

import Model.GameModel;
import Model.Position;

public class BonusController {

    private double bonusPoints;

    public BonusController(){
       this.bonusPoints = 0;
    }


    public void update(GameModel model, GameController controller){


        if((controller.toActivate(model, 60))&& (bonusPoints != model.getScore())){

            model.getBonus().setActive(true);
            bonusPoints = model.getScore();
            model.getBonus().updateTimeCheck();
        }

        if(model.getBonus().isActive())
        {

            if(System.currentTimeMillis() - model.getBonus().getTimeCheck() >= model.getBonus().getSecondsToDis()*1000)
            {
                model.getBonus().setActive(false);
                changeBonusPos(model,controller.getNewPos(model));
            }

            if(bonusEaten(model))
            {
                updateScoreBonus(model);
                controller.getSnakeCont().increaseSnake(model);
                model.getBonus().setActive(false);
                changeBonusPos(model, controller.getNewPos(model));

            }

        }


    }

    public void changeBonusPos(GameModel model, Position posf){

        model.getBonus().setPosition(posf);
    }

    public void updateScoreBonus(GameModel model){

        model.setScore(model.getScore() + model.getBonus().getPoints()*model.getMultiplier());
    }

    public boolean bonusEaten(GameModel model){

        if(model.getSnake().getPositions().get(0).equals(model.getBonus().getPos()))
            return true;
        else return false;
    }

}
