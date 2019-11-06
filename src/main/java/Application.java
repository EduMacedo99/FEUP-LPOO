import Controller.GameController;
import Model.GameModel;
import View.LanternaView;
import View.SwingSnake;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Application().start();
    }

    private void start() throws IOException, InterruptedException {

        String option = "";
        Scanner console = new Scanner(System.in);

        System.out.println("Which View would you like to play the game?\n0 -> Lanterna\n1 -> Swing");

        while((!option.equals("0"))&& (!option.equals("1"))){
            option = console.next();

        }

        if(option.equals("0")){
            GameController controller = new GameController();
            GameModel model = new GameModel(50,30);
            LanternaView view = new LanternaView(model);

            new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        view.checkKey(controller, model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        }
                    }
                }
            }.start();

            while(true){
                view.draw(model);
                controller.update(model);
            }

        }
        else{
            GameController controller = new GameController();
            GameModel model = new GameModel(40,40);
            SwingSnake viewS = new SwingSnake();


            while(true){

                viewS.drawSwing(model);
                controller.update(model);
            }

        }


    }
}
