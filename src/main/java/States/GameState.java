package States;

import Model.GameModel;

public abstract class GameState {

    protected GameModel model;

    public GameState(GameModel model){
        this.model = model;
    }

    public  abstract String readState();
}
