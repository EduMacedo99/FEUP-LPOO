package Controller;

import Model.GameModel;
import Model.Position;

public class FruitController {


    public void update(GameModel model, GameController controller) {
        if(fruitEaten(model))
        {
            updateScoreFruit(model);
            changeFruitPos(model, controller.getNewPos(model));
            controller.getSnakeCont().increaseSnake(model);
        }

    }

    public void updateScoreFruit(GameModel model) {

        model.setScore(model.getScore() + model.getFruit().getPoints() * model.getMultiplier());
    }

    public void changeFruitPos(GameModel model, Position posf) {

        model.getFruit().setPosition(posf);
    }

    public boolean fruitEaten(GameModel model) {

        if(model.getSnake().getPositions().get(0).equals(model.getFruit().getPos()))
        {
            return true;
        }
        else return false;
    }
}
