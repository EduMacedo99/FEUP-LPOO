package View;

import Model.GameModel;
import States.PlayState;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SwingSnake extends JFrame implements KeyListener{

    private final static int TILE_SIZE = 16;

    public static int getTileSize() {
        return TILE_SIZE;
    }

    private JFrame frame;
    private SnakePanel mainPanel;

    private GameModel model;

    public SwingSnake(){


        this.model = GameModel.getInstance();


        frame = new JFrame("Snake");
        frame.setLocation(30, 100);


        mainPanel = new SnakePanel();

        frame.add(mainPanel);
        frame.setSize(658, 687);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.toFront();
        frame.setVisible(true);

    }

    public void drawSwing(GameModel model){
        this.model = model;
        mainPanel.setModel(model);
        mainPanel.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processKey(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void processKey(KeyEvent e) {
        switch (model.getState().readState()){
            case "play":
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:

                        if ((!model.getSnake().getDirection().equals("right")) && (!model.getSnake().getLastDir().equals("right")))
                            model.getSnake().setDirection("left");
                        break;

                    case KeyEvent.VK_RIGHT:
                            if ((!model.getSnake().getDirection().equals("left")) && (!model.getSnake().getLastDir().equals("left")))
                                model.getSnake().setDirection("right");
                            break;

                    case KeyEvent.VK_UP:
                        if ((!model.getSnake().getDirection().equals("down")) && (!model.getSnake().getLastDir().equals("down")))
                            model.getSnake().setDirection("up");
                        break;

                    case KeyEvent.VK_DOWN:
                        if ((!model.getSnake().getDirection().equals("up")) && (!model.getSnake().getLastDir().equals("up")))
                            model.getSnake().setDirection("down");
                        break;
                }
                break;
            case "menu":
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    model.setMultiplier(0.50);
                    model.getSnake().setSpeed(110);
                    model.changeState(new PlayState(model));
                }
                if (e.getKeyCode() == KeyEvent.VK_2) {
                    model.setMultiplier(1);
                    model.getSnake().setSpeed(90);
                    model.changeState(new PlayState(model));
                }
                if (e.getKeyCode() == KeyEvent.VK_3) {
                    model.setMultiplier(1.5);
                    model.getSnake().setSpeed(55);
                    model.changeState(new PlayState(model));
                }
                break;

            case "over":
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    model.reset(40,40);
                }
                if (e.getKeyCode() == KeyEvent.VK_2) {
                    System.exit(0);
                }

        }





    }

}





