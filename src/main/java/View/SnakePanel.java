package View;

import Model.GameModel;
import Model.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SnakePanel extends JPanel {

    private GameModel model;

    private BufferedImage wall;
    private BufferedImage background;
    private BufferedImage rabbit;
    private BufferedImage mushroom;
    private BufferedImage apple;
    private BufferedImage snake;
    private BufferedImage menu;


    public SnakePanel(){

        try{
            this.background = loadImage("background");
            this.rabbit = loadImage("rabbit");
            this.wall = loadImage("wall");
            this.mushroom = loadImage("poison");
            this.apple = loadImage("apple");
            this.snake = loadImage("snake");
            this.menu = loadImage("menu");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private BufferedImage loadImage(String imageName) throws IOException {
        return ImageIO.read(getClass().getResource("/" + imageName + ".png"));
    }

    private void drawGame(Graphics graphics) {
        drawBackground(graphics);
        drawWalls(graphics);
        drawApple(graphics);
        drawSnake(graphics);
        drawScore(graphics);

        if(model.getBonus().isActive())
            drawBonus(graphics);

        if(model.getPoison().isActive())
            drawPoison(graphics);

        if(model.getScore() >= 100)
            drawObs1(graphics);

        if(model.getScore() >= 150)
            drawObs2(graphics);
    }

    protected void setModel(GameModel model){
        this.model = model;
    }

    private void drawBackground(Graphics graphics){
        for(int i = 0; i < 40 ; i++)
            for(int j = 0; j < 40 ; j++){
                graphics.drawImage(background, j * SwingSnake.getTileSize(), i * SwingSnake.getTileSize(), null);
            }
    }

    private void drawWalls(Graphics graphics){
        for (int i = 0; i < model.getWall().getWallPositions().size(); i++) {
            graphics.drawImage(
                    wall,
                    model.getWall().getWallPositions().get(i).getX() * SwingSnake.getTileSize(),
                    model.getWall().getWallPositions().get(i).getY() * SwingSnake.getTileSize(),
                    null
            );
        }
    }

    private void drawApple(Graphics graphics) {
        graphics.drawImage(
                apple,
                model.getFruit().getPos().getX() * SwingSnake.getTileSize(),
                model.getFruit().getPos().getY() * SwingSnake.getTileSize(),
                null
        );
    }

    private void drawPoison(Graphics graphics) {
        graphics.drawImage(
                mushroom,
                model.getPoison().getPos().getX() * SwingSnake.getTileSize(),
                model.getPoison().getPos().getY() * SwingSnake.getTileSize(),
                null
        );
    }

    private void drawBonus(Graphics graphics) {
        graphics.drawImage(
                rabbit,
                model.getBonus().getPos().getX() * SwingSnake.getTileSize(),
                model.getBonus().getPos().getY() * SwingSnake.getTileSize(),
                null
        );
    }

    private void drawSnake(Graphics graphics){
        for (Position pos : model.getSnake().getPositions()) {
            graphics.drawImage(snake, pos.getX() * SwingSnake.getTileSize(), pos.getY() * SwingSnake.getTileSize(), SwingSnake.getTileSize(), SwingSnake.getTileSize(), null);
        }

    }

    private void drawObs1(Graphics graphics){
        for(int i = 0; i < model.getObstacle().getObstacle1Positions().size(); i++){
            graphics.drawImage(wall,
                    model.getObstacle().getObstacle1Positions().get(i).getX() * SwingSnake.getTileSize(),
                    model.getObstacle().getObstacle1Positions().get(i).getY() * SwingSnake.getTileSize(),
                    null
            );
        }
    }

    private void drawObs2(Graphics graphics){
        for(int i = 0; i < model.getObstacle().getObstacle2Positions().size(); i++){
            graphics.drawImage(wall,
                    model.getObstacle().getObstacle2Positions().get(i).getX() * SwingSnake.getTileSize(),
                    model.getObstacle().getObstacle2Positions().get(i).getY() * SwingSnake.getTileSize(),
                    null
            );
        }
    }

    private void drawScore(Graphics graphics){
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        graphics.drawString("Score: "+ (int)model.getScore(), 3* SwingSnake.getTileSize(), 3* SwingSnake.getTileSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (model.getState().readState()) {
            case "play":
                drawGame(g);
                break;
            case "menu":
                drawMenu(g);
                break;
            case "over":
                drawOver(g);
                break;
        }
    }

    private void drawOver(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,40* SwingSnake.getTileSize(), 40* SwingSnake.getTileSize());

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        graphics.drawString("GAME OVER",13* SwingSnake.getTileSize(),17* SwingSnake.getTileSize());

        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        graphics.drawString("Press 1 to try again!",10* SwingSnake.getTileSize(),22* SwingSnake.getTileSize());
        graphics.drawString("Press 2 to quit",10* SwingSnake.getTileSize(),24* SwingSnake.getTileSize());





    }

    private void drawMenu(Graphics graphics) {
        graphics.drawImage(menu, 22 * SwingSnake.getTileSize(), 22* SwingSnake.getTileSize(), null);
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        graphics.drawString("Welcome to", 15* SwingSnake.getTileSize(),7* SwingSnake.getTileSize());
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
        graphics.setColor(Color.GREEN);
        graphics.drawString("SNAKE", 16* SwingSnake.getTileSize(),11* SwingSnake.getTileSize());
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        graphics.setColor(Color.RED);
        graphics.drawString("Choose difficulty by pressing on keyboard:", 3* SwingSnake.getTileSize(),15* SwingSnake.getTileSize());
        graphics.drawString("1", 5 * SwingSnake.getTileSize(),19* SwingSnake.getTileSize());
        graphics.drawString("2", 20 * SwingSnake.getTileSize(),19* SwingSnake.getTileSize());
        graphics.drawString("3", 35 * SwingSnake.getTileSize(),19* SwingSnake.getTileSize());

        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        graphics.drawString("Low Speed", 3 * SwingSnake.getTileSize(),22* SwingSnake.getTileSize());
        graphics.drawString("Medium Speed", 18 * SwingSnake.getTileSize(),22* SwingSnake.getTileSize());
        graphics.drawString("High speed", 33 * SwingSnake.getTileSize(),22* SwingSnake.getTileSize());

        graphics.drawString("0.5x multiplier", 3 * SwingSnake.getTileSize(),23* SwingSnake.getTileSize());
        graphics.drawString("1.0x multiplier", 18 * SwingSnake.getTileSize(),23* SwingSnake.getTileSize());
        graphics.drawString("1.5x multiplier", 33 * SwingSnake.getTileSize(),23* SwingSnake.getTileSize());


    }
}
