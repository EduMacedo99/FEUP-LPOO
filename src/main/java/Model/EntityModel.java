package Model;

public abstract class EntityModel {

    protected Position pos;

    EntityModel(int x, int y){
        this.pos = new Position(x,y);
    }

    public Position getPos(){ return pos;}

    public void setPosition(Position posi) {
        this.pos = posi;
    }

}
