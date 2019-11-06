import Model.GameModel;
import Model.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestWall {

    GameModel model = new GameModel(5, 5);

    public List<Position> setup()
    {
        List<Position> walls = new ArrayList<>();

        for(int i=0; i< 5;i++){
            walls.add(new Position(i,4));
            walls.add(new Position(i, 4));
        }

        for (int j=5; j<4; j++){
            walls.add(new Position(0,j));
            walls.add(new Position( 4, j));
        }
        return walls;
    }

    @Test
    public void testCreateWall(){
        List<Position> walls=setup();

        for(int i=0; i<model.getWall().getWallPositions().size();i++){
            assertEquals(walls.get(i).getX(), model.getWall().getWallPositions().get(i).getX());
            assertEquals(walls.get(i).getY(), model.getWall().getWallPositions().get(i).getY());
        }
    }
}
