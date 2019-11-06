import Controller.GameController;
import Model.GameModel;
import Model.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestObstacle {


    GameModel model = new GameModel(16, 16);
    GameController controller= new GameController();

    public void setup(List<Position> positions){
        positions.add(new Position(4,9));
        positions.add(new Position(4,17));
        positions.add(new Position(5,9));
        positions.add(new Position(5,17));
        positions.add(new Position(10,9));
        positions.add(new Position(10,17));
        positions.add(new Position(11,9));
        positions.add(new Position(11,17));
        positions.add(new Position(12,9));
        positions.add(new Position(12,17));
        positions.add(new Position(4,10));
        positions.add(new Position(12,10));
        positions.add(new Position(4,11));
        positions.add(new Position(12,11));
        positions.add(new Position(4,15));
        positions.add(new Position(12,15));
        positions.add(new Position(4,16));
        positions.add(new Position(12,16));
    }

    @Test
    public void testObstacle1(){

        model.getObstacle().createObstacle1();

        controller.getObstacleCont().update(model,controller);
        assertTrue(model.getObstacle().isObstacle1());

        List<Position> positions = new ArrayList<>();
        List<Position> obstacles = new ArrayList<>();

        setup(positions);

        for(int i=0; i<18;i++){
            assertEquals(positions.get(i).getX(), model.getObstacle().getObstacle1Positions().get(i).getX());
            assertEquals(positions.get(i).getY(), model.getObstacle().getObstacle1Positions().get(i).getY());
        }

        assertTrue(model.getObstacle().isObstacle1());
        assertTrue(model.getObstacle().isObstacle2());

        assertEquals(obstacles,model.getObstacle().getObstaclePositions());


        model.getObstacle().updatePositions1();

        obstacles=model.getObstacle().getObstacle1Positions();

        assertFalse(model.getObstacle().isObstacle1());
        assertTrue(model.getObstacle().isObstacle2());

        for(int i=0; i<18;i++){
            assertEquals(obstacles.get(i).getX(), model.getObstacle().getObstaclePositions().get(i).getX());
            assertEquals(obstacles.get(i).getY(), model.getObstacle().getObstaclePositions().get(i).getY());
        }


    }

    @Test
    public void testObstacle2(){

        List<Position> positions = new ArrayList<>();
        List<Position> obstacles;

        controller.getObstacleCont().update(model,controller);
        assertTrue(model.getObstacle().isObstacle1());

        controller.getBonusCont().updateScoreBonus(model);
        controller.getBonusCont().updateScoreBonus(model);

        controller.getObstacleCont().update(model,controller);

        assertFalse(model.getObstacle().isObstacle1());
        assertTrue(model.getObstacle().isObstacle2());

        obstacles=model.getObstacle().getObstacle1Positions();

        for(int i=6; i<10;i++){
            positions.add(new Position(i, 11));
            positions.add(new Position(i, 15));
        }

        model.getObstacle().createObstacle2();

        for(int i=0; i<8;i++){
            assertEquals(positions.get(i).getX(), model.getObstacle().getObstacle2Positions().get(i).getX());
            assertEquals(positions.get(i).getY(), model.getObstacle().getObstacle2Positions().get(i).getY());
        }

        assertTrue(model.getObstacle().isObstacle2());

        for(int i=0; i<18;i++){
            assertEquals(obstacles.get(i).getX(), model.getObstacle().getObstaclePositions().get(i).getX());
            assertEquals(obstacles.get(i).getY(), model.getObstacle().getObstaclePositions().get(i).getY());
        }

        model.getObstacle().updatePositions2();

        assertFalse(model.getObstacle().isObstacle2());

        for(int i=0; i< positions.size();i++){
            obstacles.add(positions.get(i));
        }

        for(int i=0; i<26;i++){
            assertEquals(obstacles.get(i).getX(), model.getObstacle().getObstaclePositions().get(i).getX());
            assertEquals(obstacles.get(i).getY(), model.getObstacle().getObstaclePositions().get(i).getY());

        }
    }

}
