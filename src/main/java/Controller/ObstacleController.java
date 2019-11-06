package Controller;

import Model.GameModel;
import Model.Position;

import java.util.List;

public class ObstacleController {

    public void update(GameModel model, GameController controller){

        boolean isAvailable=true;
        List<Position> Positions;

        if(model.getScore()>=100 && model.getObstacle().isObstacle1())
        {
            Positions= model.getObstacle().getObstacle1Positions();
            isAvailable = arePositionsAvailable(model, controller, isAvailable, Positions);
            if(isAvailable)
                model.getObstacle().updatePositions1();
        }

        if(model.getScore()>=150 && model.getObstacle().isObstacle2())
        {
            Positions= model.getObstacle().getObstacle2Positions();
            isAvailable = arePositionsAvailable(model, controller, isAvailable, Positions);
            if(isAvailable)
                model.getObstacle().updatePositions2();
        }

    }

    private boolean arePositionsAvailable(GameModel model, GameController controller, boolean isAvailable, List<Position> positions) {

        for(int i = 0; i< positions.size(); i++)
        {
            if(!controller.isAvailable(model, positions.get(i)))
            {
                isAvailable=false;
                break;
            }
        }
        return isAvailable;
    }

}
