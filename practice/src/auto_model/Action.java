package auto_model;

public class Action {
    int current;
    int target;

    public Action(int target){
        this.target = target;
    }

    public void update(){
        current++;
    }

    public boolean end(){
        return current >= target;
    }

    public void display(){
        System.out.println(current);
    }
}
