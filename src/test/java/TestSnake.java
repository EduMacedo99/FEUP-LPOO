

import Controller.GameController;
import Model.GameModel;
import Model.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestSnake {

    GameModel model = new GameModel(40, 30);
    GameController controller= new GameController();


    private void assertLists(List<Position> positions) {

        for(int i=0; i<model.getSnake().getSize();i++){
            assertEquals(positions.get(i).getX(), model.getSnake().getPositions().get(i).getX());
            assertEquals(positions.get(i).getY(), model.getSnake().getPositions().get(i).getY());
        }
    }

    private void setup(List<Position> positions2) {
        for (int i = 0; i < 3; i++) {
            Position pos = new Position(20 + i, 10);
            positions2.add(pos);
        }

        assertLists(positions2);
    }

    @Test //1

    public void testMoveLeft() {

        controller.getSnakeCont().moveSnake(model.getSnake());

        List<Position> positions2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Position pos = new Position(19 + i, 10);
            positions2.add(pos);
        }

        assertLists(positions2);
    }

    @Test //2

    public void testMoveRight() {

        model.getSnake().setDirection("right");
        model.getSnake().setLastDir("right");

        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Position pos = new Position(15 - i, 10);
            positions.add(pos);
        }

        model.getSnake().setPositions(positions);
        controller.getSnakeCont().moveSnake(model.getSnake());
        List<Position> positions2 = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            Position pos = new Position(16 - i, 10);
            positions2.add(pos);
        }

        assertLists(positions2);
    }

    @Test //3

    public void testMoveDown() {

        model.getSnake().setDirection("down");
        model.getSnake().setLastDir("down");


        controller.getSnakeCont().moveSnake(model.getSnake());
        List<Position> positions2 = new ArrayList<>();
        positions2.add(new Position(20, 11));

        setup(positions2);

    }

    @Test //4

    public void testMoveUp() {

        model.getSnake().setDirection("up");
        model.getSnake().setLastDir("up");

        controller.getSnakeCont().moveSnake(model.getSnake());
        List<Position> positions2 = new ArrayList<>();
        positions2.add(new Position(20, 9));
        setup(positions2);
    }



    @Test //5
    public void testIncreaseSnakeLeft() {

        controller.getSnakeCont().increaseSnake(model);

        List<Position> positions2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Position pos = new Position(20 + i, 10);
            positions2.add(pos);
        }

        assertLists(positions2);

    }

    @Test //6
    public void testIncreaseSnakeRight() {
        model.getSnake().setDirection("right");
        model.getSnake().setLastDir("right");

        List<Position> positions = new ArrayList<>();


        for (int i = 0; i < 4; i++) {
            Position pos = new Position(17 - i, 10);
            positions.add(pos);
        }

        model.getSnake().setPositions(positions);
        controller.getSnakeCont().increaseSnake(model);

        List<Position> positions2 = new ArrayList<>();
        for (int i = 0; i < positions.size() + 1; i++) {
            Position pos = new Position(17 - i, 10);
            positions2.add(pos);
        }

        assertLists(positions2);
    }

   @Test //7
    public void testIncreaseSizeDown() {

        model.getSnake().setDirection("down");
        model.getSnake().setLastDir("down");

        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Position pos = new Position(20, 17 - i);
            positions.add(pos);
        }
        model.getSnake().setPositions(positions);
        controller.getSnakeCont().increaseSnake(model);

       List<Position> positions2 = new ArrayList<>();

        for (int i = 0; i < positions.size() + 1; i++) {
            Position pos = new Position(20, 17 - i);
            positions2.add(pos);
        }

        assertLists(positions2);
    }


    @Test //8
    public void testIncreaseSizeUp() {

        model.getSnake().setDirection("up");
        model.getSnake().setLastDir("up");

        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Position pos = new Position(20, 10 + i);
            positions.add(pos);
        }

        model.getSnake().setPositions(positions);
        controller.getSnakeCont().increaseSnake(model);

        List<Position> positions2 = new ArrayList<>();

        for (int i = 0; i < positions.size() + 1; i++) {
            Position pos = new Position(20, 10 + i);
            positions2.add(pos);
        }

        assertLists(positions2);
    }

    @Test
    public void canMove(){

        model.getSnake().setDirection("left");
        model.getSnake().setLastDir("left");

        List<Position> positions= new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Position pos = new Position(20+i, 10);
            positions.add(pos);
        }

        model.getSnake().setPositions(positions);

        Position pos= new Position(19,10);
        controller.getPoisonCont().changePoisonPosition(model, pos);
        model.getPoison().setActive(true);

        assertTrue(controller.getSnakeCont().canMove(model));

        controller.getSnakeCont().moveSnake(model.getSnake());

        assertFalse(controller.getSnakeCont().canMove(model));
    }

    @Test
    public void testSnakeComponents(){
        assertEquals("left",model.getSnake().getDirection());
        assertEquals("left",model.getSnake().getLastDir());
        assertEquals(100.0,model.getSnake().getSpeed());
        model.getSnake().setSize(1);
        assertEquals(1,model.getSnake().getSize());

    }

}