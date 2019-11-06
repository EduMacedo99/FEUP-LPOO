package States;

import Model.GameModel;

public class OverState extends GameState {

    public OverState(GameModel model) {
        super(model);
    }

    @Override
    public String readState() {
        return "over";
    }

}
