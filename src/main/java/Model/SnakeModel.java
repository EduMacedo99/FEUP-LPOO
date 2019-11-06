package Model;

import java.util.ArrayList;
import java.util.List;


public class SnakeModel extends EntityModel {

    private int size = 4;  //initial size
    private List<Position> positions = new ArrayList<Position>();


    private String direction;
    private int speed;
    private long lastMove;
    private String lastDir;


    public SnakeModel (int x, int y, int speed){
        super(x,y);
        for(int i = 0; i < size; i++){
            Position pos = new Position(x +i,y);
            positions.add(pos);

        }
        this.direction = "left";
        this.lastDir = "left";
        this.speed=speed;


    }

    public List<Position> getPositions(){
        return positions;
    }

    public void setPositions(List<Position> pos){
        this.positions = pos;
    }

    public double getSpeed() { return speed;}

    public String getDirection() { return direction; }

    public void setDirection(String dir) { this.direction = dir; }

    public int getSize() {
        return size;
    }

    public void setSize(int size){ this.size = size;}

    public void setLastMove (){
        this.lastMove=System.currentTimeMillis();
    }

    public long getLastMove() {
        return lastMove;
    }

    public String getLastDir() {
        return lastDir;
    }

    public void setLastDir(String lastDir) {
        this.lastDir = lastDir;
    }

    public void setSpeed(int speed){ this.speed = speed; }
}
