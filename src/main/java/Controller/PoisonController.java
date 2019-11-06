package Controller;

import Model.GameModel;
import Model.Position;

public class PoisonController {

    private double poisonPoints;

    public PoisonController(){
        this.poisonPoints = 0;
    }


    public void update(GameModel model, GameController controller){


        if(controller.toActivate(model, 50) && poisonPoints!=model.getScore()) {

            model.getPoison().setActive(true);
            model.getPoison().updateTimeCheck();
            poisonPoints = model.getScore();
        }

        if(model.getPoison().isActive())
        {

            if(System.currentTimeMillis() - model.getPoison().getTimeCheck() >= model.getPoison().getSecondsToDis()*1000)
            {
                model.getPoison().setActive(false);
                changePoisonPosition(model, controller.getNewPos(model));
            }

        }
    }

    public void changePoisonPosition(GameModel model, Position posf){
        model.getPoison().setPosition(posf);
    }


}
