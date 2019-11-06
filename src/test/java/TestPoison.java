import Controller.GameController;
import Model.GameModel;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestPoison {
    GameModel model = new GameModel(50, 30);
    GameController gameController = new GameController();

    @Test
    public void testPoison(){
        assertFalse(model.getPoison().isActive());
        model.getPoison().setActive(true);

        assertTrue(model.getPoison().isActive());

        assertEquals(10,model.getPoison().getSecondsToDis());

    }
}
