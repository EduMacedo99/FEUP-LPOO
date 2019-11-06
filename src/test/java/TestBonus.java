import Controller.GameController;
import Model.GameModel;
import Model.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestBonus {
    GameModel model = new GameModel(50, 30);
    GameController gameController= new GameController();


    List<Position> positions= new ArrayList<>();

    private void setup() {

        Position pos = new Position(18,10);
        model.getBonus().setPosition(pos);
        model.getBonus().setSecondsToDis(9999);
        model.getBonus().setActive(true);
        assertTrue(model.getBonus().isActive());

        for(int i = 0; i < 4; i++){
            Position position = new Position(20 +i,10);
            positions.add(position);

        }
        model.getSnake().setPositions(positions);
    }

    @Test

    public void TestBonusEaten(){
        setup();

        //The snake is allways initialized at pos (20,10) with direction set to the left
        model.getBonus().setSecondsToDis(9999);
        assertEquals(9999, model.getBonus().getSecondsToDis());

        assertFalse(gameController.getBonusCont().bonusEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());

        assertFalse(gameController.getBonusCont().bonusEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());

        assertTrue(gameController.getBonusCont().bonusEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());

        assertFalse(gameController.getBonusCont().bonusEaten(model));

    }

    @Test
    public void TestUpdateScoreBonus(){
        setup();
        gameController.getSnakeCont().moveSnake(model.getSnake());
        gameController.getSnakeCont().moveSnake(model.getSnake());

        assertEquals(0.0, model.getScore());

        gameController.getBonusCont().update(model,gameController);

        assertEquals(50.0, model.getScore());
    }
}
