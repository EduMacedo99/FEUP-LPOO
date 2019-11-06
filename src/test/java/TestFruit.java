
import Controller.GameController;
import Model.GameModel;
import Model.Position;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestFruit {

    GameModel model = new GameModel(50, 30);
    GameController gameController = new GameController();


    private void setup() {

        Position pos = new Position(18,10);
        model.getFruit().setPosition(pos);

    }

    @Test

    public void TestFruitEaten(){
        setup();

        //The snake is allways initialized at pos (20,10) with direction set to the left

        assertFalse(gameController.getFruitCont().fruitEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());
        assertFalse(gameController.getFruitCont().fruitEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());
        assertTrue(gameController.getFruitCont().fruitEaten(model));
        gameController.getSnakeCont().moveSnake(model.getSnake());
        assertFalse(gameController.getFruitCont().fruitEaten(model));

    }

    @Test
    public void TestUpdateScoreFruit(){
        setup();
        gameController.getSnakeCont().moveSnake(model.getSnake());
        gameController.getSnakeCont().moveSnake(model.getSnake());

        assertEquals(0.0, model.getScore());

        gameController.getFruitCont().update(model,gameController);

        assertEquals(10.0, model.getScore());
    }


}
