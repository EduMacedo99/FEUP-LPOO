package States;

import Model.GameModel;

public class PlayState extends GameState {
    public PlayState(GameModel model) {
        super(model);
    }

    @Override
    public String readState() {
        return "play";
    }


}
