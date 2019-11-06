package Model;

import java.util.ArrayList;
import java.util.List;

public class ObstacleModel  {

    private List<Position> obstaclePositions = new ArrayList<>();
    private List<Position> obstacle1Position = new ArrayList<>();
    private List<Position> obstacle2Position = new ArrayList<>();
    private int width, height;
    private boolean obstacle1,obstacle2;

    public ObstacleModel(int height, int width){
        this.height=height;
        this.width = width;
        createObstacle1();
        createObstacle2();
        obstacle1=true;
        obstacle2=true;

    }

    public void createObstacle1()
    {

        //horizontais de esquerda
        for(int i=0; i<width/8;i++){
            obstacle1Position.add(new Position((width/8 * 2) + i,height/8*2+5));
            obstacle1Position.add(new Position((width/8 * 2) + i,height/8*6+5));
        }

        //horizontais direita
        for(int i= width/8*5; i<width/8*6+1;i++){
            obstacle1Position.add(new Position(i,height/8*2+5));
            obstacle1Position.add(new Position(i,height/8*6+5));
        }

        //verticais de cima
        for(int i=height/8*2+6; i<height/8*3+6;i++){
            obstacle1Position.add(new Position(width/8*2,i));
            obstacle1Position.add(new Position(width/8*6,i));
        }

        //verticais de baixo
        for(int i=height/8*5+5; i<height/8*6+5;i++){
            obstacle1Position.add(new Position(width/8*2,i));
            obstacle1Position.add(new Position(width/8*6,i));
        }
    }

    public void createObstacle2(){

        for(int i=width/8*3; i<width/8*5;i++){
            obstacle2Position.add(new Position(i, height/8*3+5));
            obstacle2Position.add(new Position(i, height/8*5+5));
        }
    }

    public void updatePositions1(){
        for(int i=0; i<obstacle1Position.size();i++){
            obstaclePositions.add(obstacle1Position.get(i));
            obstacle1=false;
        }
    }

    public void updatePositions2(){
        for(int i=0; i<obstacle2Position.size();i++){
            obstaclePositions.add(obstacle2Position.get(i));
            obstacle2=false;
        }
    }
    public List<Position> getObstaclePositions() {
        return obstaclePositions;
    }

    public List<Position> getObstacle1Positions() {
        return obstacle1Position;
    }

    public List<Position> getObstacle2Positions() {
        return obstacle2Position;
    }


    public boolean isObstacle2() {
        return obstacle2;
    }

    public boolean isObstacle1() {
        return obstacle1;
    }

}
