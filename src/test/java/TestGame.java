

import Controller.GameController;
import Model.GameModel;
import Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestGame {

    GameModel model = new GameModel(40, 30);
    GameController controller= new GameController();

    @Test //1
    public void testChangeDirectionLeft(){

        model.getSnake().setDirection("left");
        model.getSnake().setLastDir("left");

        KeyStroke key= new KeyStroke(KeyType.ArrowLeft);


        controller.processKey(key, model);
        assertEquals("left",model.getSnake().getDirection());

        key= new KeyStroke(KeyType.ArrowRight);
        controller.processKey(key,model);
        assertEquals("left",model.getSnake().getDirection());

        key= new KeyStroke(KeyType.ArrowUp);
        controller.processKey(key,model);
        assertEquals("up",model.getSnake().getDirection());

        model.getSnake().setDirection("left");
        model.getSnake().setLastDir("left");

        key= new KeyStroke(KeyType.ArrowDown);
        controller.processKey(key,model);
        assertEquals("down",model.getSnake().getDirection());
    }

    @Test //2
    public void testChangeDirectionRight(){

        model.getSnake().setDirection("right");
        model.getSnake().setLastDir("right");

        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);
        controller.processKey(key, model);
        assertEquals("right",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowRight);
        controller.processKey(key,model);
        assertEquals("right",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowUp);
        controller.processKey(key,model);
        assertEquals("up",model.getSnake().getDirection());

        model.getSnake().setDirection("right");
        model.getSnake().setLastDir("right");

        key = new KeyStroke(KeyType.ArrowDown);
        controller.processKey(key,model);
        assertEquals("down",model.getSnake().getDirection());
    }

    @Test //3
    public void testChangeDirectionDown(){

        model.getSnake().setDirection("down");
        model.getSnake().setLastDir("down");

        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        controller.processKey(key,model);
        assertEquals("down",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowUp);
        controller.processKey(key,model);
        assertEquals("down",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowLeft);
        controller.processKey(key, model);
        assertEquals("left",model.getSnake().getDirection());

        model.getSnake().setDirection("down");
        model.getSnake().setLastDir("down");

        key = new KeyStroke(KeyType.ArrowRight);
        controller.processKey(key,model);
        assertEquals("right",model.getSnake().getDirection());

    }

    @Test //4
    public void testChangeDirectionUp(){
        model.getSnake().setDirection("up");
        model.getSnake().setLastDir("up");

        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        controller.processKey(key,model);
        assertEquals("up",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowUp);
        controller.processKey(key,model);
        assertEquals("up",model.getSnake().getDirection());

        key = new KeyStroke(KeyType.ArrowLeft);
        controller.processKey(key, model);
        assertEquals("left",model.getSnake().getDirection());

        model.getSnake().setDirection("up");
        model.getSnake().setLastDir("up");

        key = new KeyStroke(KeyType.ArrowRight);
        controller.processKey(key,model);
        assertEquals("right",model.getSnake().getDirection());
    }

    @Test
    public void TestIsAvailable(){
        Position pos = new Position(12,12);
        assertTrue(controller.isAvailable(model, pos));

        Position pos2 = new Position(20,10);
        assertFalse(controller.isAvailable(model,pos2));
    }

}
