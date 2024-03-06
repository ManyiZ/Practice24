package auto;

import java.util.ArrayList;

public class Queue {
    private Action current;

    ArrayList<Action> one = new ArrayList<Action>();
    public Queue () {

    }
    void addToQueue(int willB ){
        one.add (new Action(willB));
    }
    Action getOne() {
        if (one.size()==0) {
            return null ;
        } else {
            Action willC = one.get(0);
            one.remove(0);
            return willC;
        }
    }
    void init() {
        if (one.isEmpty()) {
            return;
        }
        current = one.get(0);
    }
    void update(){
        current.update();
    }
}
