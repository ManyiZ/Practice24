package auto;

import auto.robot_component.Robot;

public class Main {
    public static void main(String[] args) {
        Robot bot = new Robot();
        Queue q = new Queue();

        q.addToQueue(1);
        q.addToQueue(2);

        System.out.println(q.getOne());
        System.out.println(q.getOne());
        System.out.println(q.getOne());

//        while (true){
//            bot.update();
//            bot.displayData();
//        }
    }
}