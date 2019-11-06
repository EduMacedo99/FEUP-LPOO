package Controller;

import Model.*;
import States.OverState;

import java.util.ArrayList;
import java.util.List;

public class SnakeController {


    public void update(GameModel model) {

            if (!canMove(model)) {
                model.changeState(new OverState(model));
            } else {
                moveSnake(model.getSnake());
                model.getSnake().setLastDir(model.getSnake().getDirection());
            }


    }


        public boolean canMove (GameModel model){

            WallModel wall = model.getWall();
            SnakeModel snake = model.getSnake();
            ObstacleModel obstacle = model.getObstacle();

            if (model.getSnake().getPositions().get(0).equals(model.getPoison().getPos()) && model.getPoison().isActive())
                return false;

            switch (snake.getDirection()) {
                case "up":
                    for (Position pos : wall.getWallPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() - 1)))
                            return false;
                    }
                    for (Position pos : obstacle.getObstaclePositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() - 1)))
                            return false;
                    }
                    for (Position pos : snake.getPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() - 1)))
                            return false;
                    }

                    break;

                case "down":
                    for (Position pos : wall.getWallPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() + 1)))
                            return false;
                    }

                    for (Position pos : obstacle.getObstaclePositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() + 1)))
                            return false;
                    }

                    for (Position pos : snake.getPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX(), snake.getPositions().get(0).getY() + 1)))
                            return false;
                    }

                    break;

                case "left":
                    for (Position pos : wall.getWallPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() - 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    for (Position pos : obstacle.getObstaclePositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() - 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    for (Position pos : snake.getPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() - 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    break;

                case "right":
                    for (Position pos : wall.getWallPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() + 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    for (Position pos : obstacle.getObstaclePositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() + 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    for (Position pos : snake.getPositions()) {
                        if (pos.equals(new Position(snake.getPositions().get(0).getX() + 1, snake.getPositions().get(0).getY())))
                            return false;
                    }

                    break;
            }

            return true;
        }


        public void moveSnake (SnakeModel snake){


            List<Position> oldPos = snake.getPositions();
            List<Position> pos = new ArrayList<>();

            switch (snake.getDirection()) {
                case "left":
                    pos.add(0, new Position(oldPos.get(0).getX() - 1, oldPos.get(0).getY()));
                    break;

                case "right":
                    pos.add(0, new Position(oldPos.get(0).getX() + 1, oldPos.get(0).getY()));
                    break;

                case "up":
                    pos.add(0, new Position(oldPos.get(0).getX(), oldPos.get(0).getY() - 1));
                    break;

                case "down":
                    pos.add(0, new Position(oldPos.get(0).getX(), oldPos.get(0).getY() + 1));
                    break;
            }

            for (int i = 1; i < snake.getSize(); i++) {
                pos.add(i, new Position(oldPos.get(i - 1).getX(), oldPos.get(i - 1).getY()));
            }

            snake.setPositions(pos);

        }


        public void increaseSnake (GameModel model){

            List<Position> positions = model.getSnake().getPositions();
            model.getSnake().setSize(model.getSnake().getSize() + 1);

            switch (model.getSnake().getDirection()) {
                case "up":
                    Position pos = new Position(model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getX(), model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getY() + 1);
                    positions.add(pos);
                    model.getSnake().setPositions(positions);
                    break;

                case "down":
                    Position pos1 = new Position(model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getX(), model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getY() - 1);
                    positions.add(pos1);
                    model.getSnake().setPositions(positions);

                    break;

                case "left":
                    Position pos2 = new Position(model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getX() + 1, model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getY());
                    positions.add(pos2);
                    model.getSnake().setPositions(positions);
                    break;

                case "right":
                    Position pos3 = new Position(model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getX() - 1, model.getSnake().getPositions().get(model.getSnake().getPositions().size() - 1).getY());
                    positions.add(pos3);
                    model.getSnake().setPositions(positions);
                    break;


            }

        }

    }
