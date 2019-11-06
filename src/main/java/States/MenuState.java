package States;

import Model.GameModel;

public class MenuState extends GameState {

    public MenuState(GameModel model) {
        super(model);
    }

    @Override
    public String readState() {
        return "menu";
    }

}
