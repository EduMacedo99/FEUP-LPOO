package Model;

import java.util.ArrayList;
import java.util.List;

public class WallModel {

    private List<Position> wallPositions;
    private int width, height;

    public WallModel(int height, int width){
        this.height=height;
        this.width = width;
        this.wallPositions = new ArrayList<>();
        this.wallPositions = createWall();
    }

    public List<Position> getWallPositions(){
        return wallPositions;
    }

    public List<Position> createWall()
    {
        List<Position> walls = new ArrayList<>();

        for(int i=0; i< width;i++){
            walls.add(new Position(i,4));
            walls.add(new Position(i, height -1));
        }

        for (int j=5; j<width-1; j++){
            walls.add(new Position(0,j));
            walls.add(new Position( width -1, j));
        }

        return walls;
    }


}
