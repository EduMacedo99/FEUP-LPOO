package View;

import Controller.GameController;
import Model.*;
import States.PlayState;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaView {

    private final TerminalScreen screen;
    private int width, height;


    public LanternaView(GameModel model) throws IOException {
        this.height = model.getHeight();
        this.width = model.getWidth();

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary


    }


    public void draw(GameModel model) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#CD853F"));
        switch (model.getState().readState()) {
            case "play":
                drawGame(model, graphics);
                break;
            case "menu":
                drawMenu(model, graphics);
                break;
            case "over":
                drawOver(model, graphics);
                break;
        }
        screen.refresh();
    }


    public void drawGame(GameModel model, TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');


        drawSnake(model.getSnake(), graphics);
        drawWall(model.getWall(), graphics);
        drawObstacle(model.getObstacle(), graphics);
        drawFruit(model.getFruit(), graphics);
        drawScore(model.getScore(), graphics);

        if(model.getBonus().isActive()) drawBonus(model.getBonus(), graphics);

        if(model.getPoison().isActive()) drawPoison(model.getPoison(), graphics);
    }

    private void drawMenu(GameModel model, TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#007700"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.enableModifiers(SGR.CIRCLED);
        graphics.putString(new TerminalPosition(16, 12), "1");
        graphics.putString(new TerminalPosition(31, 12), "2");
        graphics.putString(new TerminalPosition(46, 12), "3");
        graphics.putString(new TerminalPosition(17, 5), "WELCOME TO SNAKE");
        graphics.putString(new TerminalPosition(5, 21), "F");
        graphics.putString(new TerminalPosition(5, 22), "B");
        graphics.putString(new TerminalPosition(5, 24), "P");
        graphics.putString(new TerminalPosition(5, 25), "#");
        graphics.putString(new TerminalPosition(0, 12), "Difficulty:");
        graphics.putString(new TerminalPosition(5, 20), "Ingame:");



        graphics.disableModifiers(SGR.BOLD);
        graphics.enableModifiers(SGR.UNDERLINE);
        graphics.putString(new TerminalPosition(0, 12), "Difficulty:");
        graphics.disableModifiers(SGR.UNDERLINE);
        graphics.putString(new TerminalPosition(0, 9), "Choose difficulty and start the game by pressing: ");
        graphics.putString(new TerminalPosition(0, 15), "Speed: ");
        graphics.putString(new TerminalPosition(0, 16), "Multiplier:");
        graphics.putString(new TerminalPosition(14, 15), "Low");
        graphics.putString(new TerminalPosition(14, 16), "0.50x");
        graphics.putString(new TerminalPosition(29, 15), "Medium");
        graphics.putString(new TerminalPosition(29, 16), "1x");
        graphics.putString(new TerminalPosition(44, 15), "High");
        graphics.putString(new TerminalPosition(44, 16), "1.50x");
        graphics.putString(new TerminalPosition(6, 21), " -> Fruit");
        graphics.putString(new TerminalPosition(6, 22), " -> Bonus points, disappears after a few ");
        graphics.putString(new TerminalPosition(6, 23), "    seconds");
        graphics.putString(new TerminalPosition(6, 24), " -> Poison that makes you lose the game");
        graphics.putString(new TerminalPosition(6, 25), " -> Walls of the arena. Expect more as ");
        graphics.putString(new TerminalPosition(6, 26), "    you progress the game ");


    }

    public void drawOver(GameModel model, TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(model.getWidth(), model.getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.enableModifiers(SGR.CIRCLED);
        graphics.putString(new TerminalPosition(23, 10), "GAME OVER");

        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(15, 15), "Press 1 to try again!");
        graphics.putString(new TerminalPosition(15, 16), "Press 2 to quit");
    }

    private void drawPoison(PoisonModel poison, TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        int x = poison.getPos().getX();
        int y = poison.getPos().getY();
        graphics.putString(new TerminalPosition(x, y), "P");


    }

    public void drawSnake(SnakeModel snake, TextGraphics graphics) {

        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(new TerminalPosition(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY()), "x");
        graphics.putString(new TerminalPosition(snake.getPositions().get(snake.getSize() - 1).getX(), snake.getPositions().get(snake.getSize() - 1).getY()), "x");

        for (int i = 1; i < snake.getSize() - 1; i++)
            graphics.putString(new TerminalPosition(snake.getPositions().get(i).getX(), snake.getPositions().get(i).getY()), "o");
    }

    public void drawWall(WallModel wall, TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        for (int i = 0; i < wall.getWallPositions().size(); i++) {
            int x = wall.getWallPositions().get(i).getX();
            int y = wall.getWallPositions().get(i).getY();

            graphics.putString(new TerminalPosition(x, y), "#");
        }
    }


    public void drawFruit(FruitModel fruit, TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(fruit.getPos().getX(), fruit.getPos().getY()), "F");

    }

    public void drawScore(double score, TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.putString(new TerminalPosition(1, 1), "SCORE:    ");
        graphics.putString(new TerminalPosition(11, 1), toString().valueOf((int)score));

    }


    public void drawObstacle(ObstacleModel obstacle, TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        if (obstacle.getObstaclePositions().size() > 0) {
            for (int i = 0; i < obstacle.getObstaclePositions().size(); i++) {
                int x = obstacle.getObstaclePositions().get(i).getX();
                int y = obstacle.getObstaclePositions().get(i).getY();

                graphics.putString(new TerminalPosition(x, y), "#");

            }
        }
    }

        public void drawBonus (BonusModel bonus, TextGraphics graphics){
            graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
            graphics.putString(new TerminalPosition(bonus.getPos().getX(), bonus.getPos().getY()), "B");

        }


    public void checkKey(GameController controller, GameModel model) throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
            screen.close();
        if (key.getKeyType() == KeyType.EOF)
            System.exit((0));

        switch (model.getState().readState()) {
            case "play":
                controller.processKey(key, model);
                break;
            case "menu":
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == '1') {
                    model.setMultiplier(0.50);
                    model.getSnake().setSpeed(110);
                    model.changeState(new PlayState(model));
                }
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == '2') {
                    model.setMultiplier(1);
                    model.getSnake().setSpeed(90);
                    model.changeState(new PlayState(model));
                }
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == '3') {
                    model.setMultiplier(1.5);
                    model.getSnake().setSpeed(55);
                    model.changeState(new PlayState(model));
                }
                break;
            case "over":
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == '1') {
                    model.reset(50,30);
                }

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == '2') {
                    System.exit(0);
                }


        }


    }
}



